package com.vlasquez.androidarchitecture.testUtils;

import com.squareup.moshi.Moshi;
import com.vlasquez.androidarchitecture.model.AdapterFactory;
import com.vlasquez.androidarchitecture.model.ZonedDateTimeAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * @autor Andrés Velasquez
 * @since 2/18/19
 **/
public class TestUtils {

  private static TestUtils INSTANCE = new TestUtils();

  private static final Moshi TEST_MOSHI = initializeMoshi();

  private TestUtils() {

  }

  public static <T> T loadJson(String path, Type type) {
    try {
      String json = getFileString(path);

      //noinspection unchecked
      return (T) TEST_MOSHI.adapter(type).fromJson(json);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not deserialize " + path + " into type: " + type);
    }
  }

  public static <T> T loadJson(String path, Class<T> clazz) {
    try {
      String json = getFileString(path);

      //noinspection unchecked
      return (T) TEST_MOSHI.adapter(clazz).fromJson(json);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not deserialize " + path + " into type: " + clazz);
    }
  }

  private static String getFileString(String path) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));

      String line;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
      }
      return stringBuilder.toString();
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not readl from resource at: " + path);
    }
  }

  private static Moshi initializeMoshi() {
    Moshi.Builder builder = new Moshi.Builder();
    builder.add(AdapterFactory.create());
    builder.add(new ZonedDateTimeAdapter());
    return builder.build();
  }
}
