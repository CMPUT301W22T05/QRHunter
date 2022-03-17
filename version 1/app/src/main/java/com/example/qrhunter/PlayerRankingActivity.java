package com.example.qrhunter;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PlayerRankingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlayerRankingAdapter adapter;
    private List<Player> playerList;
    DatabaseReference dbPlayer;

    //FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ranking_layout);

        // initialize the recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));

        // initialize player list and player adapter
        playerList = new ArrayList<>();
        adapter = new PlayerRankingAdapter(this, playerList);
        recyclerView.setAdapter(adapter);

        // retrieve total score and username from the database
        dbPlayer = FirebaseDatabase.getInstance().getReference("Player");
        dbPlayer.addListenerForSingleValueEvent(valueEventListener);

        //Query query = FirebaseDatabase.getInstance().getReference("Player").orderByChild("Total score");
        //query.addListenerForSingleValueEvent(valueEventListener);

        // testing the listing format
        Player player1 = new Player("player1", "80");
        playerList.add(player1);

        Player player2 = new Player("player2", "88");
        playerList.add(player2);

        //String what = "what" + playerList.size();
        //Toast.makeText(RankingActivity.this,what,Toast.LENGTH_SHORT).show();
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            playerList.clear();
            if (dataSnapshot.exists()) {    // check if the data exists in the database or not
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Player player = snapshot.getValue(Player.class);
                    playerList.add(player); // if exists, the data is added to the player list
                }
                adapter.notifyDataSetChanged();
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {}
    };
/*
    Task<QuerySnapshot> query = FirebaseFirestore.getInstance()
            .collection("Player")
            .orderBy("Total score", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot snapshot : snapshotList) {
                        Log.d(TAG, "onSucces: " + snapshot.getData());
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: ", e);
                }
            });

*/
}

