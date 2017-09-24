package com.squorpikkor.android.app.cubechronometer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
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
import static com.squorpikkor.android.app.cubechronometer.Session.WISH_TIME;


/**
 * Created by Vadim on 27.08.2017.
 * This class for communicate classes.
 * It looks like method provider class
 */

class Controller {

    private Context context;
    private double sec;

    static final String START = "start";
    static final String STOP = "stop";
    static final String RESUME = "resume";
    static final String PAUSE = "pause";

    private static final String SHOW_TIMES = "show_times";
    private static final String END_OF_GAME = "end_of_game";
    private static final String SHOW_AVERAGE_LEFT_AND_WISH_TIME = "show_values";
    private static final String SAVE_HISTORY_AND_SET_BEST_TIME = "save_history_and_set_best_time";
    static final String RESTART_ALERT = "restart_alert";
    static final String START_THE_GAME = "start_the_game";
    static final String LOG_SESSION = "log_session";
    static final String OPEN_HISTORY_ACTIVITY = "open_history_activity";
    static final String OPEN_ABOUT_ACTIVITY = "open_about_activity";
    static final String SET_WISH_TIME_ALERT = "set_wish_time_alert";
    static final String SET_WISH_TIME = "set_wish_time";
    private static final String SHOW_BEST_AVERAGE_AND_BEST_TIME = "show_best_average_and_best_time";
    private static final String LOOSER = "looser";
    private static final String RECORD = "record";
    private static final String BEST_TIME_ALERT = "best_time_alert";
    private static final String ADD_LINE_TO_HISTORY_LIST = "add_line_to_history_list";

    private Intent intent;
    private Session session;
    private Translator translator;
    private ArrayList<TextView> timeList;
    private HashMap<String, TextView> valueTextMap;
    private ImageButton imageButton;
    private Dialog dialog;
    private GameHistory gameHistory;
    private SaveLoadController saveLoadController;
    private SquorChrono squorChrono;

    /**
     * Idea is -- session size depends only of timeList size,
     * so i don't need to think of what size it should be --
     * session automatically get the right size
     */

    Controller(Context context, ArrayList<TextView> timeList, HashMap<String, TextView> valueTextMap, ImageButton imageButton, TextView textView, Activity activity) {
        this.context = context;
        this.timeList = timeList;
        this.valueTextMap = valueTextMap;
        session = new Session(timeList.size());
        this.translator = new Translator();
        this.imageButton = imageButton;
        this.dialog = new Dialog(context, this);
        gameHistory = new GameHistory();
        saveLoadController = new SaveLoadController(context, gameHistory, session);
        squorChrono = new SquorChrono(textView, activity);
    }

    void getMethod(String command) {
        switch (command) {
            case START:
                squorChrono.start();
                break;
            case STOP:
                squorChrono.stop();
                sec = squorChrono.getTimeInSeconds();
                session.addTime(sec);
                if (sec < gameHistory.getBestTime()) getMethod(BEST_TIME_ALERT);
                getMethod(SHOW_TIMES);
                getMethod(SHOW_AVERAGE_LEFT_AND_WISH_TIME);
                if (session.leftTime() <= 0 && !session.isEnds) getMethod(LOOSER);
                if (session.isEnds) getMethod(END_OF_GAME);
                Log.e(TAG, "stop WISH: " + gameHistory.getWishTime());
                Log.e(TAG, "stop SLOWEST: " + session.getSlowest());
                Log.e(TAG, "stop SESSION SIZE: " + session.getSessionSize());
                Log.e(TAG, "stop FASTEST: " + session.getFastest());
//                Log.e(TAG, "ADVANCED AV: " + session.advancedAverage());//do not workings. Crashes
                break;
            case RESUME:
                squorChrono.resume();
                break;
            case PAUSE:
                squorChrono.pause();
                break;
            case SHOW_TIMES:
                translator.arrayToText(session.getTimeList(), timeList);
                break;
            case END_OF_GAME:
                if (session.simpleAverage() < gameHistory.getBestAverageTen()) getMethod(RECORD);
                imageButton.setEnabled(false);
                gameHistory.addGameHistoryTen(session.simpleAverage());
                getMethod(SAVE_HISTORY);
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                Log.e(TAG, "end of game: history 0: " + gameHistory.getHistoryTen().get(0).getAverageTime());
                Log.e(TAG, "end of game: history 0: " + gameHistory.getHistoryTen().get(0).getDate());
                Log.e(TAG, "end of game: history last: " + gameHistory.getHistoryTen().get(gameHistory.getHistoryTen().size() - 1).getAverageTime());
                Log.e(TAG, "end of game: history last: " + gameHistory.getHistoryTen().get(gameHistory.getHistoryTen().size() - 1).getDate());
                break;
            case START_THE_GAME:
                imageButton.setEnabled(true);
                session.clearSession();
                session.setWishTime(gameHistory.getWishTime());//KOSTYIL:((((
//                session = new Session(timeList.size());
//                saveLoadController = new SaveLoadController(context, gameHistory, session);
                getMethod(SHOW_TIMES);
                getMethod(SHOW_AVERAGE_LEFT_AND_WISH_TIME);
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                break;
            case SHOW_AVERAGE_LEFT_AND_WISH_TIME:
                translator.valueToText(session.simpleAverage(), valueTextMap.get(AVERAGE_TIME));
                translator.valueToText(session.leftTime(), valueTextMap.get(LEFT_TIME));
                translator.valueToText(session.getWishTime(), valueTextMap.get(WISH_TIME));
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
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                dialog.okAlert(R.string.new_best_average);
                break;
            case RESTART_ALERT:
                dialog.okCancelAlert(R.string.restart, START_THE_GAME);
                break;
            case BEST_TIME_ALERT:
                dialog.okCancelAlert(R.string.new_best_time, SAVE_HISTORY_AND_SET_BEST_TIME);
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
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                break;
            case SAVE_SESSION:
                saveLoadController.getMethod(SAVE_SESSION);
                break;
            case LOAD_SESSION:
                saveLoadController.getMethod(LOAD_SESSION);
                Log.e(TAG, "load WISH: " + gameHistory.getWishTime());
                Log.e(TAG, "load SLOWEST: " + session.getSlowest());
                Log.e(TAG, "load SESSION SIZE: " + session.getSessionSize());
                Log.e(TAG, "load FASTEST: " + session.getFastest());
                getMethod(SHOW_BEST_AVERAGE_AND_BEST_TIME);
                getMethod(SHOW_TIMES);
                break;
            /*case LOG_SESSION:
                Log.e(TAG, "getMethod: " + session.getTimeList().get(0));
                break;*/
            case OPEN_HISTORY_ACTIVITY:
                Log.e(TAG, "GAME_HISTORY size = " + gameHistory.stringHistoryTen().size());
                intent = new Intent(context, HistoryActivity.class);
                intent.putExtra("game_list", gameHistory.stringHistoryTen());
                context.startActivity(intent);
                break;
            case OPEN_ABOUT_ACTIVITY:
                Intent intent2 = new Intent(context, AboutActivity.class);
                context.startActivity(intent2);
                break;
            case SET_WISH_TIME_ALERT:
                dialog.okInputAlert(SET_WISH_TIME);
                break;
            case SET_WISH_TIME:
                double d = dialog.getTemp();
                gameHistory.setWishTime(d);
                session.setWishTime(d);
                translator.valueToText(gameHistory.getWishTime(), valueTextMap.get(WISH_TIME));
                saveLoadController.getMethod(SAVE_HISTORY);
                Log.e(TAG, gameHistory.getWishTime()+"");
                break;
            /*case ADD_LINE_TO_HISTORY_LIST:
                gameHistory.addGameHistoryTen();
                break;*/
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
