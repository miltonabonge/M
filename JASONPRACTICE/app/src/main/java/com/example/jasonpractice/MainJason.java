package com.example.jasonpractice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JASONPRACTICE.R;
import com.noname.jsonpractice.ShibeAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainJason extends AppCompatActivity {
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
        new ShibeTask().execute("100");

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


    class ShibeTask extends AsyncTask<String, Void, List<String>> {


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
            shibeAdapter = new ShibeAdapter(strings);
            recyclerView.setAdapter(shibeAdapter);
        }
    }

}
