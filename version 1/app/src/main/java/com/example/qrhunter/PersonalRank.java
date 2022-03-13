package com.example.qrhunter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalRank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_qr_rank_layout);


        Intent intent = getIntent();
        //把传送进来的String类型的Message的值赋给新的变量message
        String message = intent.getStringExtra(OwnerMenuActivity.EXTRA_MESSAGE);
        //把布局文件中的文本框和textview链接起来
        TextView PersonalName = findViewById(R.id.personal_rank_TextView);
        //在textview中显示出来message
        PersonalName.setText(message);


    }
}
