package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements Master_List_Fragment.OnItemClickListener {

//this next line if code acts like the memory of the clicks that are made from the user interface.
//    it virtually stores the info made via the clicks n then gives them to the switch.
    int headIndex=0,bodyIndex=0,legIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void OnItemClick(int position) {

        Toast.makeText(this, "Position Clicked In Main Activity"+position, Toast.LENGTH_SHORT).show();

//        0=Head, 1=Body, 2=Leg
        int IndexOfBodyPart=position/12;
        int indexPartClicked=position-12*IndexOfBodyPart;

        switch (IndexOfBodyPart){
            case 0: headIndex=indexPartClicked;
            break;

            case 1:bodyIndex=indexPartClicked;
            break;

            case 2: legIndex=indexPartClicked;
            break;
        }

        Bundle b= new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);

        final Intent i=new Intent (this,AndroidMeActivity.class);
        i.putExtras(b);

        findViewById(R.id.nextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }
}
