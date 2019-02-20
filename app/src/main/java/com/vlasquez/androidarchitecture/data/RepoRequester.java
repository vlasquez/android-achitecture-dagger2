package com.vlasquez.androidarchitecture.data;

import com.vlasquez.androidarchitecture.model.Contributor;
import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class RepoRequester {

  private RepoService repoService;

  @Inject RepoRequester(RepoService repoService) {
    this.repoService = repoService;
  }

  Single<List<Repo>> getTrendingRepos() {
    return repoService.getTrendingRepos()
        .map(TrendingReposResponse::repos);
  }

  Single<Repo> getRepo(String repoOwner, String repoName) {
    return repoService.getRepo(repoOwner, repoName);
  }

  Single<List<Contributor>> getContributors(String contributorUrl) {
    return repoService.getContributors(contributorUrl);
  }
}
