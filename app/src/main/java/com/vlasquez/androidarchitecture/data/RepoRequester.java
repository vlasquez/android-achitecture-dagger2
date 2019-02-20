package com.vlasquez.androidarchitecture.data;

import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class RepoRequester {

  private RepoService repoService;

  @Inject RepoRequester(RepoService repoService) {
    this.repoService = repoService;
  }

  public Single<List<Repo>> getTrendingRepos() {
    return repoService.getTrendingRepos()
        .map(TrendingReposResponse::repos)
        .subscribeOn(Schedulers.io());
  }

  public Single<Repo> getRepo(String repoOwner,String repoName){
    return repoService.getRepo(repoOwner,repoName).subscribeOn(Schedulers.io());
  }
}
