package com.vlasquez.androidarchitecture.base;

import android.support.test.InstrumentationRegistry;

public class TestApplication extends MyApplication {

  @Override protected ApplicationComponent initApplicationComponent() {
    return DaggerTestApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public static TestApplicationComponent getTestApplicationComponent() {
    return (TestApplicationComponent) ((TestApplication) InstrumentationRegistry.getTargetContext()
        .getApplicationContext()).component;
  }
}
