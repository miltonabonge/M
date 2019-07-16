package com.example.firstrealapp;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetGameFragment extends Fragment {
    private Button btn_play;
   private Spinner category, difficulty;
    private TextView tv_set_game;
    private OnFragmentInteractionListener listener;

    private static final String TAG = "SetGameFragments";

    public SetGameFragment() {
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

        View view= inflater.inflate(R.layout.fragment_set_game, container, false);


        btn_play = view.findViewById(R.id.btn_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int levelPosition = difficulty.getSelectedItemPosition();
//                int categoryPosition = category.getSelectedItemPosition();
                String levelChoice = difficulty.getSelectedItem().toString().toLowerCase();
                String categoryChoice = category.getSelectedItem().toString();
                String categoryNumber = GameUtils.getCategoryNumber(categoryChoice);
                Log.d(TAG, "onClick: " + levelChoice);
//                String categoryNumber = Locale.Category.getCategoryNumber.(categoryPosition).toString();


                listener.loadQuestionAnsFragment(categoryNumber, levelChoice);

            }
        });
        category = view.findViewById(R.id.category);
        difficulty = view.findViewById(R.id.difficulty);
        tv_set_game = view.findViewById(R.id.tv_set_game);
        return view;
    }


    interface OnFragmentInteractionListener {
        void loadQuestionAnsFragment(String category, String level);
    }
}
