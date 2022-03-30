package com.example.qrhunter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import io.paperdb.Paper;

public class PlayerMenuActivity extends AppCompatActivity {

    Button ScanQRButton;
    Button ViewQRButton;
    Button SearchButton;
    Button RankingButton;
    Button LogoutButton;
    ImageButton Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_menu_layout);

        // initialize all the buttons
        ScanQRButton = findViewById(R.id.scan_qr_code_button);
        ViewQRButton = findViewById(R.id.view_my_qr_codes_button);
        SearchButton = findViewById(R.id.search_button);
        RankingButton = findViewById(R.id.ranking_button);
        Profile = findViewById(R.id.profile_button);
        LogoutButton = findViewById(R.id.logout);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent JumpToLogInPage = new Intent();
                JumpToLogInPage.setClass(PlayerMenuActivity.this, MainActivity.class);
                startActivity(JumpToLogInPage);
            }
        });


        // get the username
        Intent intent = getIntent();
        String Username = intent.getStringExtra(MainActivity.user);

        // connect the database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // set the functionality of switching activity of scan button
        ScanQRButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToScanQRPage = new Intent();
                JumpToScanQRPage.setClass(PlayerMenuActivity.this, ScanQRcodeActivity.class);
                startActivity(JumpToScanQRPage);
            }
        });

        // set the functionality of switching activity of profile button
        Profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToMyProfilePage = new Intent();
                JumpToMyProfilePage.setClass(PlayerMenuActivity.this,MyProfileActivity.class);
                startActivity(JumpToMyProfilePage);

            }
        });

        // set the functionality of switching activity of search button
        SearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToMySearchPage = new Intent();
                JumpToMySearchPage.setClass(PlayerMenuActivity.this,Search.class);
                startActivity(JumpToMySearchPage);
            }
        });

        // set the functionality of switching activity of ranking button
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JumpToRankingActivity = new Intent();
                JumpToRankingActivity.setClass(PlayerMenuActivity.this, PlayerRankingActivity.class);
                startActivity(JumpToRankingActivity);
            }
        });

    }
}

