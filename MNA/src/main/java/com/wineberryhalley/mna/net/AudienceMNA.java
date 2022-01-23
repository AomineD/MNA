package com.wineberryhalley.mna.net;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdExtendedListener;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;

import static com.wineberryhalley.mna.net.AdManager.ntUtils;
import static com.wineberryhalley.mna.net.AdManager.ntUtilsBannerNat;

public class AudienceMNA extends AdMNA {

    private Context context;
    public AudienceMNA(AdMNA adMNA){
        context = ChalaEdChala.context;
config(adMNA);
    }

    @Override
    public void showBannerAd(LinearLayout adContainer) {
     AdView   adView = new AdView(context, getValue(), AdSize.BANNER_HEIGHT_50);
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                addLoadedTo();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                addImpressionTo();
            }
        }).build());
        adContainer.addView(adView);
    }

    @Override
    public void showBannerAd(RelativeLayout adContainer) {
        AdView   adView = new AdView(context, getValue(), AdSize.BANNER_HEIGHT_50);
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                addLoadedTo();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                addImpressionTo();
            }
        }).build());
        adContainer.addView(adView);
    }

    @Override
    public void showBannerAd(LinearLayout adContainer, MListener listener) {
        AdView   adView = new AdView(context, getValue(), AdSize.BANNER_HEIGHT_50);
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                listener.OnError(adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                addLoadedTo();
listener.OnLoad();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
addImpressionTo();
            }
        }).build());
        adContainer.addView(adView);
    }

    @Override
    public void showBannerAd(RelativeLayout adContainer, MListener listener) {
        AdView   adView = new AdView(context, getValue(), AdSize.BANNER_HEIGHT_50);
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                listener.OnError(adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                addLoadedTo();
                listener.OnLoad();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                addImpressionTo();
            }
        }).build());
        adContainer.addView(adView);
    }

    @Override
    public void showInterstitialAd(InterstitialListener listener) {
        InterstitialAd interstitialAd = new InterstitialAd(context, getValue());
        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

                listener.OnShow();
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
listener.OnDismissed();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
listener.OnError(adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                addLoadedTo();
                interstitialAd.show();
listener.OnLoad();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                addImpressionTo();
            }
        }).build());
    }

    @Override
    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener) {
        int actua = SubManager.getF();
        if(actua >= frec) {
            InterstitialAd interstitialAd = new InterstitialAd(context, getValue());
            interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                    listener.OnShow();
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    SubManager.resetF();
                    listener.OnDismissed();

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    listener.OnError(adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    addLoadedTo();
                    listener.OnLoad();
                    interstitialAd.show();
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    addImpressionTo();
                }
            }).build());
        }else{
          SubManager.saveF();
            listener.OnDismissed();
        }
    }
    
    
    public void showRewardedAd(RewardListener rewardListener){
        RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(context, getValue());
        rewardedVideoAd.loadAd(rewardedVideoAd.buildLoadAdConfig().withAdListener(new RewardedVideoAdExtendedListener() {
            @Override
            public void onRewardedVideoActivityDestroyed() {

            }

            @Override
            public void onRewardedVideoCompleted() {
rewardListener.onReward();
            }

            @Override
            public void onRewardedVideoClosed() {
rewardListener.OnDismissed();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
rewardListener.OnError(adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                addLoadedTo();
rewardListener.OnLoad();
rewardedVideoAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                addImpressionTo();
            }
        }).build());
    }


    @Override
    public void showBannerNativeIn(BannerNativeMNA layout) {
        if(ntUtilsBannerNat != null){
            ntUtilsBannerNat.into(layout).showAudienceBannerNative(getValue());
        }
    }


    @Override
    public void showNativeIn(NativeMNA layout) {

        if(ntUtils != null)
        ntUtils.into(layout).showAudienceNative(getValue());
    }

}
