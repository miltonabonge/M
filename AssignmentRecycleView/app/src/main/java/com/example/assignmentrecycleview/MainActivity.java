package com.example.assignmentrecycleview;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AssignmentRecyclerView.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_simple_spinner_dropdown_item);
        Log.d(TAG, "onCreate: ");


        new AsyncTaskWithParams().execute();


    }

    private class AsyncTaskWithParams extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");

        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d(TAG, "doInBackground: ");
            int sec = Integer.valueOf(input );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        Runnable t = new Runnable() {
            @Override
            public void run() {

            }
        }

    }

//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            Log.d(TAG, "onPostExecute: ");

//        }

}
