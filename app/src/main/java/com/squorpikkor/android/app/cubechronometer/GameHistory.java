package com.squorpikkor.android.app.cubechronometer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by VadimSquorpikkor on 02.09.2017.
 * Class for display completed games
 */

public class GameHistory {

    private double bestAverageTen;
    private double bestAverageFive;
    double bestTime;
    private String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private double wishTime;

    public double getWishTime() {
        return wishTime;
    }

    public void setWishTime(double wishTime) {
        this.wishTime = wishTime;
    }

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

    public ArrayList<Game> getHistoryTen() {
        return historyTen;
    }

    public void setHistoryTen(ArrayList<Game> historyTen) {
        this.historyTen = historyTen;
    }

    /**
     * I haven't decided what array type use for class -- List or primitive array
     *
     */
    ArrayList<Game> historyTen = new ArrayList<>();

    void addGameHistoryTen(double average) {
        historyTen.add(new Game(average, date));
    }



}
