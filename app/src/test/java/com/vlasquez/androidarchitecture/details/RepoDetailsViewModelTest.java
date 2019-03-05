package com.vlasquez.androidarchitecture.details;

import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.androidarchitecture.testUtils.TestUtils;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class RepoDetailsViewModelTest {

  private RepoDetailsViewModel viewModel;

  private Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);

  @Before
  public void setUp() throws Exception {
    viewModel = new RepoDetailsViewModel();
  }

  @Test
  public void details() throws Exception {
    viewModel.processRepo().accept(repo);

    viewModel.details().test().assertValue(
        RepoDetailState.builder()
            .loading(false)
            .name("RxJava")
            .description(
                "RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.")
            .createdDate("Jan 08, 2013")
            .updatedDate("Oct 06, 2017")
            .build()
    );
  }

  @Test
  public void detailsError() throws Exception {
    viewModel.detailsError().accept(new IOException());

    viewModel.details().test().assertValue(RepoDetailState.builder()
        .loading(false)
        .errorRes(R.string.api_error_single_repo)
        .build());
  }

  @Test
  public void contributors() throws Exception {
    viewModel.contributorsLoaded().accept(new Object());

    viewModel.contributors().test().assertValue(ContributorState.builder()
        .loading(false)
        .build());
  }

  @Test
  public void contributorsError() throws Exception {
    viewModel.contributorsError().accept(new IOException());

    viewModel.contributors().test().assertValue(ContributorState.builder()
        .loading(false)
        .errorRes(R.string.api_error_repo_contributor)
        .build());
  }
}