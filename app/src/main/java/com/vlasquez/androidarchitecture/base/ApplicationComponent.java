package com.vlasquez.androidarchitecture.base;

import com.vlasquez.androidarchitecture.data.RepoServiceModule;
import com.vlasquez.androidarchitecture.database.DatabaseModule;
import com.vlasquez.androidarchitecture.networking.ServiceModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    ApplicationModule.class, ActivityBindingModule.class, ServiceModule.class,
    RepoServiceModule.class, DatabaseModule.class,
})

public interface ApplicationComponent {
  void inject(MyApplication myApplication);
}
