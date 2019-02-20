package com.vlasquez.androidarchitecture.home;

import com.vlasquez.androidarchitecture.di.ActivityScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = { TestScreenBindingModule.class, })
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<MainActivity> {

  }
}
