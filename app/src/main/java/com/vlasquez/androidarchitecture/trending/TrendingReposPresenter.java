package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.androidarchitecture.ui.ScreenNavigator;
import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter  implements RepoAdapter.RepoClickedListener {

  private final TrendingReposViewModel viewModel;
  private final RepoRepository repoRepository;
  private ScreenNavigator screenNavigator;

  @Inject
  public TrendingReposPresenter(
      TrendingReposViewModel viewModel,
      RepoRepository repoRepository,
      ScreenNavigator screenNavigator) {
    this.viewModel = viewModel;
    this.repoRepository = repoRepository;
    this.screenNavigator = screenNavigator;
    loadRepos();
  }

  private void loadRepos() {
    repoRepository.getTrendingRepos()
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.reposUpdated(), viewModel.onError());
  }

  @Override public void onRepoClicked(Repo repo) {
    screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
  }
}
