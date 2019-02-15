package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.data.RepoRequester;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter {

  private final TrendingReposViewModel viewModel;
  private final RepoRequester repoRequester;

  @Inject
  public TrendingReposPresenter(
      TrendingReposViewModel viewModel,
      RepoRequester repoRequester) {
    this.viewModel = viewModel;
    this.repoRequester = repoRequester;
    loadRepos();
  }

  private void loadRepos() {
    repoRequester.getTrendingRepos()
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.reposUpdated(), viewModel.onError());
  }
}
