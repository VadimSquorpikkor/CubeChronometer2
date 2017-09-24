package com.squorpikkor.android.app.cubechronometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.squorpikkor.android.app.cubechronometer.BigButton.RED;
import static com.squorpikkor.android.app.cubechronometer.Controller.OPEN_HISTORY_ACTIVITY;
import static com.squorpikkor.android.app.cubechronometer.Controller.PAUSE;
import static com.squorpikkor.android.app.cubechronometer.Controller.RESTART_ALERT;
import static com.squorpikkor.android.app.cubechronometer.Controller.SET_WISH_TIME_ALERT;
import static com.squorpikkor.android.app.cubechronometer.Controller.START_THE_GAME;
import static com.squorpikkor.android.app.cubechronometer.SaveLoadController.LOAD_HISTORY;
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

    TextView chronoTextView;

    ImageButton imageButton;
    ImageButton settingsButton;
    ImageButton infoButton;
    ImageButton restartButton;
    ImageButton historyButton;
    TextView wishTimeButton;

    BigButton bigButton;

    Controller controller;

    @Override
    protected void onPause() {
        super.onPause();

        if (bigButton.getCondition().equals(RED)) {
            bigButton.freezeIt(imageButton);
            controller.getMethod(PAUSE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        controller.getMethod(LOAD_SESSION);
        Log.e(TAG, "onResume: LOAD");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextList = new ArrayList<>();
        valueTextList = new ArrayList<>();
        valueTextMap = new HashMap<>();

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        infoButton = (ImageButton) findViewById(R.id.info);
        restartButton = (ImageButton) findViewById(R.id.restart);
        settingsButton = (ImageButton) findViewById(R.id.settings);
        historyButton = (ImageButton) findViewById(R.id.history);
        wishTimeButton = (TextView)findViewById(R.id.wish_time_value);
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

        chronoTextView = (TextView)findViewById(R.id.chronoTextView);

        controller = new Controller(this, timeTextList, valueTextMap, imageButton, chronoTextView, this);
//        saveLoadController = new SaveLoadController(this);

        controller.getMethod(LOAD_HISTORY);
        controller.getMethod(START_THE_GAME);
//        Log.e(TAG, "onCreate: gameHistory:" + controller.getMethod(););
//        controller.getMethod(LOAD_SESSION);

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
                        controller.showMenu(v);
//                        controller.getMethod(LOOSER);
                        break;
                    case R.id.info:
                        controller.showInfo(R.string.info_text_for_10_times);
                        break;
                    case R.id.restart:
                        controller.getMethod(RESTART_ALERT);
                        break;
                    case R.id.history:
                        controller.getMethod(OPEN_HISTORY_ACTIVITY);
                        break;
                    case R.id.wish_time_value:
                        controller.getMethod(SET_WISH_TIME_ALERT);
                        break;
                }
            }
        };

        imageButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);
        infoButton.setOnClickListener(listener);
        restartButton.setOnClickListener(listener);
        historyButton.setOnClickListener(listener);
        wishTimeButton.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e(TAG, "onCreateOptionsMenu: Menu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_about:
                Log.e(TAG, "onOptionsItemSelected: ABOUT");
//                controller.getMethod(OPEN_ABOUT_ACTIVITY);
                return true;
            case R.id.menu_restart:
                controller.getMethod(START_THE_GAME);
                return true;
            case R.id.menu_set_average:
                controller.getMethod(SET_WISH_TIME_ALERT);
                return true;
            case R.id.menu_min_time:
                Log.e(TAG, "onOptionsItemSelected: ABOUT");
                return true;
        }

        return super.onOptionsItemSelected(item);

    }


}
