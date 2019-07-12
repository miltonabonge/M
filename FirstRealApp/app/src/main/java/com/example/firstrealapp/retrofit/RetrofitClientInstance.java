package com.example.firstrealapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    //    declare and init our base url

    private static final String BASE_URL = "https://opentdb.com/";


    //    Declare TriviaService object
    private static Retrofit retrofit;

    //    create a proivTE constructor
    private RetrofitClientInstance(){}


    //    create a public static method to get instance of the TriviaService object
    public static Retrofit getRetrofit() {


//        this statement creates a new instance of TriviaService if the current instance is null
        if (retrofit== null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }
}
