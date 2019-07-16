package com.example.firstrealapp.retrofit;

import com.example.firstrealapp.pojo.TriviaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaService {
    @GET("/api.php")
    Call<TriviaResponse>loadQuestions(
            @Query("amount") int amount,
            @Query("category") String category,
            @Query("difficulty") String level,
            @Query("type") String type
    );
}
