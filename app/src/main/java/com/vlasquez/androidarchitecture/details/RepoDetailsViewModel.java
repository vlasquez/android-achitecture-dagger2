package com.vlasquez.androidarchitecture.details;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.model.Contributor;
import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Observable;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import org.threeten.bp.format.DateTimeFormatter;
import timber.log.Timber;

@ScreenScope
class RepoDetailsViewModel {

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("MMM dd, yyyy");

  private final BehaviorRelay<RepoDetailState> detailStateBehaviorRelay = BehaviorRelay.create();
  private final BehaviorRelay<ContributorState> contributorStateBehaviorRelay =
      BehaviorRelay.create();

  @Inject RepoDetailsViewModel() {
    detailStateBehaviorRelay.accept(RepoDetailState.builder().loading(true).build());
    contributorStateBehaviorRelay.accept(ContributorState.builder().loading(true).build());
  }

  Observable<RepoDetailState> details() {
    return detailStateBehaviorRelay;
  }

  Observable<ContributorState> contributors() {
    return contributorStateBehaviorRelay;
  }

  Consumer<Repo> processRepo() {
    return repo -> detailStateBehaviorRelay.accept(RepoDetailState.builder()
        .loading(false)
        .name(repo.name())
        .description(repo.description())
        .createdDate(repo.createdDate().format(DATE_TIME_FORMATTER))
        .updatedDate(repo.updatedDate().format(DATE_TIME_FORMATTER))
        .build()
    );
  }

  Consumer<List<Contributor>> processContributors() {
    return contributors -> contributorStateBehaviorRelay.accept(
        ContributorState.builder()
            .loading(false)
            .contributors(contributors)
            .build()
    );
  }

  Consumer<Throwable> detailsError() {
    return throwable -> {
      Timber.e(throwable, "Error loading repo details");
      detailStateBehaviorRelay.accept(RepoDetailState.builder()
          .loading(false)
          .errorRes(R.string.api_error_single_repo)
          .build());
    };
  }

  Consumer<Throwable> contributorsError() {
    return throwable -> {
      Timber.e(throwable, "Error loading repo contributors");
      contributorStateBehaviorRelay.accept(ContributorState.builder()
          .loading(false)
          .errorRes(R.string.api_error_repo_contributor)
          .build());
    };
  }
}
