package com.example.qrhunter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;

import io.paperdb.Paper;

public class SignUpActivity extends AppCompatActivity {

    Button ConfirmButton;
    Button CancelButton;
    Button Generate;
    EditText UsernameSignUpEditText;
    ImageView imageView;
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        //Find corresponding view in the layout files.
        UsernameSignUpEditText = findViewById(R.id.sign_up_username);
        Generate = findViewById(R.id.generate);
        ConfirmButton = findViewById(R.id.sign_up_confirm);
        CancelButton = findViewById(R.id.sign_up_cancel);
        imageView = (ImageView)findViewById(R.id.imageView);
        chkBoxRememberMe = (CheckBox) findViewById(R.id.rm);
        Paper.init(this);
        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(UsernameSignUpEditText.getText().toString(), BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

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
        // set reations of the confirm button
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = UsernameSignUpEditText.getText().toString();
                if(chkBoxRememberMe.isChecked())
                {
                    Paper.book().write(Player.UserPhoneKey, username);
                }


                // check if the username/password/email address/full name are empty or not.
                if (username.length() == 0 ){
                    Toast.makeText(SignUpActivity.this,"Please fill in a username",Toast.LENGTH_SHORT).show();
                    UsernameSignUpEditText.setText("");

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
                                            MyProfileData.putString("username", username);
                                            MyProfileData.commit();
                                            //create password field
                                            HashMap<String, String> data = new HashMap<>();
                                            data.put("Total score","0");
                                            collectionReference
                                                    .document(username)
                                                    .set(data)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(SignUpActivity.this, "Successfully create account", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });

                                        }
                                    }
                                });
                    }
                // jump to the Player Menu
                Intent JumpToPlayerMenu = new Intent();
                JumpToPlayerMenu.setClass(SignUpActivity.this, PlayerMenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserName", username);
                JumpToPlayerMenu.putExtras(bundle);
                startActivity(JumpToPlayerMenu);
            }
        });
    }
}