package com.vlasquez.androidarchitecture.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.di.Injector;
import com.vlasquez.androidarchitecture.di.ScreenInjector;
import com.vlasquez.androidarchitecture.lifecycle.ActivityLifeCycleTask;
import com.vlasquez.androidarchitecture.ui.ActivityViewInterceptor;
import com.vlasquez.androidarchitecture.ui.RouterProvider;
import com.vlasquez.androidarchitecture.ui.ScreenNavigator;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity implements RouterProvider {

  @Inject ScreenInjector screenInjector;
  @Inject ScreenNavigator screenNavigator;
  @Inject ActivityViewInterceptor activityViewInterceptor;
  @Inject Set<ActivityLifeCycleTask> activityLifeCycleTasks;

  private static String INSTANCE_ID_KEY = "instance_id";

  private String instanceId;
  private Router router;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {

    if (savedInstanceState != null) {
      instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
    } else {
      instanceId = UUID.randomUUID().toString();
    }
    Injector.inject(this);
    activityViewInterceptor.setContentView(this, layoutRes());
    ViewGroup screenContainer = findViewById(R.id.screen_container);
    if (screenContainer == null) {
      throw new NullPointerException("Activity must have a view with id: screen_container");
    }
    router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
    monitorBackStack();

    for (ActivityLifeCycleTask task : activityLifeCycleTasks) {
      task.onCreate(this);
    }
    super.onCreate(savedInstanceState);

  }

  @Override protected void onStart() {
    super.onStart();
    for (ActivityLifeCycleTask task : activityLifeCycleTasks) {
      task.onStart(this);
    }
  }

  @Override protected void onPause() {
    super.onPause();
    for (ActivityLifeCycleTask task : activityLifeCycleTasks) {
      task.onPause(this);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    for (ActivityLifeCycleTask task : activityLifeCycleTasks) {
      task.onResume(this);
    }
  }

  @Override protected void onStop() {
    super.onStop();
    for (ActivityLifeCycleTask task : activityLifeCycleTasks) {
      task.onStop(this);
    }
  }

  public abstract Controller initialScreen();

  @LayoutRes
  protected abstract int layoutRes();

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(INSTANCE_ID_KEY, instanceId);
  }

  public String getInstanceId() {
    return instanceId;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if(isFinishing()){
      Injector.clearComponent(this);
    }
    activityViewInterceptor.clear();

    for (ActivityLifeCycleTask task : activityLifeCycleTasks) {
      task.onDestroy(this);
    }
  }

  @Override public void onBackPressed() {
    if (!screenNavigator.pop()) {
      super.onBackPressed();
    }
  }

  public ScreenInjector getScreenInjector() {
    return screenInjector;
  }

  private void monitorBackStack() {
    router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
      @Override public void onChangeStarted(@Nullable Controller to, @Nullable Controller from,
          boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {
      }

      @Override public void onChangeCompleted(@Nullable Controller to, @Nullable Controller from,
          boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {
        if (!isPush && from != null) {
          Injector.clearComponent(from);
        }
      }
    });
  }

  @Override public Router getRouter() {
    return router;
  }
}
