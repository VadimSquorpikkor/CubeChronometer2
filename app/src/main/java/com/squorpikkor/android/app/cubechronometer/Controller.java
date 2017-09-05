package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;
import static com.squorpikkor.android.app.cubechronometer.SaveLoadController.LOAD_HISTORY;
import static com.squorpikkor.android.app.cubechronometer.SaveLoadController.LOAD_SESSION;
import static com.squorpikkor.android.app.cubechronometer.SaveLoadController.SAVE_HISTORY;
import static com.squorpikkor.android.app.cubechronometer.SaveLoadController.SAVE_SESSION;
import static com.squorpikkor.android.app.cubechronometer.Session.AVERAGE_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.BEST_AVERAGE_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.BEST_TIME;
import static com.squorpikkor.android.app.cubechronometer.Session.LEFT_TIME;


/**
 * Created by Vadim on 27.08.2017.
 * This class for communicate classes.
 * It looks like method provider class
 */

class Controller {

    private Chronometer chronometer;
    private long stoppedTime = 0;
    private Context context;
    private long sec;

    static final String START = "start";
    static final String STOP = "stop";
    static final String RESUME = "resume";
    static final String PAUSE = "pause";

    private static final String SHOW_TIMES = "show_times";
    private static final String END_OF_GAME = "end_of_game";
    private static final String SHOW_AVERAGE_AND_LEFT_TIME = "show_values";
    private static final String SAVE_HISTORY_AND_SET_BEST_TIME = "save_history_and_set_best_time";
    static final String RESTART_ALERT = "restart_alert";
    static final String START_THE_GAME = "start_the_game";
    private static final String SHOW_BEST_AVERAGE_AND_BEST_TIME = "show_best_average_and_best_time";
    private static final String LOOSER = "looser";
    private static final String RECORD = "record";
    private static final String BEST_TIME_ALERT = "best_time_alert";

    private Session session;
    private Translator translator;
    private ArrayList<TextView> timeList;
    private HashMap<String, TextView> valueTextMap;
    private ImageButton imageButton;
    private Dialog dialog;
    private GameHistory gameHistory;
    private SaveLoadController saveLoadController;

    /**
     * Idea is -- session size depends only of timeList size,
     * so i don't need to think of what size it should be --
     * session automatically get the right size
     */
    Controller(Context context, Chronometer chronometer, ArrayList<TextView> timeList, HashMap<String, TextView> valueTextMap, ImageButton imageButton) {
        this.context = context;
        this.chronometer = chronometer;
        this.timeList = timeList;
        this.valueTextMap = valueTextMap;
        session = new Session(timeList.size());
        translator = new Translator();
        this.imageButton = imageButton;
        dialog = new Dialog(context, this);
        gameHistory = new GameHistory();
        saveLoadController = new SaveLoadController(context, gameHistory, session);
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
                sec = (elapsedMillis / 1000);
                session.addTime(sec);
                if (sec < gameHistory.getBestTime()) getMethod(BEST_TIME_ALERT);
                getMethod(SHOW_TIMES);
                getMethod(SHOW_AVERAGE_AND_LEFT_TIME);
                if (session.leftTime() <= 0 && !session.isEnds) getMethod(LOOSER);
                if (session.isEnds) getMethod(END_OF_GAME);
                Log.e(TAG, "WISH: " + session.getWishTime());
                Log.e(TAG, "SLOWEST: " + session.getSlowest());
                Log.e(TAG, "SESSION SIZE: " + session.getSessionSize());
                Log.e(TAG, "FASTEST: " + session.getFastest());
//                Log.e(TAG, "ADVANCED AV: " + session.advancedAverage());//do not workings. Crashes
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
                if(session.simpleAverage() < gameHistory.getBestAverageTen())getMethod(RECORD);
                imageButton.setEnabled(false);
                break;
            case START_THE_GAME:
                imageButton.setEnabled(true);
                session = new Session(timeList.size());
                chronometer.setBase(SystemClock.elapsedRealtime());
                getMethod(SHOW_TIMES);
                getMethod(SHOW_AVERAGE_AND_LEFT_TIME);
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                break;
            case SHOW_AVERAGE_AND_LEFT_TIME:
                translator.valueToText(session.simpleAverage(), valueTextMap.get(AVERAGE_TIME));
                translator.valueToText(session.leftTime(), valueTextMap.get(LEFT_TIME));
                break;
            case SHOW_BEST_AVERAGE_AND_BEST_TIME:
                translator.valueToText(gameHistory.getBestTime(), valueTextMap.get(BEST_TIME));
                translator.valueToText(gameHistory.getBestAverageTen(), valueTextMap.get(BEST_AVERAGE_TIME));
                break;
            case LOOSER:
                dialog.okAlert(R.string.looser_message);
                getMethod(END_OF_GAME);
                break;
            case RECORD:
                gameHistory.setBestAverageTen(session.simpleAverage());
                dialog.okAlert(R.string.new_best_average);
                break;
            case RESTART_ALERT:
                dialog.okCancelAlert(R.string.restart, START_THE_GAME);
                break;
            case BEST_TIME_ALERT:
                dialog.okCancelAlert(R.string.new_best_time, SAVE_HISTORY_AND_SET_BEST_TIME);
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                break;
            case SAVE_HISTORY:
                saveLoadController.getMethod(SAVE_HISTORY);
                break;
            case LOAD_HISTORY:
                saveLoadController.getMethod(LOAD_HISTORY);
                break;
            case SAVE_HISTORY_AND_SET_BEST_TIME:
                getMethod(SAVE_HISTORY);
                gameHistory.setBestTime(sec);
                break;
            case SAVE_SESSION:
                saveLoadController.getMethod(SAVE_SESSION);
                break;
            case LOAD_SESSION:
                saveLoadController.getMethod(LOAD_SESSION);
                break;


        }
    }

    void showMenu(View v) {
        PopupMenu popup = new PopupMenu(context, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        popup.show();
    }

    void showInfo(int infoText) {
        dialog.okAlert(infoText);
    }

}
