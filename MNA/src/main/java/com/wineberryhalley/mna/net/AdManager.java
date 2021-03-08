package com.wineberryhalley.mna.net;

import android.app.Activity;

import com.facebook.ads.AdSettings;
import com.wineberryhalley.mna.base.TypeNetwork;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

public class AdManager {

    private static SubManager subManager;
    static boolean testAds = false;

    protected AdManager(){
if(subManager == null){
    subManager = new SubManager();
}
    }




    static TypeNetwork network;

    public static AdManager get(){
        return new AdManager();
    }

    public int actualFrecuencyInterstitial(){
        return SubManager.getF();
    }

    public Non load(){
        return new Non();
    }

    public boolean checkIfLoad(){
        return new Non().isLoaded();
    }

    public void test(boolean isTestAds){
        testAds = isTestAds;
        AdSettings.setDebugBuild(AdManager.testAds);
    }

    public SubManager manage(){
        return subManager;
    }

    static void addAds(ArrayList<AdMNA> mnaAds){
        subManager.clearAds();
        subManager.ads.addAll(mnaAds);
    }

static String appId = "";

    static Activity getActivity() throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
        Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
        activitiesField.setAccessible(true);

        Map<Object, Object> activities = (Map<Object, Object>) activitiesField.get(activityThread);
        if (activities == null)
            return null;

        for (Object activityRecord : activities.values()) {
            Class activityRecordClass = activityRecord.getClass();
            Field pausedField = activityRecordClass.getDeclaredField("paused");
            pausedField.setAccessible(true);
            if (!pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                return activity;
            }
        }

        return null;
    }

}
