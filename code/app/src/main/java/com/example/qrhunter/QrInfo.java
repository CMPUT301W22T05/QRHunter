package com.example.qrhunter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QrInfo extends AppCompatActivity {
    FirebaseFirestore db;
    TextView highest,lowest,totalscore,number;
    String name;
    String totalsc;
    String high;
    String low;
    String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_information_layout);
        totalscore = (TextView) findViewById(R.id.sum_scoring_textView);
        highest = (TextView) findViewById(R.id.highest_scoring_textView);
        lowest = (TextView) findViewById(R.id.lowest_scoring_textView);
        number = (TextView) findViewById(R.id.num_scoring_textView);

        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String username = MyProfileData.getString("username", null);
        db = FirebaseFirestore.getInstance();
        // Get a top level reference to the collection
        DocumentReference docRef = db.collection("Player").document(username);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        totalsc = document.getData().get("Total score").toString();
                        totalscore.setText("Total Score: "+totalsc);
                    }
                }
            }
        });

        final Query cr = db.collection("Player").document(username).collection("QRCOde").orderBy("worth", Query.Direction.ASCENDING).limit(1);

        cr.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                // Clear the old list
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    String name = doc.getId();
                    low  = doc.getData().get("worth").toString();
                    lowest.setText("QR Code with Lowest Worth: "+low);


                }

            }
        });

        final Query cl = db.collection("Player").document(username).collection("QRCOde").orderBy("worth", Query.Direction.DESCENDING).limit(1);

        cl.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                // Clear the old list
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    String name = doc.getId();
                    high  = doc.getData().get("worth").toString();
                    highest.setText("QR Code with Highest Worth: "+high);


                }

            }
        });

            final Query ct = db.collection("Player").document(username).collection("QRCOde");

            ct.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                        FirebaseFirestoreException error) {
                    int count = 0;
                    for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                    {
                        count++;

                    }
                    number.setText("Number of QR Codes: "+count);

                }
            });

    }
}
