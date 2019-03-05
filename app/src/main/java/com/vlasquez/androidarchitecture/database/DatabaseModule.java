package com.vlasquez.androidarchitecture.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public abstract class DatabaseModule {

  @Provides
  @Singleton
  static AppDatabase provideDatabase(Context context) {
    return Room.databaseBuilder(context, AppDatabase.class, "favorites-database").build();
  }
}
