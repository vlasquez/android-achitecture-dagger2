package com.vlasquez.androidarchitecture.ui;

import android.support.v7.app.AppCompatActivity;
import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.lifecycle.ActivityLifeCycleTask;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestScreenNavigator extends ActivityLifeCycleTask implements ScreenNavigator {

  private DefaultScreenNavigator defaultScreenNavigator;
  private Controller overrideController;

  @Inject TestScreenNavigator() {
    this.defaultScreenNavigator = new DefaultScreenNavigator();
  }

  @Override public void onCreate(AppCompatActivity activity) {
    if (!(activity instanceof RouterProvider)) {
      throw new IllegalArgumentException("Activity must be instance of RouterProvider ");
    }
    Controller launchController =
        overrideController == null ? ((RouterProvider) activity).initialScreen()
            : overrideController;
    defaultScreenNavigator.initWithRouter(((RouterProvider) activity).getRouter(),
        launchController);
  }

  /**
   * Set the controller to launch when the Activity attaches to the screenNavigator. This will
   * be used instead of the controller provided by {@link RouterProvider#initialScreen()}
   *
   * @param overrideController Controller to launch when Activity starts. Or null to fall back to
   * default.
   */

  public void overrideInitialController(Controller overrideController) {
    this.overrideController = overrideController;
  }

  @Override public boolean pop() {
    return defaultScreenNavigator.pop();
  }

  @Override public void goToRepoDetails(String repoOwner, String repoName) {
    defaultScreenNavigator.goToRepoDetails(repoOwner, repoName);
  }

  @Override public void onDestroy(AppCompatActivity activity) {
    defaultScreenNavigator.onDestroy(activity);
  }
}