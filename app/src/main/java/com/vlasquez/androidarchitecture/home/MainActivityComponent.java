package com.vlasquez.androidarchitecture.home;

import com.vlasquez.androidarchitecture.di.ActivityScope;
import com.vlasquez.androidarchitecture.ui.ActivityViewInterceptor;
import com.vlasquez.androidarchitecture.ui.ActivityViewInterceptorModule;
import com.vlasquez.androidarchitecture.ui.NavigationModule;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = { MainScreenBindingModule.class, NavigationModule.class,
    ActivityViewInterceptorModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    @Override public void seedInstance(MainActivity instance) {

    }
  }
}
