package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 *
 */

public class SaveLoadController {

    static final String SAVE_HISTORY = "save_history";
    static final String LOAD_HISTORY = "load_history";
    static final String BEST_TIME = "best_time";
    static final String BEST_AVERAGE = "best_average";

    private Context context;
    private SaveLoad saveLoad;
    private GameHistory gameHistory;

    SaveLoadController(Context context, GameHistory gameHistory) {
        this.context = context;
        saveLoad = new SaveLoad(context);
        this.gameHistory = gameHistory;
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
        }

    }
}
