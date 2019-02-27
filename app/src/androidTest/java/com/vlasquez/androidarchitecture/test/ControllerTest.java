package com.vlasquez.androidarchitecture.test;

import android.content.Intent;
import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.data.TestRepoService;
import com.vlasquez.androidarchitecture.home.MainActivity;
import com.vlasquez.androidarchitecture.ui.TestScreenNavigator;
import org.junit.Rule;

public abstract class ControllerTest {

  @Rule
  public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

  protected TestRepoService repoService = testRule.repoService;
  protected RepoRepository repository = testRule.repository;
  TestScreenNavigator screenNavigator = testRule.screenNavigator;

  public ControllerTest() {
    screenNavigator.overrideInitialController(controllerToLaunch());
  }

  protected abstract Controller controllerToLaunch();

  protected void launch() {
    launch(null);
  }

  protected void launch(Intent intent) {
    testRule.launchActivity(intent);
  }
}
