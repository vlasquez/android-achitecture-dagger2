package com.vlasquez.androidarchitecture.data;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestRepoServiceModule {

  @Binds
  abstract RepoService binRepoService(TestRepoService repoService);
}
