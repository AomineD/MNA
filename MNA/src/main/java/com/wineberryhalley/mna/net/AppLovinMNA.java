package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkUtils;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedAdListener;
import com.mopub.mobileads.MoPubRewardedAds;
import com.mopub.mobileads.MoPubView;
import com.wineberryhalley.mna.R;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.DelayListener;
import com.wineberryhalley.mna.base.InitializeListener;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;

import java.util.Set;

import static com.mopub.common.logging.MoPubLog.LogLevel.DEBUG;
import static com.mopub.common.logging.MoPubLog.LogLevel.INFO;
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

            MoPubView adView = new MoPubView(activity);
            adView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50);
            adView.setTesting(testAds);
            adView.setBannerAdListener(new MoPubView.BannerAdListener() {
                @Override
                public void onBannerLoaded(@NonNull MoPubView moPubView) {
                    addLoadedTo();
                    addImpressionTo();
                }

                @Override
                public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {

                }

                @Override
                public void onBannerClicked(MoPubView moPubView) {

                }

                @Override
                public void onBannerExpanded(MoPubView moPubView) {

                }

                @Override
                public void onBannerCollapsed(MoPubView moPubView) {

                }
            });
            // Request a banner ad:
            adView.setAdUnitId(getValue());
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
        //Log.e(TAG, "showBannerAd: a "+isInitialized() );
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


            MoPubView adView = new MoPubView(activity);
            adView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50);
            adView.setTesting(testAds);
            adView.setBannerAdListener(new MoPubView.BannerAdListener() {
                @Override
                public void onBannerLoaded(@NonNull MoPubView moPubView) {
                    addLoadedTo();
                    addImpressionTo();
                    listener.OnLoad();
                }

                @Override
                public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
                    listener.OnError("Mopub err code: "+moPubErrorCode.getIntCode());
                }

                @Override
                public void onBannerClicked(MoPubView moPubView) {

                }

                @Override
                public void onBannerExpanded(MoPubView moPubView) {

                }

                @Override
                public void onBannerCollapsed(MoPubView moPubView) {

                }
            });
            // Request a banner ad:
            adView.setAdUnitId(getValue());
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
                MoPubInterstitial moPubInterstitial = new MoPubInterstitial(activity, getValue());
                moPubInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                    @Override
                    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                        listener.OnLoad();
                //        Log.e(TAG, "onInterstitialLoaded: show" );
                        moPubInterstitial.show();
                    }

                    @Override
                    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
listener.OnError(getError(moPubErrorCode));
                    }

                    @Override
                    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
listener.OnShow();
                    }

                    @Override
                    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {

                    }

                    @Override
                    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
listener.OnDismissed();
                    }
                });

                moPubInterstitial.load();
            }
    }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        if(isInitialized()) {
            int actua = SubManager.getF();
            if (actua >= frec) {
                MoPubInterstitial moPubInterstitial = new MoPubInterstitial(activity, getValue());
                moPubInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                    @Override
                    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                        listener.OnLoad();

                        moPubInterstitial.show();
                    }

                    @Override
                    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
                        listener.OnError(getError(moPubErrorCode));
                    }

                    @Override
                    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
                        listener.OnShow();
                    }

                    @Override
                    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {

                    }

                    @Override
                    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
                        listener.OnDismissed();
                    }
                });

                moPubInterstitial.load();
            } else {
                SubManager.saveF();
                listener.OnDismissed();
            }
        }
    }


    public void showRewardedAd(RewardListener rewardListener){
        if (isInitialized()) {
            MoPubRewardedAds.setRewardedAdListener(new MoPubRewardedAdListener() {
                @Override
                public void onRewardedAdLoadSuccess(String s) {
                    rewardListener.OnLoad();
              //      Log.e(TAG, "onRewardedAdLoadSuccess: "+s );
                    MoPubRewardedAds.showRewardedAd(getValue());
                }

                @Override
                public void onRewardedAdLoadFailure(String s, MoPubErrorCode moPubErrorCode) {
rewardListener.OnError(getError(moPubErrorCode));
                }

                @Override
                public void onRewardedAdStarted(String s) {
                    rewardListener.OnShow();
                }

                @Override
                public void onRewardedAdShowError(String s, MoPubErrorCode moPubErrorCode) {
rewardListener.OnError(getError(moPubErrorCode));
                }

                @Override
                public void onRewardedAdClicked(String s) {

                }

                @Override
                public void onRewardedAdClosed(String s) {
rewardListener.OnDismissed();
                }

                @Override
                public void onRewardedAdCompleted(Set<String> set, MoPubReward moPubReward) {
rewardListener.onReward();
                }
            });
        //    Log.e(TAG, "showRewardedAd: "+getValue() );
            MoPubRewardedAds.loadRewardedAd(getValue());
        }
    }
    private static String TAG= "MAIN";

    @Override
    public void showNativeIn(NativeMNA layout) {

    }



    @Override
    public void OnReady() {


    }


    @Override
    public void showBannerNativeIn(BannerNativeMNA layout) {
      //  Log.e(TAG, "showBannerNativeIn: a" );

    }



private boolean isInitialized(){
                activity = AdManager.getActivity();
    if(testAds){
     //   Log.e(TAG, "isInitialized: initialized "+(initialized && activity != null) + " activity "+(activity != null) );
    }
        return initialized && activity != null && MoPub.isSdkInitialized();
}


private String getError(MoPubErrorCode er){
        return "Error code MoPub: "+er.getIntCode();
}

}
