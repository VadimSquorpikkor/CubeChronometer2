package com.squorpikkor.android.app.cubechronometer;

// Created by VadimSquorpikkor on 22.08.2017.

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

class SaveLoad implements ICanSave {

    private final String SAVE_FIELD = "setting";
    private HashMap<String, SharedPreferences> prefList = new HashMap<>();
    private Context context;

    SaveLoad (Context context) {
        this.context = context;
    }

    @Override
    public void saveStringArray(ArrayList<String> list, String prefName) {//It should be own class, for better composition -- it can be using in another classes
        if (!prefList.containsKey(prefName)) {
            prefList.put(prefName, context.getSharedPreferences(prefName, Context.MODE_PRIVATE));
        }
        saveStringArray(list, prefList.get(prefName));
    }

    @Override
    public void loadStringArray(ArrayList<String> list, String prefName) {
        loadStringArray(list, prefList.get(prefName));
    }

    @Override
    public void saveDoubleArray(ArrayList<String> list, String prefName) {//It should be own class, for better composition -- it can be using in another classes
        if (!prefList.containsKey(prefName)) {
            prefList.put(prefName, context.getSharedPreferences(prefName, Context.MODE_PRIVATE));
        }
        saveDoubleArray(list, prefList.get(prefName));
    }

    @Override
    public void loadDoubleArray(ArrayList<String> list, String prefName) {
        loadDoubleArray(list, prefList.get(prefName));
    }

    private void saveStringArray(ArrayList<String> list, SharedPreferences sPref) {//It should be own class, for better composition -- it can be using in another classes
        int count = 0;
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear();//For save less variables than before, if do not clear, it will load old variables, from old session
        for (String s : list) {
            editor.putString(SAVE_FIELD + count, s);
            count++;
        }
        editor.apply();
    }

    private void loadStringArray(ArrayList<String> list, SharedPreferences sPref) {
        list.clear();
        int count = 0;
        while (sPref.contains(SAVE_FIELD + count)) {
            list.add(SAVE_FIELD + count);
            count++;
        }
    }

    private void saveDoubleArray(ArrayList<String> list, SharedPreferences preferences) {

    }

    private void loadDoubleArray(ArrayList<String> list, SharedPreferences preferences) {

    }
}
