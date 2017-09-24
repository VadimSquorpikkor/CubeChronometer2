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
    ArrayList<TextView> textList = new ArrayList<>();

    ArrayList<String> trylist = new ArrayList<>();
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        gameList = getIntent().getStringArrayListExtra("game_list");
        layout = (LinearLayout) findViewById(R.id.history_layout);
//        viewConstructor = new ViewConstructor(this, layout);

        trylist.add("uuu");
        trylist.add("uuu");
        trylist.add("uuu");

        Log.e(TAG, "HISTORY_ACTIVITY onCreate: " + gameList.size());
        createTextViewFromArray(gameList);



    }

    void createTextViewFromArray(ArrayList<String> list) {
        for (String s : list) {
            TextView textView = new TextView(this);
            textView.setText(s);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.history_text);
            }
//            textList.add(textView);
            layout.addView(textView);
        }
    }

}
