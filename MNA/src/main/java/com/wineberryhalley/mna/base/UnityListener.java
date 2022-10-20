package com.wineberryhalley.mna.base;

import android.util.Log;

import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

public class UnityListener implements IUnityAdsLoadListener, IUnityAdsShowListener {
    public TypeAd getTypeAd() {
        return typeAd;
    }

    private final TypeAd typeAd;

    public UnityListener(TypeAd typeAd){
        this.typeAd = typeAd;
    }

    public void OnShow(){

    }

    public void OnClosed(){

    }

    public void OnError(String fail){

    }

    public void OnReward(){

    }

    public void OnLoad(){

    }


    @Override
    public void onUnityAdsAdLoaded(String placementId) {
        OnLoad();
    }

    @Override
    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
        OnError(message);
    }

    @Override
    public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

    }

    @Override
    public void onUnityAdsShowStart(String placementId) {
        OnShow();
    }

    @Override
    public void onUnityAdsShowClick(String placementId) {

    }

    @Override
    public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
        if(getTypeAd() == TypeAd.INTERSTICIAL) {
            OnClosed();
        }else if(getTypeAd() == TypeAd.REWARD){
            if(state == UnityAds.UnityAdsShowCompletionState.COMPLETED){
                OnReward();
            }else if(state == UnityAds.UnityAdsShowCompletionState.SKIPPED){
                OnClosed();
            }else{
                OnError("States - >"+state.name());
            }
        }
    }
}
