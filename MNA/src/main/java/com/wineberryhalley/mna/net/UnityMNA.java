package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;
import com.wineberryhalley.mna.base.DelayListener;
import com.wineberryhalley.mna.base.InitializeListener;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.RewardListener;
import com.wineberryhalley.mna.base.UnityListener;

import java.lang.reflect.InvocationTargetException;

public class UnityMNA extends AdMNA {
    private Context context;
    private static boolean initialized = false;
    private static boolean getting = false;
    private static int c_un_count = 0;
    Activity activity = null;
    InitializeListener initializeListener;
    public UnityMNA(AdMNA adMNA) {
        context = ChalaEdChala.context;
        config(adMNA);



        try {
            activity = AdManager.getActivity();

            try {
                initializeListener = (InitializeListener) activity;
            }catch (Exception e){

            }

        }  catch (Exception e) {

        }



        if (!initialized && !getting) {
            UnityAds.addListener(new IUnityAdsListener() {
                @Override
                public void onUnityAdsReady(String s) {
                    if(AdManager.testAds){
                        Log.e(TAG, "onUnityAdsReady: "+s );
                    }

                    c_un_count++;

                    if(c_un_count > 3 && !initialized) {
                        initialized = true;


                        if (initializeListener != null) {
                            initializeListener.OnInitialized();
                        }
                    }
                }


                @Override
                public void onUnityAdsStart(String s) {
                  if(AdManager.testAds)
                    Log.e(TAG, "onUnityAdsStart: "+s );
                }

                @Override
                public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {

                }

                @Override
                public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {

                    //Log.e("MAIN", "onUnityAdsError: "+s );
                    getting = false;
                    if(initializeListener != null){
                        initializeListener.OnInitializedError(s);
                    }
                }
            });
            // Initialize the SDK:
            UnityAds.initialize(context, AdManager.appId, AdManager.testAds);
            getting = true;
        }
    }

    @Override
    public void showBannerAd(LinearLayout adContainer) {
        if (isInitialized()) {


            BannerView adView = getSmallBanner(activity);
            Log.e(TAG, "showBannerAd: "+getValue() );
            // Set the listener for banner lifcycle events:
            adView.setListener(new BannerView.Listener() {
                @Override
                public void onBannerLoaded(BannerView bannerAdView) {
                    addLoadedTo();
                    addImpressionTo();
                }

                @Override
                public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                }

                @Override
                public void onBannerClick(BannerView bannerAdView) {

                }

                @Override
                public void onBannerLeftApplication(BannerView bannerAdView) {

                }
            });
            // Request a banner ad:
            adView.load();
            adContainer.addView(adView);

        }else if(activity != null){
            showWithDelay(new DelayListener() {
                @Override
                public void OnReady() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showBannerAd(adContainer);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void showBannerAd(RelativeLayout adContainer) {
        if (isInitialized()) {


            BannerView adView = getSmallBanner(activity);
            Log.e(TAG, "showBannerAd: "+getValue() );
            // Set the listener for banner lifcycle events:
            adView.setListener(new BannerView.Listener() {
                @Override
                public void onBannerLoaded(BannerView bannerAdView) {
                    addLoadedTo();
                    addImpressionTo();
                }

                @Override
                public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {

                }

                @Override
                public void onBannerClick(BannerView bannerAdView) {

                }

                @Override
                public void onBannerLeftApplication(BannerView bannerAdView) {

                }
            });
            // Request a banner ad:
            adView.load();
            adContainer.addView(adView);

        }else if(activity != null){
            showWithDelay(new DelayListener() {
                @Override
                public void OnReady() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showBannerAd(adContainer);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void showBannerAd(LinearLayout adContainer, MListener listener) {

        if (isInitialized()) {


            BannerView adView = getSmallBanner(activity);
                Log.e(TAG, "showBannerAd: "+getValue() );
            // Set the listener for banner lifcycle events:
            adView.setListener(new BannerView.Listener() {
                @Override
                public void onBannerLoaded(BannerView bannerAdView) {
                    addLoadedTo();
                    addImpressionTo();
                    listener.OnLoad();
                }

                @Override
                public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
               listener.OnError(errorInfo.errorMessage);
                }

                @Override
                public void onBannerClick(BannerView bannerAdView) {

                }

                @Override
                public void onBannerLeftApplication(BannerView bannerAdView) {

                }
            });
            // Request a banner ad:
            adView.load();
             adContainer.addView(adView);

        }else if(activity != null){
            showWithDelay(new DelayListener() {
                @Override
                public void OnReady() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showBannerAd(adContainer, listener);
                        }
                    });
                }
            });
        }

    }

    @Override
    public void showBannerAd(RelativeLayout adContainer, MListener listener) {

        if (isInitialized()) {


            BannerView adView = getSmallBanner(activity);
            Log.e(TAG, "showBannerAd: "+getValue() );
            // Set the listener for banner lifcycle events:
            adView.setListener(new BannerView.Listener() {
                @Override
                public void onBannerLoaded(BannerView bannerAdView) {
                    addLoadedTo();
                    addImpressionTo();
                    listener.OnLoad();
                }

                @Override
                public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                    listener.OnError(errorInfo.errorMessage);
                }

                @Override
                public void onBannerClick(BannerView bannerAdView) {

                }

                @Override
                public void onBannerLeftApplication(BannerView bannerAdView) {

                }
            });
            // Request a banner ad:
            adView.load();
            adContainer.addView(adView);

        }else if(activity != null){
            showWithDelay(new DelayListener() {
                @Override
                public void OnReady() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showBannerAd(adContainer, listener);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void showInterstitialAd(InterstitialListener listener) {

        if(AdManager.testAds) {
            Log.e(TAG, "showInterstitialAd: first "+(isInitialized())+" second: "+(UnityAds.isReady (getValue())) );
        }
            if (isInitialized() && UnityAds.isReady (getValue())) {

                UnityAds.addListener(new UnityListener(getType()){
                    @Override
                    public void OnLoad() {
                     addLoadedTo();
                    }

                    @Override
                    public void OnShow() {
                        addImpressionTo();
                        listener.OnShow();
                    }

                    @Override
                    public void OnClosed() {
                        listener.OnDismissed();
                    }

                    @Override
                    public void OnError(String fail) {
                        listener.OnError(fail);
                    }
                });
                UnityAds.show (activity, getValue());
            }else if(isInitialized() && !UnityAds.isReady (getValue())){
                listener.OnError("still not load "+getName());
                UnityAds.load(getValue(), new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String s) {
                        addLoadedTo();
                        showInterstitialAd(listener);
                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String s) {
listener.OnError(s);
                    }
                });
            }
    }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        if(isInitialized() && UnityAds.isReady (getValue())) {
            int actua = SubManager.getF();
            if (actua >= frec) {
                UnityAds.addListener(new UnityListener(getType()){
                    @Override
                    public void OnLoad() {
                        addLoadedTo();
                    }

                    @Override
                    public void OnShow() {
                        addImpressionTo();
                        listener.OnShow();
                    }

                    @Override
                    public void OnClosed() {
                        listener.OnDismissed();
                    }

                    @Override
                    public void OnError(String fail) {
                        listener.OnError(fail);
                    }
                });
                UnityAds.show (activity, getValue());
            } else {
                SubManager.saveF();
                listener.OnDismissed();
            }
        }else if(isInitialized() && !UnityAds.isReady (getValue())){
            UnityAds.load(getValue(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String s) {
                    addLoadedTo();
                    showInterstitalAdFrecuency(frec, listener);
                }

                @Override
                public void onUnityAdsFailedToLoad(String s) {
                    listener.OnError(s);
                }
            });
        }
    }


    public void showRewardedAd(RewardListener rewardListener){
        if (isInitialized() && UnityAds.isReady (getValue())) {

            UnityAds.addListener(new UnityListener(getType()){
                @Override
                public void OnLoad() {
                    addLoadedTo();
                }

                @Override
                public void OnShow() {
                    addImpressionTo();
                    rewardListener.OnShow();
                }

                @Override
                public void OnClosed() {
                    rewardListener.OnDismissed();
                }

                @Override
                public void OnError(String fail) {
                    rewardListener.OnError(fail);
                }

                @Override
                public void OnReward() {
                    rewardListener.onReward();
                }
            });
            UnityAds.show (activity, getValue());
        }else if(isInitialized() && !UnityAds.isReady (getValue())){
            UnityAds.load(getValue(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String s) {
                    addLoadedTo();
                    showInterstitialAd(rewardListener);
                }

                @Override
                public void onUnityAdsFailedToLoad(String s) {
                    rewardListener.OnError(s);
                }
            });
        }
    }
    private static String TAG= "MAIN";


    @Override
    public void OnReady() {


    }

private BannerView getSmallBanner(Activity activity){
        UnityBannerSize size = null;

            size = UnityBannerSize.getDynamicSize(activity);
   // Log.e(TAG, "getSmallBanner: "+size.getWidth() );
return  new BannerView(activity, getValue(), size);
}

private boolean isInitialized(){
        if(AdManager.testAds){
            Log.e(TAG, "isInitialized: initialized "+(initialized && activity != null) + " activity "+(activity != null) );
        }

        if(activity == null){
            try {
                activity = AdManager.getActivity();
            } catch (Exception e) {

            }
        }

        return initialized && activity != null;
}


}
