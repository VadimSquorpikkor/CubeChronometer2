package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;

import static com.squorpikkor.android.app.cubechronometer.Session.WISH_TIME;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 *
 */

public class SaveLoadController {

    static final String SAVE_HISTORY = "save_history";
    static final String LOAD_HISTORY = "load_history";
    static final String SAVE_SESSION = "save_session";
    static final String LOAD_SESSION = "load_session";
    static final String BEST_TIME = "best_time";
    static final String BEST_AVERAGE = "best_average";
    static final String FASTEST = "fastest";
    static final String SLOWEST = "slowest";
    static final String SESSION_SIZE = "session_size";
    static final String TIME_LIST = "time_list";

    private Context context;
    private SaveLoad saveLoad;
    private GameHistory gameHistory;
    private Session session;

    SaveLoadController(Context context, GameHistory gameHistory, Session session) {
        this.context = context;
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
                saveLoad.loadDoubleArray(session.getTimeList(), TIME_LIST);
                break;
            case LOAD_SESSION:
                session.setWishTime(saveLoad.loadDouble(WISH_TIME));
                session.setFastest(saveLoad.loadDouble(FASTEST));
                session.setSlowest(saveLoad.loadDouble(SLOWEST));
                session.setSessionSize(saveLoad.loadDouble(SESSION_SIZE));
                saveLoad.loadDoubleArray(session.getTimeList(), TIME_LIST);
                break;
        }

    }
}
