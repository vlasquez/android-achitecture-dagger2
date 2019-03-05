package com.vlasquez.androidarchitecture.details;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.vlasquez.androidarchitecture.R;
import com.vlasquez.androidarchitecture.database.favorites.FavoriteService;
import com.vlasquez.androidarchitecture.model.Contributor;
import com.vlasquez.poweradapter.item.ItemRenderer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

public class ContributorRenderer implements ItemRenderer<Contributor> {

  private final FavoriteService favoriteService;

  @Inject
  public ContributorRenderer(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  @Override public int layoutRes() {
    return R.layout.view_user_list_item;
  }

  @Override public View createView(@NonNull ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
    view.setTag(new ViewBinder(view, favoriteService));
    return view;
  }

  @Override public void render(@NonNull View itemView, @NonNull Contributor item) {
    ((ViewBinder) itemView.getTag()).bind(item);
  }

  static class ViewBinder {
    private final FavoriteService favoriteService;
    private Contributor contributor;
    private Disposable disposable;
    @BindView(R.id.tv_user_name) TextView userNameText;
    @BindView(R.id.iv_avatar) ImageView avatarImageView;
    @BindView(R.id.parent_view) View parentView;

    ViewBinder(View itemView,
        FavoriteService favoriteService) {
      this.favoriteService = favoriteService;
      ButterKnife.bind(this, itemView);
      RxView.attachEvents(parentView)
          .subscribe(event -> {
            if (event.view().isAttachedToWindow()) {
              listenForFavoriteChanges();
            } else {
              if (disposable != null) {
                disposable.dispose();
                disposable = null;
              }
            }
          });
    }

    @OnLongClick(R.id.parent_view) boolean toogleFavorite() {
      if (contributor != null) {
        favoriteService.toggleFavoriteContributor(contributor);
      }
      return true;
    }

    private void listenForFavoriteChanges() {
      disposable = favoriteService.favoritedContributorIds()
          .filter(__ -> contributor != null)
          .map(favoriteIds -> favoriteIds.contains(contributor.id()))
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(isFavorite -> parentView.setBackgroundColor(
              isFavorite ? Color.YELLOW : Color.TRANSPARENT));
    }

    void bind(Contributor contributor) {
      this.contributor = contributor;
      userNameText.setText(contributor.login());
      Glide.with(avatarImageView.getContext())
          .load(contributor.avatarUrl())
          .into(avatarImageView);
    }
  }
}
