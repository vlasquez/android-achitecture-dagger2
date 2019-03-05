package com.vlasquez.androidarchitecture.details;

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
public abstract class RepoDetailsScreenModule {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUIManager repoDetailsUIManager);

  @Binds
  @IntoMap
  @RenderKey("Contributor")
  abstract ItemRenderer<? extends RecyclerItem> bindContributorRenderer(
      ContributorRenderer renderer);

  @Provides
  @ScreenScope
  static RecyclerDataSource provideDataSource(
      Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
    return new RecyclerDataSource(renderers);
  }
}
