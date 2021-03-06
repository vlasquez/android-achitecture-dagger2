package com.vlasquez.androidarchitecture.details;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.di.ForScreen;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.DisposableManager;
import com.vlasquez.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import javax.inject.Named;

@ScreenScope
class RepoDetailsPresenter {

  @Inject RepoDetailsPresenter(
      @Named("repo_owner") String repoOwner,
      @Named("repo_name") String repoName,
      RepoRepository repoRepository,
      RepoDetailsViewModel viewModel,
      @ForScreen DisposableManager disposableManager,
      RecyclerDataSource contributorDataSource) {
    disposableManager.add(repoRepository.getRepo(repoOwner, repoName)
        .doOnSuccess(viewModel.processRepo())
        .doOnError(viewModel.detailsError())
        .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
            .doOnError(viewModel.contributorsError()))
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(contributorDataSource::setData)
        .subscribe(viewModel.contributorsLoaded(), throwable -> {
          // We handle logging in the viewModel
        }));
  }
}
