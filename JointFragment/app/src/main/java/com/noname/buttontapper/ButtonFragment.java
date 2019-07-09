package com.noname.buttontapper;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jointfragment.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.Unbinder;

public class ButtonFragment extends Fragment {

    // Declare count variable
    private int count;
    // Declare Unbinder for butterknife
    private Unbinder unbinder;
    // Declare OnFragmentInteractionListener Interface
    private OnFragmentInteractionListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        unbinder = ButterKnife.bind(this, view); // init unbinder by setting up butterknife bind
        count = 0; // init count as 0
        return view;
    }

    @OnClick(R.id.OK)
    void buttonTap() {
        // This will increment the int count by 1
        count++;
        sendCountToListener();

    }

    private void sendCountToListener() {
        // This converts our int count to a String
        String stringCount = String.valueOf(count);

        // send to display fragment
        listener.buttonClicked(stringCount);
    }

    @OnLongClick(R.id.OK)
    void resetCounter() {
        count = 0;
        sendCountToListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void buttonClicked(String count);
    }
}