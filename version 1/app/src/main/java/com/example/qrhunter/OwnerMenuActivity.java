package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_menu_layout);

        SearchButton = findViewById(R.id.search_user_name_Button);
        RankingButton = findViewById(R.id.get_ranking_button);
        SearchUserName = findViewById(R.id.search_user_name);
        // open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();


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
                                    if (documentSnapshot.exists()) {
                                        Intent JumpToPersonalRank = new Intent();
                                        JumpToPersonalRank.setClass(OwnerMenuActivity.this, PersonalRank.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("UserName", Username);
                                        JumpToPersonalRank.putExtras(bundle);
                                        startActivity(JumpToPersonalRank);
                                    }
                                    else {
                                        Toast.makeText(OwnerMenuActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                }
            }
        });



    }
}