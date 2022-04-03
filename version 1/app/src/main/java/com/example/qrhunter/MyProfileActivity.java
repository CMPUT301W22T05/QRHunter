package com.example.qrhunter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MyProfileActivity extends AppCompatActivity implements MyProfileDialog.myProfileDialogInterface{

    TextView UserName;
    TextView ContactInfo;
    TextView UserDeviceBrand;
    TextView UserDeviceModel;
    Button editButton;
    Button Generate;
    ImageView imageView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_layout);

        // initialize all the buttons and textview in my_profile_layout
        UserName = findViewById(R.id.profile_username);
        ContactInfo = findViewById(R.id.profile_contact_information);
        UserDeviceBrand = findViewById(R.id.profile_user_device_brand);
        UserDeviceModel= findViewById(R.id.profile_user_device_model);
        editButton = findViewById(R.id.edit_button);
        Generate = findViewById(R.id.generate);
        imageView = (ImageView)findViewById(R.id.imageView);

        // get the username of the device
        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String username = MyProfileData.getString("username", null);
        
        // display the device brand and model
        UserDeviceBrand.setText("Phone Brand: " +Build.BRAND); 
        UserDeviceModel.setText("Phone Model: " +Build.MODEL);
        
        // connect to the database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference noteRef = db.collection("Player").document(username);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // search for the email and full name of the account in database and display on the screen
                            String email = documentSnapshot.getString("Email");
                            String name = documentSnapshot.getString("Name");
                            UserName.setText("Full Name: "+ name); 
                            ContactInfo.setText("Contact Information: "+ email);
                        }
                    }

                });
        
        // edit my profile button set up
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        
        // generate QR code button set up
        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences MyProfileData = getSharedPreferences("data", 0);
                String loginUsername = MyProfileData.getString("username", null);
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(loginUsername, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    
    // update the new players' input in database 
    @Override
    public void applyText(String name, String contact) {
        // get the username of the device
        SharedPreferences MyProfileData = getSharedPreferences("data", 0);
        String loginUsername = MyProfileData.getString("username", null);
        
        // update data
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Player").document(loginUsername).update("Email", contact);
        db.collection("Player").document(loginUsername).update("Name", name);
        
        // display the new input
        UserName.setText("Username: " + name);
        ContactInfo.setText("Contact Information: "+ contact);
    }
    
    // open the dialog
    public void openDialog(){
        MyProfileDialog myProfileDialog = new MyProfileDialog();
        myProfileDialog.show(getSupportFragmentManager(),"edit profile dialog");
    }

}
