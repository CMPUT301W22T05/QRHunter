// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.qrhunter.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class OwnerMenuLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button getRankingButton;

  @NonNull
  public final Button logoutForOwner;

  @NonNull
  public final ListView rankingTotalScoreList;

  @NonNull
  public final EditText searchUserName;

  @NonNull
  public final ImageButton searchUserNameButton;

  @NonNull
  public final TextView textView3;

  private OwnerMenuLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button getRankingButton, @NonNull Button logoutForOwner,
      @NonNull ListView rankingTotalScoreList, @NonNull EditText searchUserName,
      @NonNull ImageButton searchUserNameButton, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.getRankingButton = getRankingButton;
    this.logoutForOwner = logoutForOwner;
    this.rankingTotalScoreList = rankingTotalScoreList;
    this.searchUserName = searchUserName;
    this.searchUserNameButton = searchUserNameButton;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static OwnerMenuLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static OwnerMenuLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.owner_menu_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static OwnerMenuLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.get_ranking_button;
      Button getRankingButton = ViewBindings.findChildViewById(rootView, id);
      if (getRankingButton == null) {
        break missingId;
      }

      id = R.id.logout_for_owner;
      Button logoutForOwner = ViewBindings.findChildViewById(rootView, id);
      if (logoutForOwner == null) {
        break missingId;
      }

      id = R.id.ranking_total_score_list;
      ListView rankingTotalScoreList = ViewBindings.findChildViewById(rootView, id);
      if (rankingTotalScoreList == null) {
        break missingId;
      }

      id = R.id.search_user_name;
      EditText searchUserName = ViewBindings.findChildViewById(rootView, id);
      if (searchUserName == null) {
        break missingId;
      }

      id = R.id.search_user_name_Button;
      ImageButton searchUserNameButton = ViewBindings.findChildViewById(rootView, id);
      if (searchUserNameButton == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new OwnerMenuLayoutBinding((ConstraintLayout) rootView, getRankingButton,
          logoutForOwner, rankingTotalScoreList, searchUserName, searchUserNameButton, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}