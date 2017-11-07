package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 *
 */

public class ViewConstructor {

    private LinearLayout layout;
    private Context context;

    ViewConstructor(Context context, LinearLayout layout) {
        this.layout = layout;
        this.context = context;

    }

    void createTextViewFromArray(ArrayList<String> list) {
        for (String s : list) {
            TextView textView = new TextView(context);
            textView.setText(s);
            layout.addView(textView);
        }
    }



}
