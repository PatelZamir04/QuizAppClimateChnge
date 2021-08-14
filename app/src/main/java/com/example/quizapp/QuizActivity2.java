package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuizActivity2 extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";


    private List<Questions> QuestionList;
    private Questions CurrentQ;

    private TextView txtQuestion, txtPoints, txtQNum;
    private TextView txtTimer;
    private RadioGroup RBtnGroup;
    private RadioButton RBtn1, RBtn2, RBtn3, RBtn4;
    private Button BtnNxt;

    int totalQuestions = 0;
    int i = 0;
    int points = 0;



    ColorStateList RBtnDefaultColour;

    boolean OptionChosen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);


        //Creates list of questions that can be asked in quiz
        QuestionList = new ArrayList<>();
        AddQuestions();
        totalQuestions = QuestionList.size();

        txtQuestion = findViewById(R.id.txtQuestion);
        txtPoints = findViewById(R.id.txtPoints);
        txtQNum = findViewById(R.id.txtQNum);
        txtTimer = findViewById(R.id.txtTimer);

        RBtnGroup = findViewById(R.id.RBtnGroup);
        RBtn1 = findViewById(R.id.RBtn1);
        RBtn2 = findViewById(R.id.RBtn2);
        RBtn3 = findViewById(R.id.RBtn3);
        RBtn4 = findViewById(R.id.RBtn4);


        BtnNxt = findViewById(R.id.BtnNext);

        RBtnDefaultColour = RBtn1.getTextColors();

        ShowNextQuestion();


        BtnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checks if user clicked on radiobutton
                if(OptionChosen == false) {
                    if (RBtn1.isChecked() || RBtn2.isChecked() || RBtn3.isChecked() || RBtn4.isChecked())
                        checkAnswer();
                    else
                        Toast.makeText(QuizActivity2.this, "Please select an option", Toast.LENGTH_SHORT).show();
                } else {
                    ShowNextQuestion();
                    OptionChosen = false;
                }



            }
        });

    }


    //Checks if user answer is  correct
    private void checkAnswer() {
        OptionChosen = true;


        RadioButton RbSelected = findViewById(RBtnGroup.getCheckedRadioButtonId());
        int AnswerNo = RBtnGroup.indexOfChild(RbSelected) + 1;

        //If is correct, add 1 point to score
        if(AnswerNo == CurrentQ.getAnswer()) {
            points++;
            txtPoints.setText("Points: " + points);
        }

        //Set incorrect answers to red and correct answer to red
        RBtn1.setTextColor(Color.RED);
        RBtn2.setTextColor(Color.RED);
        RBtn3.setTextColor(Color.RED);
        RBtn4.setTextColor(Color.RED);

        switch (CurrentQ.getAnswer()) {
            case 1:
                RBtn1.setTextColor(Color.GREEN);
                break;
            case 2:
                RBtn2.setTextColor(Color.GREEN);
                break;
            case 3:
                RBtn3.setTextColor(Color.GREEN);
                break;
            case 4:
                RBtn4.setTextColor(Color.GREEN);
                break;
        }

        if(i < 3)
            BtnNxt.setText("Next");
        else
            BtnNxt.setText("End Quiz");

    }


    //Displays next question and answers
    private void ShowNextQuestion() {

        RBtnGroup.clearCheck();
        RBtn1.setTextColor(RBtnDefaultColour);
        RBtn2.setTextColor(RBtnDefaultColour);
        RBtn3.setTextColor(RBtnDefaultColour);
        RBtn4.setTextColor(RBtnDefaultColour);


        //when x number of questions asked, end quiz
        if (i < 3) {
            int RandomNum = 0;


            RandomNum = (int) (Math.random() * totalQuestions + 1);

            Questions QNum = QuestionList.get(RandomNum);


                CurrentQ = QuestionList.get(RandomNum);
                txtQuestion.setText(CurrentQ.getQuestion());
                RBtn1.setText(CurrentQ.getOption1());
                RBtn2.setText(CurrentQ.getOption2());
                RBtn3.setText(CurrentQ.getOption3());
                RBtn4.setText(CurrentQ.getOption4());
                i++;

                BtnNxt.setText("Submit");
                txtQNum.setText("Question " + i + "/3");

        } else
            endQuiz();



    }

    //List of questions asked in quiz
    public void AddQuestions() {
        QuestionList.add(new Questions("What is 2 + 2?", "2", "10", "4", "6", 3 ));
        QuestionList.add(new Questions("Is 1 a number?", "Yes", "No", "Not at all", "I'm not sure", 1 ));
        QuestionList.add(new Questions("Is climate change real?", "No", "Yes", "Maybe", "Its fake news", 2 ));
        QuestionList.add(new Questions("Choose Option C", "A", "B", "C", "D", 3 ));
        QuestionList.add(new Questions("Choose option B", "B", "Q", "4", "V", 1 ));
        QuestionList.add(new Questions("What is the Best food?", "Nachos", "Tacos", "Pizza", "Burger", 3 ));
        QuestionList.add(new Questions("Is Chelsea the best Football team?", "No", "Yes", "Maybe", "Not at all", 2 ));
        QuestionList.add(new Questions("Did I eat breakfast this morning?", "No", "No", "No", "Yes", 4 ));
        QuestionList.add(new Questions("What is 2 + 10?", "12", "45", "-4", "0", 1 ));
        QuestionList.add(new Questions("Random Question #10", "Wrong", "Correct", "Wrong", "Wrong", 2 ));

    }

    private void endQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, points);
        setResult(RESULT_OK, resultIntent);
        finish();

    }



}