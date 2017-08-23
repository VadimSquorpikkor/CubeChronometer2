package com.squorpikkor.android.app.cubechronometer;

// Created by VadimSquorpikkor on 22.08.2017.

import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveLoad implements ICanSave{

    private final String SAVE_FIELD = "setting";
    private SharedPreferences preferences;
    private HashMap<String, SharedPreferences> prefList;

    /*void saveStringArray(ArrayList<String> list, ) {//It should be own class, for better composition -- it can be using in another classes
        saveStringArray(list, preferences);
    }*/

    @Override
    public void saveStringArray(ArrayList<String> list, SharedPreferences sPref) {//It should be own class, for better composition -- it can be using in another classes
        int count = 0;
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear();//For save less variables than before, if do not clear, it will load old variables, from old session
        for (String s : list) {
            editor.putString(SAVE_FIELD + count, s);
            count++;
        }
        editor.apply();
    }

    /*private void loadStringArray(ArrayList<String> list) {
        loadStringArray(list, preferences);
    }*/

    @Override
    public void loadStringArray(ArrayList<String> list, SharedPreferences sPref) {
        list.clear();
        int count = 0;
        while (sPref.contains(SAVE_FIELD + count)) {
            list.add(SAVE_FIELD + count);
            count++;
        }
    }

    @Override
    public void saveDoubleArray(ArrayList<String> list, SharedPreferences preferences) {

    }

    @Override
    public void loadDoubleArray(ArrayList<String> list, SharedPreferences preferences) {

    }
}
