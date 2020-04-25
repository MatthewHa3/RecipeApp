package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    //Declare variables for XML elements
    private String TAG = "ProfileActivity";
    private TextView points, level, rank;

    //Initialise Profile Activity
    //Call activity_profile to define UI
    //Retrieve widgets in activity_profile so program can interact
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        points = findViewById(R.id.tvPoints);
        level = findViewById(R.id.tvLevel);
        rank = findViewById(R.id.tvRank);
    }

    //Called for activity to start interacting with user
    //Defining ranks through levelNum
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPoints = getSharedPreferences("POINTS", MODE_PRIVATE);
        Log.d(TAG, String.valueOf(sharedPoints.getInt("points", 0)));
        points.setText(String.valueOf(sharedPoints.getInt("points", 0)));
        double levelNum = (double)(sharedPoints.getInt("points", 0))/1000;
        level.setText(String.valueOf(levelNum));
        if (levelNum <=1 && levelNum >= 0){
            rank.setText("Beginner");
        } else if (levelNum <=5 && levelNum > 1){
            rank.setText("Amateur");
        } else if (levelNum <=10 && levelNum > 5){
            rank.setText("Home Cook");
        } else if (levelNum <=20 && levelNum > 10){
            rank.setText("Sous Chef");
        } else if (levelNum <=30 && levelNum > 20){
            rank.setText("Executive Chef");
        } else if (levelNum <=50 && levelNum > 30){
            rank.setText("Master Chef");
        }
    }
}
