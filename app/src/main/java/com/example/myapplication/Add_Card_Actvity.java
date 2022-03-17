package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Add_Card_Actvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_actvity);

        ImageView cancel_button = findViewById(R.id.cancel_button);
        ImageView save_button = findViewById(R.id.save_button);


        cancel_button.setOnClickListener(v->{
            finish();
        });

        save_button.setOnClickListener(view -> {
            Intent data = new Intent();

            String input_question= ((EditText) findViewById(R.id.question_entry)).getText().toString();
            String input_answer= ((EditText) findViewById(R.id.answer_entry)).getText().toString();

            data.putExtra("question_key", input_question);
            data.putExtra("answer_key", input_answer);
            setResult(RESULT_OK, data);
            finish();
        });

    }
}