package com.vlasquez.androidarchitecture.home;

import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.di.ControllerKey;
import com.vlasquez.androidarchitecture.trending.TrendingReposComponent;
import com.vlasquez.androidarchitecture.trending.TrendingReposController;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = { TrendingReposComponent.class })
public abstract class MainScreenBindingModule {

  @Binds
  @IntoMap
  @ControllerKey(TrendingReposController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(
      TrendingReposComponent.Builder builder);


}
