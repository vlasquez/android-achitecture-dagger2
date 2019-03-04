package com.vlasquez.androidarchitecture.trending;

import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import com.vlasquez.androidarchitecture.util.ButterKnifeUtils;
import javax.inject.Inject;

@ScreenScope
public class TrendingReposUIManager extends ScreenLifecycleTask {
  @BindView(R.id.toolbar) Toolbar toolbar;

  private Unbinder unbinder;

  @Inject
  TrendingReposUIManager(){

  }

  @Override
  public void onEnterScope(View view){
    unbinder = ButterKnife.bind(this,view);
    toolbar.setTitle(R.string.screen_title_trending);
  }

  @Override public void onExitScope() {
    ButterKnifeUtils.unbind(unbinder);
  }
}
