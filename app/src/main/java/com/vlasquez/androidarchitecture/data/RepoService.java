package com.vlasquez.androidarchitecture.data;

import com.vlasquez.androidarchitecture.model.Contributor;
import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RepoService {

  /**
   * Single RxJava:
   * Is a stream that will only emit one item or error much better fit than Observable
   */
  @GET("search/repositories?q=language:java&order=desc&sort=stars")
  Single<TrendingReposResponse> getTrendingRepos();

  @GET("repos/{owner}/{name}")
  Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);

  @GET
  Single<List<Contributor>> getContributors(@Url String contributorsUrl);
}
