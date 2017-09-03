package com.squorpikkor.android.app.cubechronometer;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 *
 */

class Game {

    Game(double averageTime, String date) {
        this.averageTime = averageTime;
        this.date = date;
    }

    private double averageTime;
    private String date;

    public double getAverageTime() {
        return averageTime;
    }

    public String getDate() {
        return date;
    }
}
