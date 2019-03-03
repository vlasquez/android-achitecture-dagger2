package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.di.ForScreen;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.DisposableManager;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.androidarchitecture.ui.ScreenNavigator;
import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

  private final TrendingReposViewModel viewModel;
  private final RepoRepository repoRepository;
  private ScreenNavigator screenNavigator;
  private DisposableManager disposableManager;

  @Inject
  public TrendingReposPresenter(
      TrendingReposViewModel viewModel,
      RepoRepository repoRepository,
      ScreenNavigator screenNavigator,
      @ForScreen DisposableManager disposableManager) {
    this.viewModel = viewModel;
    this.repoRepository = repoRepository;
    this.screenNavigator = screenNavigator;
    this.disposableManager = disposableManager;
    loadRepos();
  }

  private void loadRepos() {
    disposableManager.add(repoRepository.getTrendingRepos()
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.reposUpdated(), viewModel.onError()));
  }

  @Override public void onRepoClicked(Repo repo) {
    screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
  }
}
