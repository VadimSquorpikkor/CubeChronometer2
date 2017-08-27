package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vadim on 20.08.2017.
 * Translator class for send data between activity and Session
 */

class Translator implements ICanTranslate {

    private Context context;
    private Session session = new Session(context, "10");

    Translator(Context context) {
        this.context = context;
    }

    @Override
    public void showTimes(ArrayList<TextView> textViewList) {
        int count = 0;
        ArrayList<Double> list = new ArrayList<>(session.getTimeList());
        for (TextView textView : textViewList) {
            textView.setText(String.valueOf(list.get(count)));
            count++;
        }
    }
}
