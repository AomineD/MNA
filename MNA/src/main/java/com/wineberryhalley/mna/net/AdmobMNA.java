package com.wineberryhalley.mna.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;

import static com.wineberryhalley.mna.net.AdManager.cacheInterstitial;
import static com.wineberryhalley.mna.net.AdManager.ntUtils;
import static com.wineberryhalley.mna.net.AdManager.testAds;

public class AdmobMNA extends AdMNA{
    private Context context;
    private InterstitialAd interstitialAd;
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


    public void showBannerAd(LinearLayout adContainer, AdSize adSize) {
        AdView adView = new AdView(context);

        adView.setAdUnitId(getValue());
        adView.setAdSize(adSize);
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
       // Log.e(TAG, "showBannerAd: "+getValue() );
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

    public void showBannerAd(LinearLayout adContainer,AdSize adSize, MListener listener) {
        AdView adView = new AdView(context);
        // Log.e(TAG, "showBannerAd: "+getValue() );
        adView.setAdUnitId(getValue());
        adView.setAdSize(adSize);
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

        if(testAds){
            Log.e(TAG, "interstitialIsCached: "+(interstitialIsCached()) );
        }



        if(interstitialIsCached()){
            showIntersCached(listener);
            return;
        }

        if(activity != null) {
            InterstitialAd.load(context, getValue(), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    addLoadedTo();

                    if(testAds){
                        Log.e(TAG, "onAdLoaded: loaded ad, is cached? "+cacheInterstitial);
                    }

                    AdmobMNA.this.interstitialAd = interstitialAd;
                    if(!cacheInterstitial){
                        showInters(listener);
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);

                    if(testAds){
                        Log.e(TAG, "onAdFailedToLoad: is cached? "+cacheInterstitial+" error -> "+loadAdError.getMessage() );
                    }

                    if (listener != null) {
                        listener.OnError(loadAdError.getMessage());
                    }
                }
            });
        }else {
            if (listener != null){
                listener.OnError("No activity to show intersticial");
            }
trySetActivity();
        }
        }

    @Override
    protected boolean interstitialIsCached() {
        return interstitialAd != null;
    }

    @Override
    public void reloadInterstitialCached() {
        if(testAds) {
            Log.e(TAG, "reloadInterstitialCached: loading...");
        }
        showInterstitialAd(null);
    }

    @Override
    public void showIntersCached(InterstitialListener listener){
        if(interstitialIsCached()){
            showInters(listener);
        }else{
            listener.OnError("No interstitial cached");
        }
    }

    private void showInters(InterstitialListener listener){
        if(testAds){
            Log.e(TAG, "showInters: cached showing..." );
        }


        trySetActivity();
        if(activity != null) {
            listener.OnError("no activity");
            return;
        }

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
                interstitialAd = null;
                listener.OnDismissed();
                reloadInterstitialCached();
                SubManager.resetF();
            }
        });
        interstitialAd.show(activity);
    }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        int actua = SubManager.getF();
        if(actua >= frec) {
            if(activity != null) {
                if(interstitialIsCached()){
                    showIntersCached(listener);
                    return;
                }
                InterstitialAd.load(context, getValue(), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        addLoadedTo();

                        if(testAds){
                            Log.e(TAG, "onAdLoaded: loaded ad, is cached? "+cacheInterstitial);
                        }

                        AdmobMNA.this.interstitialAd = interstitialAd;
                        if(!cacheInterstitial){
                            showInters(listener);
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        if(testAds){
                            Log.e(TAG, "onAdFailedToLoad: is cached? "+cacheInterstitial+" error -> "+loadAdError.getMessage() );
                        }
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
        if(ntUtils != null){
            ntUtils.into(layout).showAdmobNative();
        }

    }



    @Override
    public void showBannerNativeIn(BannerNativeMNA layout) {
        if(ntUtils != null){
            ntUtils.into(layout).showAdmobNative();
        }
    }


}
