package com.example.qrhunter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button LogInButton;
    Button SignUpButton;
    ImageButton ScanButton;
    public static String user;

    // restrict user click back button
    // make "LOG-OUT" meaningful
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(MainActivity.this,"No back action here! Please LOG-IN/SIGN-UP button",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(this);
        //Find corresponding view in the layout files.

        SignUpButton = findViewById(R.id.signup_button);
        LogInButton = findViewById(R.id.login_button);
        ScanButton = findViewById(R.id.scan_button);

        String UserPhoneKey = Paper.book().read(Player.UserPhoneKey);

        if (UserPhoneKey != "" )
        {
            if (!TextUtils.isEmpty(UserPhoneKey) )
            {
                AllowAccess(UserPhoneKey);

            }
        }




        // set the functionality of switching activity of scan button
        ScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List OwnerUsernameList;
                startActivity(new Intent(getApplicationContext(), LoginScanner.class));
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


        // set the functionality of switching activity of login button
        LogInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginScanner.class));
            }

        });
    }

    public void AllowAccess(final String Username){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    // check if username/password are empty or not

        //get user input from EditText View
        final List OwnerUsernameList;
        OwnerUsernameList = new ArrayList();
        OwnerUsernameList.add("wyou1");
        OwnerUsernameList.add("ning2");
        OwnerUsernameList.add("xuantong");
        OwnerUsernameList.add("gemmary");
        OwnerUsernameList.add("xibei");
        OwnerUsernameList.add("rahul");
    // check owner/player account login
                if(OwnerUsernameList.contains(Username))

    {// owner account login
        DocumentReference noteRef = db.collection("Owner").document(Username);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //if get Snapshot in owner document successfully, compares the password with the user input
                        Intent JumpToOwnerMenu = new Intent();
                        JumpToOwnerMenu.setClass(MainActivity.this, OwnerMenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserName", Username);
                        JumpToOwnerMenu.putExtras(bundle);
                        startActivity(JumpToOwnerMenu);
                    }
                });
    }
                else

    {// player account login
        DocumentReference noteRef = db.collection("Player").document(Username);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //if get Snapshot in the player document successfully, compares the password with the user input
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

                });
    }
}
}