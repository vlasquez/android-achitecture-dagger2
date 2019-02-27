package com.vlasquez.androidarchitecture.details;

import android.support.test.espresso.matcher.ViewMatchers;
import com.vlasquez.androidarchitecture.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

class RepoDetailsRobot {

  static RepoDetailsRobot init() {
    return new RepoDetailsRobot();
  }

  private RepoDetailsRobot() {

  }

  RepoDetailsRobot verifyName(String name) {
    onView(withId(R.id.tv_repo_name)).check(matches(withText(name)));
    return this;
  }

  RepoDetailsRobot verifyDescription(String description) {
    onView(withId(R.id.tv_repo_description)).check(matches(withText(description)));
    return this;
  }

  RepoDetailsRobot verifyCreatedDate(String createdDate) {
    onView(withId(R.id.tv_creation_date)).check(matches(withText(createdDate)));
    return this;
  }

  RepoDetailsRobot verifyUpdatedDate(String updatedDate) {
    onView(withId(R.id.tv_updated_date)).check(matches(withText(updatedDate)));
    return this;
  }

  RepoDetailsRobot verifyErrorText(Integer textRes) {
    if (textRes == null) {
      onView(withId(R.id.tv_error)).check(matches(withText("")));
    } else {
      onView(withId(R.id.tv_error)).check(matches(withText(textRes)));
    }
    return this;
  }

  RepoDetailsRobot verifyErrorVisibility(ViewMatchers.Visibility visibility) {
    onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(visibility)));
    return this;
  }

  RepoDetailsRobot verifyContributorsErrorText(Integer textRes) {
    if (textRes == null) {
      onView(withId(R.id.tv_contributors_error)).check(matches(withText("")));
    } else {
      onView(withId(R.id.tv_contributors_error)).check(matches(withText(textRes)));
    }
    return this;
  }

  RepoDetailsRobot verifyContributorsErrorVisibility(ViewMatchers.Visibility visibility) {
    onView(withId(R.id.tv_contributors_error)).check(matches(withEffectiveVisibility(visibility)));
    return this;
  }

  RepoDetailsRobot verifyLoadingVisibility(ViewMatchers.Visibility visibility) {
    onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(visibility)));
    return this;
  }

  RepoDetailsRobot verifyContributorsLoadingVisibility(ViewMatchers.Visibility visibility) {
    onView(withId(R.id.contributor_loading_indicator)).check(
        matches(withEffectiveVisibility(visibility)));
    return this;
  }

  RepoDetailsRobot verifyContributorShown(String login) {
    onView(allOf(withId(R.id.tv_user_name), withText(login))).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    return this;
  }

  RepoDetailsRobot verifyContentVisibility(ViewMatchers.Visibility visibility) {
    onView(withId(R.id.content)).check(matches(withEffectiveVisibility(visibility)));
    return this;
  }
}
