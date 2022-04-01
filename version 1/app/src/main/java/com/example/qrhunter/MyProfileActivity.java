package com.example.qrhunter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class MyProfileActivity extends AppCompatActivity implements MyProfileDialog.myProfileDialogInterface{

    TextView UserName;
    TextView ContactInfo;
    TextView UserDeviceBrand;
    TextView UserDeviceModel;
    Button editButton;

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
        editButton = findViewById(R.id.edit_button);


        // set the text about TextViews
        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String username = MyProfileData.getString("username", null);
        String email = MyProfileData.getString("email", null);
        UserName.setText("Username: " + username);  // display the username of this account
        ContactInfo.setText("Contact Information: "+ email);  // display the email address
        UserDeviceBrand.setText("Phone Brand: " +Build.BRAND);  // display the brand of the phone now
        UserDeviceModel.setText("Phone Model: " +Build.MODEL);  // display the model of the phone now

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    @Override
    public void applyText(String name, String contact) {
        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String previousUsername = MyProfileData.getString("username", null);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Player").document(previousUsername).update("Email", contact);

        UserName.setText("Username: " + name);
        ContactInfo.setText("Contact Information: "+ contact);
    }

    public void openDialog(){
        MyProfileDialog myProfileDialog = new MyProfileDialog();
        myProfileDialog.show(getSupportFragmentManager(),"edit profile dialog");
    }

}
