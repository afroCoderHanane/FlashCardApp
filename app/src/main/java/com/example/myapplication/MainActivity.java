package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AtomicBoolean isShowAnswers = new AtomicBoolean(false);


        TextView flashCardQuestion = findViewById(R.id.flashcard_question);
        TextView flashCardAnswer= findViewById(R.id.flashcard_answer);
        TextView fcAnswer1 = findViewById(R.id.answer1);
        TextView fcAnswer2 = findViewById(R.id.answer2);
        TextView fcAnswer3 = findViewById(R.id.answer3);

        ImageView eye_open = findViewById(R.id.toggle_visibility);
        ImageView eye_close = findViewById(R.id.toggle_invisibility);

        eye_open.setOnClickListener(v ->{
            eye_open.setImageResource(R.mipmap.eye_2_foreground);
            eye_open.setVisibility(View.INVISIBLE);
            eye_close.setVisibility(View.VISIBLE);
            isShowAnswers.set(true);
            if (isShowAnswers.get()==true){
                fcAnswer1.setVisibility(View.INVISIBLE);
                fcAnswer2.setVisibility(View.INVISIBLE);
                fcAnswer3.setVisibility(View.INVISIBLE);
            }
        });

        eye_close.setOnClickListener(v ->{
            eye_close.setImageResource(R.mipmap.eye_1_foreground);
            eye_close.setVisibility(View.INVISIBLE);
            eye_open.setVisibility(View.VISIBLE);
            isShowAnswers.set(false);
            if (isShowAnswers.get()==false){
                fcAnswer1.setVisibility(View.VISIBLE);
                fcAnswer2.setVisibility(View.VISIBLE);
                fcAnswer3.setVisibility(View.VISIBLE);
            }
        });


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
