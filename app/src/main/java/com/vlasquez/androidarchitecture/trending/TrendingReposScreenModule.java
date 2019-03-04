package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TrendingReposScreenModule {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManager(TrendingReposUIManager manager);
}
