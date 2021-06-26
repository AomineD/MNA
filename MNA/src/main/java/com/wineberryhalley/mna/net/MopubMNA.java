package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedAdListener;
import com.mopub.mobileads.MoPubRewardedAds;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubRecyclerAdapter;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.ViewBinder;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;
import com.wineberryhalley.mna.R;
import com.wineberryhalley.mna.base.DelayListener;
import com.wineberryhalley.mna.base.InitializeListener;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;
import com.wineberryhalley.mna.base.UnityListener;

import java.util.Set;

import static com.mopub.common.logging.MoPubLog.LogLevel.DEBUG;
import static com.mopub.common.logging.MoPubLog.LogLevel.INFO;
import static com.wineberryhalley.mna.net.AdManager.testAds;

public class MopubMNA extends AdMNA {
    private Context context;
    private static boolean initialized = false;
    private static boolean getting = false;
    private static int c_un_count = 0;
  static Activity activity = null;
    static InitializeListener initializeListener;
    public MopubMNA(AdMNA adMNA) {
        context = ChalaEdChala.context;
        if(AdManager.testAds){
            adMNA.setValue(configAdsTestMoPub(adMNA));
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


    static void initialize(String adunit){
            activity = AdManager.getActivity();

            try {
                initializeListener = (InitializeListener) activity;
            }catch (Exception e){
                Log.e(TAG, "err: no listener" );
            }



        if (!initialized && !getting && activity != null) {

            SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(adunit);

            if (testAds) {
                sdkConfiguration.withLogLevel(DEBUG);
            } else {
                sdkConfiguration.withLogLevel(INFO);
            }

            MoPub.initializeSdk(activity, sdkConfiguration.build(), new SdkInitializationListener() {
                @Override
                public void onInitializationFinished() {
                 if(initializeListener != null){
               //      Log.e(TAG, "onInitializationFinished: "+adunit );

                     initializeListener.OnInitialized();
                 }
                 initialized = true;
                }
            });
      getting = true;
        }
    }

    @Override
    public void showBannerAd(LinearLayout adContainer) {
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
        new NtUtils(context, getValue()).loadMoPubNative().setListener(new NtUtils.OnNativeLoadInterface() {
            @Override
            public void OnSuccess()
            {
                addLoadedTo();
            }

            @Override
            public void OnFail(String ss, int pos) {
                layout.setVisibility(View.GONE);
            }
            @Override
            public void OnImpression() {
                addImpressionTo();
            }
        }).into(layout);
    }

    @Override
    public void showNativeIn(NativeMNA layout, MListener listener) {
    //    Log.e(TAG, "showNativeIn: " );
        new NtUtils(context, getValue()).loadMoPubNative().setListener(new NtUtils.OnNativeLoadInterface() {
            @Override
            public void OnSuccess()
            {
                addLoadedTo();
                listener.OnLoad();
            }

            @Override
            public void OnFail(String ss, int pos) {
                listener.OnError(ss);
                layout.setVisibility(View.GONE);
            }
            @Override
            public void OnImpression() {
                addImpressionTo();
            }
        }).into(layout);
    }

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
