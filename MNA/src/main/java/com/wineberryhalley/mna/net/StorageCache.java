package com.wineberryhalley.mna.net;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageCache {
    private static SharedPreferences sharedPreferences;
    public StorageCache(Context context){
        sharedPreferences = context.getSharedPreferences("adm_mna", Context.MODE_PRIVATE);
    }

    public static boolean isAdLoadedRecently(String ad_unit){
        long millis = System.currentTimeMillis();
        long savedMills = sharedPreferences.getLong("mna_ad_"+ad_unit, 0);
        return millis > savedMills;
    }

    public static void adLoaded(String ad_unit){
        long mills = System.currentTimeMillis();
        long millis = mills + 20 * 1000;

        sharedPreferences
                .edit()
                .putLong("mna_ad_"+ad_unit, millis)
                .apply();
    }
}
