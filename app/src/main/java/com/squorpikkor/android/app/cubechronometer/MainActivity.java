package com.squorpikkor.android.app.cubechronometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.squorpikkor.android.app.cubechronometer.BigButton.BLUE;
import static com.squorpikkor.android.app.cubechronometer.Controller.PAUSE;
import static com.squorpikkor.android.app.cubechronometer.Session.AVERAGE_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.BEST_AVERAGE_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.BEST_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.LEFT_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.WISH_TIME;

public class MainActivity extends AppCompatActivity{

    /**
     * Activity for 10 time competition
     */

    public static final String TAG = "LOG!!";

    ArrayList<TextView> timeTextList;
    ArrayList<TextView> valueTextList;
    HashMap<String, TextView> valueTextMap;

    Chronometer chronometer;

    ImageButton imageButton;
    ImageButton settingsButton;
    ImageButton infoButton;

    BigButton bigButton;

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
        valueTextList = new ArrayList<>();
        valueTextMap = new HashMap<>();

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        bigButton = new BigButton();

        settingsButton = (ImageButton) findViewById(R.id.settings);
        infoButton = (ImageButton)findViewById(R.id.info);

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

        valueTextList.add((TextView)findViewById(R.id.best_time_value));
        valueTextList.add((TextView)findViewById(R.id.best_average_value));
        valueTextList.add((TextView)findViewById(R.id.average_time_value));
        valueTextList.add((TextView)findViewById(R.id.wish_time_value));
        valueTextList.add((TextView)findViewById(R.id.left_time_value));

        valueTextMap.put(BEST_TIME, (TextView)findViewById(R.id.best_time_value));
        valueTextMap.put(BEST_AVERAGE_TIME, (TextView)findViewById(R.id.best_average_value));
        valueTextMap.put(AVERAGE_TIME, (TextView)findViewById(R.id.average_time_value));
        valueTextMap.put(WISH_TIME, (TextView)findViewById(R.id.wish_time_value));
        valueTextMap.put(LEFT_TIME, (TextView)findViewById(R.id.left_time_value));

        controller = new Controller(this, chronometer, timeTextList, valueTextMap, imageButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageButton:
                        bigButton.tapIt(imageButton);
                        Log.e(TAG, "onClick: buttonPressed");
                        controller.getMethod(bigButton.getCommand());//Do method which name button requesting
                        break;
                    case R.id.settings:
                        showPopup(v);
                        break;
                }
            }
        };

        imageButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        popup.show();
    }
}
