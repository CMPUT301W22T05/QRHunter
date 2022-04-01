package com.example.qrhunter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
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

    ListView personallist;
    ArrayAdapter<PersonalScoreOnrankpage> personalAdapter;
    ArrayList<PersonalScoreOnrankpage> personaldatalist;
    long personalPos = 1000;
    String DisplayUserName;
    String DisplayTotalScore;
    FirebaseFirestore db;





    @SuppressLint("SetTextI18n")//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Player");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_qr_rank_layout);




        FirebaseFirestore db = FirebaseFirestore.getInstance();



        personallist = findViewById(R.id.personal_ranking_list);
        personaldatalist = new ArrayList<>();

        String [] QRNumbers ={"Edmonton", "Vancouver", "Toronto", "Hamilton"};
        String [] PersonalSCore = {"AB", "BC", "ON", "ON"};
        for (int i = 0; i < QRNumbers.length; i++) {
            personaldatalist.add(new PersonalScoreOnrankpage(QRNumbers[i], PersonalSCore[i]));
        }






        db.collection("Player")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("Total score") == null){
                                    DisplayUserName = document.getId();
                                    DisplayTotalScore = "NULL";
                                }
                                else {
                                    DisplayUserName = document.getId();
                                    DisplayTotalScore = document.getData().get("Total score").toString();
                                }
                                personaldatalist.add(new PersonalScoreOnrankpage(DisplayUserName, DisplayTotalScore));
                            }
                        }
                    }
                });


        personalAdapter = new ScoreListOnpersonalrankPage(this,personaldatalist);
        personallist.setAdapter(personalAdapter);


        // receive name from search button
        Intent intent = getIntent();
        String message = intent.getStringExtra(OwnerMenuActivity.EXTRA_MESSAGE);
        TextView PersonalName = findViewById(R.id.personal_rank_TextView);
        PersonalName.setText(message + "'s QR Codes");

    }

    public void onOkPressd(PersonalScoreOnrankpage newPersonalScoreOnrankpage){
        personalAdapter.add(newPersonalScoreOnrankpage);
    }
//    personallist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
//        @Override
//        public boolean onItemLongClick(AdapterView<?> adpterView,View view,int i,long l){
//            collectionReference.document(personaldatalist.get(i).getQRnumber()).delete();
//            personaldatalist.remove(i);
//            personallist.setAdapter(personalAdapter);
//            personallist.notifyDataSetChanged();
//            result false;
//        }
//
//    });

}
