package com.vlasquez.androidarchitecture.details;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import javax.inject.Inject;
import javax.inject.Named;

@ScreenScope
class RepoDetailsPresenter {

  @Inject RepoDetailsPresenter(@Named("repo_owner") String repoOwner,
      @Named("repo_name") String repoName, RepoRepository repoRepository,
      RepoDetailsViewModel viewModel) {
    repoRepository.getRepo(repoOwner, repoName)
        .doOnSuccess(viewModel.processRepo())
        .doOnError(viewModel.detailsError())
        .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
            .doOnError(viewModel.contributorsError()))
        .subscribe(viewModel.processContributors(), throwable -> {
          // We handle logging in the viewModel
        });
  }
}
