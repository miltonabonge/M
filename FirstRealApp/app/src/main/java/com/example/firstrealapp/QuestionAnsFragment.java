package com.example.firstrealapp;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstrealapp.pojo.QuestionItem;
import com.example.firstrealapp.pojo.TriviaResponse;
import com.example.firstrealapp.retrofit.RetrofitClientInstance;
import com.example.firstrealapp.retrofit.TriviaService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionAnsFragment extends Fragment {
    private static final String TAG = "QuestionAnsFragment";
    private List<QuestionItem> questionItems;
    private Button btn_a, btn_b, btn_c, btn_d, btn_restart;
    private TextView tv_question, ansA, ansB, ansC, ansD;

    private int currentQuestionIndex =0;
    List<String> choices = new ArrayList<>();
    private String correctAnswer = "";
    public QuestionAnsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

      }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_ans, container, false);

        btn_a = view.findViewById(R.id.btn_a);
        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                this line of code is saying if i click btn_a, select ans a and get check if this answer is correct or wrong.
                checkAnswer(ansA.getText().toString());
                loadQuestion();
//                Toast.makeText(getContext(), "btn_a", Toast.LENGTH_SHORT).show();
//                currentQuestionIndex++;
            }
        });

        btn_b = view.findViewById(R.id.btn_b);
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(ansB.getText().toString());
                loadQuestion();
//                Toast.makeText(getContext(), "btn_b", Toast.LENGTH_SHORT).show();
//                currentQuestionIndex++;
            }
        });
        btn_c = view.findViewById(R.id.btn_c);
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(ansC.getText().toString());
                loadQuestion();
//                Toast.makeText(getContext(), "btn_c", Toast.LENGTH_SHORT).show();
//                currentQuestionIndex++;


            }
        });
        btn_d = view.findViewById(R.id.btn_d);
        btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(ansD.getText().toString());
                loadQuestion();
//                Toast.makeText(getContext(), "btn_d", Toast.LENGTH_SHORT).show();
//                currentQuestionIndex++;
            }
        });

        btn_restart = view.findViewById(R.id.btn_RESTART);
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "btn_Restart", Toast.LENGTH_SHORT).show();
//                Fragment fragment = new Fragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.layout.fragment_set_game);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            }
        });

        tv_question = view.findViewById(R.id.tv_question);

        ansA = view.findViewById(R.id.ansA);
        ansB = view.findViewById(R.id.ansB);
        ansC = view.findViewById(R.id.ansC);
        ansD = view.findViewById(R.id.ansD);

        return view;
    }

    void loadAllQuestions(String category, String level) {
        getTriviaQuestions(5, category, level);
    }


    private void getTriviaQuestions(final int count, String category, String level) {
        // 1: Declare and Initialize your Retrofit Service Interface Class
        TriviaService service = RetrofitClientInstance.getRetrofit().create(TriviaService.class);

        // 2: Declare Call class with the return type expected and Initialize it when the correct method using the Service
        Call<TriviaResponse> responseCall = service.loadQuestions(count, category, level, "multiple");

        // 3: Trigger the network call
        responseCall.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(@NonNull Call<TriviaResponse> call, @NonNull Response<TriviaResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Success");
                    // Declare our expected Response Object and Initialize it when the
                    TriviaResponse triviaResponse = response.body();

                    if (triviaResponse != null) {
                        questionItems = triviaResponse.getQuestionItem();
                        loadQuestion();
                    }


                } else {
                    Log.d(TAG, "onResponse: Failed");
                }

            }

            @Override
            public void onFailure(Call<TriviaResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestionIndex >= questionItems.size()) {
            //This means there are no more questions
            btn_restart.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
        } else {

            QuestionItem questionItem = questionItems.get(currentQuestionIndex);
            btn_restart.setVisibility(View.GONE);

            // Create choices and shuffle
            choices.add(questionItem.getCorrectAnswer());
            choices.addAll(questionItem.getIncorrectAnswers());
            Collections.shuffle(choices);

            // Set correct answer
            correctAnswer = questionItem.getCorrectAnswer();

            // Load questions
            tv_question.setText(questionItem.getQuestion());


            String one = choices.get(0);
            String two = choices.get(1);
            String three = choices.get(2);
            String four = choices.get(3);
            // Load answers
            setAnswers(one, two, three, four);


            currentQuestionIndex++;
        }
    }

    public void setAnswers(String one, String two, String three, String four) {
        ansA.setText(one);
        ansB.setText(two);
        ansC.setText(three);
        ansD.setText(four);
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer == correctAnswer) {
            // This runs if you selected the right answer
            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();


        } else {
            // This runs if you selected the wrong answer
            Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();

        }
    }
}
