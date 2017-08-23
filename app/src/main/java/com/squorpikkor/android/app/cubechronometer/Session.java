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

    private double bestTime;
    private double bestSessionTime;
    private double averageTime;
    private double leftTime;
    private double iWishTime;

    private ArrayList<Double> doubleList;

    private ICanSave iCanSave;

    private Context context;

    private ArrayList<Double> timeList = new ArrayList<>();

    private void doubleArray() {
        doubleList.clear();
        doubleList.add(bestTime);
        doubleList.add(bestSessionTime);
        doubleList.add(averageTime);
        doubleList.add(leftTime);
        doubleList.add(iWishTime);
    }

    void saveMe() {
       iCanSave.saveDoubleArray(timeList, name);
    }

    Session(Context context, String name) {
        this.name = name;
        iCanSave = new SaveLoad(context);
        this.context = context;
    }


    public List<Double> getTimeList() {
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

    public void addTime(double time) {
        if (timeList.size() == 0) {
            fastest = time;
        } else if (time > slowest) {
            slowest = time;
        } else if (time < fastest) {
            fastest = time;
        }
        timeList.add(time);
    }

}
