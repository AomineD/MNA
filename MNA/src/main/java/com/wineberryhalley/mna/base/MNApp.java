package com.wineberryhalley.mna.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ironsource.mediationsdk.IronSource;
import com.mopub.common.MoPub;
import com.wineberryhalley.mna.net.AdManager;
import com.wineberryhalley.mna.net.MopubMNA;

public class MNApp extends Application implements Application.ActivityLifecycleCallbacks {

    public static MNApp mnApp;
    @Override
    public void onCreate() {
        super.onCreate();
    mnApp = this;
        registerActivityLifecycleCallbacks(this);
    }

    Activity current;

    public Activity getCurrent(){
        return current;
    }

    private String TAG = "MAIN";
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
     //   Log.e(TAG, "onActivityCreated: "+activity.getClass().getSimpleName() );
        current = activity;
        if(AdManager.get().initialized() && AdManager.get().manage().isMoPub()){
            MoPub.onResume(activity);
        }

        if(AdManager.get().initialized() && AdManager.get().manage().getActualNetwork() == TypeNetwork.IRON_SOURCE && !AdManager.get().manage().showingInterstitial()){
            IronSource.onResume(activity);
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        if(AdManager.get().initialized() && AdManager.get().manage().isMoPub()){
            MoPub.onPause(activity);
        }

        if(AdManager.get().initialized() && AdManager.get().manage().getActualNetwork() == TypeNetwork.IRON_SOURCE && !AdManager.get().manage().showingInterstitial()){
            IronSource.onPause(activity);
        }
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        if(AdManager.get().initialized() && AdManager.get().manage().isMoPub()){
            MoPub.onStop(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {


    }
}
