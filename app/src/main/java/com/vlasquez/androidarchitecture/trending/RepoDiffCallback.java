package com.vlasquez.androidarchitecture.trending;

import android.support.v7.util.DiffUtil;
import com.vlasquez.androidarchitecture.model.Repo;
import java.util.List;

/**
 * @autor Andrés Velasquez
 * @since 2/18/19
 **/
class RepoDiffCallback extends DiffUtil.Callback {

  private final List<Repo> oldList;
  private final List<Repo> newList;

  public RepoDiffCallback(List<Repo> oldList, List<Repo> repos) {
    this.oldList = oldList;
    this.newList = repos;
  }

  @Override public int getOldListSize() {
    return oldList.size();
  }

  @Override public int getNewListSize() {
    return newList.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).id() == newList.get(newItemPosition).id();
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
  }
}
