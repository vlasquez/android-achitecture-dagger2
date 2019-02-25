package com.vlasquez.androidarchitecture.data;

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

  private boolean sendError;
  private TestUtils testUtils;

  @Inject TestRepoService(TestUtils testUtils) {
    this.testUtils = testUtils;
  }

  @Override public Single<TrendingReposResponse> getTrendingRepos() {
    if (!sendError) {
      TrendingReposResponse response =
          testUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
      return Single.just(response);
    }
    return Single.error(new IOException());
  }

  @Override public Single<Repo> getRepo(String repoOwner, String repoName) {
    return null;
  }

  @Override public Single<List<Contributor>> getContributors(String contributorsUrl) {
    return null;
  }

  public void setSendError(boolean sendError) {
    this.sendError = sendError;
  }
}
