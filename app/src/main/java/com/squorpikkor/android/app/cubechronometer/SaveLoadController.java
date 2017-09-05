package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.squorpikkor.android.app.cubechronometer.Session.WISH_TIME;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 *
 */

class SaveLoadController {

    static final String SAVE_HISTORY = "save_history";
    static final String LOAD_HISTORY = "load_history";
    static final String SAVE_SESSION = "save_session";
    static final String LOAD_SESSION = "load_session";
    private static final String BEST_TIME = "best_time";
    private static final String BEST_AVERAGE = "best_average";
    private static final String FASTEST = "fastest";
    private static final String SLOWEST = "slowest";
    private static final String SESSION_SIZE = "session_size";
    private static final String TIME_LIST = "time_list";

    private SaveLoad saveLoad;
    private GameHistory gameHistory;
    private Session session;

    SaveLoadController(Context context, GameHistory gameHistory, Session session) {
        saveLoad = new SaveLoad(context);
        this.gameHistory = gameHistory;
        this.session = session;
    }

    void getMethod(String command) {
        switch (command) {
            case SAVE_HISTORY:
                saveLoad.saveDouble(gameHistory.getBestTime(), BEST_TIME);
                saveLoad.saveDouble(gameHistory.getBestAverageTen(), BEST_AVERAGE);
                break;
            case LOAD_HISTORY:
                gameHistory.setBestTime(saveLoad.loadDouble(BEST_TIME));
                gameHistory.setBestAverageTen(saveLoad.loadDouble(BEST_AVERAGE));
                break;
            case SAVE_SESSION:
                saveLoad.saveDouble(session.getWishTime(), WISH_TIME);
                saveLoad.saveDouble(session.getFastest(), FASTEST);
                saveLoad.saveDouble(session.getSlowest(), SLOWEST);
                saveLoad.saveDouble(session.getSessionSize(), SESSION_SIZE);
//                Log.e(TAG, "SAVE getMethod: " + session.getTimeList().get(0));
                Log.e(TAG, "WISH: " + session.getWishTime());
                Log.e(TAG, "SLOWEST: " + session.getSlowest());
                Log.e(TAG, "SESSION SIZE: " + session.getSessionSize());
                Log.e(TAG, "FASTEST: " + session.getFastest());
                saveLoad.saveDoubleArray(session.getTimeList(), TIME_LIST);
                break;
            case LOAD_SESSION:
                session.setWishTime(saveLoad.loadDouble(WISH_TIME));
                session.setFastest(saveLoad.loadDouble(FASTEST));
                session.setSlowest(saveLoad.loadDouble(SLOWEST));
                session.setSessionSize(saveLoad.loadDouble(SESSION_SIZE));
                session.setTimeList(saveLoad.loadDoubleArray(TIME_LIST));
                break;
        }

    }
}
