package com.vlasquez.androidarchitecture.trending;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.base.TestApplication;
import com.vlasquez.androidarchitecture.data.TestRepoService;
import com.vlasquez.androidarchitecture.test.ControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class TrendingReposControllerTest extends ControllerTest {

  @Before
  public void setUp() {
    TestApplication.getTestApplicationComponent().inject(this);
  }

  @Test
  public void loadRepos() {
    repoService.clearErrorFlags();
    launch();

    onView(withId(R.id.loading_pb)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    onView(withId(R.id.error_tv)).check(
       matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    onView(withId(R.id.repo_list_rv)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    onView(allOf(withId(R.id.repo_name_tv), withText("RxJava"))).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
  }

  @Test
  public void loadReposError() {
    repoService.setErrorFlags(TestRepoService.FLAG_TRENDING_REPOS);
    launch();

    onView(withId(R.id.loading_pb)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    onView(withId(R.id.repo_list_rv)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    onView(withId(R.id.error_tv)).check(matches(allOf(withText(R.string.api_error_repos),
        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
  }

  @Override protected Controller controllerToLaunch() {
    return new TrendingReposController();
  }
}
