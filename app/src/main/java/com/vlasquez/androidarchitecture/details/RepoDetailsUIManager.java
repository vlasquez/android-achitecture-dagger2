package com.vlasquez.androidarchitecture.details;

import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import com.vlasquez.androidarchitecture.ui.ScreenNavigator;
import com.vlasquez.androidarchitecture.util.ButterKnifeUtils;
import javax.inject.Inject;
import javax.inject.Named;

@ScreenScope
public class RepoDetailsUIManager extends ScreenLifecycleTask {
  private Unbinder unbinder;

  @BindView(R.id.toolbar) Toolbar toolbar;
  private String repoName;
  private ScreenNavigator screenNavigator;

  @Inject RepoDetailsUIManager(@Named("repo_name") String repoName,
      ScreenNavigator screenNavigator) {
    this.repoName = repoName;
    this.screenNavigator = screenNavigator;
  }

  @Override public void onEnterScope(View view) {
    unbinder = ButterKnife.bind(this, view);
    toolbar.setTitle(repoName);
    toolbar.setNavigationIcon(R.drawable.ic_back);
    toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
  }

  @Override public void onExitScope() {
    toolbar.setNavigationOnClickListener(null);
    ButterKnifeUtils.unbind(unbinder);
  }
}
