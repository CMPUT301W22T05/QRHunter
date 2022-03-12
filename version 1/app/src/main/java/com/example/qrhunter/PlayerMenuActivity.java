package com.example.qrhunter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerMenuActivity extends AppCompatActivity {

    Button ScanQRButton;
    Button ViewQRButton;
    Button SearchButton;
    Button RankingButton;
    ImageButton Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_menu_layout);

        ScanQRButton = findViewById(R.id.scan_qr_code_button);
        ViewQRButton = findViewById(R.id.view_my_qr_codes_button);
        SearchButton = findViewById(R.id.search_button);
        RankingButton = findViewById(R.id.ranking_button);
        Profile = findViewById(R.id.profile_button);


        ScanQRButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToScanQRPage = new Intent();
                JumpToScanQRPage.setClass(PlayerMenuActivity.this, ScanQRcodeActivity.class);
                startActivity(JumpToScanQRPage);
            }
        });

        //get username from bundle
        Bundle bundle = getIntent().getExtras();
        String Username = bundle.getString("UserName");

        Profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToMyProfilePage = new Intent();
                JumpToMyProfilePage.setClass(PlayerMenuActivity.this,MyProfileActivity.class);
                startActivity(JumpToMyProfilePage);
            }
        });

        SearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToMySearchPage = new Intent();
                JumpToMySearchPage.setClass(PlayerMenuActivity.this,Search.class);
                startActivity(JumpToMySearchPage);
            }
        });


    }
}

