package com.example.firstrealapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstrealapp.pojo.QuestionItem;
import com.example.firstrealapp.pojo.TriviaResponse;
import com.example.firstrealapp.retrofit.RetrofitClientInstance;
import com.example.firstrealapp.retrofit.TriviaService;

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
    Button btn_a, btn_b, btn_c, btn_d;
    TextView tv_question,AnsA, AnsB, AnsC, AnsD;

    public QuestionAnsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_display, container, false);
        View view = inflater.inflate(R.layout.fragment_question_ans,container,false);

        btn_a = view.findViewById(R.id.btn_a);
        btn_b = view.findViewById(R.id.btn_b);
        btn_c = view.findViewById(R.id.btn_c);
        btn_b = view.findViewById(R.id.btn_d);

        tv_question = view.findViewById(R.id.tv_question);

        AnsA = view.findViewById(R.id.AnsA);
        AnsB = view.findViewById(R.id.AnsB);
        AnsC = view.findViewById(R.id.AnsC);
        AnsD = view.findViewById(R.id.AnsD);


        return  view;



    }

    private void getTriviaQuestions(int count) {
        // 1: Declare and Initialize your Retrofit Service Interface Class
        TriviaService service = RetrofitClientInstance.getRetrofit().create(TriviaService.class);

        // 2: Declare Call class with the return type expected and Initialize it when the correct method using the Service
        Call<TriviaResponse> responseCall = service.loadQuestions(count);

        // 3: Trigger the network call
        responseCall.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(@NonNull Call<TriviaResponse> call, @NonNull Response<TriviaResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Success");
                    // Declare our expected Response Object and Initialize it when the
                    TriviaResponse triviaResponse = response.body();

                    if (triviaResponse != null)
                        questionItems = triviaResponse.getQuestionItem();


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

}
