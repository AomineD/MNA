package com.wineberryhalley.mna.net;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.mopub.nativeads.AdapterHelper;
import com.mopub.nativeads.FacebookTemplateRenderer;
import com.mopub.nativeads.GooglePlayServicesAdRenderer;
import com.mopub.nativeads.GooglePlayServicesViewBinder;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;
import com.squareup.picasso.Picasso;
import com.wineberryhalley.mna.R;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.MNApp;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.cons.Cons;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Random;

import static com.wineberryhalley.mna.net.AdManager.nativesLoaded;
import static com.wineberryhalley.mna.net.AdManager.testAds;

public class NtUtils {

    public static NtUtils getInstance(Context c, OnNativeLoadInterface nativeLoadInterface) {
        return new NtUtils(c, nativeLoadInterface);
    }

    public static NtUtils getInstance(Context c, AdMNA ad_unit) {
        return new NtUtils(c, ad_unit);
    }

    public static NtUtils getAdmobInstance(Context c, AdMNA ad_unit, int size) {
        return new NtUtils(c, ad_unit, size);
    }

    public static NtUtils getAdmobInstance(Context c, AdMNA ad_unit, int size,OnNativeLoadInterface a) {
        return new NtUtils(c, ad_unit, size,a);
    }



    public static NtUtils getInstance(Context c, ArrayList<AudienceMNA> ad_units) {
        return new NtUtils(c, ad_units);
    }

    public static NtUtils getInstance(Context c, AdMNA ad_unit, OnNativeLoadInterface a) {
        return new NtUtils(c, ad_unit, a);
    }

    public static NtUtils getInstance(Context c, ArrayList<AudienceMNA> ad_units, OnNativeLoadInterface a) {
        return new NtUtils(c, ad_units, a);
    }


    protected NtUtils setBannerNatives(ArrayList<AudienceMNA> banners){
        this.idsBannerNative = banners;
        return this;
    }


    protected NtUtils(Context c, OnNativeLoadInterface a) {
        this.context = c;
        this.intre2 = a;
    }

    protected NtUtils(Context c, AdMNA ad_unit) {
        this.context = c;
        this.idsnat = ad_unit;

    }

    protected NtUtils(Context c, AdMNA ad_unit, int size) {
        this.context = c;
        this.idsnat = ad_unit;
this.sizeAdmobNative = size;

    }

    protected NtUtils(Context c, AdMNA ad_unit, int size, OnNativeLoadInterface a) {
        this.context = c;
        this.idsnat = ad_unit;
        this.intre2 = a;
        this.sizeAdmobNative = size;
    }


    protected NtUtils(Context c, AdMNA ad_unit, OnNativeLoadInterface a) {
        this.context = c;
        this.idsnat = ad_unit;
        this.intre2 = a;

    }

    protected NtUtils(Context c, ArrayList<AudienceMNA> ad_units, OnNativeLoadInterface a) {
        this.context = c;
        this.idsAudience = ad_units;

        this.intre2 = a;

    }

    protected NtUtils(Context c, ArrayList<AudienceMNA> ad_units) {
        this.context = c;
        this.idsAudience = ad_units;
    }

    public NtUtils setListener(OnNativeLoadInterface a) {
        this.intre2 = a;
        return this;
    }


    public NtUtils into(BannerNativeMNA bn) {
        this.banner_container = bn.getView(AdManager.natives_network);
        return this;
    }

    public NtUtils into(NativeMNA bn) {
        this.banner_container = bn.getView(AdManager.natives_network);
        return this;
    }

    private View banner_container;
    private NativeAdView nativeAdView;
    private OnNativeLoadInterface intre2;

    private OnNativeLoadInterface intre = new OnNativeLoadInterface() {
        @Override
        public void OnSuccess() {
            nativesLoaded = true;
         if(intre2 != null){
             intre2.OnSuccess();
         }
         if(idsnat != null){
             idsnat.addLoadedTo();
         }
        }

        @Override
        public void OnFail(String ss, int pos) {
            if(intre2 != null){
                intre2.OnFail(ss, pos);
            }
        }

        @Override
        public void OnImpression() {

            if(idsnat != null){
                idsnat.addImpressionTo();
            }
        }
    };
    private int sizeAdmobNative = 1;
    private AdMNA idsnat;
    private final ArrayList<View> clickables = new ArrayList<>();

    private ArrayList<AudienceMNA> idsBannerNative = new ArrayList<>();
    private ArrayList<AudienceMNA> idsAudience = new ArrayList<>();


    private final Context context;
    private int nativeLoadeds = 0;


    public interface OnNativeLoadInterface {
        void OnSuccess();

        void OnFail(String ss, int pos);

        void OnImpression();
    }

    /** ADMOB **/

    private ArrayList<com.google.android.gms.ads.nativead.NativeAd> nativeAds_admob = new ArrayList<>();

    /** =====================   **/


    /** AUDIENCE **/

    private ArrayList<NativeAd> nativeAds = new ArrayList<>();

    /** =====================   **/

    /** MOPUB **/

    private ArrayList<com.mopub.nativeads.NativeAd> nativeAds_MoPub = new ArrayList<>();

    /** =====================   **/
    
    protected NtUtils loadAds() {

        nativeLoadeds = 0;

        for (int i = 0; i < idsAudience.size(); i++) {


            NativeAd   nativeAd = new NativeAd(context, idsAudience.get(i).getValue());
            int finalI = i;
            int finalI1 = i;
            nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                
nativeAds.add(nativeAd);
if(idTryingToShow.contains(nativeAd.getPlacementId())){

        showAudienceNative(nativeAd.getPlacementId());
        idTryingToShow.remove(nativeAd.getPlacementId());
 //   Log.e(TAG, "onMediaDownloaded: si" );
}

                nativeLoadeds++;
if(nativeLoadeds >= idsAudience.size()){
    if (intre != null) {
        intre.OnSuccess();

    }
}
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                nativeLoadeds++;

                if(nativeLoadeds >= idsAudience.size()){
                    if (intre != null && idsAudience.size() > 0) {
                        intre.OnSuccess();
                    }
                }

                if (testAds)
                    Log.e("MAIN", "NATIVOS onError: " + adError.getErrorMessage() + " el index => "+finalI+" "+idsAudience.get(finalI));

                if (intre != null)
                    intre.OnFail(adError.getErrorMessage() + " el index => "+finalI, finalI);


            }

            @Override
            public void onAdLoaded(Ad ad) {


                idsAudience.get(finalI1).addLoadedTo();
                if (testAds) {
                    Log.e("MAIN", "NATIVOS onAdLoaded: "+finalI);
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
                idsAudience.get(finalI1).addImpressionTo();
            }
        }).build();
        nativeAd.loadAd();
            if (AdManager.testAds)
                Log.e("MAIN", "loadAds: loading native "+i);


        }


        return this;
    }





    protected NtUtils loadAdmobNative() {
        nativeLoadeds = 0;

  //      Log.e(TAG, "loadAdmobNative: "+idsnat.getValue() );
        AdLoader adLoader = new AdLoader.Builder(context, idsnat.getValue())
                .forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
 nativeAds_admob.add(nativeAd);
 nativeLoadeds++;
 if(nativeLoadeds >= sizeAdmobNative){
     if (intre != null) {
         intre.OnSuccess();
     }
 }
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        if (intre != null) {
                            intre.OnFail(loadAdError.getMessage(), 0);
                        }
                        nativeLoadeds++;
                        if(nativeLoadeds >= sizeAdmobNative){
                            if (intre != null && nativeAds_admob.size() > 0) {
                                intre.OnSuccess();
                            }
                        }
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();

                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        if (intre != null) {
                            intre.OnImpression();
                        }
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        if(sizeAdmobNative < 2) {
            adLoader.loadAd(new AdRequest.Builder().build());

        }else{
            adLoader.loadAds(new AdRequest.Builder().build(), sizeAdmobNative);
        }

        return this;
    }

    protected NtUtils loadMoPubNative() {
        nativeLoadeds = 0;
        for (int i = 0; i < sizeAdmobNative; i++) {


        MoPubNative moPubNative = new MoPubNative(context, idsnat.getValue(), new MoPubNative.MoPubNativeNetworkListener() {
            @Override
            public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
                
                nativeLoadeds++;
                if(nativeLoadeds >= sizeAdmobNative){
                    if (intre != null) {
                        intre.OnSuccess();

                    }
                }

                nativeAds_MoPub.add(nativeAd);

            }

            @Override
            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                nativeLoadeds++;
                if(nativeLoadeds >= sizeAdmobNative && nativeAds_MoPub.size() > 0){
                    if (intre != null) {
                        intre.OnSuccess();

                    }
                }
                if (intre != null) {
                    intre.OnFail("Native err: " + nativeErrorCode.getIntCode(), 0);
                }
            }
        });
     //   Log.e(TAG, "loadMoPubNative: "+idsnat );


        ViewBinder viewBinder = new ViewBinder.Builder(R.layout.mna_native_ad_mopub)
                .mainImageId(R.id.native_main_image)
                .iconImageId(R.id.native_icon_image)
                .titleId(R.id.native_title)
                .textId(R.id.native_text)
                .privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                .sponsoredTextId(R.id.native_sponsored_text_view)
                .build();

        MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(viewBinder);



    // FACEBOOK


    // ADMOB

            final GooglePlayServicesAdRenderer googlePlayServicesAdRenderer = new GooglePlayServicesAdRenderer(
                    new GooglePlayServicesViewBinder.Builder(R.layout.mna_native_ad_mopub)
                            .mediaLayoutId(R.id.ad_media) // bind to your `com.mopub.nativeads.MediaLayout` element
                            .iconImageId(R.id.ad_app_icon)
                            .titleId(R.id.ad_headline)
                            .textId(R.id.ad_body)
                            .callToActionId(R.id.ad_call_to_action)
                            .privacyInformationIconImageId(R.id.ad_advertiser)
                            .build());

            FacebookTemplateRenderer facebookAdRenderer = new FacebookTemplateRenderer(new NativeAdViewAttributes(context));

            moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);

            moPubNative.registerAdRenderer(facebookAdRenderer);
            moPubNative.registerAdRenderer(googlePlayServicesAdRenderer);

        EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                RequestParameters.NativeAdAsset.TITLE,
                RequestParameters.NativeAdAsset.TEXT,
                RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT,
                RequestParameters.NativeAdAsset.MAIN_IMAGE,
                RequestParameters.NativeAdAsset.ICON_IMAGE,
                RequestParameters.NativeAdAsset.STAR_RATING
        );

        RequestParameters mRequestParameters = new RequestParameters.Builder()
                .desiredAssets(desiredAssets)
                .build();

        moPubNative.makeRequest(mRequestParameters);
        }
       // Log.e(TAG, "loadMoPubNative: a" );

        return this;
    }

    protected NtUtils loadGeneral(boolean hasBannerNatives){
        switch (AdManager.natives_network){
            case AUDIENCE:
               if(hasBannerNatives){
                   loadBannerAds();
               }
                loadAds();
                break;
            case MOPUB:
                loadMoPubNative();
                break;
            case ADMOB:
                loadAdmobNative();
                break;
        }

        return this;
    }

    /**
     * CACHED
     * **/
    private ArrayList<String> idTryingToShow = new ArrayList<>();


    public void showAudienceNative(String id){
        if(nativeAds.size() < 1){
            return;
        }
        boolean show = false;
        for (NativeAd ad:
             nativeAds) {
            if(ad.getPlacementId().equalsIgnoreCase(id)){
              populateNativeIn(ad);
              show = true;
              break;
            }
        }

        if(!show){
            banner_container.setVisibility(View.GONE);
            idTryingToShow.add(id);
        }
    }

    public void showAudienceBannerNative(String id){
        if(nativeBannerAds.size() < 1){
            return;
        }
        boolean show = false;
        for (NativeBannerAd ad:
                nativeBannerAds) {
            if(ad.getPlacementId().equalsIgnoreCase(id)){
                    populateBannerNativeIn(ad);
                    show = true;
                    break;

            }
        }

        if(!show){
            banner_container.setVisibility(View.GONE);
            idTryingToShow.add(id);
        }
    }

    public void showAdmobNative() {
        if (nativeAds_admob.size() < 1) {
            return;
        }


            int indx = new Random().nextInt(nativeAds_admob.size());
            com.google.android.gms.ads.nativead.NativeAd random = nativeAds_admob.get(indx);

            populateUnifiedNativeAdView(random);

    }

    public void showMoPubNative() {
        if (nativeAds_MoPub.size() < 1) {
            return;
        }
        // Log.e(TAG, "showMoPubNative: "+nativeAds_MoPub.size() );
            int indx = new Random().nextInt(nativeAds_MoPub.size());
            com.mopub.nativeads.NativeAd random = nativeAds_MoPub.get(indx);
            populateNativeMopub(random);

    }


    private void populateNativeMopub(com.mopub.nativeads.NativeAd nativeAd) {
        AdapterHelper adapterHelper = new AdapterHelper(context, 0, 3);


        View v = adapterHelper.getAdView(null, nativeAdView, nativeAd, new ViewBinder.Builder(0).build());
        // Set the native event listeners (onImpression, and onClick).
        nativeAd.setMoPubNativeEventListener(new com.mopub.nativeads.NativeAd.MoPubNativeEventListener() {
            @Override
            public void onImpression(View view) {
                if(intre != null){
                    intre.OnImpression();
                }
            }

            @Override
            public void onClick(View view) {

            }
        });

        // Add the ad view to our view hierarchy
        RelativeLayout parentView = (RelativeLayout) banner_container;

        parentView.setVisibility(View.VISIBLE);
        parentView.addView(v);
    //    Log.e(TAG, "populateNativeMopub: a" );
    }


    // Banner nativo aaa ===============================================

    private int banner_nativo_count = 0;

    private ArrayList<NativeBannerAd> nativeBannerAds = new ArrayList<>();

    protected NtUtils loadBannerAds(){

        if(idsBannerNative.size() < 1){
            return this;
        }

        for (int i = 0; i < idsBannerNative.size(); i++) {



       NativeBannerAd nativeBannerAd = new NativeBannerAd(context, idsBannerNative.get(i).getValue());
            int finalI = i;
            nativeBannerAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                    nativeBannerAds.add(nativeBannerAd);
                    banner_nativo_count++;

                        if (idTryingToShow.contains(nativeBannerAd.getPlacementId())) {
                            populateBannerNativeIn(nativeBannerAd);
                            idTryingToShow.remove(nativeBannerAd.getPlacementId());

                    }
                    if(banner_nativo_count >= idsBannerNative.size()){
                        if (intre != null) {
                            intre.OnSuccess();
                        }
                    }
                }

                @Override
                public void onError(Ad ad, AdError adError) {


                    if(AdManager.testAds)
                        Log.e("MAIN", "NATIVOS onError: "+adError.getErrorMessage() + " el index => 0");

                    banner_nativo_count++;

                    if(banner_nativo_count >= idsBannerNative.size() && nativeBannerAds.size() > 0) {
                        if (intre != null) {
                            intre.OnSuccess();
                        }
                    }
                        if (intre != null)
                            intre.OnFail(adError.getErrorMessage(), 0);



                }

                @Override
                public void onAdLoaded(Ad ad) {
                    idsBannerNative.get(finalI).addImpressionTo();

                    if(AdManager.testAds){
                        Log.e("MAIN", "NATIVOS onAdLoaded: ");
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    idsBannerNative.get(finalI).addImpressionTo();
                    if (intre != null) {
                        intre.OnImpression();
                    }
                }
            }).build();
        nativeBannerAd.loadAd();

        }

            if(AdManager.testAds)
                Log.e("MAIN", "loadAds: loading native 0");
        return this;
    }


    public void populateNativeIn(NativeAd nativeAd) {

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
        if(sponsor != null && !sponsor.getText().toString().isEmpty()){
            return;
        }
        desc_ad = banner_container.findViewById(R.id.sponsor_adw);
        //      normal_view = itemView.findViewById(R.id.normal_view);

        button_action = banner_container.findViewById(R.id.button_action);
            mediaView = banner_container.findViewById(R.id.media_view);
            iconView = banner_container.findViewById(R.id.ad_icon_view);
     //       Log.e("MAIN", "setupViews: "+(mediaView!=null) );


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
           
        }
    }



    public void populateBannerNativeIn(NativeBannerAd nativeBannerAd) {
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
        if(sponsor != null && !sponsor.getText().toString().isEmpty()){
            return;
        }
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

        }
    }


    public  void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        // Set the media view.

        nativeAdView = (NativeAdView) banner_container;
         if (nativeAdView == null) {
             return;
                        }



         NativeAdView adView = nativeAdView;
        adView.setVisibility(View.VISIBLE);

       TextView t = adView.findViewById(R.id.ad_headline);
       if(t != null && !t.getText().toString().isEmpty()){
           return;
       }

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
      //  Log.e(TAG, "populateUnifiedNativeAdView: "+nativeAd.getBody() );
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


    }

    private String TAG = "MAIN";
}
