package com.wineberryhalley.mna.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        Log.e(TAG, "onActivityCreated: "+activity.getClass().getSimpleName() );
        current = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
