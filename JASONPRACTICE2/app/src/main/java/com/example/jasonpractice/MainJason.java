package com.example.jasonpractice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jasonpractice.Retrofit.RetrofitClientInstance;
import com.example.jasonpractice.Retrofit.ShibeService;
import com.example.jasonpractice2.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainJason extends AppCompatActivity implements ShibeAdapter.OnShibeClicked {
    private static final String TAG = "MainJason";

    private RecyclerView recyclerView;
    private ShibeAdapter shibeAdapter;
    Boolean brandonsboolean = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_jason);
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
//        new ShibeTask().execute("100");
//        volleyRequest(100);
        retrofitRequest(50);


        findViewById(R.id.flip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brandonsboolean == true) {
                    brandonsboolean = false;
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

                } else {
                    brandonsboolean = true;

                    recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));

                }
            }
        });

    }

    @Override
    public void shibeclicked(String url) {
        Toast.makeText(MainJason.this, url, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainJason.this, SecondActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);

//        to move from one activity to the other, you wanna use an intent!!!
//        so in the intent we used above, we are litteraly saying
//        1; im in MainJason (this) andi wanna GO TO the Second Activity which is a class
//        2; were in the SecondActivity, i want you to put this String calles url in the 2nd acrtivity
//        3; start the intent in the 2nd activity;
    }

    public void volleyRequest (int count) {

//        1; Setup url
        String baseURL = "http://shibe.online/api/shibes";
        String query = "?count=" + count;
        String url = baseURL + query;

//        2; Declare RequestQueue object instance and init it with Volley. newRequestQueue()
        RequestQueue requestQueue = Volley.newRequestQueue(MainJason.this);

//        3;Declare JsonArrayRequest or JsonObjectRequest (depepnds on )

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

//   to get our images loading using the volley, you have three main steps
//                        Step 1; create a list ;
                        List<String>url = new ArrayList<>();

                        for ( int i = 0; i< response. length(); i++) {
                            try {

//                           Step 2;     adding each resp to the loop
                                url.add(response.get(i).toString());
                                Log.d(TAG, "onResponse:" + response.get(i).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

//                Step 3;        passing the list of strings into the recycler method.
                        loadRecyclerView(url);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonArrayRequest);

    }


    private class ShibeTask extends AsyncTask<String, Void, List<String>> {


        @Override
        protected List<String> doInBackground(String... strings) {

            HttpURLConnection httpURLConnection = null;

            String baseURL = "http://shibe.online/api/shibes";
            String query = "?count=" + strings[0];
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(baseURL + query);


                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

//                Declare Bufferreader Object and init it with the inputStream
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line;
//                read each line from the  bufferReader object and append int our result (StringBuilder)
                while ((line = reader.readLine()) != null) {

                    result.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
            }

            Log.d(TAG, "doInBackground: " + result);


//            convert string(jason) to list<String>
//            we wanna get everything in the quotes so that thay will b out string

            String removeBrackets = result.substring(1, result.length() - 1);

            String removeQuotes = removeBrackets.replace("\"", "");

            String[] urls = removeQuotes.split(",");


            return Arrays.asList(urls);
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
           loadRecyclerView(strings);
        }


    }

    public void retrofitRequest (int count) {
//        declare ShibeService and initialize it using RetrofitClient Instance
        ShibeService shibeService = RetrofitClientInstance
                                    .getRetrofit()
                                    .create(ShibeService.class);
        
//        2; Declare ShibeService Return ttype and Init it using the ShibeService from step 1
        Call<List<String>> shibeCall =shibeService.loadShibes(count);
        
//        3; Use the shibeCall from step 2 and call the .enqueue method
        shibeCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {

                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: Success");
                    loadRecyclerView(response.body());

                } else {
                    Log.d(TAG, "onResponse: Failure");
                }
                
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void loadRecyclerView(List<String> strings) {
        shibeAdapter = new ShibeAdapter(strings,MainJason.this);
        recyclerView.setAdapter(shibeAdapter);
    }

}
