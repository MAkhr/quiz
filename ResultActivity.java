package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);


        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        //トータルスコアの呼び出し
        SharedPreferences prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = prefs.getInt("totalScore",0);

        //トータルスコアに今のスコア加算
        totalScore += score;

        //表示
        resultLabel.setText(score + " / 7");

        if (score <= 2){
            totalScoreLabel.setText("あなたは都会人!!");
        }
        if (score == 3) {
            totalScoreLabel.setText("あなたは隠れ都会人!!");
        }
        if (score == 4){
            totalScoreLabel.setText("あなたは隠れ山形人!!");
        }
        if (score >= 5){
            totalScoreLabel.setText("あなたは山形県民!!");
        }
        //totalScoreLabel.setText("トータルスコア　:" + totalScore);

        //スコア保存
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", score);
        editor.apply();
    }

    public void returnTop(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}
