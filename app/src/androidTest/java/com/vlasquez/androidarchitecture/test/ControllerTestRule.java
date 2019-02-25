package com.vlasquez.androidarchitecture.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import com.vlasquez.androidarchitecture.base.TestApplication;
import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.data.TestRepoService;
import com.vlasquez.androidarchitecture.ui.TestScreenNavigator;

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

  public final TestScreenNavigator screenNavigator;
  public final TestRepoService repoService;
  public final RepoRepository repository;

  public ControllerTestRule(Class<T> activityClass) {
    super(activityClass, true, false);

    screenNavigator = TestApplication.getTestApplicationComponent().screenNavigator();
    repoService = TestApplication.getTestApplicationComponent().testRepoService();
    repository = TestApplication.getTestApplicationComponent().repoRepostory();
  }

  public void clearState(){
    repository.clearCache();
  }
}
