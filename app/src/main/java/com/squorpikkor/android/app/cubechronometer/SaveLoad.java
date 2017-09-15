package com.squorpikkor.android.app.cubechronometer;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by VadimSquorpikkor on 22.08.2017.
 *
 */

class SaveLoad{

    private final String SAVE_FIELD = "setting";
    private Context context;

    private SharedPreferences preferences;

    SaveLoad(Context context) {
        this.context = context;
    }

    /**
     * Можно было бы, конечно, сделать методы без перегрузки, т.е. сохранять
     * всЁ в одном методе, а не разбивать на два, но так как есть будет
     * удобнее для использования класса в будущем для более гибкого использования методов,
     * для композиции и т.д.
     * Прикол методов: ссылка всегда одна, но она ссылается на разны объекты
     * //Прикол методов: ссылка каждый раз создается заново -- она существует только в теле метода(старый вариант)//
     * P.S. Другой вариант класса -- можно было бы использовать коллекцию для хранения ссылок
     */

    /*public void saveDoubleStringObj(ArrayList<Double> dList, ArrayList<String> sList, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        saveDoubleArray(dList, preferences);
        saveStringArray(sList, preferences);
    }

    public ArrayList<ArrayList<Double>, ArrayList<String>> loadDouble
    */

    public void saveStringArray(ArrayList<String> list, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        saveStringArray(list, preferences);
    }

    public ArrayList<String> loadStringArray(String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return loadStringArray(preferences);
    }

    void saveDoubleArray(ArrayList<Double> list, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        saveDoubleArray(list, preferences);
    }

    ArrayList<Double> loadDoubleArray(String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return loadDoubleArray(preferences);
    }

    void saveDouble(double d, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        saveDouble(d, preferences);
    }

    double loadDouble(String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return loadDouble(preferences);
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

    private ArrayList<String> loadStringArray(SharedPreferences sPref) {
        ArrayList<String> list = new ArrayList<>();
//        list.clear();
        int count = 0;
        while (sPref.contains(SAVE_FIELD + count)) {
            //list.add(SAVE_FIELD + count);
            String s = sPref.getString(SAVE_FIELD + count, "");
            list.add(s);
            count++;
        }
        return list;
    }

    private void saveDoubleArray(ArrayList<Double> list, SharedPreferences sPref) {
        int count = 0;
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear();//For save less variables than before, if do not clear, it will load old variables, from old session
        for (Double d : list) {
            editor.putFloat(SAVE_FIELD + count, Float.parseFloat(d.toString()));
            count++;
        }
        editor.apply();
    }

    private ArrayList<Double> loadDoubleArray(SharedPreferences sPref) {
        ArrayList<Double> list = new ArrayList<>();
        int count = 0;
        while (sPref.contains(SAVE_FIELD + count)) {
            double d = sPref.getFloat(SAVE_FIELD + count, (float) 0);
            list.add(d);
            count++;
        }
        return list;
    }

    private void saveDouble(double d, SharedPreferences sPref) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putFloat(SAVE_FIELD, (float)d);
        editor.apply();
    }

    private double loadDouble(SharedPreferences sPref) {
        double d = 55;
        if (sPref.contains(SAVE_FIELD)) {
            d = sPref.getFloat(SAVE_FIELD, 0);
        }
        return d;
    }

}
