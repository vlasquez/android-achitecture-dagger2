package com.vlasquez.androidarchitecture.database.favorites;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavoriteContributor {
  @PrimaryKey
  private final long id;

  public FavoriteContributor(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
