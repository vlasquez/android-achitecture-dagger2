package com.vlasquez.androidarchitecture.data;

import android.os.Handler;
import android.os.Looper;
import com.squareup.moshi.Types;
import com.vlasquez.androidarchitecture.model.Contributor;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.androidarchitecture.test.TestUtils;
import io.reactivex.Single;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestRepoService implements RepoService {

  public static final int FLAG_TRENDING_REPOS = 1;
  public static final int FLAG_GET_REPO = 2;
  public static final int FLAG_GET_CONTRIBUTORS = 4;

  private int errorFlags;

  private int holdFlags;

  private TestUtils testUtils;

  @Inject TestRepoService(TestUtils testUtils) {
    this.testUtils = testUtils;
  }

  @Override public Single<TrendingReposResponse> getTrendingRepos() {
    if ((errorFlags & FLAG_TRENDING_REPOS) == 0) {
      TrendingReposResponse response =
          testUtils.loadJson("mock/search/get_trending_repos.json", TrendingReposResponse.class);
      if ((holdFlags & FLAG_TRENDING_REPOS) == FLAG_TRENDING_REPOS) {
        return holdSingle(response, FLAG_TRENDING_REPOS);
      }
      return Single.just(response);
    }
    return Single.error(new IOException());
  }

  @Override public Single<Repo> getRepo(String repoOwner, String repoName) {
    if ((errorFlags & FLAG_GET_REPO) == 0) {
      Repo repo = testUtils.loadJson("mock/repos/get_repo.json", Repo.class);
      if ((holdFlags & FLAG_GET_REPO) == FLAG_GET_REPO) {
        return holdSingle(repo, FLAG_GET_REPO);
      }
      return Single.just(repo);
    }
    return Single.error(new IOException());
  }

  public void setHoldFlags(int holdFlags) {
    this.holdFlags = holdFlags;
  }

  public void clearHoldFlags() {
    this.holdFlags = 0;
  }

  public void setErrorFlags(int errorFlags) {
    this.errorFlags = errorFlags;
  }

  public void clearErrorFlags() {
    this.errorFlags = 0;
  }

  @Override public Single<List<Contributor>> getContributors(String contributorsUrl) {
    if ((errorFlags & FLAG_GET_CONTRIBUTORS) == 0) {
      List<Contributor> contributorsList = testUtils.loadJson("mock/repo/contributors/get_contributors.json",
          Types.newParameterizedType(List.class, Contributor.class));
      if ((holdFlags & FLAG_GET_CONTRIBUTORS) == FLAG_GET_CONTRIBUTORS) {
        return holdSingle(contributorsList, FLAG_GET_CONTRIBUTORS);
      }
      return Single.just(contributorsList);
    }
    return Single.error(new IOException());
  }

  private <T> Single<T> holdSingle(T result, int flag) {
    return Single.create(e -> {
      final Handler handler = new Handler(Looper.myLooper());
      Runnable holdRunnable = new Runnable() {
        @Override public void run() {
          if ((holdFlags & flag) == flag) {
            handler.postDelayed(this, 50);
          } else {
            e.onSuccess(result);
          }
        }
      };
      holdRunnable.run();
    });
  }
}
