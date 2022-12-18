package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkUtils;
import com.mopub.common.MoPub;
import com.mopub.mobileads.MoPubErrorCode;
import com.wineberryhalley.mna.R;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.DelayListener;
import com.wineberryhalley.mna.base.InitializeListener;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;

import static com.wineberryhalley.mna.net.AdManager.ntUtils;
import static com.wineberryhalley.mna.net.AdManager.ntUtilsBannerNat;
import static com.wineberryhalley.mna.net.AdManager.testAds;

public class AppLovinMNA extends AdMNA {
    private Context context;
    private static boolean initialized = false;
    private static boolean getting = false;
    private static int c_un_count = 0;
  static Activity activity = null;
    static InitializeListener initializeListener;
    public AppLovinMNA(AdMNA adMNA) {
        context = ChalaEdChala.context;
        if(AdManager.testAds){
          //  adMNA.setValue(configAdsTestMoPub(adMNA));
        }
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

    private String configAdsTestMoPub(AdMNA adMNA) {

        switch (adMNA.getType()){
            case BANNER:
                return context.getString(R.string.banner_ad_test_mopub);
            case INTERSTICIAL:
                return context.getString(R.string.interstitial_ad_test_mopub);
            case REWARD:
                return context.getString(R.string.reward_ad_test_mopub);
            case NATIVO:
                return context.getString(R.string.native_ad_test_mopub);

        }

        return "";
    }


    static void initialize(){
            activity = AdManager.getActivity();

            try {
                initializeListener = (InitializeListener) activity;
            }catch (Exception e){
                Log.e(TAG, "err: no listener" );
            }



        if (!initialized && !getting && activity != null) {

            AppLovinSdk.getInstance( activity ).setMediationProvider( "max" );
            AppLovinSdk.getInstance(activity).getSettings().setCreativeDebuggerEnabled(testAds);
            AppLovinSdk.initializeSdk( activity, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
                {
                    // AppLovin SDK is initialized, start loading ads
                    if(initializeListener != null){
                        //      Log.e(TAG, "onInitializationFinished: "+adunit );

                        initializeListener.OnInitialized();
                    }
                    initialized = true;
                }
            } );
      getting = true;
        }
    }

    @Override
    public void showBannerAd(LinearLayout adContainer) {
        if (isInitialized()) {


          MaxAdView adView = new MaxAdView( getValue(), activity );
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
addLoadedTo();
addImpressionTo();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });

            // Stretch to the width of the screen for banners to be fully functional
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            // Banner height on phones and tablets is 50 and 90, respectively
            // Get the adaptive banner height.
            int heightDp = MaxAdFormat.BANNER.getAdaptiveSize( activity ).getHeight();
            int heightPx = AppLovinSdkUtils.dpToPx( activity, heightDp );


            adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );



            // Load the ad
            adView.loadAd();
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

            MaxAdView adView = new MaxAdView( getValue(), activity );
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    addLoadedTo();
                    addImpressionTo();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });

            // Stretch to the width of the screen for banners to be fully functional
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            // Banner height on phones and tablets is 50 and 90, respectively
            // Get the adaptive banner height.
            int heightDp = MaxAdFormat.BANNER.getAdaptiveSize( activity ).getHeight();
            int heightPx = AppLovinSdkUtils.dpToPx( activity, heightDp );


            adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );



            // Load the ad
            adView.loadAd();
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
        Log.e(TAG, "showBannerAd: a "+isInitialized() );
        if (isInitialized()) {

            Log.e(TAG, "showBannerAd: "+getValue()+" TO BIEN POR ACA" );
            MaxAdView adView = new MaxAdView( getValue(), activity );
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    addLoadedTo();
                    addImpressionTo();
                    listener.OnLoad();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
listener.OnError(error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });

            // Stretch to the width of the screen for banners to be fully functional
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            // Banner height on phones and tablets is 50 and 90, respectively
            // Get the adaptive banner height.
            int heightDp = MaxAdFormat.BANNER.getAdaptiveSize( activity ).getHeight();
            int heightPx = AppLovinSdkUtils.dpToPx( activity, heightDp );


            adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );



            // Load the ad
            adView.loadAd();
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


            MaxAdView adView = new MaxAdView( getValue(), activity );
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    addLoadedTo();
                    addImpressionTo();

                    listener.OnLoad();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
listener.OnError(error.getMessage()+ " - id unit: "+adUnitId);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });

            // Stretch to the width of the screen for banners to be fully functional
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            // Banner height on phones and tablets is 50 and 90, respectively
            // Get the adaptive banner height.
            int heightDp = MaxAdFormat.BANNER.getAdaptiveSize( activity ).getHeight();
            int heightPx = AppLovinSdkUtils.dpToPx( activity, heightDp );


            adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );



            // Load the ad
            adView.loadAd();
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

            if (isInitialized()) {
                MaxInterstitialAd interstitialAd = new MaxInterstitialAd(getValue(), activity );
                interstitialAd.setListener(new MaxAdViewAdListener() {
                    @Override
                    public void onAdExpanded(MaxAd ad) {

                    }

                    @Override
                    public void onAdCollapsed(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        interstitialAd.showAd();
                        if(listener != null)
                        listener.OnLoad();
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {
                        if(listener != null)
                        listener.OnShow();
                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        if(listener != null)
                        listener.OnDismissed();
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        if(listener != null)
                        listener.OnError(error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });

                // Load the first ad
                interstitialAd.loadAd();

            }
    }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        if(isInitialized()) {
            int actua = SubManager.getF();
            if (actua >= frec) {
                MaxInterstitialAd interstitialAd = new MaxInterstitialAd( getValue(), activity );
                interstitialAd.setListener(new MaxAdViewAdListener() {
                    @Override
                    public void onAdExpanded(MaxAd ad) {

                    }

                    @Override
                    public void onAdCollapsed(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        interstitialAd.showAd();
                        if(listener != null)
                        listener.OnLoad();
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {
                        if(listener != null)
                        listener.OnShow();
                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        if(listener != null)
                        listener.OnDismissed();
                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        if(listener != null)
                        listener.OnError(error.getMessage());
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                });

                // Load the first ad
                interstitialAd.loadAd();
            } else {
                SubManager.saveF();
                listener.OnDismissed();
            }
        }
    }


    public void showRewardedAd(RewardListener rewardListener){
        if (isInitialized()) {
          MaxRewardedAd  rewardedAd = MaxRewardedAd.getInstance( getValue(), activity );
            rewardedAd.setListener(new MaxRewardedAdListener() {
                @Override
                public void onRewardedVideoStarted(MaxAd ad) {

                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {

                }

                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {
rewardListener.onReward();
                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    rewardedAd.showAd();
rewardListener.OnLoad();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
rewardListener.OnShow();
                }

                @Override
                public void onAdHidden(MaxAd ad) {
rewardListener.OnDismissed();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
rewardListener.OnError(error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });

            rewardedAd.loadAd();
        }
    }
    private static String TAG= "MAIN";


    @Override
    public void showNativeIn(NativeMNA layout) {
       if(ntUtils != null){
           ntUtils.into(layout).showALNative(getValue());
       }
    }



    @Override
    public void OnReady() {


    }


    @Override
    public void showBannerNativeIn(BannerNativeMNA layout) {

        if(ntUtilsBannerNat != null){
            ntUtilsBannerNat.into(layout).showALBannerNative(getValue());
        }
    }



private boolean isInitialized(){
                activity = AdManager.getActivity();
    if(testAds){
       // Log.e(TAG, "isInitialized: initialized "+(initialized && activity != null) + " activity "+(activity != null) );
    }
        return initialized && activity != null;
}


}
