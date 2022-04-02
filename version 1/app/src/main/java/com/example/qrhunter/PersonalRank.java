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

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class PersonalRank extends AppCompatActivity {
    FirebaseFirestore db;
    ListView personalList;
    ArrayAdapter<PersonalScoreOnrankpage> personalAdapter;
    ArrayList<PersonalScoreOnrankpage> personalDatalist;
    final String TAG = "Sample";
    String DisplayUserName;
    String DisplayTotalScore;

    @SuppressLint("SetTextI18n")//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        final CollectionReference collectionReference = db.collection("Player");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_qr_rank_layout);
        db = FirebaseFirestore.getInstance();


        final Query collectionReference = db.collection("Player");
        personalList = findViewById(R.id.personal_ranking_list);
        personalDatalist = new ArrayList<>();


        personalAdapter = new ScoreListOnpersonalrankPage(this,personalDatalist);
        personalList.setAdapter(personalAdapter);
////
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                // Clear the old list
                personalDatalist.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    String number = doc.getId();
                    String score = doc.getData().get("Total score").toString();
                    personalDatalist.add(new PersonalScoreOnrankpage(number, score));
                }
                personalAdapter.notifyDataSetChanged();
            }
        });

//        String [] QRNumbers ={"Edmonton", "Vancouver", "Toronto", "Hamilton"};
//        String [] PersonalSCore = {"AB", "BC", "ON", "ON"};
//        for (int i = 0; i < QRNumbers.length; i++) {
//            personalDatalist.add(new PersonalScoreOnrankpage(QRNumbers[i], PersonalSCore[i]));
//        }
//
//        String [] QRNumber = {};
//        String [] PersonalSCores = {};
//        List<String> QRNumber = new ArrayList<String>();



//









        // receive name from search button
        Intent intent = getIntent();
        String message = intent.getStringExtra(OwnerMenuActivity.EXTRA_MESSAGE);
        TextView PersonalName = findViewById(R.id.personal_rank_TextView);
        PersonalName.setText(message + "'s QR Codes");

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

    public void onOkPressed(PersonalScoreOnrankpage newPersonalScoreOnrankpage) {
        personalAdapter.add(newPersonalScoreOnrankpage);

    }

}
