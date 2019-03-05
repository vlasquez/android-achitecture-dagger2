package com.vlasquez.androidarchitecture.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import timber.log.Timber;

@ScreenScope
public class TrendingReposViewModel {


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


  Consumer<Boolean> loadingUpdated() {
    return loadingRelay;
  }

  Action reposUpdated() {
    return () -> errorRelay.accept(-1);
  }

  Consumer<Throwable> onError() {
    return throwable -> {
      Timber.e(throwable, "Error loading repos");
      errorRelay.accept(R.string.api_error_repos);
    };
  }
}
