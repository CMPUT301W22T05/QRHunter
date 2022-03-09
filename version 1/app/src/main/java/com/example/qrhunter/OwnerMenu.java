package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class OwnerMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownermenu);
    }
    public void checkRank (View view){
        Intent intent = new Intent(this, DisplayRanking.class);



    }
}
