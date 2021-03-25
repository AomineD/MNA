package com.wineberryhalley.mna.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;
import com.wineberryhalley.mna.base.TypeAd;
import com.wineberryhalley.mna.base.TypeNetwork;

import java.util.ArrayList;

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

        }
    }

    /** =======================================================> **/
    /** BANNER NATIVO **/

    public void showBannerNativeIn(BannerNativeMNA nm){
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

    public void showBannerNativeIn(BannerNativeMNA nm, MListener listener){
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

    /** =======================================================> **/

    /** NATIVO **/

    public void showNativeIn(NativeMNA nm){
        if(AdManager.network == TypeNetwork.ADMOB){
            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.NATIVO);

            admobMNA.showNativeIn(nm);
        }else{


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
        if(AdManager.network == TypeNetwork.ADMOB){

            AdmobMNA admobMNA = getOfTypeAdmob(TypeAd.NATIVO);

            admobMNA.showNativeIn(nm, listener);

        }else {
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
}
