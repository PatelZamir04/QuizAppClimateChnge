package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView txtHighScore;

    private int highscore;

    Button BtnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnStart = findViewById(R.id.BtnStart);
        txtHighScore = findViewById(R.id.txtHighScore);
        loadHighScore();

        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startQuiz();
            }
        });

    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity2.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ) {
            if(resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity2.EXTRA_SCORE, 0);

                if(score > highscore) {
                    newHighScore(score);
                    highscore = score;
                }
            }
        }
    }


    //Loads previous highscore after opening app
    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        txtHighScore.setText("Highscore: " + 3);
    }

    //Set new highscore on main menu if achieved
    private void newHighScore(int HighScoreNew) {
        txtHighScore.setText("Highscore: " + HighScoreNew);

        //Saves highscore even if app closed
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, HighScoreNew);
        editor.apply();
    }
}
