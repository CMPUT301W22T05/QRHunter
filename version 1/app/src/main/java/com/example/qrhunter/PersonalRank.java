package com.example.qrhunter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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


public class PersonalRank extends AppCompatActivity {


    private String Null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_qr_rank_layout);

        // receive name from search button
        Intent intent = getIntent();
        String message = intent.getStringExtra(OwnerMenuActivity.EXTRA_MESSAGE);
        TextView PersonalName = findViewById(R.id.personal_rank_TextView);
        if (message == Null) {
            PersonalName.setText("UnknowID player's QR Codes");

        } else {
            PersonalName.setText(message + "'s QR Codes");


        }
    }
}
