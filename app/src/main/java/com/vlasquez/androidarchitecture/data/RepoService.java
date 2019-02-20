package com.vlasquez.androidarchitecture.data;

import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {

  /**
   * Single RxJava:
   * Is a stream that will only emit one item or error much better fit than Observable
   */
  @GET("search/repositories?q=language:java&order=desc&sort=stars")
  Single<TrendingReposResponse> getTrendingRepos();

  @GET("repos/{owner}/{name}")
  Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);
}
