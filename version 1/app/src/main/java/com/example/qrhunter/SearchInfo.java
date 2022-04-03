package com.example.qrhunter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchInfo extends AppCompatActivity {
    private static final String TAG ="Firelog" ;
    private RecyclerView codelist;
    private List<QrCodes> qr;
    private QrCodeAdapter qrCodeAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView header_title;
    TextView total_score;
    TextView contact_info;
    String Email;
    String Score;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
        qr = new ArrayList<>();

        // get the username
        Bundle bundle = getIntent().getExtras();
        String player_name = bundle.getString("PlayerName");
        String friend_name = bundle.getString("FriendName");

        qrCodeAdapter = new QrCodeAdapter(qr, new QrCodeAdapter.ItemClickListener() {
            @Override
            public void onItemClick(QrCodes qrCodes) {
                String qr_name = qrCodes.getTitle();
                Intent JumpToQRDetailPage = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("PlayerName", player_name);
                bundle.putString("FriendName", friend_name);
                bundle.putString("QRName", qr_name);
                JumpToQRDetailPage.putExtras(bundle);
                JumpToQRDetailPage.setClass(SearchInfo.this, CommentingActivity.class);
                startActivity(JumpToQRDetailPage);
            }
        });

        codelist = (RecyclerView) findViewById(R.id.QR_list);
        codelist.setHasFixedSize(true);
        codelist.setLayoutManager(new LinearLayoutManager(this));
        codelist.setAdapter(qrCodeAdapter);
        header_title = findViewById(R.id.header_title);
        header_title.setText(friend_name + "'s Profile");
        total_score = findViewById(R.id.total_score);
        contact_info = findViewById(R.id.contact_info);
        final CollectionReference collectionReference = db.collection("Player");
        DocumentReference noteRef = db.collection("Player").document(friend_name);
        noteRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Email = value.getString("Email");
                Score = value.getData().get("Total score").toString();
                contact_info.setText("Contact information: "+Email);
                total_score.setText("Total score: "+Score);
            }
        });
        collectionReference.document(friend_name)
                .collection("QRCOde").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        collectionReference
                                .document(friend_name)
                                .collection("QRCOde");
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            QrCodes qrcodes = documentSnapshot.toObject(QrCodes.class);
                            qr.add(qrcodes);
                            qrCodeAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(SearchInfo.this, "Successfully create account", Toast.LENGTH_SHORT).show();


                    }
                });


    }
}