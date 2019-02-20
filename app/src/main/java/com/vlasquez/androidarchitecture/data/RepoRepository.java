package com.vlasquez.androidarchitecture.data;

import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class RepoRepository {

  private final Provider<RepoRequester> repoRequesterProvider;
  private final List<Repo> cachedTrendingReposList = new ArrayList<>();

  @Inject
  public RepoRepository(Provider<RepoRequester> repoRequesterProvider) {
    this.repoRequesterProvider = repoRequesterProvider;
  }

  public Single<List<Repo>> getTrendingRepos() {
    return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
        .firstOrError();
  }

  public Single<Repo> getRepo(String repoOwner, String repoName) {
    return Maybe.concat(cachedRepo(repoOwner, repoName), apiGetRepo(repoOwner, repoName))
        .firstOrError();
  }

  private Maybe<Repo> cachedRepo(String repoOwner, String repoName) {
    return Maybe.create(emmiter -> {
      for (Repo cachedRepo : cachedTrendingReposList) {
        if (cachedRepo.owner().login().equals(repoOwner) && cachedRepo.name().equals(repoName)) {
          emmiter.onSuccess(cachedRepo);
          break;
        }
      }
      emmiter.onComplete();
    });
  }

  private Maybe<Repo> apiGetRepo(String repoOwner, String repoName) {
    return repoRequesterProvider.get().getRepo(repoOwner, repoName)
        .toMaybe();
  }

  private Maybe<List<Repo>> cachedTrendingRepos() {
    return Maybe.create(emmiter -> {
      if (!cachedTrendingReposList.isEmpty()) {
        emmiter.onSuccess(cachedTrendingReposList);
      }
      emmiter.onComplete();
    });
  }

  private Maybe<List<Repo>> apiTrendingRepos() {
    return repoRequesterProvider.get().getTrendingRepos()
        .doOnSuccess(repos -> {
          cachedTrendingReposList.clear();
          cachedTrendingReposList.addAll(repos);
        }).toMaybe();
  }
}
