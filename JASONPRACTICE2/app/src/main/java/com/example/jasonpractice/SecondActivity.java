package com.example.jasonpractice;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.jasonpractice2.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String url = getIntent().getStringExtra("url");
        ImageView largePic=findViewById(R.id.Zoomed_Photo);
        Glide.with(this).load(url).into(largePic);

//        4;
    }
}
