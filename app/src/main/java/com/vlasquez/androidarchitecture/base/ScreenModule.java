package com.vlasquez.androidarchitecture.base;

import com.vlasquez.androidarchitecture.lifecycle.ScreenLifecycleTask;
import dagger.Module;
import dagger.multibindings.Multibinds;
import java.util.Set;

@Module
public abstract class ScreenModule {

  @Multibinds
  abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
