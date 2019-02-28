package com.vlasquez.androidarchitecture.network;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public abstract class NetworkModule {

  @Provides
  @Singleton
  static Call.Factory provideOkHttp(MockInterceptor mockInterceptor) {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(mockInterceptor).build();
  }

  @Provides
  @Named("base_url")
  static String provideBaseUrl() {
    return "https://api.github.com/";
  }
}
