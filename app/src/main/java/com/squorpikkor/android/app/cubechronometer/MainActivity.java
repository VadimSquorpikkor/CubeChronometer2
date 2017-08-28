package com.squorpikkor.android.app.cubechronometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.squorpikkor.android.app.cubechronometer.BigButton.BLUE;
import static com.squorpikkor.android.app.cubechronometer.Controller.PAUSE;
import static com.squorpikkor.android.app.cubechronometer.Controller.SHOW_TIMES;

public class MainActivity extends AppCompatActivity {

    /**
     * Activity for 10 time competition
     */

    public static final String TAG = "LOG!!";

    ArrayList<TextView> timeTextList;
    //Session session;
    Chronometer chronometer;

    ImageButton imageButton;
    BigButton bigButton;

    ICanTranslate iCanTranslate;

    Controller controller;

    @Override
    protected void onPause() {
        super.onPause();

        if (bigButton.getCondition().equals(BLUE)) {
            bigButton.freezeIt(imageButton);
            controller.getMethod(PAUSE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextList = new ArrayList<>();
        //session = new Session(this, "10");
        chronometer = (Chronometer) findViewById(R.id.chronometer);


        imageButton = (ImageButton) findViewById(R.id.imageButton);
        bigButton = new BigButton();

        iCanTranslate = new Translator(this);

        controller = new Controller(this, chronometer, timeTextList);

        timeTextList.add((TextView) findViewById(R.id.time1));
        timeTextList.add((TextView) findViewById(R.id.time2));
        timeTextList.add((TextView) findViewById(R.id.time3));
        timeTextList.add((TextView) findViewById(R.id.time4));
        timeTextList.add((TextView) findViewById(R.id.time5));
        timeTextList.add((TextView) findViewById(R.id.time6));
        timeTextList.add((TextView) findViewById(R.id.time7));
        timeTextList.add((TextView) findViewById(R.id.time8));
        timeTextList.add((TextView) findViewById(R.id.time9));
        timeTextList.add((TextView) findViewById(R.id.time10));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageButton:
                        bigButton.tapIt(imageButton);
                        Log.e(TAG, "onClick: buttonPressed");
                        controller.getMethod(bigButton.getCommand());//Do method which name button requesting
                        //controller.getMethod(SHOW_TIMES, timeTextList);


                }
            }
        };

        imageButton.setOnClickListener(listener);
    }


    /*void showTime() {
        controller.getMethod(SHOW_TIMES, timeTextList);
    }*/

    /*void showTimes() {
        iCanTranslate.showTimes(timeTextList);
    }*/

    /*public void showTimes() {
        int count = 0;
        ArrayList<Double> list = new ArrayList<>(session.getTimeList());
        for (TextView textView : timeTextList) {
            textView.setText(String.valueOf(list.get(count)));
            count++;
        }
    }*/


}
