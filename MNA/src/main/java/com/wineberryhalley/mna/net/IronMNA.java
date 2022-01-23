package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;

public class IronMNA extends AdMNA{
    private Context context;
    static Activity activity = null;
    public IronMNA(AdMNA adMNA){
        context = ChalaEdChala.context;

        config(adMNA);
        try {
            activity = AdManager.getActivity();

            if(!loaded) {
initializeIron();
            }
        }  catch (Exception e) {
            Log.e(TAG, "err: no activity" );
        }
    }


    private String TAG = "MAIN";

    private static boolean loaded = false;
public static void initializeIron() {


    if (activity == null) {
        try {
            activity = AdManager.getActivity();

            //     IronSource.init(this, getValue());
            IronSource.init(activity, AdManager.appId);
            loaded = true;
            IronSource.setMetaData("Facebook_IS_CacheFlag","ALL");
      //      Log.e("MAIN", "initializeIron: " + AdManager.appId);
            IronSource.setMetaData("AdMob_TFCD","false");
            IronSource.setMetaData("AdMob_TFUA","false");
if(AdManager.testAds)
            IntegrationHelper.validateIntegration(activity);


        } catch (Exception e) {
            Log.e("MAIN", "err: no activity");
        }
    } else {

        IronSource.init(activity, AdManager.appId);
    }
}

    @Override
    public void showBannerAd(LinearLayout adContainer) {
        if(isReady()){
            IronSourceBannerLayout bannerLayout = IronSource.createBanner(activity, ISBannerSize.BANNER);


            bannerLayout.setBannerListener(new BannerListener() {
                @Override
                public void onBannerAdLoaded() {
                   // Log.e(TAG, "onBannerAdLoaded: a" );
                    addLoadedTo();
                }

                @Override
                public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                   // Log.e(TAG, "onBannerAdLoadFailed: "+ironSourceError.getErrorMessage() );
                }

                @Override
                public void onBannerAdClicked() {

                }

                @Override
                public void onBannerAdScreenPresented() {
addImpressionTo();
                }

                @Override
                public void onBannerAdScreenDismissed() {

                }

                @Override
                public void onBannerAdLeftApplication() {

                }
            });
            IronSource.loadBanner(bannerLayout, getValue());


     //       Log.e(TAG, "showBannerAd: cargando banner "+(adContainer.getVisibility() == View.VISIBLE)+" "+getValue() );
            adContainer.addView(bannerLayout);

        }else{
            initializeIron();
        }
    }

    @Override
    public void showBannerAd(RelativeLayout adContainer) {
        if(isReady()){
            IronSourceBannerLayout bannerLayout = IronSource.createBanner(activity, ISBannerSize.BANNER);
            bannerLayout.setPlacementName(getValue());
            bannerLayout.setBannerListener(new BannerListener() {
                @Override
                public void onBannerAdLoaded() {
                    addLoadedTo();
                }

                @Override
                public void onBannerAdLoadFailed(IronSourceError ironSourceError) {

                }

                @Override
                public void onBannerAdClicked() {

                }

                @Override
                public void onBannerAdScreenPresented() {
                    addImpressionTo();
                }

                @Override
                public void onBannerAdScreenDismissed() {

                }

                @Override
                public void onBannerAdLeftApplication() {

                }
            });

            IronSource.loadBanner(bannerLayout);

            adContainer.addView(bannerLayout);

        }else{
            initializeIron();
        }
    }

    @Override
    public void showBannerAd(LinearLayout adContainer, MListener listener) {
        if(isReady()){
            IronSourceBannerLayout bannerLayout = IronSource.createBanner(activity, ISBannerSize.BANNER);

            bannerLayout.setBannerListener(new BannerListener() {
                @Override
                public void onBannerAdLoaded() {
                    listener.OnLoad();
                    addLoadedTo();
                    bannerLayout.removeBannerListener();



                }

                @Override
                public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
listener.OnError(ironSourceError.getErrorMessage());
                    bannerLayout.removeBannerListener();
                }

                @Override
                public void onBannerAdClicked() {

                }

                @Override
                public void onBannerAdScreenPresented() {
addImpressionTo();
                }

                @Override
                public void onBannerAdScreenDismissed() {

                }

                @Override
                public void onBannerAdLeftApplication() {

                }
            });

          //  Log.e(TAG, "showBannerAd: "+getValue() );
            IronSource.loadBanner(bannerLayout, getValue());

            adContainer.addView(bannerLayout);

        }else{
            initializeIron();
        }

    }

    @Override
    public void showBannerAd(RelativeLayout adContainer, MListener listener) {
        if(isReady()){
            IronSourceBannerLayout bannerLayout = IronSource.createBanner(activity, ISBannerSize.BANNER);
            bannerLayout.setPlacementName(getValue());

            bannerLayout.setBannerListener(new BannerListener() {
                @Override
                public void onBannerAdLoaded() {
                    listener.OnLoad();
                    addLoadedTo();
                }

                @Override
                public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                    listener.OnError(ironSourceError.getErrorMessage());
                }

                @Override
                public void onBannerAdClicked() {

                }

                @Override
                public void onBannerAdScreenPresented() {
addImpressionTo();
                }

                @Override
                public void onBannerAdScreenDismissed() {

                }

                @Override
                public void onBannerAdLeftApplication() {

                }
            });

            IronSource.loadBanner(bannerLayout);

            adContainer.addView(bannerLayout);

        }else{
            initializeIron();
        }

    }

    private void trySetActivity(){
        try {
            activity = AdManager.getActivity();



        }  catch (Exception e) {
            Log.e(TAG, "err: no activity" );
        }
    }

    @Override
    public void showInterstitialAd(InterstitialListener listener) {
        if(isReady()) {

            IronSource.setInterstitialListener(new com.ironsource.mediationsdk.sdk.InterstitialListener() {
                @Override
                public void onInterstitialAdReady() {
                    listener.OnLoad();
                    addLoadedTo();
                    if(IronSource.isInterstitialReady()){
                        AdManager.showingInterstitial = true;
                        IronSource.showInterstitial();
                    }
                    IronSource.removeInterstitialListener();
                }

                @Override
                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
listener.OnError(ironSourceError.getErrorMessage());
                    IronSource.removeInterstitialListener();
                }

                @Override
                public void onInterstitialAdOpened() {

                }

                @Override
                public void onInterstitialAdClosed() {
listener.OnDismissed();
                    AdManager.showingInterstitial = false;
                }

                @Override
                public void onInterstitialAdShowSucceeded() {
                    addImpressionTo();
listener.OnShow();
                }

                @Override
                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
listener.OnError(ironSourceError.getErrorMessage());
                    IronSource.removeInterstitialListener();
                }

                @Override
                public void onInterstitialAdClicked() {

                }
            });

            IronSource.loadInterstitial();

        }else{
            listener.OnError("No activity to show intersticial");
initializeIron();
        }

        }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        int actua = SubManager.getF();
        if(actua >= frec) {
            if(isReady()) {

                IronSource.setInterstitialListener(new com.ironsource.mediationsdk.sdk.InterstitialListener() {
                    @Override
                    public void onInterstitialAdReady() {
                        listener.OnLoad();
                        addLoadedTo();
                        if(IronSource.isInterstitialReady()){
                            AdManager.showingInterstitial = true;
                            IronSource.showInterstitial();
                        }
                        IronSource.removeInterstitialListener();
                    }

                    @Override
                    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                        listener.OnError(ironSourceError.getErrorMessage());
                        IronSource.removeInterstitialListener();
                    }

                    @Override
                    public void onInterstitialAdOpened() {

                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        AdManager.showingInterstitial = false;
                        SubManager.resetF();
                        listener.OnDismissed();
                    }

                    @Override
                    public void onInterstitialAdShowSucceeded() {
                        addImpressionTo();
                        listener.OnShow();
                    }

                    @Override
                    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                        listener.OnError(ironSourceError.getErrorMessage());
                        IronSource.removeInterstitialListener();
                    }

                    @Override
                    public void onInterstitialAdClicked() {

                    }
                });

                IronSource.loadInterstitial();

            }else{
                listener.OnError("No activity to show intersticial");
                initializeIron();
            }
        }else{
            SubManager.saveF();
            listener.OnDismissed();
        }
    }


    public void showRewardedAd(RewardListener rewardListener){
        if(isReady()) {

            if(IronSource.isRewardedVideoAvailable()) {


            IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                @Override
                public void onRewardedVideoAdOpened() {

                    addImpressionTo();
                    rewardListener.OnShow();

                }

                @Override
                public void onRewardedVideoAdClosed() {
rewardListener.OnDismissed();
                    AdManager.showingInterstitial = false;
                    IronSource.removeRewardedVideoListener();
                }

                @Override
                public void onRewardedVideoAvailabilityChanged(boolean b) {
                 //   Log.e(TAG, "onRewardedVideoAvailabilityChanged: "+b );

                }

                @Override
                public void onRewardedVideoAdStarted() {

                }

                @Override
                public void onRewardedVideoAdEnded() {

                }

                @Override
                public void onRewardedVideoAdRewarded(Placement placement) {
rewardListener.onReward();
                }

                @Override
                public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
rewardListener.OnError(ironSourceError.getErrorMessage());
                    IronSource.removeRewardedVideoListener();
                }

                @Override
                public void onRewardedVideoAdClicked(Placement placement) {

                }
            });
                AdManager.showingInterstitial = true;
                IronSource.showRewardedVideo();

            }

        }else{
            rewardListener.OnError("No activity to show reward");
          initializeIron();
        }

    }


    @Override
    public void showNativeIn(NativeMNA layout) {

    if(isReady()){
                        showBannerAd(layout);

    }else{
        initializeIron();
    }

    }



    @Override
    public void showBannerNativeIn(BannerNativeMNA layout) {
        if(isReady()){
            showBannerAd(layout);

        }else{
            initializeIron();
        }
    }


    public boolean isReady(){
    if(!loaded){
        return false;
    }


    if(activity == null){
        trySetActivity();
    }

    return loaded && activity != null;
    }
}
