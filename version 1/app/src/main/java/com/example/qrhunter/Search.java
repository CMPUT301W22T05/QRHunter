package com.example.qrhunter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    ImageButton SearchPlayerButton;
    ImageButton camera;
    EditText UsernameSearchEdit;
    SupportMapFragment sm;
    FusedLocationProviderClient cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get the username
        Bundle bundle = getIntent().getExtras();
        String player_name = bundle.getString("PlayerName");

        // initialize all the settings
        SearchPlayerButton = findViewById(R.id.search);
        camera = findViewById(R.id.scan_btn);
        UsernameSearchEdit = findViewById(R.id.Username);

        // set the functionality of switching activity of scan button
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JumpToFriendScanner = new Intent();  // for player
                Bundle bundle = new Bundle();
                bundle.putString("PlayerName", player_name);
                JumpToFriendScanner.putExtras(bundle);
                JumpToFriendScanner.setClass(Search.this,FriendScanner.class);
                startActivity(JumpToFriendScanner);
            }
        });

        // set the functionality of switching activity of searchPlayer button
        SearchPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String friend_name = UsernameSearchEdit.getText().toString();
                if (friend_name.length() == 0) {
                    Toast.makeText(Search.this, "Username can not be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent JumpToPlayerProfile = new Intent();  // for player
                    JumpToPlayerProfile.setClass(Search.this, SearchInfo.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PlayerName", player_name);
                    bundle.putString("FriendName", friend_name);
                    JumpToPlayerProfile.putExtras(bundle);
                    startActivity(JumpToPlayerProfile);
                }

            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.small_map);
        cl= LocationServices.getFusedLocationProviderClient(Search.this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("QRCODES").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(QueryDocumentSnapshot doc : value){
                    sm.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng latLng = new LatLng(Double.parseDouble(doc.getData().get("lat").toString()), Double.parseDouble(doc.getData().get("lag").toString()));

                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(doc.getId().toString());
                            googleMap.addMarker(markerOptions);
                        }
                    });

                }
            }
        });


        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getmylocation();
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
    public void getmylocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Task<Location> task = cl.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                sm.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here...!!");
                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    }
                });
            }
        });
    }
}