package com.example.qrhunter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LoginScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
        // authorize the permission of the camera
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        String Username=rawResult.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScanner.this);

        builder.setTitle("Do you want to login as "+ Username);


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                builder.setMessage("Do you wish to Login as" + Username);
                dialog.dismiss();
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
                if (OwnerUsernameList.contains(Username)) {// owner account login
                    DocumentReference noteRef = db.collection("Owner").document(Username);
                    noteRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    //if get Snapshot in owner document successfully, compares the password with the user input
                                    Intent JumpToOwnerMenu = new Intent();
                                    JumpToOwnerMenu.setClass(LoginScanner.this, OwnerMenuActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("UserName", Username);
                                    JumpToOwnerMenu.putExtras(bundle);
                                    startActivity(JumpToOwnerMenu);
                                }
                            });
                } else {// player account login
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
                                    JumpToPlayerMenu.setClass(LoginScanner.this, PlayerMenuActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("UserName", Username);
                                    JumpToPlayerMenu.putExtras(bundle);
                                    startActivity(JumpToPlayerMenu);

                                }

                            });
                }

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        AlertDialog.Builder remember = new AlertDialog.Builder(this);

        remember.setTitle("Do you want to remember your login");

        remember.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                Paper.book().write(Player.UserPhoneKey, Username);
                dialog.dismiss();
            }
        });

        remember.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog next = remember.create();
        next.show();

    }
    // pause the camera if needed
    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
    // resume the camera if needed
    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}