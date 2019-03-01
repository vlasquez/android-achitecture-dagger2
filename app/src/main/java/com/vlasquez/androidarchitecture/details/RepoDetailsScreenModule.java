package com.vlasquez.androidarchitecture.details;

import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class RepoDetailsScreenModule {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUIManager repoDetailsUIManager);
}
