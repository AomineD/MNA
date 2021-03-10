package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.util.Log;

import com.facebook.ads.AdSettings;
import com.wineberryhalley.mna.base.MNApp;
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

    static Activity getActivity() {

        if (MNApp.mnApp != null && MNApp.mnApp.getCurrent() != null) {
return MNApp.mnApp.getCurrent();
        } else {
            // Log.e("MAIN", "getActivity: paso" );
            return null;
        }
    }

}
