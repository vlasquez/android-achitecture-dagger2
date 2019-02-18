package com.vlasquez.androidarchitecture.trending;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.model.Repo;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Andrés Velasquez
 * @since 2/18/19
 **/
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {
  private final RepoClickedListener listener;
  private final List<Repo> data = new ArrayList<>();

  RepoAdapter(RepoClickedListener listener) {
    this.listener = listener;
    setHasStableIds(true);
  }

  @Override public RepoAdapter.RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_repo_list_item, parent, false);
    return new RepoViewHolder(itemView, listener);
  }

  @Override public void onBindViewHolder(RepoAdapter.RepoViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @Override public long getItemId(int position) {
    return data.get(position).id();
  }

  void setData(List<Repo> repos) {
    if (repos != null) {
      DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RepoDiffCallback(data, repos));
      data.clear();
      data.addAll(repos);
      diffResult.dispatchUpdatesTo(this);
    } else {
      data.clear();
      notifyDataSetChanged();
    }
  }

  static final class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repo_name_tv) TextView repoNameTv;
    @BindView(R.id.repo_description_tv) TextView repoDescriptionTv;
    @BindView(R.id.fork_count_tv) TextView forkCountText;
    @BindView(R.id.star_count_tv) TextView starCountText;

    private Repo repo;

    public RepoViewHolder(View itemView, RepoClickedListener listener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(v -> {
        if (repo != null) {
          listener.onRepoClicked(repo);
        }
      });
    }

    void bind(Repo repo) {
      this.repo = repo;
      repoNameTv.setText(repo.name());
      repoDescriptionTv.setText(repo.description());
      forkCountText.setText(NumberFormat.getInstance().format(repo.forksCount()));
      starCountText.setText(NumberFormat.getInstance().format(repo.stargazersCount()));
    }
  }

  interface RepoClickedListener {
    void onRepoClicked(Repo repo);
  }
}
