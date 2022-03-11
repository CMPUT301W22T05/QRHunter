package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerMenuActivity extends AppCompatActivity {
    ImageButton SearchButton;
    Button RankingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_menu_layout);

        SearchButton = findViewById(R.id.search_user_name_Button);
        RankingButton = findViewById(R.id.get_ranking_button);


        RankingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToRankingPage = new Intent();
                JumpToRankingPage.setClass(OwnerMenuActivity.this, RankingActivity.class);
                startActivity(JumpToRankingPage);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String Username = bundle.getString("UserName");

        SearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToPlayerinformationpage = new Intent();
                JumpToPlayerinformationpage.setClass(OwnerMenuActivity.this,PlayerQRinformation.class);
                JumpToPlayerinformationpage.putExtras(bundle);
                startActivity(JumpToPlayerinformationpage);
            }
        });



    }
}