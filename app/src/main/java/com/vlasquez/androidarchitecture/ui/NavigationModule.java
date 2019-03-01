package com.vlasquez.androidarchitecture.ui;

import com.vlasquez.androidarchitecture.lifecycle.ActivityLifeCycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class NavigationModule {

  @Binds
  abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

  @Binds
  @IntoSet
  abstract ActivityLifeCycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
