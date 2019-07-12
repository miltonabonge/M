package com.example.firstrealapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartGame extends Fragment {
    Button btn_startgame;


    public StartGame() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_start_game, container, false);

        btn_startgame = view.findViewById(R.id.btn_startgame);
        btn_startgame. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QuestionAnsFragment questionAnsFragmen = new QuestionAnsFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, questionAnsFragmen,"FrameLayout")
                        .commit();

            }
        });
        return view;


    }

}
