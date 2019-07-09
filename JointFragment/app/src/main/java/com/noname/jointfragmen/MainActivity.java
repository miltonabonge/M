package com.noname.jointfragmen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jointfragment.DisplayFragment;
import com.example.jointfragment.R;
import com.noname.buttontapper.ButtonFragment;

public class MainActivity extends AppCompatActivity implements ButtonFragment.OnFragmentInteractionListener {

    // Declare fragments
    DisplayFragment displayFragment; // Static Fragment
    ButtonFragment buttonFragment; // Dynamic Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Init the static fragment
        displayFragment = (DisplayFragment) getSupportFragmentManager().findFragmentById(R.id.container_a);

        // Init Dynamic fragment and load into framelayout
        buttonFragment = new ButtonFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_b, buttonFragment, "ButtonFragment")
                .commit();
    }

    @Override
    public void buttonClicked(String count) {
        displayFragment.displayCount(count);
    }
}