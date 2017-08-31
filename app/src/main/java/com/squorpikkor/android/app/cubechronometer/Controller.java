package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import static com.squorpikkor.android.app.cubechronometer.Session.AVERAGE_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.LEFT_TIME;

/**
 * Created by Vadim on 27.08.2017.
 * This class for communicate classes.
 * It looks like method provider class
 */

class Controller {

    private Chronometer chronometer;
    private long stoppedTime = 0;
    Context context;

    PopupMenu popup;

    static final String START = "start";
    static final String STOP = "stop";
    static final String RESUME = "resume";
    static final String PAUSE = "pause";

    private static final String SHOW_TIMES = "show_times";
    private static final String END_OF_GAME = "end_of_game";
    private static final String SHOW_AVERAGE_AND_LEFT_TIME = "show_values";
    static final String START_THE_GAME = "start_the_game";
    private static final String LOOSER = "looser";
    private static final String RECORD = "record";

    private Session session;
    private Translator translator;
    private ArrayList<TextView> timeList;
    private HashMap<String, TextView> valueTextMap;
    private ImageButton imageButton;

    /**
     * Idea is -- session size depends only of timeList size,
     * so i don't need to think of what size it should be --
     * session automatically get the right size
     */
    Controller(Context context, Chronometer chronometer, ArrayList<TextView> timeList, HashMap<String, TextView> valueTextMap, ImageButton imageButton) {
        this.chronometer = chronometer;
        this.timeList = timeList;
        this.valueTextMap = valueTextMap;
        session = new Session(context, timeList.size());
        translator = new Translator();
        this.imageButton = imageButton;
    }

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
                getMethod(SHOW_AVERAGE_AND_LEFT_TIME);
                if(session.leftTime()<=0)getMethod(LOOSER);
                if(session.isEnds)getMethod(END_OF_GAME);
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
                imageButton.setEnabled(false);

                break;
            case START_THE_GAME:
                imageButton.setEnabled(true);
                session = new Session(context, timeList.size());
                break;
            case SHOW_AVERAGE_AND_LEFT_TIME:
                translator.valueToText(session.simpleAverage(), valueTextMap.get(AVERAGE_TIME));
                translator.valueToText(session.leftTime(), valueTextMap.get(LEFT_TIME));
                break;
            case LOOSER:

                break;
            case RECORD:

                break;


        }
    }

    void showMenu(Context con, View v) {
        popup = new PopupMenu(con, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        popup.show();
    }

    void showInfo(String infoText) {

    }

    public void theEndOfGameAlert (Context con){
        final AlertDialog.Builder alert = new AlertDialog.Builder(con);

        alert.setTitle("Среднее время = сек");
        alert.setMessage("Перезапустить?");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                getMethod(START_THE_GAME);
                dialog.cancel();
            }
        });

        alert.setNegativeButton("Ай, нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.show();
    }

}
