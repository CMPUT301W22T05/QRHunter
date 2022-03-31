package com.example.qrhunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlayerRankingCustomList extends ArrayAdapter<Player> {

    private ArrayList<Player> players;
    private Context context;

    public PlayerRankingCustomList(Context context, ArrayList<Player> players) {
        super(context, 0, players);
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.player_ranking_content, parent,false);
        }

        Player player = players.get(position);

        TextView playerName = view.findViewById(R.id.player_ranking_name);
        TextView playerScore = view.findViewById(R.id.player_ranking_score);

        playerName.setText(player.getUsername());
        playerScore.setText(player.getTotalScore());

        return view;

    }

}
