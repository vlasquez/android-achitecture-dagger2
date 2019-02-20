package com.vlasquez.androidarchitecture.data;

import com.vlasquez.androidarchitecture.model.Contributor;
import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class RepoRepository {

  private final Provider<RepoRequester> repoRequesterProvider;
  private final List<Repo> cachedTrendingReposList = new ArrayList<>();
  private final Map<String, List<Contributor>> cachedContributors = new HashMap<>();

  @Inject
  public RepoRepository(Provider<RepoRequester> repoRequesterProvider) {
    this.repoRequesterProvider = repoRequesterProvider;
  }

  public Single<List<Repo>> getTrendingRepos() {
    return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
        .firstOrError()
        .subscribeOn(Schedulers.io());
  }

  public Single<Repo> getRepo(String repoOwner, String repoName) {
    return Maybe.concat(cachedRepo(repoOwner, repoName), apiGetRepo(repoOwner, repoName))
        .firstOrError()
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Contributor>> getContributors(String contributorsUrl) {
    return Maybe.concat(cachedContributors(contributorsUrl), apiContributors(contributorsUrl))
        .firstOrError()
        .subscribeOn(Schedulers.io());
  }

  private Maybe<List<Contributor>> cachedContributors(String contributorsUrl) {
    return Maybe.create(emmiter -> {
      if (cachedContributors.containsKey(contributorsUrl)) {
        emmiter.onSuccess(cachedContributors.get(contributorsUrl));
      }
      emmiter.onComplete();
    });
  }

  private Maybe<List<Contributor>> apiContributors(String contributorsUrl) {
    return repoRequesterProvider.get().getContributors(contributorsUrl)
        .doOnSuccess(contributors -> cachedContributors.put(contributorsUrl, contributors))
        .toMaybe();
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
