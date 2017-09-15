package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

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
    private static final String DOUBLE_GAME_LIST = "double_game_list";
    private static final String STRING_GAME_LIST = "string_game_list";

    private SaveLoad saveLoad;
    private GameHistory gameHistory;
    private Session session;

    SaveLoadController(Context context, GameHistory gameHistory, Session session) {
        this.saveLoad = new SaveLoad(context);
        this.gameHistory = gameHistory;
        this.session = session;
        Log.e(TAG, "SavLoCon CREATED!!!");
    }

    void getMethod(String command) {
        switch (command) {
            case SAVE_HISTORY:
                saveLoad.saveDouble(gameHistory.getBestTime(), BEST_TIME);
                saveLoad.saveDouble(gameHistory.getBestAverageTen(), BEST_AVERAGE);
                saveLoad.saveDouble(gameHistory.getWishTime(), WISH_TIME);
                saveGameHistoryList();
                //saveLoad.saveDoubleArray(gameHistory.);
                break;
            case LOAD_HISTORY:
                gameHistory.setBestTime(saveLoad.loadDouble(BEST_TIME));
                gameHistory.setBestAverageTen(saveLoad.loadDouble(BEST_AVERAGE));
                gameHistory.getHistoryTen().clear();
                gameHistory.getHistoryTen().addAll(loadGameHistoryList());
                gameHistory.setWishTime(saveLoad.loadDouble(WISH_TIME));
                session.setWishTime(saveLoad.loadDouble(WISH_TIME));//KOSTYIL:((((
                //Log.e(TAG, "end of game: " + gameHistory.getHistoryTen().get(gameHistory.getHistoryTen().size() - 1).getDate());
                break;
            /*case SAVE_SESSION:
                saveLoad.saveDouble(session.getWishTime(), WISH_TIME);
                saveLoad.saveDouble(session.getFastest(), FASTEST);
                saveLoad.saveDouble(session.getSlowest(), SLOWEST);
                saveLoad.saveDouble(session.getSessionSize(), SESSION_SIZE);
//                Log.e(TAG, "SAVE getMethod: " + session.getTimeList().get(0));
                Log.e(TAG, "svCon WISH: " + session.getWishTime());
                Log.e(TAG, "svCon SLOWEST: " + session.getSlowest());
                Log.e(TAG, "svCon SESSION SIZE: " + session.getSessionSize());
                Log.e(TAG, "svCon FASTEST: " + session.getFastest());
                saveLoad.saveDoubleArray(session.getTimeList(), TIME_LIST);
                break;
            case LOAD_SESSION:
                session.setWishTime(saveLoad.loadDouble(WISH_TIME));
                session.setFastest(saveLoad.loadDouble(FASTEST));
                session.setSlowest(saveLoad.loadDouble(SLOWEST));
                session.setSessionSize(saveLoad.loadDouble(SESSION_SIZE));
                session.setTimeList(saveLoad.loadDoubleArray(TIME_LIST));
                Log.e(TAG, "ldCon WISH: " + session.getWishTime());
                Log.e(TAG, "ldCon SLOWEST: " + session.getSlowest());
                Log.e(TAG, "ldCon SESSION SIZE: " + session.getSessionSize());
                Log.e(TAG, "ldCon FASTEST: " + session.getFastest());
                break;*/
        }

    }

    private void saveGameHistoryList() {
        ArrayList<Double> dList = new ArrayList<>();
        ArrayList<String> sList = new ArrayList<>();
        for (Game game : gameHistory.getHistoryTen()) {
            dList.add(game.getAverageTime());
            sList.add(game.getDate());
        }
        saveLoad.saveDoubleArray(dList, DOUBLE_GAME_LIST);
        saveLoad.saveStringArray(sList, STRING_GAME_LIST);
    }

    private ArrayList<Game> loadGameHistoryList() {
        ArrayList<Game> gList = new ArrayList<>();
        ArrayList<Double> dList = saveLoad.loadDoubleArray(DOUBLE_GAME_LIST);
        ArrayList<String> sList = saveLoad.loadStringArray(STRING_GAME_LIST);
        for (int i = 0; i < dList.size(); i++) {
            gList.add(new Game(dList.get(i), sList.get(i)));
        }
        return gList;
    }
}
