package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import static com.squorpikkor.android.app.cubechronometer.Session.AVERAGE_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.LEFT_TIME;

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

    static final String START = "start";
    static final String STOP = "stop";
    static final String RESUME = "resume";
    static final String PAUSE = "pause";
    static final String SHOW_TIMES = "show_times";
    static final String END_OF_GAME = "end_of_game";
    static final String SHOW_AVERAGE_AND_LEFT_TIME = "show_values";


    private Session session;
    private Translator translator;
    private ArrayList<TextView> timeList;
    private HashMap<String, TextView> valueTextMap;

    /**
     * Idea is -- session size depends only of timeList size,
     * so i don't need to think of what size it should be --
     * session automatically get the right size
     */
    Controller(Context context, Chronometer chronometer, ArrayList<TextView> timeList, HashMap<String, TextView> valueTextMap) {
        this.chronometer = chronometer;
        this.timeList = timeList;
        this.valueTextMap = valueTextMap;
        session = new Session(context, timeList.size());
        translator = new Translator();
    }

    /**
     * Method for textView
     * It overloads base method for adding parameter without adding one to constructor
     * (to prevent unnecessarily large quantity of parameters)
     * Its for only one command -- SHOW_TIMES. If it will be needed more command, it should be
     * done with a sw1tch case
     */

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
                if(session.isEnds)getMethod(END_OF_GAME);
                getMethod(SHOW_AVERAGE_AND_LEFT_TIME);
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
                translator.arrayToText(session.getTimeList(), timeList);
                break;
            case END_OF_GAME:

                break;
            case SHOW_AVERAGE_AND_LEFT_TIME:
                translator.valueToText(session.simpleAverage(), valueTextMap.get(AVERAGE_TIME));
                translator.valueToText(session.leftTime(), valueTextMap.get(LEFT_TIME));
                break;


        }
    }

}
