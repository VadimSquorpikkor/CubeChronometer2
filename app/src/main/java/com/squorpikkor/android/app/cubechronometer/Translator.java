package com.squorpikkor.android.app.cubechronometer;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vadim on 20.08.2017.
 * Translator class for send data between activity and Session
 */

class Translator{

    /*void arrayToText(ArrayList<Double> fromArray, ArrayList<TextView> toTextView) {
        int count = 0;
        for (Double d : fromArray) {
            toTextView.get(count).setText(String.valueOf(d));
            count++;
        }
    }*/

    void arrayToText(ArrayList<Double> fromArray, ArrayList<TextView> toTextView) {
        int count = 0;
        for (TextView text : toTextView) {
            if (fromArray.size() <= count) {
                text.setText(R.string.zero_value);
            } else {
                text.setText(String.valueOf(fromArray.get(count)));
            }
            count++;
        }
    }

    void valueToText(Double fromValue, TextView toText) {
        //String s = String.valueOf(fromValue);
        String s = String.format("%.2f", fromValue);
        toText.setText(s);
    }


}
