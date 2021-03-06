// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.qrhunter.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText Username;

  @NonNull
  public final ImageButton scanBtn;

  @NonNull
  public final ImageButton search;

  private ActivitySearchBinding(@NonNull ConstraintLayout rootView, @NonNull EditText Username,
      @NonNull ImageButton scanBtn, @NonNull ImageButton search) {
    this.rootView = rootView;
    this.Username = Username;
    this.scanBtn = scanBtn;
    this.search = search;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Username;
      EditText Username = ViewBindings.findChildViewById(rootView, id);
      if (Username == null) {
        break missingId;
      }

      id = R.id.scan_btn;
      ImageButton scanBtn = ViewBindings.findChildViewById(rootView, id);
      if (scanBtn == null) {
        break missingId;
      }

      id = R.id.search;
      ImageButton search = ViewBindings.findChildViewById(rootView, id);
      if (search == null) {
        break missingId;
      }

      return new ActivitySearchBinding((ConstraintLayout) rootView, Username, scanBtn, search);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
