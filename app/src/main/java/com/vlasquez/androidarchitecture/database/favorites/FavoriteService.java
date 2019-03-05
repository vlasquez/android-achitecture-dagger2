package com.vlasquez.androidarchitecture.database.favorites;

import android.annotation.SuppressLint;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.vlasquez.androidarchitecture.database.AppDatabase;
import com.vlasquez.androidarchitecture.model.Contributor;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;

@Singleton
public class FavoriteService {
  private final BehaviorRelay<Set<Long>> favoritedContributorsIdRelay =
      BehaviorRelay.createDefault(new HashSet<>());
  private final AppDatabase appDatabase;

  @SuppressLint("CheckResult")
  @Inject FavoriteService(AppDatabase appDatabase) {
    this.appDatabase = appDatabase;
    appDatabase.favoriteContributorDAO().getFavoritedContributors()
        .subscribeOn(Schedulers.io())
        .map(favoriteContributors -> {
          Set<Long> contributorIds = new HashSet<>();
          for (FavoriteContributor favoriteContributor : favoriteContributors) {
            contributorIds.add(favoriteContributor.getId());
          }
          return contributorIds;
        })
        .subscribe(favoritedContributorsIdRelay,
            throwable -> Timber.e(throwable, "Error loading favorited contributors from database"));
  }

  public Observable<Set<Long>> favoritedContributorIds() {
    return favoritedContributorsIdRelay;
  }

  public void toggleFavoriteContributor(Contributor contributor) {
    runDbOp(() -> {
      if (favoritedContributorsIdRelay.getValue().contains(contributor.getId())) {
        deleteFavoriteContributor(contributor);
      } else {
        addFavoriteContributor(contributor);
      }
    });
  }

  private void addFavoriteContributor(Contributor contributor) {
    appDatabase.favoriteContributorDAO().addFavorite(new FavoriteContributor(contributor.id()));
  }

  private void deleteFavoriteContributor(Contributor contributor) {
    appDatabase.favoriteContributorDAO().deleteFavorite(new FavoriteContributor(contributor.id()));
  }

  private void runDbOp(Action action) {
    Completable.fromAction(action)
        .subscribeOn(Schedulers.io())
        .subscribe(() -> {
        }, throwable -> Timber.e(throwable, "Error performing database operation"));
  }
}
