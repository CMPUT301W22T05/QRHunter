package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SearchInOwnerMenu extends AppCompatActivity {

    ImageButton SearchUserNameButton;
    EditText SearchUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_menu_layout);

        //Find corresponding view in the layout files.
        SearchUserNameButton = findViewById(R.id.search_user_name_Button);
        SearchUserName = findViewById(R.id.search_user_name);
        // open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        SearchUserNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Username = SearchUserName.getText().toString();
                if (Username.length() == 0){  // user did not enter any information in
                    Toast.makeText(SearchInOwnerMenu.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent JumpToPersonalRank = new Intent();
                    JumpToPersonalRank.setClass(SearchInOwnerMenu.this, PersonalRank.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("UserName", Username);
//                    JumpToPersonalRank.putExtras(bundle);
                    startActivity(JumpToPersonalRank);

                }
            }

        });
    }
}
