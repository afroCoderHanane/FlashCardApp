package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        ImageView delete_button = findViewById(R.id.delete_button);


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
            int cx  = flashCardAnswer.getWidth()/2;
            int cy  = flashCardAnswer.getHeight()/2;

            float finalRadius = (float) Math.hypot(cx, cy);

            Animator anim= ViewAnimationUtils.createCircularReveal(flashCardAnswer,cx, cy, 0f,finalRadius);


            flashCardQuestion.setVisibility(View.INVISIBLE);
            flashCardAnswer.setVisibility(View.VISIBLE);

            anim.setDuration(3000);
            anim.start();

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
            overridePendingTransition(R.anim.add_activ_anim,R.anim.add_actic_anim_1);
        });

        next_button.setOnClickListener(v->{

            final Animation leftOutAnim= AnimationUtils.loadAnimation(v.getContext(), R.anim.add_activ_anim);
            final Animation rightOutAnim= AnimationUtils.loadAnimation(v.getContext(), R.anim.add_actic_anim_1);

            leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    flashCardQuestion.startAnimation(leftOutAnim);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    flashCardQuestion.startAnimation(rightOutAnim);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if(allFlashcards.size()==0){
                return;
            }
            currentCardDisplayIndex ++;

            if(currentCardDisplayIndex>=allFlashcards.size()){
                //Snackbar.make(questionSideView,
                        //"You've reached the end of the cards, going back to start.", Snackbar.LENGTH_SHORT)
                        //.show();
                Toast.makeText(getApplicationContext(),
                        "You've reached the end of the cards, going back to start.",
                        Toast.LENGTH_SHORT
                        ).show();
                currentCardDisplayIndex=0;
            }
            allFlashcards = flashcardDatabase.getAllCards();
            //animation

            next_button.startAnimation(leftOutAnim);

            Flashcard flashcard = allFlashcards.get(currentCardDisplayIndex);

            flashCardQuestion.setText(flashcard.getQuestion());
            flashCardAnswer.setText(flashcard.getAnswer());
        });

        delete_button.setOnClickListener(v -> {
            flashcardDatabase.deleteCard(flashCardQuestion.getText().toString());

//            if(allFlashcards.size()==0){
//                return;
//            }
//            currentCardDisplayIndex --;
//
//            if(currentCardDisplayIndex>=allFlashcards.size()){
//                //Snackbar.make(questionSideView,
//                //"You've reached the end of the cards, going back to start.", Snackbar.LENGTH_SHORT)
//                //.show();
//                Toast.makeText(getApplicationContext(),
//                        "You've reached the end of the cards, going back to start.",
//                        Toast.LENGTH_SHORT
//                ).show();
//                currentCardDisplayIndex=0;
//            }
//
//            allFlashcards=flashcardDatabase.getAllCards();
//            Flashcard flashcard = allFlashcards.get(currentCardDisplayIndex+1);
//            flashCardQuestion.setText(flashcard.getQuestion());
//            flashCardAnswer.setText(flashcard.getAnswer());
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
