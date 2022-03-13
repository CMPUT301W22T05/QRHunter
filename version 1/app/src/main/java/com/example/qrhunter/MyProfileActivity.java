package com.example.qrhunter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfileActivity extends AppCompatActivity {

    TextView UserName;
    TextView ContactInfo;
    TextView UserDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_layout);

        UserName = findViewById(R.id.profile_username);
        ContactInfo = findViewById(R.id.profile_contact_information);
        UserDevice = findViewById(R.id.profile_user_device);




    }
}
