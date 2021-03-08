package com.wineberryhalley.mna.net;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.DelayListener;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.TypeAd;

import java.util.Timer;
import java.util.TimerTask;

public class AdMNA implements DelayListener {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getShoweds() {
        return showeds;
    }

    public void setShoweds(String showeds) {
        this.showeds = showeds;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    private String id;
    private String app_id;
    private String network_id;
    private String name;
    private String impressions;
    private String showeds;
    private String value;
    private String ad_type;

    protected AdMNA(){

    }

    protected void config(AdMNA a){
       setId(a.id);
       setApp_id(a.app_id);
       setNetwork_id(a.network_id);
       setName(a.name);
       setImpressions(a.impressions);
       setShoweds(a.showeds);
        setValue(a.getValue());
       setAd_type(a.getAd_type());
    }

    public void showBannerAd(LinearLayout adContainer){

    }

    public void showBannerAd(RelativeLayout adContainer){

    }

    public void showBannerAd(LinearLayout adContainer, MListener listener){

    }

    public void showBannerAd(RelativeLayout adContainer, MListener listener){

    }

    public void showInterstitialAd(InterstitialListener listener){

    }

    public void showInterstitalAdFrecuency(int frec, InterstitialListener listener){

    }

    public void showNativeIn(NativeMNA layout){

    }

    public void showNativeIn(NativeMNA layout, MListener listener){

    }

    public void showBannerNativeIn(BannerNativeMNA layout){

    }

    public void showBannerNativeIn(BannerNativeMNA layout, MListener listener){

    }



    public void addImpressionTo(){
      //  Log.e("MAIN", "addImpressionTo: "+id );
        if(!getId().isEmpty())
        Non.addImpressiont(getId());
    }

    public void addLoadedTo(){
        //  Log.e("MAIN", "addImpressionTo: "+id );
        if(!getId().isEmpty())
            Non.addLoaded(getId());
    }

    public TypeAd getType(){
        int a = Integer.parseInt(ad_type);
        switch (a){
            case 0:
                return TypeAd.BANNER;
            case 1:
                return TypeAd.INTERSTICIAL;
            case 2:
                return TypeAd.REWARD;

        }

        if(a > 2 && a < 6){
            return TypeAd.BANNER_NATIVO;
        }else{
            return TypeAd.NATIVO;
        }
    }

    @Override
    public void OnReady() {

    }




    protected void showWithDelay(long segundos, DelayListener listener){

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                listener.OnReady();
            }
        }, segundos * 1000);

    }

    protected void showWithDelay(DelayListener listener){

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                listener.OnReady();
            }
        }, 1000);

    }
}
