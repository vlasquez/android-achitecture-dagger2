package com.vlasquez.androidarchitecture.ui;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ActivityViewInterceptorModule {

  @Binds
  abstract ActivityViewInterceptor bindDebugActivityViewInterceptor(
      DebugActivityViewInterceptor activityViewInterceptor);
}
