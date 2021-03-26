package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.wineberryhalley.mna.R;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;

public class AdmobMNA extends AdMNA{
    private Context context;
    static Activity activity = null;
    public AdmobMNA(AdMNA adMNA){
        context = ChalaEdChala.context;
        if(AdManager.testAds){
           adMNA.setValue(configAdsTestAdmob(adMNA));
        }
        config(adMNA);
        try {
            activity = AdManager.getActivity();



        }  catch (Exception e) {
            Log.e(TAG, "err: no activity" );
        }
    }

    private String configAdsTestAdmob(AdMNA adMNA) {

   switch (adMNA.getType()){
       case BANNER:
           return context.getString(R.string.banner_ad_test_admob);
       case INTERSTICIAL:
           return context.getString(R.string.interstitial_ad_test_admob);
       case REWARD:
           return context.getString(R.string.reward_ad_test_admob);
       case NATIVO:
           return context.getString(R.string.native_ad_test_admob);

   }

   return "";
    }

    private String TAG = "MAIN";


    @Override
    public void showBannerAd(LinearLayout adContainer) {
        AdView adView = new AdView(context);

        adView.setAdUnitId(getValue());
adView.setAdSize(AdSize.BANNER);
adView.setAdListener(new AdListener(){
    @Override
    public void onAdLoaded() {
        super.onAdLoaded();
        addLoadedTo();
    }

    @Override
    public void onAdImpression() {
        super.onAdImpression();
        addImpressionTo();
    }
});

        adView.loadAd(new AdRequest.Builder().build());
        adContainer.addView(adView);
    }

    @Override
    public void showBannerAd(RelativeLayout adContainer) {
        AdView adView = new AdView(context);

        adView.setAdUnitId(getValue());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                addLoadedTo();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                addImpressionTo();
            }
        });

        adView.loadAd(new AdRequest.Builder().build());
        adContainer.addView(adView);
    }

    @Override
    public void showBannerAd(LinearLayout adContainer, MListener listener) {
        AdView adView = new AdView(context);

        adView.setAdUnitId(getValue());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                addLoadedTo();
                listener.OnLoad();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                listener.OnError(loadAdError.getMessage());
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                addImpressionTo();
            }
        });

        adView.loadAd(new AdRequest.Builder().build());
        adContainer.addView(adView);

    }

    @Override
    public void showBannerAd(RelativeLayout adContainer, MListener listener) {
        AdView adView = new AdView(context);

        adView.setAdUnitId(getValue());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                addLoadedTo();
                listener.OnLoad();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                listener.OnError(loadAdError.getMessage());
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                addImpressionTo();
            }
        });

        adView.loadAd(new AdRequest.Builder().build());
        adContainer.addView(adView);

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
        if(activity != null) {
            InterstitialAd.load(context, getValue(), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    addLoadedTo();
                    interstitialAd.setOnPaidEventListener(new OnPaidEventListener() {
                        @Override
                        public void onPaidEvent(AdValue adValue) {
                            Log.e("MAIN", "onPaidEvent: " + adValue.getCurrencyCode());
                        }
                    });
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            addImpressionTo();
                            listener.OnShow();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            listener.OnDismissed();
                        }
                    });
                    interstitialAd.show(activity);

                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    listener.OnError(loadAdError.getMessage());
                }
            });
        }else{
            listener.OnError("No activity to show intersticial");
trySetActivity();
        }
        }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        int actua = SubManager.getF();
        if(actua >= frec) {
            if(activity != null) {
                InterstitialAd.load(context, getValue(), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        addLoadedTo();
                        interstitialAd.setOnPaidEventListener(new OnPaidEventListener() {
                            @Override
                            public void onPaidEvent(AdValue adValue) {
                                Log.e("MAIN", "onPaidEvent: " + adValue.getCurrencyCode());
                            }
                        });
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                                addImpressionTo();
                                listener.OnShow();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                listener.OnDismissed();
                            }
                        });
                        interstitialAd.show(activity);

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        listener.OnError(loadAdError.getMessage());
                    }
                });
            }else{
                listener.OnError("No activity to show intersticial");
                trySetActivity();
            }
        }else{
            SubManager.saveF();
            listener.OnDismissed();
        }
    }


    public void showRewardedAd(RewardListener rewardListener){
        if(activity != null) {
            RewardedAd.load(context, getValue(), new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                    super.onAdLoaded(rewardedAd);
                    addLoadedTo();
                    rewardListener.OnLoad();
                    rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            rewardListener.OnError(adError.getMessage());
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            rewardListener.OnShow();
                            addImpressionTo();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            rewardListener.OnDismissed();
                        }
                    });
                    rewardedAd.setImmersiveMode(true);
                    rewardedAd.show(activity, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            rewardListener.onReward();
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    rewardListener.OnError(loadAdError.getMessage());
                }
            });
        }else{
            rewardListener.OnError("No activity to show reward");
            trySetActivity();
        }

    }


    @Override
    public void showNativeIn(NativeMNA layout) {
        new NtUtils(context, getValue()).loadAdmobNative().setListener(new NtUtils.OnNativeLoadInterface() {
            @Override
            public void OnSuccess() {
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
        new NtUtils(context, getValue()).loadAdmobNative().setListener(new NtUtils.OnNativeLoadInterface() {
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

}
