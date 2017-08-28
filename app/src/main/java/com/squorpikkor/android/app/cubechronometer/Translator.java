package com.squorpikkor.android.app.cubechronometer;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vadim on 20.08.2017.
 * Translator class for send data between activity and Session
 */

class Translator{

    void ArrayToText(ArrayList<Double> fromArray, ArrayList<TextView> toTextView) {
        int count = 0;
        for (Double d : fromArray) {
            toTextView.get(count).setText(String.valueOf(d));
            count++;
        }
    }


}
