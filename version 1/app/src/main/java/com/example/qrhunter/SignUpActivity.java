package com.example.qrhunter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    Button confirmButton;
    EditText signUpUsername;
    EditText signUpPassword;

    FirebaseFirestore db;
    final String TAG = "Sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        confirmButton = findViewById(R.id.confirm_button);
        signUpUsername = findViewById(R.id.username_editTextText);
        signUpPassword = findViewById(R.id.password_editTextText);

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Username");

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = signUpUsername.getText().toString();
                final String password = signUpPassword.getText().toString();

                HashMap<String, String> data = new HashMap<>();

                if (username.length()>0 && password.length()>0) { //needed to decide the constrain of creating an account
                    data.put("Password", password);
                }
                    collectionReference.document(username)
                            .set(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "Data has been added successfully!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Data could not be added!" + e.toString());
                                }
                            });
                signUpUsername.setText("");
                signUpPassword.setText("");
            }
        });



    }
}