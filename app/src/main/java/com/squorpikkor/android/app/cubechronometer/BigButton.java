package com.squorpikkor.android.app.cubechronometer;

import android.util.Log;
import android.widget.ImageButton;

import static android.content.ContentValues.TAG;
import static com.squorpikkor.android.app.cubechronometer.Controller.RESUME;
import static com.squorpikkor.android.app.cubechronometer.Controller.START;
import static com.squorpikkor.android.app.cubechronometer.Controller.STOP;

/**
 * Created by Vadim on 27.08.2017.
 * Class for bigButton
 */

class BigButton {

    private static final String GREEN = "green";
    static final String RED = "red";
    private static final String BLUE = "paused";

    private String command;


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
            case BLUE:
                Log.e(TAG, "tapIt: onCase Blue");
                //resume chronometer
                condition = RED;
                imageButton.setImageResource(R.drawable.big_red);
                command = RESUME;
                break;
        }

    }

    void freezeIt(ImageButton imageButton) {
        condition = BLUE;
        imageButton.setImageResource(R.drawable.big_blue);
    }


    /**
     * Command and condition is near each other, i use both of them for better code understanding
     */
    String getCommand() {
        return command;
    }

    String getCondition() {
        return condition;
    }
}
