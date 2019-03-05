package com.vlasquez.androidarchitecture.trending;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.model.Repo;
import com.vlasquez.poweradapter.item.ItemRenderer;
import java.text.NumberFormat;
import javax.inject.Inject;
import javax.inject.Provider;

class RepoRenderer implements ItemRenderer<Repo> {

  private final Provider<TrendingReposPresenter> presenterProvider;

  @Inject RepoRenderer(Provider<TrendingReposPresenter> presenterProvider) {

    this.presenterProvider = presenterProvider;
  }

  @Override public int layoutRes() {
    return R.layout.view_repo_list_item;
  }

  @Override public View createView(@NonNull ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
    view.setTag(new ViewBinder(view, presenterProvider.get()));
    return view;
  }

  @Override public void render(@NonNull View itemView, @NonNull Repo item) {
    ((ViewBinder) itemView.getTag()).bind(item);
  }

  static class ViewBinder {
    @BindView(R.id.repo_name_tv) TextView repoNameTv;
    @BindView(R.id.repo_description_tv) TextView repoDescriptionTv;
    @BindView(R.id.fork_count_tv) TextView forkCountText;
    @BindView(R.id.star_count_tv) TextView starCountText;

    private Repo repo;

    ViewBinder(View itemView, TrendingReposPresenter presenter) {
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(v -> {
        if (repo != null) {
          presenter.onRepoClicked(repo);
        }
      });
    }

    public void bind(Repo item) {
      this.repo = item;
      repoNameTv.setText(repo.name());
      repoDescriptionTv.setText(repo.description());
      forkCountText.setText(NumberFormat.getInstance().format(repo.forksCount()));
      starCountText.setText(NumberFormat.getInstance().format(repo.stargazersCount()));
    }
  }
}
