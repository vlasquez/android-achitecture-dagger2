package com.vlasquez.androidarchitecture.test;

import com.squareup.moshi.Moshi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import javax.inject.Inject;

public class TestUtils {

  private Moshi moshi;

  @Inject TestUtils(Moshi moshi) {
    this.moshi = moshi;
  }

  public <T> T loadJson(String path, Type type) {
    try {
      String json = getFileString(path);

      //noinspection unchecked
      return (T) moshi.adapter(type).fromJson(json);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not deserialize " + path + " into type: " + type);
    }
  }

  public <T> T loadJson(String path, Class<T> clazz) {
    try {
      String json = getFileString(path);

      //noinspection unchecked
      return moshi.adapter(clazz).fromJson(json);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not deserialize " + path + " into type: " + clazz);
    }
  }

  private String getFileString(String path) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path)));

      String line;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
      }
      return stringBuilder.toString();
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not readl from resource at: " + path);
    }
  }
}
