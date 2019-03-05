package com.vlasquez.poweradapter.item;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public interface ItemRenderer< T extends RecyclerItem> {

  @LayoutRes
  int layoutRes();

  View createView(@NonNull ViewGroup parent);

  void render(@NonNull View itemView, @NonNull T item);
}
