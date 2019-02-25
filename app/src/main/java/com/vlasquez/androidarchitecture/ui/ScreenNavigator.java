package com.vlasquez.androidarchitecture.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface ScreenNavigator {

  void initWithRouter(Router router, Controller rootScreen);

  boolean pop();

  void goToRepoDetails(String repoOwner, String repoName);

  void clear();
}
