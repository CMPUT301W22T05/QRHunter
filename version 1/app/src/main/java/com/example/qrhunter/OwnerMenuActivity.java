package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class OwnerMenuActivity extends AppCompatActivity {
    private ImageButton SearchButton;
    private Button RankingButton;
    private TextView SearchUserName;
    private TextView PersonalName;
    private ListView ListResult;

    private DatabaseReference mUserDatabase;

    public static final String EXTRA_MESSAGE = "com.example.qrhunter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_menu_layout);

        //
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Players");

        // initialize all the buttons and textview in owner_menu_layout
        SearchButton = (ImageButton) findViewById(R.id.search_user_name_Button);
        RankingButton = (Button) findViewById(R.id.get_ranking_button);
        SearchUserName = (EditText) findViewById(R.id.search_user_name);
        // open the firebase and connect
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // update information in personal_qr_rank_layout.xml
        PersonalName = (TextView) findViewById(R.id.personal_rank_TextView);

        // listview
        ListResult = (ListView) findViewById(R.id.ranking_total_score_list);
//        ListResult.setHasFixedSize;
//        ListResult.setLayoutManger


        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseUserSearch();
            }
        });









        // set the functionality of switching activity of ranking button
        // 点击排序按钮 --> 再跳转
        RankingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent JumpToRankingPage = new Intent();
                JumpToRankingPage.setClass(OwnerMenuActivity.this, PlayerRankingActivity.class);
                startActivity(JumpToRankingPage);
            }
        });

//        // set the functionality of search button
//        // 老版本： 点击搜索按钮 --> 再跳转
//        SearchButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                final String Username = SearchUserName.getText().toString();
//                if (Username.length() == 0){  // user did not enter any information in
//                    Toast.makeText(OwnerMenuActivity.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    DocumentReference noteRef = db.collection("Player").document(Username);
//                    noteRef.get()
//                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                @Override
//                                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                    if (documentSnapshot.exists()) {  // can find player name in database
////                                        Intent JumpToPersonalRank = new Intent();
//////                                        JumpToPersonalRank.setClass(OwnerMenuActivity.this, PersonalRank.class);
////                                        startActivity(JumpToPersonalRank);
//                                        // put user's name to next page
//                                        String name = documentSnapshot.getString("Name");
//                                        Intent SendToNextTitle = new Intent(OwnerMenuActivity.this, PersonalRank.class);
//                                        SendToNextTitle.putExtra(EXTRA_MESSAGE, name);
//                                        startActivity(SendToNextTitle);
//
//                                    }
//                                    else {  // can't find player name in database
//                                        Toast.makeText(OwnerMenuActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                            });
//
//
//                }
//
//            }
//        });
//


    }

    private void firebaseUserSearch() {

        FirebaseRecyclerAdapter<RankOnOwnerPage, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RankOnOwnerPage, UsersViewHolder>(
                RankOnOwnerPage.class,
                R.layout.ranking_list_in_owner_layout,
                UsersViewHolder.class,
                mUserDatabase

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, RankOnOwnerPage model, int position) {

                viewHolder.setDetails(model.getName(), model.getTotalScore());
            }

        };
        ListResult.setAdapter((ListAdapter) firebaseRecyclerAdapter);
    }

    // View Holder Class
    public class UsersViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setDetails(String playerName, String playerScore) {
            TextView PlayerName = (TextView) mView.findViewById(R.id.player_name_textview);
            TextView TotalScore = (TextView) mView.findViewById(R.id.total_score_textview);

            PlayerName.setText(playerName);
            TotalScore.setText(playerScore);


        }




    }
}