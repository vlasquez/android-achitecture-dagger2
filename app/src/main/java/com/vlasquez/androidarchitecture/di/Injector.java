package com.vlasquez.androidarchitecture.di;

import android.app.Activity;

public class Injector {

  public Injector() {
  }

  public static void inject(Activity activity) {
    ActivityInjector.get(activity).inject(activity);
  }

  public static void clearComponent(Activity activity) {
    ActivityInjector.get(activity).clear(activity);
  }
}
