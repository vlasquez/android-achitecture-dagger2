package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.di.ForScreen;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.DisposableManager;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.androidarchitecture.ui.ScreenNavigator;
import com.vlasquez.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter {

  private final TrendingReposViewModel viewModel;
  private final RepoRepository repoRepository;
  private ScreenNavigator screenNavigator;
  private DisposableManager disposableManager;
  private final RecyclerDataSource recyclerDataSource;

  @Inject
  public TrendingReposPresenter(
      TrendingReposViewModel viewModel,
      RepoRepository repoRepository,
      ScreenNavigator screenNavigator,
      @ForScreen DisposableManager disposableManager,
      RecyclerDataSource recyclerDataSource) {
    this.viewModel = viewModel;
    this.repoRepository = repoRepository;
    this.screenNavigator = screenNavigator;
    this.disposableManager = disposableManager;
    this.recyclerDataSource = recyclerDataSource;
    loadRepos();
  }

  private void loadRepos() {
    disposableManager.add(repoRepository.getTrendingRepos()
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(__ -> viewModel.reposUpdated().run())
        .subscribe(recyclerDataSource::setData, viewModel.onError()));
  }

  public void onRepoClicked(Repo repo) {
    screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
  }
}
