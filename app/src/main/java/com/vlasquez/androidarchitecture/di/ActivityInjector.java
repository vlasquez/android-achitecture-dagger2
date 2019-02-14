package com.vlasquez.androidarchitecture.di;

import android.app.Activity;
import android.content.Context;
import com.vlasquez.androidarchitecture.base.BaseActivity;
import com.vlasquez.androidarchitecture.base.MyApplication;
import dagger.android.AndroidInjector;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

public class ActivityInjector {

  private final Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>>
      activityInjectors;
  private final Map<String, AndroidInjector<? extends Activity>> cache = new HashMap<>();

  @Inject ActivityInjector(
      Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors) {
    this.activityInjectors = activityInjectors;
  }

  void inject(Activity activity) {
    if (!(activity instanceof BaseActivity)) {
      throw new IllegalArgumentException("Activity must extend BaseActivity");
    }

    String instanceId = ((BaseActivity) activity).getInstanceId();
    if (cache.containsKey(instanceId)) {
      //noinspection unchecked
      ((AndroidInjector<Activity>) cache.get(instanceId)).inject(activity);
      return;
    }
    //noinspection unchecked
    AndroidInjector.Factory<Activity> injectorsFactory =
        (AndroidInjector.Factory<Activity>) activityInjectors.get(activity.getClass()).get();
    AndroidInjector<Activity> injector = injectorsFactory.create(activity);
    cache.put(instanceId, injector);
    injector.inject(activity);
  }

  void clear(Activity activity) {
    if (!(activity instanceof BaseActivity)) {
      throw new IllegalArgumentException("Activity must extend BaseActivity");
    }
    cache.remove(((BaseActivity) activity).getInstanceId());
  }

  static ActivityInjector get(Context context) {
    return ((MyApplication) context.getApplicationContext()).getActivityInjector();
  }
}
