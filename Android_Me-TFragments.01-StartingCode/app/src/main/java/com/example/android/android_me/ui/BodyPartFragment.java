package com.example.android.android_me.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BodyPartFragment extends Fragment {

    public static final String IMAGE_TO_LIST="image_id";
    public static final String LIST_INDEX="list_index";


    private static final String TAG = BodyPartFragment.class.getSimpleName();


    private List<Integer> mImageIds;

    private int mListIndex;


    public BodyPartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        if(savedInstanceState!=null)
        {
            mImageIds=savedInstanceState.getIntegerArrayList(IMAGE_TO_LIST);
            mListIndex=savedInstanceState.getInt(LIST_INDEX);
        }
        if (mImageIds != null && mImageIds.size() > 0) {

            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < mImageIds.size() - 1) {
                        mListIndex++;
                    } else {
                        mListIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));

                }
            });

        } else {
            Log.v(TAG, "This fragment has a full list of image id's");
        }

        return rootView;
    }


    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }


    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
outState.putIntegerArrayList(IMAGE_TO_LIST,(ArrayList<Integer>)mImageIds);
        super.onSaveInstanceState(outState);
    }
}
