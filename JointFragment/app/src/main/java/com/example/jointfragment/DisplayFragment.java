package com.example.jointfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jointfragment.R;

public class DisplayFragment extends Fragment {

    // Declare view from our layout(R.layout.fragment_display)
    private TextView display;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Declare and Init our layout and pass it into the view Object
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        // Init our view using the layout view object from above
        display = view.findViewById(R.id.tv_display);

        // return layout view so it can be loaded
        return view;
    }

    // This is a public method that will allow other classes to pass in a a string
    // to be displayed in the textview
    public void displayCount(String count) {
        display.setText(count);
    }
}