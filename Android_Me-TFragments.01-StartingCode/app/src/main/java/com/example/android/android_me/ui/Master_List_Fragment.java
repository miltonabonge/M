package com.example.android.android_me.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * A simple {@link Fragment} subclass.
 */
public class Master_List_Fragment extends Fragment {

    OnItemClickListener listener;


    public interface OnItemClickListener
    {
        void OnItemClick(int position);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(OnItemClickListener)getActivity();
        }catch (ClassCastException Ex)
        {
            throw new ClassCastException(getContext().toString()+"should be an instance of OnItemClickListener");

        }
    }


//    public Master_List_Fragment() {
//        // Required empty public constructor
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView gridView= view.findViewById(R.id.master_list_grid_view);
        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.OnItemClick(position);
            }
        });

        return view;
    }

}
