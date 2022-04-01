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

public class ScoreListOnPersonalrankPage extends ArrayAdapter<PersonalScoreOnPersonalrankPage> {
    private ArrayList<PersonalScoreOnPersonalrankPage> Score;
    private Context context;

    public ScoreListOnPersonalrankPage(Context context, ArrayList<PersonalScoreOnPersonalrankPage> Score) {
        super (context,0,Score);
        this.Score = Score;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.personal_qr_rank_content_layout, parent, false);
        }

        PersonalScoreOnPersonalrankPage score = Score.get(position);

        TextView Qrnumbers = view.findViewById(R.id.number_personal_QR);
        TextView Personalscores = view.findViewById(R.id.score_text);

        Qrnumbers.setText(score.getQRnumber());
        Personalscores.setText(score.getPersonalscore());

        return view;
    }


}
