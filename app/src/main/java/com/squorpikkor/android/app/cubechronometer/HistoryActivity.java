package com.squorpikkor.android.app.cubechronometer;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {


    private static final String TAG = "LOG!!";
    ArrayList<String> gameList;
    ViewConstructor viewConstructor;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        gameList = getIntent().getStringArrayListExtra("game_list");
        layout = (LinearLayout) findViewById(R.id.history_layout);
        viewConstructor = new ViewConstructor(this, layout);

        viewConstructor.createTextViewFromArray(gameList);
    }

}
