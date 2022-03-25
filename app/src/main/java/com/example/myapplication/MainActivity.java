package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    TextView flashCardQuestion;
    TextView flashCardAnswer;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayIndex = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AtomicBoolean isShowAnswers = new AtomicBoolean(false);


        flashCardQuestion = findViewById(R.id.flashcard_question);
        flashCardAnswer= findViewById(R.id.flashcard_answer);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        ImageView next_button = findViewById(R.id.next_button);



        if (allFlashcards!=null &&allFlashcards.size()>0){
            flashCardQuestion.setText(allFlashcards.get(0).getQuestion());
            flashCardAnswer.setText(allFlashcards.get(0).getAnswer());
        }

        TextView fcAnswer1 = findViewById(R.id.answer1);
        TextView fcAnswer2 = findViewById(R.id.answer2);
        TextView fcAnswer3 = findViewById(R.id.answer3);

        ImageView eye_open = findViewById(R.id.toggle_visibility);
        ImageView eye_close = findViewById(R.id.toggle_invisibility);

        //variable for add new card activity March 12

        ImageView add_card_activity = findViewById(R.id.add_activity);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());

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

        //add card activity March 16 2022
        add_card_activity.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, Add_Card_Actvity.class);
            MainActivity.this.startActivityForResult(intent, 100);
        });

        next_button.setOnClickListener(v->{

            if(allFlashcards.size()==0){
                return;
            }
            currentCardDisplayIndex ++;

            if(currentCardDisplayIndex>=allFlashcards.size()){
                //Snackbar.make(questionSideView,
                        //"You've reached the end of the cards, going back to start.", Snackbar.LENGTH_SHORT)
                        //.show();
                currentCardDisplayIndex=0;
            }
            allFlashcards = flashcardDatabase.getAllCards();
            Flashcard flashcard = allFlashcards.get(currentCardDisplayIndex);
            flashCardQuestion.setText(flashcard.getQuestion());
            flashCardAnswer.setText(flashcard.getAnswer());
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (null != data) {
                String question = data.getExtras().getString("question_key");
                String answer = data.getExtras().getString("answer_key");

                flashCardQuestion.setText(question);
                flashCardAnswer.setText(answer);

                flashcardDatabase.insertCard(new Flashcard(question, answer));
                allFlashcards= flashcardDatabase.getAllCards();

            }
        }
    }
}
