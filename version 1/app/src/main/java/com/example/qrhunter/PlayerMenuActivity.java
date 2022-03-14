package com.example.qrhunter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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


        // get the username
        Intent intent = getIntent();
        String Username = intent.getStringExtra(MainActivity.user);

        // connect the database
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        ScanQRButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToScanQRPage = new Intent();
                JumpToScanQRPage.setClass(PlayerMenuActivity.this, ScanQRcodeActivity.class);
                startActivity(JumpToScanQRPage);
            }
        });


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
        RankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JumpToRankingActivity = new Intent();
                JumpToRankingActivity.setClass(PlayerMenuActivity.this,RankingActivity.class);
                startActivity(JumpToRankingActivity);
            }
        });

    }
}

