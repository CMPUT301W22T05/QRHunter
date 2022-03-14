package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OwnerMenuActivity extends AppCompatActivity {
    ImageButton SearchButton;
    Button RankingButton;
    TextView SearchUserName;
    TextView PersonalName;
    public static final String EXTRA_MESSAGE = "com.example.qrhunter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_menu_layout);

        SearchButton = findViewById(R.id.search_user_name_Button);
        RankingButton = findViewById(R.id.get_ranking_button);
        SearchUserName = findViewById(R.id.search_user_name);
        // open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // update information in personal_qr_rank_layout.xml
        PersonalName = findViewById(R.id.personal_rank_TextView);


        RankingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToRankingPage = new Intent();
                JumpToRankingPage.setClass(OwnerMenuActivity.this, RankingActivity.class);
                startActivity(JumpToRankingPage);
            }
        });

        SearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String Username = SearchUserName.getText().toString();
                if (Username.length() == 0){  // user did not enter any information in
                    Toast.makeText(OwnerMenuActivity.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    DocumentReference noteRef = db.collection("Player").document(Username);
                    noteRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {  // can find player name in database
                                        Intent JumpToPersonalRank = new Intent();
                                        JumpToPersonalRank.setClass(OwnerMenuActivity.this, PersonalRank.class);
                                        startActivity(JumpToPersonalRank);
                                        // put user's name to next page
                                        String name = documentSnapshot.getString("Name");
                                        Intent SendToNextTitle = new Intent(OwnerMenuActivity.this, PersonalRank.class);
                                        SendToNextTitle.putExtra(EXTRA_MESSAGE, name);
                                        startActivity(SendToNextTitle);

                                    }
                                    else {  // can't find player name in database
                                        Toast.makeText(OwnerMenuActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });


                }

            }
        });



    }
}