package com.vlasquez.androidarchitecture.trending;

import com.vlasquez.androidarchitecture.data.RepoRepository;
import com.vlasquez.androidarchitecture.data.TrendingReposResponse;
import com.vlasquez.androidarchitecture.lifecycle.DisposableManager;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.androidarchitecture.testUtils.TestUtils;
import com.vlasquez.androidarchitecture.ui.ScreenNavigator;
import com.vlasquez.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @autor Andrés Velasquez
 * @since 2/18/19
 **/
public class TrendingReposPresenterTest {
  static {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(
        schedulerCallable -> Schedulers.trampoline());
  }

  @Mock RepoRepository repoRepository;
  @Mock TrendingReposViewModel viewModel;
  @Mock Consumer<Throwable> onErrorConsumer;
  @Mock Consumer<Boolean> loadingConsumer;
  @Mock ScreenNavigator screenNavigator;
  @Mock RecyclerDataSource recyclerDataSource;

  private TrendingReposPresenter presenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
    when(viewModel.onError()).thenReturn(onErrorConsumer);
    when(viewModel.reposUpdated()).thenReturn(() -> {
    });
  }

  @Test
  public void reposLoaded() throws Exception {
    List<Repo> repos = setUpSuccess();
    initializePresenter();

    verify(repoRepository).getTrendingRepos();
    verify(recyclerDataSource).setData(repos);
    verifyZeroInteractions(onErrorConsumer);
  }

  @Test
  public void reposLoadedError() throws Exception {
    Throwable error = setUpError();
    initializePresenter();

    verify(onErrorConsumer).accept(error);
    verifyZeroInteractions(recyclerDataSource);
  }

  @Test
  public void loadingSuccess() throws Exception {
    setUpSuccess();
    initializePresenter();

    InOrder inOrder = Mockito.inOrder(loadingConsumer);
    inOrder.verify(loadingConsumer).accept(true);
    inOrder.verify(loadingConsumer).accept(false);
  }

  @Test
  public void loadingError() throws Exception {
    //noinspection ThrowableNotThrown
    setUpError();
    initializePresenter();

    InOrder inOrder = Mockito.inOrder(loadingConsumer);
    inOrder.verify(loadingConsumer).accept(true);
    inOrder.verify(loadingConsumer).accept(false);
  }

  @Test
  public void onRepoClicked() throws Exception {
    Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);
    setUpSuccess();
    initializePresenter();
    presenter.onRepoClicked(repo);

    verify(screenNavigator).goToRepoDetails(repo.owner().login(), repo.name());
  }

  private List<Repo> setUpSuccess() {
    TrendingReposResponse response =
        TestUtils.loadJson("mock/search/get_trending_repos.json", TrendingReposResponse.class);
    List<Repo> repos = response.repos();

    when(repoRepository.getTrendingRepos()).thenReturn(Single.just(repos));

    return repos;
  }

  private Throwable setUpError() {
    Throwable error = new IOException();
    when(repoRepository.getTrendingRepos()).thenReturn(Single.error(error));
    return error;
  }

  private void initializePresenter() {
    presenter =
        new TrendingReposPresenter(viewModel, repoRepository, screenNavigator, Mockito.mock(
            DisposableManager.class), recyclerDataSource);
  }
}