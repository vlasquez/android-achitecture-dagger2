package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.model.Repo;
import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter  implements RepoAdapter.RepoClickedListener {

  private final TrendingReposViewModel viewModel;
  private final RepoRepository repoRepository;

  @Inject
  public TrendingReposPresenter(
      TrendingReposViewModel viewModel,
      RepoRepository repoRepository) {
    this.viewModel = viewModel;
    this.repoRepository = repoRepository;
    loadRepos();
  }

  private void loadRepos() {
    repoRepository.getTrendingRepos()
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.reposUpdated(), viewModel.onError());
  }

  @Override public void onRepoClicked(Repo repo) {

  }
}
