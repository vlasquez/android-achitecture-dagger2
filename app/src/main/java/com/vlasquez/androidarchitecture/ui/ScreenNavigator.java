package com.vlasquez.androidarchitecture.ui;

public interface ScreenNavigator {


  boolean pop();

  void goToRepoDetails(String repoOwner, String repoName);

}
