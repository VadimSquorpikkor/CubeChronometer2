package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Chronometer;

import static com.squorpikkor.android.app.cubechronometer.BigButton.START;
import static com.squorpikkor.android.app.cubechronometer.BigButton.STOP;

/**
 * Created by Vadim on 27.08.2017.
 * This class for communicate classes
 */

class Controller {

    Chronometer chronometer;

    /*Context context;


    Controller(Context context) {
        this.context = context;
    }

    Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
    */

    Controller(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    void methodRequesting(String command) {
        switch (command) {
            case START:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case STOP:
                chronometer.stop();
                break;

        }
    }
}
