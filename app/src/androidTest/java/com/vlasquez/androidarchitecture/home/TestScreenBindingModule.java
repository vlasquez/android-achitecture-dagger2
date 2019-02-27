package com.vlasquez.androidarchitecture.home;

import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.details.RepoDetailsComponent;
import com.vlasquez.androidarchitecture.details.RepoDetailsController;
import com.vlasquez.androidarchitecture.di.ControllerKey;
import com.vlasquez.androidarchitecture.trending.TrendingReposComponent;
import com.vlasquez.androidarchitecture.trending.TrendingReposController;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
    TrendingReposComponent.class,
    RepoDetailsComponent.class
})
public abstract class TestScreenBindingModule {

  @Binds
  @IntoMap
  @ControllerKey(TrendingReposController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(
      TrendingReposComponent.Builder builder);

  @Binds
  @IntoMap
  @ControllerKey(RepoDetailsController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(
      RepoDetailsComponent.Builder builder);
}
