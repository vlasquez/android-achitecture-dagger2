package com.vlasquez.androidarchitecture.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.model.Repo;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

@ScreenScope
public class TrendingReposViewModel {

  private final BehaviorRelay<List<Repo>> reposRelay = BehaviorRelay.create();
  private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
  private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

  @Inject TrendingReposViewModel() {

  }

  Observable<Boolean> loading() {
    return loadingRelay;
  }

  Observable<Integer> error() {
    return errorRelay;
  }

  Observable<List<Repo>> repos() {
    return reposRelay;
  }

  Consumer<Boolean> loadingUpdated() {
    return loadingRelay;
  }

  Consumer<List<Repo>> reposUpdated() {
    errorRelay.accept(-1);
    return reposRelay;
  }

  Consumer<Throwable> onError() {
    return throwable -> {
      Timber.e(throwable, "Error loading repos");
      errorRelay.accept(R.string.api_error_repos);
    };
  }
}
