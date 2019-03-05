package com.vlasquez.androidarchitecture.database.favorites;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface FavoriteContributorDAO {

  @Query("SELECT * from favoritecontributor")
  Flowable<List<FavoriteContributor>> getFavoritedContributors();

  @Insert
  void addFavorite(FavoriteContributor contributor);

  @Delete
  void deleteFavorite(FavoriteContributor contributor);
}
