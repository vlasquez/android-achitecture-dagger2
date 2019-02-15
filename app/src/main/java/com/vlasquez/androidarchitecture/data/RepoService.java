package com.vlasquez.androidarchitecture.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {

  /**
   * Single RxJava:
   * Is a stream that will only emit one item or error much better fit than Observable
   */
  @GET("search/repositories?q=language:java&order=desc&sort=stars")
  Single<TrendingReposResponse> getTrendingRepos();
}
