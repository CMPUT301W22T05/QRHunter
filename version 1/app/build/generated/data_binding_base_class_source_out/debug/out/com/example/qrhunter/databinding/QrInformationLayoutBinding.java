// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class QrInformationLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView highestScoringTextView;

  @NonNull
  public final TextView lowestScoringTextView;

  @NonNull
  public final TextView numScoringTextView;

  @NonNull
  public final TextView sumScoringTextView;

  @NonNull
  public final TextView textView2;

  private QrInformationLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView highestScoringTextView, @NonNull TextView lowestScoringTextView,
      @NonNull TextView numScoringTextView, @NonNull TextView sumScoringTextView,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.highestScoringTextView = highestScoringTextView;
    this.lowestScoringTextView = lowestScoringTextView;
    this.numScoringTextView = numScoringTextView;
    this.sumScoringTextView = sumScoringTextView;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static QrInformationLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static QrInformationLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.qr_information_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static QrInformationLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.highest_scoring_textView;
      TextView highestScoringTextView = ViewBindings.findChildViewById(rootView, id);
      if (highestScoringTextView == null) {
        break missingId;
      }

      id = R.id.lowest_scoring_textView;
      TextView lowestScoringTextView = ViewBindings.findChildViewById(rootView, id);
      if (lowestScoringTextView == null) {
        break missingId;
      }

      id = R.id.num_scoring_textView;
      TextView numScoringTextView = ViewBindings.findChildViewById(rootView, id);
      if (numScoringTextView == null) {
        break missingId;
      }

      id = R.id.sum_scoring_textView;
      TextView sumScoringTextView = ViewBindings.findChildViewById(rootView, id);
      if (sumScoringTextView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new QrInformationLayoutBinding((ConstraintLayout) rootView, highestScoringTextView,
          lowestScoringTextView, numScoringTextView, sumScoringTextView, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
