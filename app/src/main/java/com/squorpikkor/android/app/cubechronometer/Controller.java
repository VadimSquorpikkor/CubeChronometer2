package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Vadim on 27.08.2017.
 * This class for communicate classes
 * It looks like method provider class
 * <p>
 * May be i should use class with interface, something like:
 * implements interface ICanControlView
 * with methods start etc?
 * Think its bad idea -- how can i use object links from activity class?
 */

class Controller {

    private Chronometer chronometer;
    private long stoppedTime = 0;
    //    private Context context;
    private Translator translator;

    static final String START = "start";
    static final String STOP = "stop";
    static final String RESUME = "resume";
    static final String PAUSE = "pause";
    static final String SHOW_TIMES = "show_times";


    private Session session;

    private ArrayList<TextView> timeList;

    /*Controller(Context context, Chronometer chronometer) {
        this.chronometer = chronometer;
        session = new Session(context, "10");
        translator = new Translator(context);
    }*/

    Controller(Context context, Chronometer chronometer, ArrayList<TextView> timeList) {
        this.chronometer = chronometer;
        this.timeList = timeList;
        session = new Session(context, timeList.size());
        translator = new Translator(context);
    }

    /*Controller(HashMap<String, Object> paramList) {

    }*/

    /**
     * Method for textView
     * It overloads base method for adding parameter without adding one to constructor
     * (to prevent unnecessarily large quantity of parameters)
     * Its for only one command -- SHOW_TIMES. If it will be needed more command, it should be
     * done with a sw1tch case
     */
    /*void getMethod(String command, ArrayList<TextView> textList) {
        switch (command) {
            case SHOW_TIMES:
                translator.showTimes(textList);
                break;
        }
    }*/

    void getMethod(String command) {
        switch (command) {
            case START:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case STOP:
                chronometer.stop();
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                long sec = (elapsedMillis / 1000);
                session.addTime(sec);
                getMethod(SHOW_TIMES);
                break;
            case RESUME:
                chronometer.setBase(SystemClock.elapsedRealtime() + stoppedTime);
                chronometer.start();
                break;
            case PAUSE:
                stoppedTime = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                break;
            case SHOW_TIMES:
                showTimes(timeList);
                break;


        }
    }

    public void showTimes(ArrayList<TextView> textViewList) {
        int count = 0;
        ArrayList<Double> list = new ArrayList<>(session.getTimeList());
        for (Double d : list) {
            textViewList.get(count).setText(String.valueOf(d));
            count++;
        }
    }
}
