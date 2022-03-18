package com.example.qrhunter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfileActivity extends AppCompatActivity {

    TextView UserName;
    TextView ContactInfo;
    TextView UserDeviceBrand;
    TextView UserDeviceModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_layout);
        // initialize all the buttons and textview in my_profile_layout
        UserName = findViewById(R.id.profile_username);
        ContactInfo = findViewById(R.id.profile_contact_information);
        UserDeviceBrand = findViewById(R.id.profile_user_device_brand);
        UserDeviceModel= findViewById(R.id.profile_user_device_model);

        // set the text about TextViews
        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String username = MyProfileData.getString("username", null);
        String email = MyProfileData.getString("email", null);
        UserName.setText("Username: " + username);  // display the username of this account
        ContactInfo.setText("Contact Information: "+ email);  // display the email address
        UserDeviceBrand.setText("Phone Brand: " +Build.BRAND);  // display the brand of the phone now
        UserDeviceModel.setText("Phone Model: " +Build.MODEL);  // display the model of the phone now





    }
}
