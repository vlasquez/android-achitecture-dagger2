package com.vlasquez.androidarchitecture.base;

import android.app.Activity;
import com.vlasquez.androidarchitecture.home.MainActivity;
import com.vlasquez.androidarchitecture.home.MainActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = { MainActivityComponent.class })
public abstract class ActivityBindingModule {
  @Binds
  @IntoMap
  @ActivityKey(MainActivity.class)
  abstract AndroidInjector<? extends Activity> provideMainActivityInjector(
      MainActivityComponent.Builder builder);
}
