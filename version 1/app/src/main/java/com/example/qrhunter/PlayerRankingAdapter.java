package com.example.qrhunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
// This class is used in the functionality of ranking part, but the ranking functionality
// is incomplete, and this class does not work yet.
public class PlayerRankingAdapter extends RecyclerView.Adapter<PlayerRankingAdapter.PlayerViewHolder> {

    private Context context;
    private List<Player> playerList;

    public PlayerRankingAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_ranking_content,parent,false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.textViewUsername.setText("Username: " + player.username);
        holder.textViewTotalScore.setText("Total score: " + player.totalScore);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{
        TextView    textViewUsername, textViewTotalScore;
        public PlayerViewHolder(View itemView){
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.player_ranking_name);
            textViewTotalScore = itemView.findViewById(R.id.player_ranking_score);
        }

    }
}
