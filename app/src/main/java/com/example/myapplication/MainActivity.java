package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView flashCardQuestion = findViewById(R.id.flashcard_question);
        TextView flashCardAnswer= findViewById(R.id.flashcard_answer);
        TextView fcAnswer1 = findViewById(R.id.answer1);
        TextView fcAnswer2 = findViewById(R.id.answer2);
        TextView fcAnswer3 = findViewById(R.id.answer3);

        flashCardQuestion.setOnClickListener(v -> {

            flashCardQuestion.setVisibility(View.INVISIBLE);
            flashCardAnswer.setVisibility(View.VISIBLE);
        });

        flashCardAnswer.setOnClickListener(v -> {

            flashCardAnswer.setVisibility(View.INVISIBLE);
            flashCardQuestion.setVisibility(View.VISIBLE);
        });

        fcAnswer1.setOnClickListener(v ->{

                fcAnswer1.setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
                fcAnswer3.setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
                fcAnswer2.setBackgroundColor(getResources().getColor(R.color.neutral_fc, null));
        });
        fcAnswer2.setOnClickListener(v ->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fcAnswer2.setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
                fcAnswer3.setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
                fcAnswer1.setBackgroundColor(getResources().getColor(R.color.neutral_fc, null));
            }
        });
        fcAnswer3.setOnClickListener(v ->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fcAnswer3.setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
                fcAnswer2.setBackgroundColor(getResources().getColor(R.color.neutral_fc, null));
                fcAnswer1.setBackgroundColor(getResources().getColor(R.color.neutral_fc, null));
            }
        });
    }
}
