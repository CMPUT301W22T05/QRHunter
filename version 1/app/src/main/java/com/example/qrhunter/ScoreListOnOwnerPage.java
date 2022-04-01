package com.example.qrhunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ScoreListOnOwnerPage extends ArrayAdapter<TotalScoreOnOwnerPage> {
    private ArrayList<TotalScoreOnOwnerPage> scores;
    private Context context;

    public ScoreListOnOwnerPage(Context context, ArrayList<TotalScoreOnOwnerPage> scores) {
        super (context, 0, scores);
        this.scores = scores;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.personal_qr_rank_content_layout, parent, false);
        }

        TotalScoreOnOwnerPage score = scores.get(position);

        TextView UserName = view.findViewById(R.id.number_personal_QR);
        TextView TotalScore = view.findViewById(R.id.score_text);

        UserName.setText(score.getUsername());
        TotalScore.setText(score.getTotalscore());

        return view;
    }


}
