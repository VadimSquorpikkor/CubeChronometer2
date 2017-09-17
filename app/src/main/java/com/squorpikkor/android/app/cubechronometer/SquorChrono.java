package com.squorpikkor.android.app.cubechronometer;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 * Chronometer with millis. It use outer textView to show value
 */

class SquorChrono {

    private TextView textView;
    private Activity activity;

    private String s;
//    private long startTime;
//    private long endTime;
    private long elapsedTime;

    private boolean stopButtonNotPressed = true;

    private int min;
    private int sec;
    private int millis;
    private int time;

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public int getMillis() {
        return millis;
    }

    SquorChrono(TextView textView, Activity activity) {
        this.textView = textView;
        textView.setText("0:00.0");
        this.activity = activity;
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis() - elapsedTime;//elapsed time is for pause
            while (stopButtonNotPressed) {
                Log.e("LOGG!!", "Came inside while cycle");
                try {
                    Thread.sleep(100);
                    time = (int) ((System.currentTimeMillis() - startTime) / 100);
                    millis = time % 10;
                    sec = time / 10 % 60;
                    String sSec;
                    if (sec < 10) {
                        sSec = "0" + sec;
                    } else {
                        sSec = "" + sec;
                    }
                    min = time / 10 / 60;
                    s = min + ":" + sSec + "." + millis;

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(s);
                        }
                    });

//                    endTime = System.currentTimeMillis() - startTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            elapsedTime = System.currentTimeMillis() - startTime;
        }
    };

    void start() {
        elapsedTime = 0;
        stopButtonNotPressed = true;
        Thread thread = new Thread(runnable);
        thread.start();
    }

    void stop() {
        stopButtonNotPressed = false;
    }

    void pause() {
        stopButtonNotPressed = false;
    }

    void resume() {
        stopButtonNotPressed = true;
        Thread thread = new Thread(runnable);
        thread.start();
    }


    double getTimeInSeconds() {
        return (double)time/10;
    }


}
