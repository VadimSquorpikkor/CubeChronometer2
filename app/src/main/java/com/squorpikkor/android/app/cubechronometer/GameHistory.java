package com.squorpikkor.android.app.cubechronometer;

import java.util.ArrayList;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 * Class for display completed games
 */

public class GameHistory {

    private double bestAverageTen;
    private double bestAverageFive;
    double bestTime;

    public double getBestAverageTen() {
        return bestAverageTen;
    }

    public void setBestAverageTen(double bestAverageTen) {
        this.bestAverageTen = bestAverageTen;
    }

    public double getBestAverageFive() {
        return bestAverageFive;
    }

    public void setBestAverageFive(double bestAverageFive) {
        this.bestAverageFive = bestAverageFive;
    }

    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }

    /**
     * I haven't decided what array type use for class -- List or primitive array
     *
     */
    ArrayList<Game> history = new ArrayList<>();



}
