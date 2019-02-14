package com.vlasquez.androidarchitecture.home;

import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.base.BaseActivity;
import com.vlasquez.androidarchitecture.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

  @Override protected int layoutRes() {
    return R.layout.activity_main;
  }

  @Override protected Controller initialScreen() {
    return new TrendingReposController();
  }
}
