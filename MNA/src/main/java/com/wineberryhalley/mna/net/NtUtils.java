package com.wineberryhalley.mna.net;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.squareup.picasso.Picasso;
import com.wineberryhalley.mna.R;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.NativeMNA;

import java.util.ArrayList;

public class NtUtils {
    
    public static NtUtils getInstance(Context c, String ad_unit){
        return new NtUtils(c, ad_unit);
    }

    public static NtUtils getInstance(Context c, String ad_unit, OnNativeLoadInterface a){
        return new NtUtils(c, ad_unit, a);
    }

    protected NtUtils(Context c, String ad_unit) {
        this.context = c;
        this.idsnat = ad_unit;


    }

    protected NtUtils(Context c, String ad_unit, OnNativeLoadInterface a) {
        this.context = c;
        this.idsnat = ad_unit;
this.intre = a;

    }

    public NtUtils setListener(OnNativeLoadInterface a){
        this.intre = a;
        return this;
    }

    public void into(BannerNativeMNA bn){
        this.banner_container = bn.getView();
    }

    public void into(NativeMNA bn){
        this.banner_container = bn.getView(AdManager.network);
    }

    private View banner_container;
    private NativeAdView nativeAdView;
    private OnNativeLoadInterface intre;
    private final String idsnat;
    private final ArrayList<View> clickables = new ArrayList<>();
    private NativeAd nativeAd;
    private NativeBannerAd nativeBannerAd;
    private final Context context;


    public interface OnNativeLoadInterface {
        void OnSuccess();

        void OnFail(String ss, int pos);
        void OnImpression();
    }


    protected NtUtils loadAds(){

        nativeAd = new NativeAd(context, idsnat);
        nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {


                    if(AdManager.testAds)
                        Log.e("MAIN", "NATIVOS onError: "+adError.getErrorMessage() + " el index => 0");

                    if (intre != null)
                        intre.OnFail(adError.getErrorMessage() + " el index => 0", 0);


                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if(banner_container != null)
                    populateNativeIn();
                    if (intre != null) {
                        intre.OnSuccess();
                    }

                    if(AdManager.testAds){
                        Log.e("MAIN", "NATIVOS onAdLoaded: 0");
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    if (intre != null) {
                        intre.OnImpression();
                    }
                }
            }).build();
        nativeAd.loadAd();
           

            if(AdManager.testAds)
                Log.e("MAIN", "loadAds: loading native 0");
        
        return this;
    }

    protected NtUtils loadAdmobNative(){
        AdLoader adLoader = new AdLoader.Builder(context, idsnat)
                .forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                        if(nativeAdView == null){
                            nativeAdView = (NativeAdView) banner_container;
                        }
populateUnifiedNativeAdView(nativeAd, nativeAdView);
                    }
                })
                .withAdListener(new AdListener(){
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        if(intre != null){
                            intre.OnFail(loadAdError.getMessage(), 0);
                        }
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        if(intre != null){
                            intre.OnSuccess();
                        }
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        if(intre != null){
                            intre.OnImpression();
                        }
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());
        return this;
    }

    private void loadBannerAgain(){
        banner_container.setVisibility(View.GONE);
        nativeBannerAd = new NativeBannerAd(context, nativeBannerAd.getPlacementId());



        //   Log.e("MAIN", "loadBannerAgain: "+index );

        nativeBannerAd.buildLoadAdConfig().withAdListener(new NativeAdListener(){
            @Override
            public void onMediaDownloaded(Ad ad) {

                        populateBannerNativeIn();
                        
                //         Log.e("MAIN", "now load: "+index );
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                //     Log.e("MAIN", "loadBannerAgain error: "+index );
//new Timer().schedule(canReload(), 7000);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (intre != null) {
                    intre.OnSuccess();
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                if (intre != null) {
                    intre.OnImpression();
                }
            }
        }).build();


        nativeBannerAd.loadAd();
    }

    private void loadNativeAgain(){
        banner_container.setVisibility(View.GONE);
        nativeAd = new NativeAd(context, nativeAd.getPlacementId());



        //   Log.e("MAIN", "loadBannerAgain: "+index );

        nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener(){
            @Override
            public void onMediaDownloaded(Ad ad) {

        

                        populateNativeIn();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                //     Log.e("MAIN", "loadBannerAgain error: "+index );
//new Timer().schedule(canReload(), 7000);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (intre != null) {
                    intre.OnSuccess();
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                if (intre != null) {
                    intre.OnImpression();
                }
            }
        }).build();


        nativeAd.loadAd();
    }

    // Banner nativo aaa ===============================================


    protected NtUtils loadBannerAds(){
        nativeBannerAd = new NativeBannerAd(context, idsnat);
        nativeBannerAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {


                    if(AdManager.testAds)
                        Log.e("MAIN", "NATIVOS onError: "+adError.getErrorMessage() + " el index => 0");

                    if (intre != null)
                        intre.OnFail(adError.getErrorMessage(), 0);


                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if(banner_container != null)
                        populateBannerNativeIn();
                    if (intre != null) {
                        intre.OnSuccess();
                    }

                    if(AdManager.testAds){
                        Log.e("MAIN", "NATIVOS onAdLoaded: ");
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    if (intre != null) {
                        intre.OnImpression();
                    }
                }
            }).build();
        nativeBannerAd.loadAd();

            if(AdManager.testAds)
                Log.e("MAIN", "loadAds: loading native 0");
        return this;
    }


    public void populateNativeIn() {

        final TextView action;
        final TextView title_ad;
        final TextView desc_ad;
        final ImageView ad_choices;
        final TextView sponsor;
        final CardView button_action;
        final CardView background;
        final MediaView mediaView;
        final ImageView iconView;


        action = banner_container.findViewById(R.id.callto);
        title_ad = banner_container.findViewById(R.id.title_ad);
        sponsor = banner_container.findViewById(R.id.sponsor_ad);
        desc_ad = banner_container.findViewById(R.id.sponsor_adw);
        //      normal_view = itemView.findViewById(R.id.normal_view);

        button_action = banner_container.findViewById(R.id.button_action);
            mediaView = banner_container.findViewById(R.id.media_view);
            iconView = banner_container.findViewById(R.id.ad_icon_view);
            Log.e("MAIN", "setupViews: "+(mediaView!=null) );


        background = banner_container.findViewById(R.id.card);
        ad_choices = banner_container.findViewById(R.id.ad_choices);
        background.setCardBackgroundColor(context.getResources().getColor(R.color.white_nt));
        desc_ad.setTextColor(context.getResources().getColor(R.color.black_nt));
        title_ad.setTextColor(context.getResources().getColor(R.color.black_nt));
        sponsor.setTextColor(context.getResources().getColor(R.color.black_nt));
        button_action.setCardBackgroundColor(context.getResources().getColor(R.color.white_nt));
        action.setTextColor(context.getResources().getColor(R.color.black_nt));

        if (nativeAd != null && nativeAd.isAdLoaded()) {
            banner_container.setVisibility(View.VISIBLE);
            String title = nativeAd.getAdvertiserName();
            String provider = nativeAd.getSponsoredTranslation();
            String boton_action = nativeAd.getAdCallToAction();
            String patrocinador = nativeAd.getAdBodyText();
            String sp = nativeAd.getAdTranslation();

            //  com.facebook.ads.AdChoicesView adChoicesView = new com.facebook.ads.AdChoicesView(context, nativeAd, true);
            if (nativeAd.getAdChoicesImageUrl() != null)
                Picasso.get().load(Uri.parse(nativeAd.getAdChoicesImageUrl())).fit().into(ad_choices);

            title_ad.setText(title);
            desc_ad.setText(patrocinador);

            sponsor.setText(sp);

            //    ad_choices.addView(adChoicesView, 0);
            //  Log.e("MAIN", "setupViews: COLOR => "+textco);
            action.setText(boton_action);


            if (clickables.size() < 1) {
                clickables.add(button_action);
                // clickables.add(title_ad);
                // clickables.add(sponsor);
               /* if(mediaView!=null)
                clickables.add(mediaView);*/
                clickables.add(action);
            } else if (!clickables.contains(button_action)) {
                clickables.add(button_action);
                //       clickables.add(title_ad);
                //     if(mediaView!=null)
                //   clickables.add(mediaView);
                // clickables.add(sponsor);
                clickables.add(action);
            }
            
                nativeAd.registerViewForInteraction(banner_container, mediaView, iconView, clickables);
           
        } else if(nativeAd != null){

            loadNativeAgain();
        }
    }



    public void populateBannerNativeIn() {
final int textco = context.getResources().getColor(R.color.black_nt);
final int colorbck = context.getResources().getColor(R.color.white_nt);
        final TextView action;
        final TextView title_ad;
        final TextView desc_ad;
        final ImageView ad_choices;
        final TextView sponsor;
        final CardView button_action;
        final CardView background;
        final ImageView iconView;


        action = banner_container.findViewById(R.id.callto);
        title_ad = banner_container.findViewById(R.id.title_ad);
        sponsor = banner_container.findViewById(R.id.sponsor_ad);
        desc_ad = banner_container.findViewById(R.id.sponsor_adw);
        //      normal_view = itemView.findViewById(R.id.normal_view);

        button_action = banner_container.findViewById(R.id.button_action);
        iconView = banner_container.findViewById(R.id.ad_icon_view);


        background = (CardView) banner_container;// banner_container.findViewById(R.id.card);
        ad_choices = banner_container.findViewById(R.id.ad_choices);
        background.setCardBackgroundColor(colorbck);
        desc_ad.setTextColor(textco);
        title_ad.setTextColor(textco);
        sponsor.setTextColor(textco);
        button_action.setCardBackgroundColor(context.getResources().getColor(R.color.blue_fb));
        action.setTextColor(colorbck);
    //    Log.e(TAG, "populateBannerNativeIn: sik" );
        if(nativeBannerAd == null){
            return;
        }
  //      Log.e(TAG, "populateBannerNativeIn: 2a" );
        if (nativeBannerAd.isAdLoaded()) {
banner_container.setVisibility(View.VISIBLE);
            String title = nativeBannerAd.getAdvertiserName();
            String provider = nativeBannerAd.getSponsoredTranslation();
            String boton_action = nativeBannerAd.getAdCallToAction();
            String patrocinador = nativeBannerAd.getAdBodyText();
            String sp = nativeBannerAd.getAdTranslation();

            //  com.facebook.ads.AdChoicesView adChoicesView = new com.facebook.ads.AdChoicesView(context, nativeAd, true);
            if (nativeBannerAd.getAdChoicesImageUrl() != null)
                Picasso.get().load(Uri.parse(nativeBannerAd.getAdChoicesImageUrl())).fit().into(ad_choices);

            title_ad.setText(title);
            desc_ad.setText(patrocinador);

            sponsor.setText(sp);

            //    ad_choices.addView(adChoicesView, 0);
            //  Log.e("MAIN", "setupViews: COLOR => "+textco);
            action.setText(boton_action);


            if (clickables.size() < 1) {
                clickables.add(button_action);
                // clickables.add(title_ad);
                // clickables.add(sponsor);
               /* if(mediaView!=null)
                clickables.add(mediaView);*/
                clickables.add(action);
            } else if (!clickables.contains(button_action)) {
                clickables.add(button_action);
                //       clickables.add(title_ad);
                //     if(mediaView!=null)
                //   clickables.add(mediaView);
                // clickables.add(sponsor);
                clickables.add(action);
            }


            nativeBannerAd.registerViewForInteraction(banner_container, iconView, clickables);

        } else {
          //  Log.e(TAG, "populateBannerNativeIn: nooo" );

loadBannerAgain();

        }
    }


    public  void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, NativeAdView adView) {
        // Set the media view.
        adView.setVisibility(View.VISIBLE);
        adView.setMediaView(adView.findViewById(R.id.ad_media));


        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        Log.e(TAG, "populateUnifiedNativeAdView: "+nativeAd.getBody() );
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
       /*VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
            videoStatus.setText(String.format(Locale.getDefault(),
                    "Video status: Ad contains a %.2f:1 video asset.",
                    vc.getAspectRatio()));

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    refresh.setEnabled(true);
                    videoStatus.setText("Video status: Video playback has ended.");
                    super.onVideoEnd();
                }
            });
        } else {
            videoStatus.setText("Video status: Ad does not contain a video asset.");
            refresh.setEnabled(true);
        }*/
    }

    private String TAG = "MAIN";
}
