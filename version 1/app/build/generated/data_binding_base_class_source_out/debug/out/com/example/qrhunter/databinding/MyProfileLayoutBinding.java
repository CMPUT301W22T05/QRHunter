// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class MyProfileLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button editButton;

  @NonNull
  public final Button generate;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView profileContactInformation;

  @NonNull
  public final TextView profileUserDeviceBrand;

  @NonNull
  public final TextView profileUserDeviceModel;

  @NonNull
  public final TextView profileUsername;

  @NonNull
  public final TextView textView2;

  private MyProfileLayoutBinding(@NonNull ConstraintLayout rootView, @NonNull Button editButton,
      @NonNull Button generate, @NonNull ImageView imageView,
      @NonNull TextView profileContactInformation, @NonNull TextView profileUserDeviceBrand,
      @NonNull TextView profileUserDeviceModel, @NonNull TextView profileUsername,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.editButton = editButton;
    this.generate = generate;
    this.imageView = imageView;
    this.profileContactInformation = profileContactInformation;
    this.profileUserDeviceBrand = profileUserDeviceBrand;
    this.profileUserDeviceModel = profileUserDeviceModel;
    this.profileUsername = profileUsername;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MyProfileLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MyProfileLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.my_profile_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MyProfileLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edit_button;
      Button editButton = ViewBindings.findChildViewById(rootView, id);
      if (editButton == null) {
        break missingId;
      }

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

      id = R.id.profile_contact_information;
      TextView profileContactInformation = ViewBindings.findChildViewById(rootView, id);
      if (profileContactInformation == null) {
        break missingId;
      }

      id = R.id.profile_user_device_brand;
      TextView profileUserDeviceBrand = ViewBindings.findChildViewById(rootView, id);
      if (profileUserDeviceBrand == null) {
        break missingId;
      }

      id = R.id.profile_user_device_model;
      TextView profileUserDeviceModel = ViewBindings.findChildViewById(rootView, id);
      if (profileUserDeviceModel == null) {
        break missingId;
      }

      id = R.id.profile_username;
      TextView profileUsername = ViewBindings.findChildViewById(rootView, id);
      if (profileUsername == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new MyProfileLayoutBinding((ConstraintLayout) rootView, editButton, generate,
          imageView, profileContactInformation, profileUserDeviceBrand, profileUserDeviceModel,
          profileUsername, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
