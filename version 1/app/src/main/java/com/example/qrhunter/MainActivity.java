package com.example.qrhunter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button LogInButton;
    Button SignUpButton;
    ImageButton ScanButton;
    EditText UsernameLoginEditText;
    EditText PasswordLoginEditText;
    public static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find corresponding view in the layout files.
        UsernameLoginEditText = findViewById(R.id.username_input);
        PasswordLoginEditText = findViewById(R.id.password_input);
        SignUpButton = findViewById(R.id.signup_button);
        LogInButton = findViewById(R.id.login_button);
        ScanButton = findViewById(R.id.scan_button);

        // set the functionality of switching activity of scan button
        ScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JumpToScanPage = new Intent();
                JumpToScanPage.setClass(MainActivity.this, scannerView.class);
                startActivity(JumpToScanPage);
            }
        });

        // set the functionality of switching activity of signup button
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            //For user to sign up an account
            @Override
            public void onClick(View view) {
                Intent JumpToSignUpPage = new Intent();
                JumpToSignUpPage.setClass(MainActivity.this, SignUpActivity.class);
                startActivity(JumpToSignUpPage);
            }
        });

        //open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // set the functionality of switching activity of login button
        LogInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //get user input from EditText View
                final String Username = UsernameLoginEditText.getText().toString();
                user = UsernameLoginEditText.getText().toString();
                final String Password = PasswordLoginEditText.getText().toString();
                final List OwnerUsernameList;

                // owner accounts are stored into the database
                OwnerUsernameList = new ArrayList();
                OwnerUsernameList.add("wyou1");
                OwnerUsernameList.add("ning2");
                OwnerUsernameList.add("xuantong");
                OwnerUsernameList.add("gemmary");
                OwnerUsernameList.add("xibei");
                OwnerUsernameList.add("rahul");


                // check if username/password are empty or not
                if (Username.length() == 0 || Password.length() == 0) {
                    Toast.makeText(MainActivity.this, "Account information cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // check owner/player account login
                    if (OwnerUsernameList.contains(Username)) {// owner account login
                        DocumentReference noteRef = db.collection("Owner").document(Username);
                        noteRef.get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        //if get Snapshot in owner document successfully, compares the password with the user input
                                        if (documentSnapshot.exists()) {
                                            String password = documentSnapshot.getString("password");
                                            // if passwords match, jump to menu page
                                            if (password.equals(Password)) {
                                                Intent JumpToOwnerMenu = new Intent();
                                                JumpToOwnerMenu.setClass(MainActivity.this, OwnerMenuActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("UserName", Username);
                                                JumpToOwnerMenu.putExtras(bundle);
                                                startActivity(JumpToOwnerMenu);
                                            }
                                            // else come out the error message visible
                                            else {
                                                // display error whether incorrect password for player/owner
                                                Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                                UsernameLoginEditText.setText("");
                                                PasswordLoginEditText.setText("");
                                            }
                                        }
                                        // else come out the error message
                                        else {
                                            Toast.makeText(MainActivity.this, "Username not found, please try again", Toast.LENGTH_SHORT).show();
                                            UsernameLoginEditText.setText("");
                                            PasswordLoginEditText.setText("");
                                        }
                                    }
                                });
                    }
                    else {// player account login
                        DocumentReference noteRef = db.collection("Player").document(Username);
                        noteRef.get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        //if get Snapshot in the player document successfully, compares the password with the user input
                                        if (documentSnapshot.exists()) {
                                            String password = documentSnapshot.getString("Password");
                                            // if passwords match, jump to menu page
                                            if (password.equals(Password)) {
                                                SharedPreferences.Editor MyProfileData = getSharedPreferences("data", 0).edit();
                                                MyProfileData.putString("username", Username);
                                                MyProfileData.commit();
                                                // jump to the Player Menu
                                                Intent JumpToPlayerMenu = new Intent();
                                                JumpToPlayerMenu.setClass(MainActivity.this, PlayerMenuActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("UserName", Username);
                                                JumpToPlayerMenu.putExtras(bundle);
                                                startActivity(JumpToPlayerMenu);
                                            }
                                            // else turn the error message visible
                                            else {
                                                // display error whether incorrect password for player/owner
                                                Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                                UsernameLoginEditText.setText("");
                                                PasswordLoginEditText.setText("");
                                            }

                                        }
                                        // else turn the error message visible
                                        else {
                                            Toast.makeText(MainActivity.this, "Username not found, please try again", Toast.LENGTH_SHORT).show();
                                            UsernameLoginEditText.setText("");
                                            PasswordLoginEditText.setText("");
                                        }
                                    }

                                });
                    }
                }
            }
        });
    }
}