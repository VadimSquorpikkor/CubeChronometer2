package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import java.util.ArrayList;

/**
 * Created by Vadim on 20.08.2017.
 *
 */

class Session {
    private double slowest = 0;
    private double fastest;
    private int sessionSize;
    boolean isEnds = false;
    private double wishTime = 50;

    public double getWishTime() {
        return wishTime;
    }

    public void setWishTime(double wishTime) {
        this.wishTime = wishTime;
    }

    public double getSlowest() {
        return slowest;
    }

    public void setSlowest(double slowest) {
        this.slowest = slowest;
    }

    public double getFastest() {
        return fastest;
    }

    public void setFastest(double fastest) {
        this.fastest = fastest;
    }

    public double getSessionSize() {//i done it with double (instead int) course its lazy to make method for int (for double it already done)
        return sessionSize;
    }

    public void setSessionSize(double sessionSize) {//i done it with double (instead int) course its lazy to make method for int (for double it already done)
        this.sessionSize = (int)sessionSize;
    }

    ArrayList<Double> getTimeList() {
        return timeList;
    }

    public void setTimeList(ArrayList<Double> timeList) {
        this.timeList = timeList;
    }

    static final String BEST_TIME = "best_time";
    static final String BEST_AVERAGE_TIME = "best_average_time";
    static final String AVERAGE_TIME = "average_time";
    static final String ADVANCED_AVERAGE_TIME = "advanced_average_time";
    static final String WISH_TIME = "wish_time";
    static final String LEFT_TIME = "left_time";
/*
    private double averageTime;
    private double leftTime;
    private double iWishTime;*/

    private ICanSave iCanSave;

    private Context context;

    private ArrayList<Double> timeList = new ArrayList<>();


//    private ArrayList<Double> doubleList = new ArrayList<>();

  /*  private ArrayList<Double> getDoubleArray() {
        doubleList.clear();
        doubleList.add(bestTime);
        doubleList.add(bestSessionTime);
        doubleList.add(averageTime);
        doubleList.add(leftTime);
        doubleList.add(iWishTime);
        return doubleList;
    }
*/

    /*void saveMe() {
        iCanSave.saveDoubleArray(timeList, String.valueOf(sessionSize));
    }*/

    Session(Context context, int size) {
        //this.name = name;
        sessionSize = size;
//        iCanSave = new SaveLoad(context);
        this.context = context;
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

    public double advancedAverage() {
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

}
