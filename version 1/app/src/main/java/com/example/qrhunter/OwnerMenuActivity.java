package com.example.qrhunter;

import android.os.Bundle;
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
    }
}
