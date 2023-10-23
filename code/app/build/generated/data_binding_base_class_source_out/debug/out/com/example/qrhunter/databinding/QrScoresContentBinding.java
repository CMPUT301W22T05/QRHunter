// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.qrhunter.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class QrScoresContentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView qrNumberText;

  @NonNull
  public final TextView qrScoreText;

  private QrScoresContentBinding(@NonNull LinearLayout rootView, @NonNull TextView qrNumberText,
      @NonNull TextView qrScoreText) {
    this.rootView = rootView;
    this.qrNumberText = qrNumberText;
    this.qrScoreText = qrScoreText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static QrScoresContentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static QrScoresContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.qr_scores_content, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static QrScoresContentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.qr_number_text;
      TextView qrNumberText = ViewBindings.findChildViewById(rootView, id);
      if (qrNumberText == null) {
        break missingId;
      }

      id = R.id.qr_score_text;
      TextView qrScoreText = ViewBindings.findChildViewById(rootView, id);
      if (qrScoreText == null) {
        break missingId;
      }

      return new QrScoresContentBinding((LinearLayout) rootView, qrNumberText, qrScoreText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}