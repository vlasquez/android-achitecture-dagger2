package com.vlasquez.androidarchitecture.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.vlasquez.poweradapter.item.RecyclerItem;

@AutoValue
public abstract class Contributor implements RecyclerItem {

  public abstract long id();

  public abstract String login();

  @Json(name = "avatar_url")
  public abstract String avatarUrl();

  public static JsonAdapter<Contributor> jsonAdapter(Moshi moshi) {
    return new AutoValue_Contributor.MoshiJsonAdapter(moshi);
  }

  @Override public long getId() {
    return id();
  }

  @Override public String renderKey() {
    return "Contributor";
  }
}
