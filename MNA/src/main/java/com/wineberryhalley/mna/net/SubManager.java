package com.wineberryhalley.mna.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdView;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.mopub.mobileads.MoPubView;
import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;
import com.wineberryhalley.mna.base.TypeAd;
import com.wineberryhalley.mna.base.TypeNetwork;

import java.util.ArrayList;

import static com.wineberryhalley.mna.net.AdManager.natives_network;
import static com.wineberryhalley.mna.net.AdManager.ntUtils;
import static com.wineberryhalley.mna.net.AdManager.ntUtilsBannerNat;

public class SubManager {
    protected SubManager(){


    }


    private AudienceMNA getOfType(TypeAd typeAd){
        AudienceMNA audienceMNA = null;
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                audienceMNA = new AudienceMNA(ad);
                break;
            }
        }
        return audienceMNA;

    }

    private ArrayList<AudienceMNA> getArrayOf(TypeAd typeAd){
        ArrayList<AudienceMNA> arrayList = new ArrayList<>();
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
              AudienceMNA  audienceMNA = new AudienceMNA(ad);
              arrayList.add(audienceMNA);
            }
        }
        return arrayList;

    }

    private ArrayList<AppLovinMNA> getApplovinArrayOf(TypeAd typeAd){
        ArrayList<AppLovinMNA> arrayList = new ArrayList<>();
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                AppLovinMNA  audienceMNA = new AppLovinMNA(ad);
                arrayList.add(audienceMNA);
            }
        }
        return arrayList;

    }

    private UnityMNA getOfTypeUnity(TypeAd typeAd){
        UnityMNA audienceMNA = null;
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                audienceMNA = new UnityMNA(ad);
                break;
            }
        }
        return audienceMNA;

    }

    private AdmobMNA getOfTypeAdmob(TypeAd typeAd){
        AdmobMNA admobMNA = null;
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                admobMNA = new AdmobMNA(ad);
                break;
            }
        }
        return admobMNA;

    }

    private MopubMNA getOfTypeMoPub(TypeAd typeAd){
        MopubMNA mopubMNA = null;
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                mopubMNA = new MopubMNA(ad);
                break;
            }
        }
        return mopubMNA;

    }

    private AppLovinMNA getOfTypeAppLovin(TypeAd typeAd){
        AppLovinMNA appLovinMNA = null;
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                appLovinMNA = new AppLovinMNA(ad);
                break;
            }
        }
        return appLovinMNA;

    }


    private IronMNA getOfTypeIron(TypeAd typeAd){
        IronMNA ironMNA = null;
        for (AdMNA ad :
                ads) {
            if(ad.getType() == typeAd) {
                ironMNA = new IronMNA(ad);
                break;
            }
        }
        return ironMNA;

    }

    protected ArrayList<AdMNA> ads = new ArrayList<>();
/** BANNER AD **/
    public void showBannerAd(LinearLayout linearLayout){

        if(AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    audienceMNA = new AudienceMNA(ad);
                }
            }
            if(audienceMNA != null)
                audienceMNA.showBannerAd(linearLayout);

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    unityMNA = new UnityMNA(ad);
                }
            }
            if(unityMNA != null)
                unityMNA.showBannerAd(linearLayout);
        }else if(AdManager.network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    admobMNA = new AdmobMNA(ad);
                }
            }
            if(admobMNA != null)
                admobMNA.showBannerAd(linearLayout);
        }else if(AdManager.network == TypeNetwork.MOPUB){
            MopubMNA mopubMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    mopubMNA = new MopubMNA(ad);
                }
            }
            if(mopubMNA != null)
                mopubMNA.showBannerAd(linearLayout);
        }else if(AdManager.network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.BANNER);

            if(ironMNA != null){
                ironMNA.showBannerAd(linearLayout);
            }
        }else if(AdManager.network == TypeNetwork.APPLOVIN){

            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.BANNER);
            if(appLovinMNA != null)
                appLovinMNA.showBannerAd(linearLayout);



        }

    }

    public void showBannerAd(LinearLayout linearLayout, MListener listener){

        if(AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    audienceMNA = new AudienceMNA(ad);
                    break;
                }
            }
            if(audienceMNA != null)
                audienceMNA.showBannerAd(linearLayout, listener);
            else{
                listener.OnError("No banners");
            }

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    unityMNA = new UnityMNA(ad);
                    break;
                }
            }
            if(unityMNA != null)
                unityMNA.showBannerAd(linearLayout, listener);
            else{
                listener.OnError("No banners");
            }
        }else if(AdManager.network == TypeNetwork.ADMOB){

            AdmobMNA admobMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    admobMNA = new AdmobMNA(ad);
                    break;
                }
            }
            if(admobMNA != null)
                admobMNA.showBannerAd(linearLayout, listener);
            else{
                listener.OnError("No banners");
            }

        }else if(AdManager.network == TypeNetwork.MOPUB){

            MopubMNA mopubMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    mopubMNA = new MopubMNA(ad);
                    break;
                }
            }
            if(mopubMNA != null)
                mopubMNA.showBannerAd(linearLayout, listener);
            else{
                listener.OnError("No banners");
            }

        }else if(AdManager.network == TypeNetwork.APPLOVIN){

            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.BANNER);
            if(appLovinMNA != null)
                appLovinMNA.showBannerAd(linearLayout, listener);
            else{
                listener.OnError("No banners");
            }

        }else if(AdManager.network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.BANNER);

            if(ironMNA != null){
                ironMNA.showBannerAd(linearLayout, listener);
            }else{
                listener.OnError("No banners");
            }
        }

    }

    public void showBannerAd(RelativeLayout relativeLayout){

        if(AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = getOfType(TypeAd.BANNER);
            if(audienceMNA != null)
                audienceMNA.showBannerAd(relativeLayout);

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA = getOfTypeUnity(TypeAd.BANNER);
            if(unityMNA != null)
                unityMNA.showBannerAd(relativeLayout);
        }else if(AdManager.network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.BANNER);
            if(admobMNA != null)
                admobMNA.showBannerAd(relativeLayout);
        }else if(AdManager.network == TypeNetwork.MOPUB){
            MopubMNA mopubMNA = null;
            for (AdMNA ad :
                    ads) {
                if(ad.getType() == TypeAd.BANNER) {
                    mopubMNA = new MopubMNA(ad);
                }
            }
            if(mopubMNA != null)
                mopubMNA.showBannerAd(relativeLayout);
        }else if(AdManager.network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.BANNER);

            if(ironMNA != null){
                ironMNA.showBannerAd(relativeLayout);
            }
        }else if(AdManager.network == TypeNetwork.APPLOVIN){

            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.BANNER);
            if(appLovinMNA != null)
                appLovinMNA.showBannerAd(relativeLayout);


        }

    }

    public void showBannerAd(RelativeLayout relativeLayout, MListener listener) {

        if (AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = getOfType(TypeAd.BANNER);
            if (audienceMNA != null)
                audienceMNA.showBannerAd(relativeLayout, listener);
            else {
                listener.OnError("No banners");

            }

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA = getOfTypeUnity(TypeAd.BANNER);
            if (unityMNA != null)
                unityMNA.showBannerAd(relativeLayout, listener);
            else {
                listener.OnError("No banners");

            }
        }else if(AdManager.network == TypeNetwork.ADMOB){

            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.BANNER);
            if (admobMNA != null)
                admobMNA.showBannerAd(relativeLayout, listener);
            else {
                listener.OnError("No banners");

            }

        }else if(AdManager.network == TypeNetwork.MOPUB){

            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.BANNER);
            if (mopubMNA != null)
                mopubMNA.showBannerAd(relativeLayout, listener);
            else {
                listener.OnError("No banners");

            }

        }else if(AdManager.network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.BANNER);

            if(ironMNA != null){
                ironMNA.showBannerAd(relativeLayout, listener);
            }else {
                listener.OnError("No banners");

            }
        }else if(AdManager.network == TypeNetwork.APPLOVIN){

            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.BANNER);
            if(appLovinMNA != null)
                appLovinMNA.showBannerAd(relativeLayout, listener);
            else{
                listener.OnError("No banners");
            }

        }
    }

/** =======================================================> **/

    /** INTERSTITIAL AD **/

    public void showInterstitialAd(int frec, InterstitialListener listener){
        if (AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = getOfType(TypeAd.INTERSTICIAL);
            if (audienceMNA != null)
                audienceMNA.showInterstitalAdFrecuency(frec, listener);
            else {
                listener.OnError("No interstitial");

            }

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA = getOfTypeUnity(TypeAd.INTERSTICIAL);
            if (unityMNA != null)
                unityMNA.showInterstitalAdFrecuency(frec, listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.INTERSTICIAL);
            if (admobMNA != null)
                admobMNA.showInterstitalAdFrecuency(frec, listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.MOPUB){
            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.INTERSTICIAL);
            if (mopubMNA != null)
                mopubMNA.showInterstitalAdFrecuency(frec, listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.INTERSTICIAL);
            if (ironMNA != null)
                ironMNA.showInterstitalAdFrecuency(frec, listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.APPLOVIN){
            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.INTERSTICIAL);

            if(appLovinMNA != null){
                appLovinMNA.showInterstitalAdFrecuency(frec, listener);
            }else
                listener.OnError("No interstitial");


        }
    }


    public void showInterstitialAd(InterstitialListener listener){
        if (AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = getOfType(TypeAd.INTERSTICIAL);
            if (audienceMNA != null)
                audienceMNA.showInterstitialAd(listener);
            else {
                listener.OnError("No interstitial");

            }

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA  = getOfTypeUnity(TypeAd.INTERSTICIAL);
            if (unityMNA != null)
                unityMNA.showInterstitialAd(listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.ADMOB){

            AdmobMNA admobMNA  = getOfTypeAdmob(TypeAd.INTERSTICIAL);
            if (admobMNA != null)
                admobMNA.showInterstitialAd(listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.MOPUB){

            MopubMNA mopubMNA  = getOfTypeMoPub(TypeAd.INTERSTICIAL);
            if (mopubMNA != null)
                mopubMNA.showInterstitialAd(listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.INTERSTICIAL);
            if (ironMNA != null)
                ironMNA.showInterstitialAd(listener);
            else {
                listener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.APPLOVIN){
            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.INTERSTICIAL);

            if(appLovinMNA != null)
                appLovinMNA.showInterstitialAd(listener);
            else
                listener.OnError("No interstitial");
            }
    }




    /** =======================================================> **/

    /** REWARD AD **/

    public void showRewardAd(RewardListener rewardListener){
        if (AdManager.network == TypeNetwork.AUDIENCE) {
            AudienceMNA audienceMNA = getOfType(TypeAd.REWARD);
            if (audienceMNA != null)
                audienceMNA.showRewardedAd(rewardListener);
            else {
                rewardListener.OnError("No interstitial");

            }

        }else if(AdManager.network == TypeNetwork.UNITYADS){
            UnityMNA unityMNA = getOfTypeUnity(TypeAd.REWARD);
            if (unityMNA != null)
                unityMNA.showRewardedAd(rewardListener);
            else {
                rewardListener.OnError("No interstitial");

            }
        }else if(AdManager.network == TypeNetwork.ADMOB){

            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.REWARD);
            if (admobMNA != null)
                admobMNA.showRewardedAd(rewardListener);
            else {
                rewardListener.OnError("No interstitial");

            }

        } else if(AdManager.network == TypeNetwork.MOPUB){

            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.REWARD);
            if (mopubMNA != null)
                mopubMNA.showRewardedAd(rewardListener);
            else {
                rewardListener.OnError("No interstitial");

            }

        } else if(AdManager.network == TypeNetwork.IRON_SOURCE){

            IronMNA ironMNA = getOfTypeIron(TypeAd.REWARD);
            if (ironMNA != null)
                ironMNA.showRewardedAd(rewardListener);
            else {
                rewardListener.OnError("No interstitial");

            }

        }
    }

    /** =======================================================> **/
    /** BANNER NATIVO **/

    public void showBannerNativeIn(BannerNativeMNA nm){
        if(AdManager.natives_network == TypeNetwork.MOPUB){
            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.NATIVO);

            mopubMNA.showBannerNativeIn(nm);
        }
        else if(AdManager.natives_network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.NATIVO);

            admobMNA.showBannerNativeIn(nm);
        }else if(AdManager.natives_network == TypeNetwork.APPLOVIN){
            AppLovinMNA admobMNA = getOfTypeAppLovin(TypeAd.BANNER_NATIVO);

            admobMNA.showBannerNativeIn(nm);
        }else {
            ArrayList<AudienceMNA> mna = getArrayOf(TypeAd.BANNER_NATIVO);
            if (mna.size() > 0) {
                int get = getIndex();
                if (get >= mna.size()) {
                    resetIndex();
                    get = 0;
                }

                mna.get(get).showBannerNativeIn(nm);
                saveIndex();
            }
        }
    }

    public void showBannerNativeIn(BannerNativeMNA nm, MListener listener){

        if(AdManager.natives_network == TypeNetwork.MOPUB){
            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.NATIVO);

            mopubMNA.showBannerNativeIn(nm, listener);
        }
        else if(AdManager.natives_network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.NATIVO);

            admobMNA.showBannerNativeIn(nm, listener);
        }else if(natives_network == TypeNetwork.IRON_SOURCE){
            IronMNA ironMNA = getOfTypeIron(TypeAd.NATIVO);
            ironMNA.showBannerNativeIn(nm);
        }else if(AdManager.natives_network == TypeNetwork.APPLOVIN){
            AppLovinMNA admobMNA = getOfTypeAppLovin(TypeAd.BANNER_NATIVO);

            admobMNA.showBannerNativeIn(nm, listener);
        }
        else {
            ArrayList<AudienceMNA> mna = getArrayOf(TypeAd.BANNER_NATIVO);
            if (mna.size() > 0) {
                int get = getIndex();
                if (get >= mna.size()) {
                    resetIndex();
                    get = 0;
                }

                mna.get(get).showBannerNativeIn(nm, listener);
                saveIndex();
            }
        }
    }

    /** =======================================================> **/

    /** NATIVO **/

    public void showNativeIn(NativeMNA nm){
        if(AdManager.natives_network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.NATIVO);

            admobMNA.showNativeIn(nm);
        }
        else if(AdManager.natives_network == TypeNetwork.MOPUB){

            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.NATIVO);

            mopubMNA.showNativeIn(nm);

        } else if(natives_network == TypeNetwork.IRON_SOURCE){
                IronMNA ironMNA = getOfTypeIron(TypeAd.NATIVO);
            ironMNA.showNativeIn(nm);
        }else if(natives_network == TypeNetwork.APPLOVIN){
            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.NATIVO);
            appLovinMNA.showNativeIn(nm);
        }
        else{


            ArrayList<AudienceMNA> mna = getArrayOf(TypeAd.NATIVO);
            if (mna.size() > 0) {
                int get = getIndex();
                if (get >= mna.size()) {
                    resetIndex();
                    get = 0;
                }

                mna.get(get).showNativeIn(nm);
                saveIndex();
            }
        }
    }

    public void showNativeIn(NativeMNA nm, MListener listener){
        if(AdManager.natives_network == TypeNetwork.ADMOB){

            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.NATIVO);

            admobMNA.showNativeIn(nm, listener);

        }else if(AdManager.natives_network == TypeNetwork.MOPUB){

            MopubMNA mopubMNA = getOfTypeMoPub(TypeAd.NATIVO);

            mopubMNA.showNativeIn(nm, listener);

        }else if(natives_network == TypeNetwork.APPLOVIN){
            AppLovinMNA appLovinMNA = getOfTypeAppLovin(TypeAd.NATIVO);
            appLovinMNA.showNativeIn(nm, listener);
        } else {
            ArrayList<AudienceMNA> mna = getArrayOf(TypeAd.NATIVO);

            if (mna.size() > 0) {
                int get = getIndex();
                if (get >= mna.size()) {
                    resetIndex();
                    get = 0;
                }

                mna.get(get).showNativeIn(nm, listener);
                saveIndex();
            }
        }
    }

    /** =======================================================> **/



    protected void clearAds(){
        if(ads != null && ads.size() > 0)
        ads.clear();
    }

    static void saveF(){

        SharedPreferences preferences = ChalaEdChala.context.getSharedPreferences("mna-pref", Context.MODE_PRIVATE);
        int get = preferences.getInt("key_fr_cuency", 0);
        get++;
        preferences.edit().putInt("key_fr_cuency", get).apply();
    }
    static int getF(){
        SharedPreferences preferences = ChalaEdChala.context.getSharedPreferences("mna-pref", Context.MODE_PRIVATE);
        return preferences.getInt("key_fr_cuency", 0);
    }

    static void resetF(){
        SharedPreferences preferences = ChalaEdChala.context.getSharedPreferences("mna-pref", Context.MODE_PRIVATE);
        preferences.edit().putInt("key_fr_cuency", 0).apply();
    }

    public boolean hasBannerAds(){
        boolean has = false;
        for (AdMNA ad:
             ads) {
          //  Log.e("MAIN", "hasBannerAds: "+ad.getType().name() );
            if(ad.getType() == TypeAd.BANNER){
                has = true;
            }
        }

        return has;
    }

    public boolean hasIntersticial(){
        boolean has = false;
        for (AdMNA ad:
                ads) {
            if(ad.getType() == TypeAd.INTERSTICIAL){
                has = true;
            }
        }

        return has;
    }

    public boolean hasReward(){
        boolean has = false;
        for (AdMNA ad:
                ads) {
            if(ad.getType() == TypeAd.REWARD){
                has = true;
            }
        }

        return has;
    }

    public boolean hasNativeAds(){
        boolean has = false;
        for (AdMNA ad:
                ads) {
            if(ad.getType() == TypeAd.NATIVO){
                has = true;
            }
        }

        return has;
    }

    public boolean hasBannerNativeAds(){
      if(AdManager.natives_network != TypeNetwork.AUDIENCE) {
          for (AdMNA ad :
                  ads) {
if(ad.getType() == TypeAd.NATIVO){
    return true;
}
          }
      }
        boolean has = false;
        for (AdMNA ad:
                ads) {
            if(ad.getType() == TypeAd.BANNER_NATIVO){
                has = true;
            }
        }

        return has;
    }

    static void saveIndex(){

        SharedPreferences preferences = ChalaEdChala.context.getSharedPreferences("mna-pref", Context.MODE_PRIVATE);
        int get = preferences.getInt("key_ind_ex", 0);
        get++;
        preferences.edit().putInt("key_ind_ex", get).apply();
    }
    static int getIndex(){
        SharedPreferences preferences = ChalaEdChala.context.getSharedPreferences("mna-pref", Context.MODE_PRIVATE);
        return preferences.getInt("key_ind_ex", 0);
    }

    static void resetIndex(){
        SharedPreferences preferences = ChalaEdChala.context.getSharedPreferences("mna-pref", Context.MODE_PRIVATE);
        preferences.edit().putInt("key_ind_ex", 0).apply();
    }

     void destroyAd(LinearLayout lin){

        if(lin != null && lin.getChildCount() > 0){
            for (int i = 0; i < lin.getChildCount(); i++) {
                View v = lin.getChildAt(i);
                if(v instanceof MoPubView){
                    MoPubView moPubView = (MoPubView) v;
                    moPubView.destroy();
                }else if(v instanceof AdView){
                    AdView ad = (AdView) v;
                    ad.destroy();
                }else if(v instanceof com.facebook.ads.AdView){
                    com.facebook.ads.AdView ad = (com.facebook.ads.AdView) v;
                    ad.destroy();
                }else if(v instanceof IronSourceBannerLayout){
                    IronSourceBannerLayout ad = (IronSourceBannerLayout) v;
                    IronSource.destroyBanner(ad);
                }
            }
            if(lin.getChildCount() > 0)
            lin.removeAllViews();
        }
    }

    public boolean isMoPub(){
        return AdManager.network == TypeNetwork.MOPUB;
    }

    public TypeNetwork getActualNetwork(){
        return AdManager.network;
    }

    public boolean showingInterstitial()
    {
        return AdManager.showingInterstitial;
    }

    private int count = 3;
    public SubManager setNativeCount(int c){
count = c;
        return this;
    }



    public void loadNatives(NtUtils.OnNativeLoadInterface listener){

        if(AdManager.natives_network == TypeNetwork.UNITYADS){
            return;
        }

        if(hasNativeAds()){

        switch (AdManager.natives_network){
            case AUDIENCE:
                ArrayList<AudienceMNA> mna = getArrayOf(TypeAd.NATIVO);

                if (mna.size() > 0) {

                    ntUtils = NtUtils.getInstance(ChalaEdChala.context, mna, listener);
                }
                break;
            case APPLOVIN:
                ArrayList<AppLovinMNA> appLovinMNAS = getApplovinArrayOf(TypeAd.NATIVO);

                if (appLovinMNAS.size() > 0) {

                    ntUtils = NtUtils.getALInstance(ChalaEdChala.context, appLovinMNAS, listener);
                }
                break;
            case ADMOB:
                AdmobMNA m = getOfTypeAdmob(TypeAd.NATIVO);

                ntUtils = NtUtils.getAdmobInstance(ChalaEdChala.context, m, count,listener);
                break;
            case MOPUB:
                MopubMNA moPub = getOfTypeMoPub(TypeAd.NATIVO);

                ntUtils = NtUtils.getAdmobInstance(ChalaEdChala.context, moPub, count,listener);
                break;
            case IRON_SOURCE:
                listener.OnSuccess();
                break;
        }

        }


        if(ntUtils != null){

            ntUtils.loadGeneral(false);

        }

    }

    public void loadBannerNatives(NtUtils.OnNativeLoadInterface listener){
        if(hasBannerNativeAds()){
            if (AdManager.natives_network == TypeNetwork.AUDIENCE) {
                ArrayList<AudienceMNA> mna = getArrayOf(TypeAd.BANNER_NATIVO);


                if (mna.size() > 0) {
                    if(ntUtilsBannerNat == null ){
                        ntUtilsBannerNat = NtUtils.getInstance(ChalaEdChala.context, listener);
                    }

                    ntUtilsBannerNat.setBannerNatives(mna);
                    ntUtilsBannerNat.loadGeneral(true);
                }
            }else if (AdManager.natives_network == TypeNetwork.APPLOVIN){
                ArrayList<AppLovinMNA> mna = getApplovinArrayOf(TypeAd.BANNER_NATIVO);
                Log.e("MAIN", "loadBannerNatives: bien aqui "+mna.size() );

                if (mna.size() > 0) {
                    if(ntUtilsBannerNat == null ){
                        ntUtilsBannerNat = NtUtils.getInstance(ChalaEdChala.context, listener);
                    }

                    ntUtilsBannerNat.setALBannerNatives(mna);
                    ntUtilsBannerNat.loadGeneral(true);
                }
            }
        }
    }
}
