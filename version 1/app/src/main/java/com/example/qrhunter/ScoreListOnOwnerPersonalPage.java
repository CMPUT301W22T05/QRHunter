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

public class ScoreListOnOwnerPersonalPage extends ArrayAdapter<ScoreOnOwnerPersonalPage> {
    private ArrayList<ScoreOnOwnerPersonalPage> scores;
    private Context context;

    public ScoreListOnOwnerPersonalPage(Context context, ArrayList<ScoreOnOwnerPersonalPage> scores) {
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

        ScoreOnOwnerPersonalPage score = scores.get(position);

        TextView SequenceNumber = view.findViewById(R.id.number_personal_QR);
        TextView Score = view.findViewById(R.id.score_text);

        SequenceNumber.setText(score.getSequenceNumber());
        Score.setText(score.getScore());

        return view;
    }
}
