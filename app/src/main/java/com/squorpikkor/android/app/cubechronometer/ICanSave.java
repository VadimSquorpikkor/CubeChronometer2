package com.squorpikkor.android.app.cubechronometer;

// Created by VadimSquorpikkor on 22.08.2017.

import android.content.SharedPreferences;

import java.util.ArrayList;

public interface ICanSave {
    void saveStringArray(ArrayList<String> list, String prefName);

    void loadStringArray(ArrayList<String> list, String prefName);

    void saveDoubleArray(ArrayList<Double> list, String prefName);

    void loadDoubleArray(ArrayList<Double> list, String prefName);
}
