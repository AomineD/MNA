package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.unity3d.ads.IUnityAdsInitializationListener;
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

import static com.wineberryhalley.mna.net.AdManager.testAds;

public class UnityMNA extends AdMNA {
    private static boolean initialized = false;
    private static boolean getting = false;
     Activity activity = null;
    static InitializeListener initializeListener;
    public UnityMNA(AdMNA adMNA) {
        config(adMNA);

        try {
            activity = AdManager.getActivity();

            try {
                initializeListener = (InitializeListener) activity;
            }catch (Exception e){
                Log.e(TAG, "err: no listener" );
            }

        }  catch (Exception e) {
            Log.e(TAG, "err: no activity" );
        }

    }


    static void initialize(){
        Activity activity = AdManager.getActivity();

            try {
                initializeListener = (InitializeListener) activity;
            }catch (Exception e){
                Log.e("MAIN", "err: no listener" );
            }



        if (!initialized && !getting) {
            // Initialize the SDK:
            UnityAds.initialize(ChalaEdChala.context, AdManager.appId, testAds, new IUnityAdsInitializationListener() {
                @Override
                public void onInitializationComplete() {
                        initialized = true;


                        if (initializeListener != null) {
                            initializeListener.OnInitialized();
                        }
                }

                @Override
                public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
                    getting = false;
                    if(initializeListener != null){
                        initializeListener.OnInitializedError(message);
                    }
                }
            });
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
            showWithDelay(() -> activity.runOnUiThread(() -> showBannerAd(adContainer)));
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
            showWithDelay(() -> activity.runOnUiThread(() -> showBannerAd(adContainer)));
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
            showWithDelay(() -> activity.runOnUiThread(() -> showBannerAd(adContainer, listener)));
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
            showWithDelay(() -> activity.runOnUiThread(() -> showBannerAd(adContainer, listener)));
        }
    }

    @Override
    public void showInterstitialAd(InterstitialListener listener) {

        if(testAds) {
        Log.e(TAG, "showInterstitialAd: first "+(isInitialized()));
        }
            if (isInitialized() && UnityAds.isInitialized ()) {

                if(testAds){
                    Log.e(TAG, "showInterstitialAd: showing interstitial "+getType().name()+" value: "+getValue()+" in activity "+activity.getClass().getSimpleName() );
                }
                UnityAds.show (activity, getValue(), new UnityListener(getType()){
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

            }else if(isInitialized() && !UnityAds.isInitialized ()){
                listener.OnError("still not load "+getName());
                UnityAds.load(getValue(), new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String s) {
                        addLoadedTo();
                        showInterstitialAd(listener);
                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                        listener.OnError(message);
                    }

                   
                });
            }
    }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        if(isInitialized() && UnityAds.isInitialized ()) {
            int actua = SubManager.getF();
            if (actua >= frec) {
                UnityAds.show (activity, getValue(), new UnityListener(getType()){
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
                        SubManager.resetF();
                    }

                    @Override
                    public void OnError(String fail) {
                        listener.OnError(fail);
                    }
                });
            } else {
                SubManager.saveF();
                listener.OnDismissed();
            }
        }else if(isInitialized() && !UnityAds.isInitialized ()){
            UnityAds.load(getValue(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String s) {
                    addLoadedTo();
                    showInterstitalAdFrecuency(frec, listener);
                }

                @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                    listener.OnError(message);
                }
            });
        }
    }


    public void showRewardedAd(RewardListener rewardListener){
        if (isInitialized() && UnityAds.isInitialized ()) {

            UnityAds.show (activity, getValue(), new UnityListener(getType()){
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
        }else if(isInitialized() && !UnityAds.isInitialized ()){
            UnityAds.load(getValue(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String s) {
                    addLoadedTo();
                    showInterstitialAd(rewardListener);
                }

                 @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                     rewardListener.OnError(message);
                }
            });
        }
    }
    private final String TAG= "MAIN";


    @Override
    public void OnReady() {


    }

private BannerView getSmallBanner(Activity activity){
        UnityBannerSize size;

            size = UnityBannerSize.getDynamicSize(activity);
   // Log.e(TAG, "getSmallBanner: "+size.getWidth() );
return  new BannerView(activity, getValue(), size);
}

private boolean isInitialized(){
                activity = AdManager.getActivity();
    if(testAds){
        Log.e(TAG, "isInitialized: initialized "+(initialized && activity != null) + " activity "+(activity != null) );
    }
        return initialized && activity != null;
}


}
