package com.example.qrhunter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentingActivity extends AppCompatActivity {
    private static final String TAG ="Firelog" ;
    TextView header;
    TextView worth;
    TextView lat;
    TextView log;
    TextView description;
    ImageView qr_image;
    ImageView yes;
    ImageView no;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_qr_detail_layout);

        header = findViewById(R.id.header_title);
        worth = findViewById(R.id.worth);
        lat = findViewById(R.id.lat);
        log = findViewById(R.id.log);
        description = findViewById(R.id.description);
        qr_image = findViewById(R.id.qr_image);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        Bundle bundle = getIntent().getExtras();
        String player_name = bundle.getString("PlayerName");
        String friend_name = bundle.getString("FriendName");
        String qr_name = bundle.getString("QRName");
        header.setText(qr_name);
        final CollectionReference collectionReference = db.collection("Player");
        collectionReference.document(player_name)
                .collection("NEW").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.getId());
                            }
                            Log.d(TAG, list.toString());
                            boolean contains = list.contains(qr_name);
                            if (contains == true){
                                yes.setVisibility(View.VISIBLE);
                            }else{
                                no.setVisibility(View.VISIBLE);
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        DocumentReference noteRef = db.collection("Player").document(friend_name).collection("NEW").document(qr_name);
        noteRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String Description = value.getString("description");
                String url = value.getString("url");
                String Latitude = value.getDouble("lat").toString();
                String Longitude = value.getDouble("lag").toString();
                String Score = value.getData().get("worth").toString();
                description.setText("Comments: "+Description);
                worth.setText("Total Score: "+Score);

                lat.setText("Latitude: "+Latitude);
                log.setText("Longitude: "+Longitude);

                Glide.with(CommentingActivity.this).load(url).into(qr_image);

            }
        });
    }
}
