package com.vlasquez.androidarchitecture.data;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Module
public class RepoServiceModule {

  @Provides
  @Singleton
  static RepoService provideRepoService(Retrofit retrofit) {
    return retrofit.create(RepoService.class);
  }
}
