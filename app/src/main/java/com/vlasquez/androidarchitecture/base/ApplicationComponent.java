package com.vlasquez.androidarchitecture.base;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { ApplicationModule.class, ActivityBindingModule.class })

public interface ApplicationComponent {
  void inject(MyApplication myApplication);
}
