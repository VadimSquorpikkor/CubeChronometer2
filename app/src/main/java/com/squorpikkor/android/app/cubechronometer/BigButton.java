package com.squorpikkor.android.app.cubechronometer;

import android.util.Log;
import android.widget.ImageButton;

import static com.squorpikkor.android.app.cubechronometer.MainActivity.TAG;

/**
 * Created by Vadim on 27.08.2017.
 * Class for bigButton
 */

class BigButton {

    private final String GREEN = "green";
    private final String RED = "red";
    private final String PAUSED = "paused";

    private String command;
    public static final String START = "start";
    public static final String STOP = "stop";
    public static final String RESUME = "resume";

    private String condition;

    /**
     * Constructor
     */
    BigButton() {
        condition = GREEN;
    }

    /**
     * Tap method depends of condition (button colour)
     * Condition should be a separate class: object with String and
     * imageButton.setImageResource(R.drawable.green_but2) values
     */
    void tapIt(ImageButton imageButton) {
        switch (condition) {
            case GREEN:
                Log.e(TAG, "tapIt: onCase Green");
                //start chronometer
                condition = RED;
                imageButton.setImageResource(R.drawable.big_red);
                command = START;
                break;
            case RED:
                Log.e(TAG, "tapIt: onCase Red");
                //stop chronometer
                condition = GREEN;
                imageButton.setImageResource(R.drawable.big_green);
                command = STOP;
                break;
            case PAUSED:
                Log.e(TAG, "tapIt: onCase Paused");
                //resume chronometer
                condition = RED;
                imageButton.setImageResource(R.drawable.big_red);
                command = RESUME;
                break;
        }

    }

    void freezeIt(ImageButton imageButton) {
        condition = PAUSED;
        imageButton.setImageResource(R.drawable.big_blue);
    }


    /**
     * Command and condition is near each other, i use both of them for better code understanding
     */
    String getCommand() {
        return command;
    }

}
