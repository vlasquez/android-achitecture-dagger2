package com.vlasquez.poweradapter.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

  private final RecyclerDataSource dataSource;

  public RecyclerAdapter(RecyclerDataSource dataSource) {
    this.dataSource = dataSource;
    dataSource.attachToAdapter(this);
    setHasStableIds(true);
  }

  @NonNull @Override
  public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new RecyclerViewHolder(viewGroup, dataSource.rendererForType(i));
  }

  @Override public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
    recyclerViewHolder.bind(dataSource.getItem(i));
  }

  @Override public int getItemCount() {
    return dataSource.getCount();
  }

  @Override public int getItemViewType(int position) {
    return dataSource.viewResourceForPosition(position);
  }

  @Override public long getItemId(int position) {
    return dataSource.getItem(position).getId();
  }
}
