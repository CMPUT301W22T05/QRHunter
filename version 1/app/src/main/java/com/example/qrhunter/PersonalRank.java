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
import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class PersonalRank extends AppCompatActivity {
    ListView scoreList;
    ArrayAdapter<ScoreOnOwnerPersonalPage> scoreAdapter;
    ArrayList<ScoreOnOwnerPersonalPage> scoreDataList;
    String Username;
    Integer counter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_qr_rank_layout);
        // receive username from search button
        Intent intent = getIntent();
        String message = intent.getStringExtra(OwnerMenuActivity.EXTRA_MESSAGE);
        Username = message;
        TextView PersonalName = findViewById(R.id.personal_rank_TextView);
        PersonalName.setText(Username + "'s QR Codes");

        scoreList = findViewById(R.id.personal_ranking_list);
        scoreDataList = new ArrayList<>();
        scoreAdapter = new ScoreListOnOwnerPersonalPage(this, scoreDataList);
        scoreList.setAdapter(scoreAdapter);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Player").document(Username).collection("QRCOde");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                // Clear the old list
                scoreDataList.clear();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    scoreDataList.add(new ScoreOnOwnerPersonalPage("QR "+counter.toString(), document.getData().get("worth").toString()));
                    counter = counter + 1;
                }
                scoreAdapter.notifyDataSetChanged();
            }
        });
        scoreList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adpterView,View view,int i,long l){
                collectionReference.document(scoreDataList.get(i).getSequenceNumber()).delete();
                scoreDataList.remove(i);
                scoreList.setAdapter(scoreAdapter);
                scoreAdapter.notifyDataSetChanged();
                Toast.makeText(PersonalRank.this, "Deleted successfully", Toast.LENGTH_SHORT).show();


                return false;

            }


        });


    }
    // display the information to list
    public void onOkPressed(ScoreOnOwnerPersonalPage newScoreOnOwnerPersonalPage) {
        scoreAdapter.add(newScoreOnOwnerPersonalPage);
    }

}
