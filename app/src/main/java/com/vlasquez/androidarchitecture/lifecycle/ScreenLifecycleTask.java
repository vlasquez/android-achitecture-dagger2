package com.vlasquez.androidarchitecture.lifecycle;

import android.view.View;
import com.vlasquez.androidarchitecture.di.ActivityScope;

public abstract class ScreenLifecycleTask {

  /**
   * Callback received when a Screen becomes the visible screen
   */
  public void onEnterScope(View view) {

  }

  /**
   * Callback received when a Screen is either pooped of moved to the back stack.
   */
  public void onExitScope() {

  }

  /**
   * Callback received when a Screen is destroyed and will not be coming back(except as a new
   * instance).
   * This should be used to clear any {@link ActivityScope}
   */

  public void onDestroy() {

  }
}
