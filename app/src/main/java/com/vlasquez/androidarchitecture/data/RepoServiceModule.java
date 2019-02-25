package com.vlasquez.androidarchitecture.data;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Module
public class RepoServiceModule {

  @Provides
  @Singleton
  static RepoService provideRepoService(Retrofit retrofit) {
    return retrofit.create(RepoService.class);
  }

  @Provides
  @Named("network_scheduler")
  static Scheduler provideNetworkScheduler() {
    return Schedulers.io();
  }

}
