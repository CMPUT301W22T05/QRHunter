package com.example.qrhunter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OwnerMenuActivity extends AppCompatActivity {

    long scorePos = 10000;
    ListView scoreList;
    ArrayAdapter<TotalScoreOnOwnerPage> scoreAdapter;
    ArrayList<TotalScoreOnOwnerPage> scoreDataList;
    String DisplayUserName;
    String DisplayTotalScore;

    ImageButton SearchButton;
    Button RankingButton;
    TextView SearchUserName;
    TextView PersonalName;
    public static final String EXTRA_MESSAGE = "com.example.qrhunter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_menu_layout);

        // initialize all the buttons and textview in owner_menu_layout
        SearchButton = findViewById(R.id.search_user_name_Button);
        RankingButton = findViewById(R.id.get_ranking_button);
        SearchUserName = findViewById(R.id.search_user_name);
        // open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // update information in personal_qr_rank_layout.xml
        PersonalName = findViewById(R.id.personal_rank_TextView);

        scoreDataList = new ArrayList<>();

        db.collection("Player")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getString("Total score") == null){
                                    DisplayUserName = document.getId();;
                                    DisplayTotalScore = "NULL";
                                }
                                else {
                                    DisplayUserName = document.getId();;
                                    DisplayTotalScore = document.getString("Total score");
                                }
                                scoreDataList.add(new TotalScoreOnOwnerPage(DisplayUserName, DisplayTotalScore));
                            }
                        }
                    }
                });




        /////////////////////////////////
        scoreList = findViewById(R.id.ranking_total_score_list);
        scoreAdapter = new ScoreListOnOwnerPage(this, scoreDataList);
        scoreList.setAdapter(scoreAdapter);

        // 点击item跳转activity

        scoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // long position = scoreList.getItemIdAtPosition(position);
                scorePos = position;
                System.out.println(scorePos);
                Intent SendToNextTitle = new Intent(OwnerMenuActivity.this, PersonalRank.class);
                startActivity(SendToNextTitle);
            }
        });



        // set the functionality of switching activity of ranking button
        RankingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToRankingPage = new Intent();
                JumpToRankingPage.setClass(OwnerMenuActivity.this, PlayerRankingActivity.class);
                startActivity(JumpToRankingPage);
            }
        });

        // set the functionality of search button
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
//                                        Intent JumpToPersonalRank = new Intent();
////                                        JumpToPersonalRank.setClass(OwnerMenuActivity.this, PersonalRank.class);
//                                        startActivity(JumpToPersonalRank);
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
    // display the information to list
    public void onOkPressed(TotalScoreOnOwnerPage newTotalScoreOnOwnerPage) {
        scoreAdapter.add(newTotalScoreOnOwnerPage);
    }
}