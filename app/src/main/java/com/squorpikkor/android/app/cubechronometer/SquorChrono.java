package com.squorpikkor.android.app.cubechronometer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 * Chronometer with millis. It use outer textView to show value
 */

public class SquorChrono {

    private TextView textView;
    private Context context;
    private Activity activity;

    private String s;
    private long startTime;
    private long endTime;
    private boolean stopButtonNotPressed = true;

    SquorChrono(Context context, TextView textView, Activity activity) {
        this.textView = textView;
        this.context = context;
        this.activity = activity;
    }



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (stopButtonNotPressed) {
                Log.e("LOGG!!", "Came inside while cycle");
                try {
                    Thread.sleep(100);
                    Log.e("LOGG!!!", (System.currentTimeMillis() - startTime) + " ms");
                    s = (System.currentTimeMillis() - startTime)/100 + " ms";

//                    textView.setText(s);//////////////////////////////
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            textView.setText(s);
                        }
                    });


                    endTime = System.currentTimeMillis() - startTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e("LOGG!!", "Passing while cycle");
//            endTime = System.currentTimeMillis() - startTime;
        }
    };

    Thread thread;

        void start () {
            stopButtonNotPressed = true;
            thread = new Thread(runnable);
            thread.start();
        }

        void stop () {
            stopButtonNotPressed = false;
        }



    long getTime() {
        return System.currentTimeMillis();
    }




}
