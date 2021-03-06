// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

public final class SignUpLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button generate;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final CheckBox rm;

  @NonNull
  public final Button signUpCancel;

  @NonNull
  public final Button signUpConfirm;

  @NonNull
  public final EditText signUpUsername;

  @NonNull
  public final TextView textView2;

  private SignUpLayoutBinding(@NonNull ConstraintLayout rootView, @NonNull Button generate,
      @NonNull ImageView imageView, @NonNull CheckBox rm, @NonNull Button signUpCancel,
      @NonNull Button signUpConfirm, @NonNull EditText signUpUsername,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.generate = generate;
    this.imageView = imageView;
    this.rm = rm;
    this.signUpCancel = signUpCancel;
    this.signUpConfirm = signUpConfirm;
    this.signUpUsername = signUpUsername;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SignUpLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SignUpLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.sign_up_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SignUpLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.generate;
      Button generate = ViewBindings.findChildViewById(rootView, id);
      if (generate == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.rm;
      CheckBox rm = ViewBindings.findChildViewById(rootView, id);
      if (rm == null) {
        break missingId;
      }

      id = R.id.sign_up_cancel;
      Button signUpCancel = ViewBindings.findChildViewById(rootView, id);
      if (signUpCancel == null) {
        break missingId;
      }

      id = R.id.sign_up_confirm;
      Button signUpConfirm = ViewBindings.findChildViewById(rootView, id);
      if (signUpConfirm == null) {
        break missingId;
      }

      id = R.id.sign_up_username;
      EditText signUpUsername = ViewBindings.findChildViewById(rootView, id);
      if (signUpUsername == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new SignUpLayoutBinding((ConstraintLayout) rootView, generate, imageView, rm,
          signUpCancel, signUpConfirm, signUpUsername, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
