package com.vlasquez.androidarchitecture.base;

import com.vlasquez.androidarchitecture.di.ForScreen;
import com.vlasquez.androidarchitecture.di.ScreenScope;
import com.vlasquez.androidarchitecture.lifecycle.DisposableManager;
import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import java.util.Set;

@Module
public abstract class ScreenModule {

  @Provides
  @ScreenScope
  @ForScreen
  static DisposableManager provideDisposableManager(){
    return new DisposableManager();
  }

  @Multibinds
  abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
