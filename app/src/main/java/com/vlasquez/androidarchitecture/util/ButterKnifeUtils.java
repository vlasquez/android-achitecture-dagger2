package com.vlasquez.androidarchitecture.util;

import butterknife.Unbinder;
import timber.log.Timber;

public class ButterKnifeUtils {
  private ButterKnifeUtils() {
  }

  public static void unbind(Unbinder unbinder) {
    if (unbinder != null) {
      try {
        unbinder.unbind();
      } catch (IllegalStateException e) {
        Timber.e(e, "Error unbinding views");
      }
    }
  }
}
