package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.data.TrendingReposResponse;
import com.vlasquez.androidarchitecture.testUtils.TestUtils;
import io.reactivex.observers.TestObserver;
import java.io.IOException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

/**
 * @autor Andrés Velasquez
 * @since 2/18/19
 **/
public class TrendingReposViewModelTest {

  private TrendingReposViewModel viewModel;

  @Before
  public void setUp() throws Exception {
    viewModel = new TrendingReposViewModel();
  }

  @Test
  public void loading() throws Exception {
    TestObserver<Boolean> loadingObserver = viewModel.loading().test();
    viewModel.loadingUpdated().accept(true);
    viewModel.loadingUpdated().accept(false);

    loadingObserver.assertValues(true, false);
  }

  @Test
  public void error() throws Exception {
    TestObserver<Integer> errorObserver = viewModel.error().test();
    viewModel.onError().accept(new IOException());
    viewModel.reposUpdated().accept(Collections.emptyList());

    errorObserver.assertValues(R.string.api_error_repos, -1);
  }

  @Test
  public void repos() throws Exception {
    TrendingReposResponse response =
        TestUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
    viewModel.reposUpdated().accept(response.repos());

    viewModel.repos().test().assertValue(response.repos());
  }
}