package com.vlasquez.androidarchitecture.di;

import com.vlasquez.androidarchitecture.lifecycle.DisposableManager;
import dagger.android.AndroidInjector;

public interface ScreenComponent<T> extends AndroidInjector<T> {
  @ForScreen DisposableManager disposableManager();
}
