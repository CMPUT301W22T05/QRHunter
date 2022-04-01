package com.example.qrhunter;

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

public class PlayerRankingActivity extends AppCompatActivity {

    ListView playerList;
    ArrayAdapter<Player> playerAdapter;
    ArrayList<Player> playerDataList;

    final String TAG = "Sample";
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ranking_layout);

        db = FirebaseFirestore.getInstance();
        // Get a top level reference to the collection
        final Query collectionReference = db.collection("Player").orderBy("Total score", Query.Direction.ASCENDING);
        // Get a reference to the ListView and create an object for the city list
        playerList = findViewById(R.id.ranking_list);
        playerDataList = new ArrayList<>();

        // Set the adapter for the ListView to the CustomAdapter that we created in Lab 3
        playerAdapter = new PlayerRankingCustomList(this, playerDataList);
        playerList.setAdapter(playerAdapter);

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
                    FirebaseFirestoreException error) {
                // Clear the old list
                playerDataList.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    String name = doc.getId();
                    String score = (String) doc.getData().get("Total score");
                    playerDataList.add(new Player(name, score)); // Adding the cities and provinces from FireStore
                }
                playerAdapter.notifyDataSetChanged(); // Notifying the adapter to render any new data fetched from the cloud
            }
        });

    }


}


