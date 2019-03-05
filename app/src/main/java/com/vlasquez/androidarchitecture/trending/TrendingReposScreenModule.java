package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import com.vlasquez.poweradapter.adapter.RecyclerDataSource;
import com.vlasquez.poweradapter.item.ItemRenderer;
import com.vlasquez.poweradapter.item.RecyclerItem;
import com.vlasquez.poweradapter.item.RenderKey;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import java.util.Map;

@Module
public abstract class TrendingReposScreenModule {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManager(TrendingReposUIManager manager);

  @Binds
  @IntoMap
  @RenderKey("Repo")
  abstract ItemRenderer<? extends RecyclerItem> bindRepoRenderer(RepoRenderer repoRenderer);

  @Provides
  @ScreenScope
  static RecyclerDataSource provideRecyclerDataSource(
      Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
    return new RecyclerDataSource(renderers);
  }
}
