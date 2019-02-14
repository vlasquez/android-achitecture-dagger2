package com.vlasquez.androidarchitecture.model;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import javax.annotation.Nullable;
import org.threeten.bp.ZonedDateTime;

public class ZonedDateTimeAdapter {
  @FromJson ZonedDateTime fromJson(String json) {
    return ZonedDateTime.parse(json);
  }

  @ToJson String toJson(@Nullable ZonedDateTime value) {
    return value != null ? value.toString() : null;
  }
}
