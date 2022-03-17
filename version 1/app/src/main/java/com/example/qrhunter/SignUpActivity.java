package com.example.qrhunter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    Button ConfirmButton;
    Button CancelButton;
    EditText UsernameSignUpEditText;
    EditText PasswordSignUpEditText;
    EditText EmailSignUpEditText;
    EditText NameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        //Find corresponding view in the layout files.
        UsernameSignUpEditText = findViewById(R.id.sign_up_username);
        PasswordSignUpEditText = findViewById(R.id.sign_up_password);
        EmailSignUpEditText = findViewById(R.id.sign_up_email_address);
        NameEditText = findViewById(R.id.sign_up_name);

        ConfirmButton = findViewById(R.id.sign_up_confirm);
        CancelButton = findViewById(R.id.sign_up_cancel);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            //Go back to the login page
            @Override
            public void onClick(View view) {
                Intent JumpToLogInPage = new Intent();
                JumpToLogInPage.setClass(SignUpActivity.this, MainActivity.class);
                startActivity(JumpToLogInPage);
            }

        });



        // open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = UsernameSignUpEditText.getText().toString();
                final String password = PasswordSignUpEditText.getText().toString();
                final String name = NameEditText.getText().toString();
                final String email = EmailSignUpEditText.getText().toString();

                // check if the username/password/email address/full name are empty or not.
                if (username.length() == 0 || password.length() == 0 || name.length() == 0 || email.length() == 0){
                    Toast.makeText(SignUpActivity.this,"Please fill in all the personal information.",Toast.LENGTH_SHORT).show();
                    UsernameSignUpEditText.setText("");
                    PasswordSignUpEditText.setText("");
                    EmailSignUpEditText.setText("");
                    NameEditText.setText("");
                }
                else{
                        // connect to collection
                        final CollectionReference collectionReference = db.collection("Player");

                        // get the document reference
                        DocumentReference noteRef = db.collection("Player").document(username);

                        noteRef.get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()) {    // check if the input username exists or not
                                            Toast.makeText(SignUpActivity.this, "UserName already exist!", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            // set the information to MyProfile Page
                                            SharedPreferences.Editor MyProfileData = getSharedPreferences("data", 0).edit();
                                            MyProfileData.putString("email", email);
                                            MyProfileData.commit();
                                            //create password field
                                            HashMap<String, String> data = new HashMap<>();
                                            data.put("Password", password);
                                            data.put("Total score","0");
                                            data.put("Name", name);
                                            data.put("Email", email);
                                            collectionReference
                                                    .document(username)
                                                    .set(data)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(SignUpActivity.this, "Successfully create account", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });

/* This part is not necessary for part 3
                                            //create QRCodes document
                                            HashMap<String, String> QRCodes = new HashMap<>();
                                            QRCodes.put("QRCodes", "QRCodes");
                                            collectionReference
                                                    .document(username)
                                                    .set(QRCodes);

                                            //create MyQRCodes document
                                            HashMap<String, String> MyQRCodes = new HashMap<>();
                                            collectionReference
                                                    .document("MyQRCodes")
                                                    .set(MyQRCodes);

                                            //create ContactInfo document
                                            HashMap<String, String> ContactInfo = new HashMap<>();
                                            collectionReference
                                                    .document("ContactInfo")
                                                    .set(ContactInfo);

                                            //create UserDevice document
                                            HashMap<String, String> UserDevice = new HashMap<>();
                                            collectionReference
                                                    .document("UserDevice")
                                                    .set(UserDevice);
*/

                                            //after successfully create an account return to login page
                                            Intent JumpToLogInPage = new Intent();
                                            JumpToLogInPage.setClass(SignUpActivity.this, MainActivity.class);
                                            startActivity(JumpToLogInPage);
                                        }
                                    }
                                });
                    }
            }
        });
    }
}