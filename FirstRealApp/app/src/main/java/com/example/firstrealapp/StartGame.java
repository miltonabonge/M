package com.example.firstrealapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartGame extends Fragment {
    private Button btn_startgame;
    private ImageView iv_logo;
    private OnFragmentInteractionListener listener;

    public StartGame() {
        // Required empty public constructor

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_game, container, false);

        // to set up an image from the drawable folder, you folle the two steps below;
        iv_logo = view.findViewById(R.id.iv_logo);
        iv_logo.setImageResource(R.drawable.trivia_crush_logo);


        btn_startgame = view.findViewById(R.id.btn_startgame);
        btn_startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.startGame();
            }
        });
        return view;


    }

    interface OnFragmentInteractionListener {
        void startGame();
    }

}
