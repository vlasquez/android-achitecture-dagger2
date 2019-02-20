package com.vlasquez.androidarchitecture.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.base.BaseController;

public class RepoDetailsController extends BaseController {

  static final String REPO_NAME_KEY = "repo_name";
  static final String REPO_OWNER_KEY = "repo_owner";

  public static Controller newInstance(String repoName, String repoOwner) {
    Bundle bundle = new Bundle();
    bundle.putString(REPO_NAME_KEY, repoName);
    bundle.putString(REPO_OWNER_KEY, repoOwner);

    return new RepoDetailsController(bundle);
  }

  public RepoDetailsController(@Nullable Bundle args) {
    super(args);
  }

  @Override protected int layoutRes() {
    return R.layout.screen_repo_details;
  }
}
