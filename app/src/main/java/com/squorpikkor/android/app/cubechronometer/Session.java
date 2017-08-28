package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 20.08.2017.
 *
 */

class Session {
    private double slowest = 0;
    private double fastest;
    private String name;
    private int sessionSize;
    boolean isEnds = false;


    static final String BEST_TIME = "best_time";
    static final String BEST_AVERAGE_TIME = "best_average_time";
    static final String AVERAGE_TIME = "average_time";
    static final String ADVANCED_AVERAGE_TIME = "advanced_average_time";
    static final String WISH_TIME = "wish_time";
    static final String MAX_TIME = "max_time";

    private double bestTime;
    private double bestSessionTime;
    private double averageTime;
    private double leftTime;
    private double iWishTime;

    private ICanSave iCanSave;

    private Context context;

    private ArrayList<Double> timeList = new ArrayList<>();
    private ArrayList<Double> doubleList = new ArrayList<>();

    private ArrayList<Double> getDoubleArray() {
        doubleList.clear();
        doubleList.add(bestTime);
        doubleList.add(bestSessionTime);
        doubleList.add(averageTime);
        doubleList.add(leftTime);
        doubleList.add(iWishTime);
        return doubleList;
    }


    void saveMe() {
        iCanSave.saveDoubleArray(timeList, name);
    }

    Session(Context context, int size) {
        //this.name = name;
        this.sessionSize = size;
        iCanSave = new SaveLoad(context);
        this.context = context;
    }

    /*void resetSession() {
        for
    }*/

    ArrayList<Double> getTimeList() {
        return timeList;
    }

    private double summa() {
        double sum = 0;
        for (double d : timeList) {
            sum += d;
        }
        return sum;
    }

    public double simpleAverage(List list) {
        return summa() / list.size();
    }

    public double advancedAverage() {
        List<Double> list = new ArrayList<>(timeList);
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

    public int getSessionSize() {
        return sessionSize;
    }


}
