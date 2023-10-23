package com.example.qrhunter;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewQRCode extends AppCompatActivity {
    private static final String TAG ="Firelog" ;
    private RecyclerView codelist;
    private List<QrCodes> qr;
    private QrCodeAdapter qrCodeAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_qr_codes_layout);
        qr = new ArrayList<>();
        qrCodeAdapter = new QrCodeAdapter(qr, new QrCodeAdapter.ItemClickListener() {
            @Override
            public void onItemClick(QrCodes qrCodes) {
                Toast.makeText(ViewQRCode.this, "LIST clicked", Toast.LENGTH_SHORT).show();
            }
        });
        codelist = (RecyclerView) findViewById(R.id.main_list);
        codelist.setHasFixedSize(true);
        codelist.setLayoutManager(new LinearLayoutManager(this));
        codelist.setAdapter(qrCodeAdapter);
        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String username = MyProfileData.getString("username", null);
        final CollectionReference collectionReference = db.collection("Player");
        DocumentReference noteRef = db.collection("Player").document(username);
        collectionReference.document(username)
                .collection("QRCOde").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        collectionReference
                                .document(username)
                                .collection("QRCOde");
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            QrCodes qrcodes = documentSnapshot.toObject(QrCodes.class);
                            qr.add(qrcodes);
                            qrCodeAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(ViewQRCode.this, "All QRCODES LIST", Toast.LENGTH_SHORT).show();


                    }
                });

    }

}

