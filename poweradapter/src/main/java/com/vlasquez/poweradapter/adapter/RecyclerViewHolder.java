package com.vlasquez.poweradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.vlasquez.poweradapter.item.ItemRenderer;
import com.vlasquez.poweradapter.item.RecyclerItem;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

  private final ItemRenderer<RecyclerItem> renderer;

  public RecyclerViewHolder(ViewGroup parent, ItemRenderer<RecyclerItem> renderer) {
    super(renderer.createView(parent));
    this.renderer = renderer;
  }

  void bind(RecyclerItem item) {
    renderer.render(itemView, item);
  }
}
