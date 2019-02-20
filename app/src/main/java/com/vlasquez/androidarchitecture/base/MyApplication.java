package com.vlasquez.androidarchitecture.base;

import android.app.Application;
import com.vlasquez.androidarchitecture.BuildConfig;
import com.vlasquez.androidarchitecture.di.ActivityInjector;
import javax.inject.Inject;
import timber.log.Timber;

public class MyApplication extends Application {

  @Inject ActivityInjector activityInjector;

  protected ApplicationComponent component;

  @Override public void onCreate() {
    super.onCreate();

    component = initApplicationComponent();
    component.inject(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  protected ApplicationComponent initApplicationComponent() {
    return DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }
  public ActivityInjector getActivityInjector() {
    return activityInjector;
  }
}
