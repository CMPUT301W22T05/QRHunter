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

public class ScoreListOnpersonalrankPage extends ArrayAdapter<PersonalScoreOnrankpage> {
    private ArrayList<PersonalScoreOnrankpage> Scores;
    private Context context;

    public ScoreListOnpersonalrankPage(Context context,ArrayList<PersonalScoreOnrankpage> Scores){
        super (context, 0, Scores);
        this.Scores = Scores;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from (context).inflate(R.layout.personal_qr_rank_content_layout,parent,false);}

        PersonalScoreOnrankpage Score = Scores.get(position);


        TextView QRNumbers = view.findViewById(R.id.number_personal_QR);
        TextView PersonalSCore = view.findViewById(R.id.score_text);

        QRNumbers.setText(Score.getQRnumber());
        PersonalSCore.setText(Score.getPersonalmark());
        return view;



    }
}
