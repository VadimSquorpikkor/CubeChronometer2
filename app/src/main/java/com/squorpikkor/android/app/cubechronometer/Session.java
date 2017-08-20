package com.squorpikkor.android.app.cubechronometer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 20.08.2017.
 *
 */

public class Session {
    private double slowest = 0;
    private double fastest;

    private List<Double> timeList = new ArrayList<>();

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
        return summa()/list.size();
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
