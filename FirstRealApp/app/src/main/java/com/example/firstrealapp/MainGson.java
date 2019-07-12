package com.example.firstrealapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainGson extends AppCompatActivity {
    private static final String TAG = "MainGson";
    QuestionAnsFragment questionAnsFragmen;
    StartGame startGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gson);

        startGame = new StartGame();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout, startGame, "FrameLayout")
                .commit();
    }
}
