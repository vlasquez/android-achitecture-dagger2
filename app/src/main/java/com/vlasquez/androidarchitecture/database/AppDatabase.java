package com.vlasquez.androidarchitecture.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.vlasquez.androidarchitecture.database.favorites.FavoriteContributor;
import com.vlasquez.androidarchitecture.database.favorites.FavoriteContributorDAO;

@Database(entities = FavoriteContributor.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

  public abstract FavoriteContributorDAO favoriteContributorDAO();
}
