package com.squorpikkor.android.app.cubechronometer;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
//import java.util.HashMap;

/**
 * Created by VadimSquorpikkor on 22.08.2017.
 * Класс состот из мапа <String, SharedPreferences>. который хранит ссылки на префы, я его использую
 */

class SaveLoad implements ICanSave {

    private final String SAVE_FIELD = "setting";
    //    private HashMap<String, SharedPreferences> prefList = new HashMap<>();
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

    @Override
    public void saveStringArray(ArrayList<String> list, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        saveStringArray(list, preferences);
    }

    @Override
    public void loadStringArray(ArrayList<String> list, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        loadStringArray(list, preferences);
    }

    @Override
    public void saveDoubleArray(ArrayList<Double> list, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        saveDoubleArray(list, preferences);
    }

    @Override
    public void loadDoubleArray(ArrayList<Double> list, String prefName) {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        loadDoubleArray(list, preferences);
    }

    /**
     * Старые методы с использованием HashMap для хранения ссылок на префы
     * В новой версии методов сохраняю в префы напрямую, минуя мапу
     */
    /*@Override
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
    }*/
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
            //list.add(SAVE_FIELD + count);
            String s = sPref.getString(SAVE_FIELD + count, "");
            list.add(s);
            count++;
        }
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

    private void loadDoubleArray(ArrayList<Double> list, SharedPreferences sPref) {
        list.clear();
        int count = 0;
        while (sPref.contains(SAVE_FIELD + count)) {
            double d = sPref.getFloat(SAVE_FIELD + count, (float) 0);
            list.add(d);
            count++;
        }
    }


}
