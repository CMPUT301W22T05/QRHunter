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

public class FriendScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
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
        // get the username
        Bundle bundle = getIntent().getExtras();
        String player_name = bundle.getString("PlayerName");

        String friend_name = rawResult.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(FriendScanner.this);

        builder.setTitle("Do you want to search up "+ friend_name +"'s profile");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                builder.setMessage("Do you want to search up "+ friend_name+"'s profile");
                dialog.dismiss();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference noteRef = db.collection("Player").document(friend_name);
                noteRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                //if get Snapshot in the player document successfully, compares the password with the user input
                                Intent JumpToPlayerMenu = new Intent();
                                JumpToPlayerMenu.setClass(FriendScanner.this, SearchInfo.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("PlayerName", player_name);
                                bundle.putString("FriendName", friend_name);
                                JumpToPlayerMenu.putExtras(bundle);
                                startActivity(JumpToPlayerMenu);

                            }

                        });
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