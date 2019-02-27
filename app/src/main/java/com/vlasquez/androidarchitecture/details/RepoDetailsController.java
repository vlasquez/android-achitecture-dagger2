package com.vlasquez.androidarchitecture.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.bluelinelabs.conductor.Controller;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.base.BaseController;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

public class RepoDetailsController extends BaseController {

  static final String REPO_NAME_KEY = "repo_name";
  static final String REPO_OWNER_KEY = "repo_owner";

  public static Controller newInstance(String repoOwner,String repoName) {
    Bundle bundle = new Bundle();
    bundle.putString(REPO_NAME_KEY, repoName);
    bundle.putString(REPO_OWNER_KEY, repoOwner);

    return new RepoDetailsController(bundle);
  }

  @Inject RepoDetailsViewModel viewModel;
  @Inject RepoDetailsPresenter presenter;

  @BindView(R.id.tv_repo_name) TextView repoNameText;
  @BindView(R.id.tv_repo_description) TextView repoDescriptionText;
  @BindView(R.id.tv_creation_date) TextView createdDateText;
  @BindView(R.id.tv_updated_date) TextView updateDateText;
  @BindView(R.id.contributor_list) RecyclerView contributorListRv;
  @BindView(R.id.loading_indicator) View detailsLoadingView;
  @BindView(R.id.contributor_loading_indicator) View contributorsLoadingView;
  @BindView(R.id.content) View contentContainer;
  @BindView(R.id.tv_error) TextView errorText;
  @BindView(R.id.tv_contributors_error) TextView contributorsErrorText;

  @Override protected void onViewBound(View view) {
    contributorListRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    contributorListRv.setAdapter(new ContributorAdapter());
  }

  @Override protected Disposable[] subscriptions() {
    return new Disposable[] {
        viewModel.details()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(details -> {
          if (details.loading()) {
            detailsLoadingView.setVisibility(View.VISIBLE);
            contentContainer.setVisibility(View.GONE);
            errorText.setVisibility(View.GONE);
            errorText.setText(null);
          } else {
            if (details.isSuccess()) {
              errorText.setText(null);
            } else {
              //noinspection ConstantConditions
              errorText.setText(details.errorRes());
            }
            detailsLoadingView.setVisibility(View.GONE);
            contentContainer.setVisibility(details.isSuccess() ? View.VISIBLE : View.GONE);
            errorText.setVisibility(details.isSuccess() ? View.GONE : View.VISIBLE);
            repoNameText.setText(details.name());
            repoDescriptionText.setText(details.description());
            createdDateText.setText(details.createdDate());
            updateDateText.setText(details.updatedDate());
          }
        }),
        viewModel.contributors()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(contributorState -> {
          if (contributorState.loading()) {
            contributorsLoadingView.setVisibility(View.VISIBLE);
            contributorListRv.setVisibility(View.GONE);
            contributorsErrorText.setVisibility(View.GONE);
            contributorsErrorText.setText(null);
          } else {
            contributorsLoadingView.setVisibility(View.GONE);
            contributorListRv.setVisibility(
                contributorState.isSuccess() ? View.VISIBLE : View.GONE);
            contributorsErrorText.setVisibility(
                contributorState.isSuccess() ? View.GONE : View.VISIBLE);
            if (contributorState.isSuccess()) {
              contributorsErrorText.setText(null);
              ((ContributorAdapter) contributorListRv.getAdapter()).setData(
                  contributorState.contributors());
            } else {
              //noinspection ConstantConditions
              contributorsErrorText.setText(contributorState.errorRes());
            }
          }
        })
    };
  }

  public RepoDetailsController(@Nullable Bundle args) {
    super(args);
  }

  @Override protected int layoutRes() {
    return R.layout.screen_repo_details;
  }
}
