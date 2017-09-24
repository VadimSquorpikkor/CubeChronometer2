package com.squorpikkor.android.app.cubechronometer;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Vadim on 20.08.2017.
 *
 */

class Session {
    private double slowest = 0;
    private double fastest;
    private int sessionSize;
    boolean isEnds = false;
    private double wishTime;

    static final String BEST_TIME = "best_time";
    static final String BEST_AVERAGE_TIME = "best_average_time";
    static final String AVERAGE_TIME = "average_time";
//    static final String ADVANCED_AVERAGE_TIME = "advanced_average_time";
    static final String WISH_TIME = "wish_time";
    static final String LEFT_TIME = "left_time";

    private ArrayList<Double> timeList = new ArrayList<>();

    double getWishTime() {
        return wishTime;
    }

    void setWishTime(double wishTime) {
        this.wishTime = wishTime;
    }

    double getSlowest() {
        return slowest;
    }

    void setSlowest(double slowest) {
        this.slowest = slowest;
    }

    double getFastest() {
        return fastest;
    }

    void setFastest(double fastest) {
        this.fastest = fastest;
    }

    double getSessionSize() {//i done it with double (instead int) course its lazy to make method for int (for double it already done)
        return sessionSize;
    }

    void setSessionSize(double sessionSize) {//i done it with double (instead int) course its lazy to make method for int (for double it already done)
        this.sessionSize = (int)sessionSize;
    }

    ArrayList<Double> getTimeList() {
        return timeList;
    }

    void setTimeList(ArrayList<Double> timeList) {
//        this.timeList = timeList;
        this.timeList = new ArrayList<>(timeList);//???
    }


    Session(int size) {
        this.sessionSize = size;
        Log.e(TAG, "Session: NEW SESSION CREATED!!!");
    }

    private double summa() {
        double sum = 0;
        for (double d : timeList) {
            sum += d;
        }
        return sum;
    }

    double simpleAverage() {
        return summa() == 0 ? 0 : summa() / timeList.size();
    }

    private double simpleAverage(ArrayList<Double> list) {
        return summa() / list.size();
    }

    double advancedAverage() {
        ArrayList<Double> list = new ArrayList<>(timeList);
        for (double d : list) {
            if (d == fastest) {
                list.remove(d);
            }
            if (d == slowest) {
                list.remove(d);
            }
        }
        return simpleAverage(list);
    }

    double leftTime() {
        double d = 0;
        if (sessionSize != timeList.size()) {
            d = (sessionSize*wishTime-summa())/(sessionSize-timeList.size());
        }
        return d;
    }

    void addTime(double time) {
        if (timeList.size() == 0) {
            fastest = time;
        } else if (time > slowest) {
            slowest = time;
        } else if (time < fastest) {
            fastest = time;
        }
        timeList.add(time);
        isEnds = timeList.size() == sessionSize;//if timeList.size() == sessionSize, isEnd = true, else: isEnd = false
    }

    void clearSession() {
        fastest = 0;
        slowest = 0;
        timeList.clear();
    }

}
