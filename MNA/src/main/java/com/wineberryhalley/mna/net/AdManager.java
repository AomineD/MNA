package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.ads.AdSettings;
import com.wineberryhalley.mna.base.MNApp;
import com.wineberryhalley.mna.base.TypeNetwork;

import java.util.ArrayList;

public class AdManager {

    private static SubManager subManager;
    static boolean testAds = false;
    static boolean isInitializedAlready = false;
    static boolean nativesLoaded;
    static TypeNetwork mediation_mopub;
    static boolean showingInterstitial = false;
    static boolean cacheInterstitial = false;

    static boolean preventMultipleBannerLoad = false;

    protected AdManager(){
        if(subManager == null){
            subManager = new SubManager();
        }
    }


    public void cacheInterstitial(boolean enableCache){
        cacheInterstitial = enableCache;
    }



    static NtUtils ntUtils;
    static NtUtils ntUtilsBannerNat;
    static TypeNetwork network;
    static TypeNetwork natives_network;

    public static AdManager get(){
        return new AdManager();
    }

    public int actualFrecuencyInterstitial(){
        return SubManager.getF();
    }

    public Non load(){
        return new Non();
    }

    public boolean isNativesLoaded(){
        return nativesLoaded;
    }

    public boolean checkIfLoad(){
        return new Non().isLoaded();
    }

    public void preventMultipleBannerLoad(){
        preventMultipleBannerLoad = true;
    }

    public void test(boolean isTestAds){
        testAds = isTestAds;
        AdSettings.setDebugBuild(AdManager.testAds);
       // Log.e("MAIN", "test: "+testAds );
    }

    public SubManager manage(){
        return subManager;
    }

    public void destroyBannerAdIn(LinearLayout linearLayout){
        if(subManager != null){
            subManager.destroyAd(linearLayout);
        }
    }

    static void addAds(ArrayList<AdMNA> mnaAds) {
        if (subManager == null) {
            subManager = new SubManager();
        }
        subManager.clearAds();
        subManager.ads.addAll(mnaAds);
        subManager.initOn();
        if (cacheInterstitial) {
            subManager.cacheInterstitial();
        }
    }

    static String appId = "";

    static Activity getActivity() {

        if (MNApp.mnApp != null && MNApp.mnApp.getCurrent() != null) {
return MNApp.mnApp.getCurrent();
        } else {
             Log.e("MAIN", "getActivity: No activity" );
            return null;
        }
    }

    public boolean initialized(){
        return isInitializedAlready;
    }

}
