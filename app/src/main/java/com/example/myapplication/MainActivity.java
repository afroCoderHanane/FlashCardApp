package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashCardQuestion = findViewById(R.id.flashcard_question);
        TextView flashCardAnswer= findViewById(R.id.flashcard_answer);


        flashCardQuestion.setOnClickListener(v -> {

                flashCardQuestion.setVisibility(View.INVISIBLE);
                flashCardAnswer.setVisibility(View.VISIBLE);
        });

        flashCardAnswer.setOnClickListener(v -> {

            flashCardAnswer.setVisibility(View.INVISIBLE);
            flashCardQuestion.setVisibility(View.VISIBLE);
        });
    }
}