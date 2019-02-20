package com.vlasquez.androidarchitecture.base;

import com.vlasquez.androidarchitecture.data.TestRepoServiceModule;
import com.vlasquez.androidarchitecture.networking.ServiceModule;
import com.vlasquez.androidarchitecture.trending.TrendingReposControllerTest;
import com.vlasquez.androidarchitecture.ui.NavigationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    ApplicationModule.class,
    TestActivityBindingModule.class,
    TestRepoServiceModule.class,
    ServiceModule.class,
    NavigationModule.class
})
public interface TestApplicationComponent extends ApplicationComponent {
  void inject(TrendingReposControllerTest trendingReposControllerTest);
}
