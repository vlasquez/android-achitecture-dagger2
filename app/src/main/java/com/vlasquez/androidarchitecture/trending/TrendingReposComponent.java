package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.base.ScreenModule;
import com.vlasquez.androidarchitecture.di.ScreenComponent;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = { ScreenModule.class,TrendingReposScreenModule.class })
public interface TrendingReposComponent extends ScreenComponent<TrendingReposController> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {
  }
}

