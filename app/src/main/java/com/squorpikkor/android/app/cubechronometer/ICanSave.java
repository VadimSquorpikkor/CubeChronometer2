package com.squorpikkor.android.app.cubechronometer;

// Created by VadimSquorpikkor on 22.08.2017.

import android.content.SharedPreferences;

import java.util.ArrayList;

public interface ICanSave {
    void saveStringArray(ArrayList<String> list, SharedPreferences preferences);

    void loadStringArray(ArrayList<String> list, SharedPreferences preferences);

    void saveDoubleArray(ArrayList<String> list, SharedPreferences preferences);

    void loadDoubleArray(ArrayList<String> list, SharedPreferences preferences);
}
