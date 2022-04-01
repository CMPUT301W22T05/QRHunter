package com.example.qrhunter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
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


public class PersonalRank extends AppCompatActivity {
    long scorePos = 10000;
    ListView scoreList;
    ArrayAdapter<TotalScoreOnOwnerPage> scoreAdapter;
    ArrayList<TotalScoreOnOwnerPage> scoreDataList;
    String DisplayUserName;
    String DisplayTotalScore;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_qr_rank_layout);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        scoreList = findViewById(R.id.personal_ranking_list);
        scoreAdapter = new ScoreListOnOwnerPage(this, scoreDataList);
        scoreList.setAdapter(scoreAdapter);




        // receive name from search button
        Intent intent = getIntent();
        String message = intent.getStringExtra(OwnerMenuActivity.EXTRA_MESSAGE);
        TextView PersonalName = findViewById(R.id.personal_rank_TextView);
        PersonalName.setText(message + "'s QR Codes");

    }
    public void onOkPressed(TotalScoreOnOwnerPage newTotalScoreOnOwnerPage) {
        scoreAdapter.add(newTotalScoreOnOwnerPage);}


}
