package com.squorpikkor.android.app.cubechronometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

/**
 * Activity for 10 time competition
 * */

    ArrayList<TextView> timeTextList= new ArrayList<>();
    Session session;
    Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session("10");
        chronometer = new Chronometer(this);


    }


    public void showTimes() {
        int count = 0;
        ArrayList<Double> list = new ArrayList<>(session.getTimeList());
        for (TextView textView : timeTextList) {
            textView.setText(String.valueOf(list.get(count)));
            count++;
        }
    }



}
