package com.vlasquez.androidarchitecture.base;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.di.Injector;

public abstract class BaseController extends Controller {

  private boolean injected = false;

  @Override protected void onContextAvailable(@NonNull Context context) {
    /** Controller instances are retained across config changes, so this method can be called more than one. This makes
     * sure we don't waste any time injecting more than once, though technically it would't change functionality
     */
    if (!injected) {
      Injector.inject(this);
      injected = true;
    }
    super.onContextAvailable(context);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
