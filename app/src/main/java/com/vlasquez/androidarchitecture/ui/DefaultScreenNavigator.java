package com.vlasquez.androidarchitecture.ui;

import android.support.v7.app.AppCompatActivity;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.vlasquez.androidarchitecture.details.RepoDetailsController;
import com.vlasquez.androidarchitecture.di.ActivityScope;
import com.vlasquez.androidarchitecture.lifecycle.ActivityLifeCycleTask;
import javax.inject.Inject;


@ActivityScope
public class DefaultScreenNavigator extends ActivityLifeCycleTask implements ScreenNavigator {
  private Router router;

  @Inject DefaultScreenNavigator() {

  }

  @Override public void onCreate(AppCompatActivity activity) {
    if (!(activity instanceof RouterProvider)) {
      throw new IllegalArgumentException("Activity must be instance of RouterProvider");
    }
    initWithRouter(((RouterProvider) activity).getRouter(),
        ((RouterProvider) activity).initialScreen());
  }

  void initWithRouter(Router router, Controller rootScreen) {
    this.router = router;
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(rootScreen));
    }
  }

  @Override public boolean pop() {
    return router != null && router.handleBack();
  }

  @Override public void onDestroy(AppCompatActivity activity) {
    router = null;
  }

  @Override public void goToRepoDetails(String repoOwner, String repoName) {
    if(router != null){
      router.pushController(RouterTransaction.with(RepoDetailsController.newInstance(repoOwner,repoName))
      .popChangeHandler(new FadeChangeHandler())
      .pushChangeHandler(new FadeChangeHandler()));
    }


  }
}
