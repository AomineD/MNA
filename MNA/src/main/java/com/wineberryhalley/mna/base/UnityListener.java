package com.wineberryhalley.mna.base;

import android.util.Log;

import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;

public class UnityListener implements IUnityAdsExtendedListener {
    public TypeAd getTypeAd() {
        return typeAd;
    }

    private TypeAd typeAd;

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
    public void onUnityAdsClick(String s) {

    }

    @Override
    public void onUnityAdsPlacementStateChanged(String s, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState1) {
        Log.e("MAIN", "onUnityAdsPlacementStateChanged: "+placementState.name() );
    }

    @Override
    public void onUnityAdsReady(String s) {
OnLoad();
    }

    @Override
    public void onUnityAdsStart(String s) {
OnShow();
    }

    @Override
    public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
        if(typeAd == TypeAd.INTERSTICIAL) {
            UnityAds.removeListener(this);
            OnClosed();
        }else if(typeAd == TypeAd.REWARD){
            if(finishState == UnityAds.FinishState.COMPLETED){
                OnReward();
            }else if(finishState == UnityAds.FinishState.SKIPPED){
                OnClosed();
            }else{
                OnError(s);
            }
            UnityAds.removeListener(this);
        }
    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
OnError(s);
    }
}
