package com.example.qrhunter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    ImageButton SearchPlayerButton;
    ImageButton camera;
    EditText UsernameSearchEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchPlayerButton = findViewById(R.id.search);
        camera = findViewById(R.id.scan_btn);
        UsernameSearchEdit = findViewById(R.id.Username);
        SearchPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Username = UsernameSearchEdit.getText().toString();
                if (Username.length() == 0){
                    Toast.makeText(Search.this, "Username can not be empty!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent JumpToPlayerProfile = new Intent();  // for player
                    JumpToPlayerProfile.setClass(Search.this,SearchInfo.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("UserName", Username);
                    JumpToPlayerProfile.putExtras(bundle);
                    startActivity(JumpToPlayerProfile);
                }

            }
        });

    }
}