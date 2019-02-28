package com.vlasquez.androidarchitecture.ui;

import android.app.Activity;
import android.support.annotation.LayoutRes;

public interface ActivityViewInterceptor {

  void setContentView(Activity activity, @LayoutRes int layoutRes);

  void clear();

  ActivityViewInterceptor DEFAULT = new ActivityViewInterceptor() {
    @Override public void setContentView(Activity activity, int layoutRes) {
      activity.setContentView(layoutRes);
    }

    @Override public void clear() {

    }
  };
}
