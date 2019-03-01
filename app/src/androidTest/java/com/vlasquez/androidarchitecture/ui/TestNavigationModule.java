package com.vlasquez.androidarchitecture.ui;

import com.vlasquez.androidarchitecture.lifecycle.ActivityLifeCycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TestNavigationModule {

  @Binds
  abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);

  @Binds
  @IntoSet
  abstract ActivityLifeCycleTask binScreenNavigatorTask(TestScreenNavigator screenNavigator);
}
