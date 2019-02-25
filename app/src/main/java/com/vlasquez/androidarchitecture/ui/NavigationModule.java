package com.vlasquez.androidarchitecture.ui;

import com.vlasquez.androidarchitecture.di.ActivityScope;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationModule {

  @Binds
  @ActivityScope
  abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
