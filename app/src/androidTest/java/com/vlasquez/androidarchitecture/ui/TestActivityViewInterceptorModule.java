package com.vlasquez.androidarchitecture.ui;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestActivityViewInterceptorModule {

  @Provides
  static ActivityViewInterceptor provideActivityViewInterceptor() {
    return ActivityViewInterceptor.DEFAULT;
  }
}
