package com.wineberryhalley.mna.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.wineberryhalley.mna.R;

public class BannerNativeMNA extends LinearLayout {
    public BannerNativeMNA(Context context) {
        super(context);
        config();
    }

    public BannerNativeMNA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        config();
    }

    public BannerNativeMNA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config();
    }

    public BannerNativeMNA(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        config();
    }

    private View ah;

    public View getView(TypeNetwork typeNetwork){
        ah = null;
        if(typeNetwork == TypeNetwork.MOPUB){
            ah =  LayoutInflater.from(getContext()).inflate(R.layout.mna_native_mopub_rel, (ViewGroup) getRootView(), false);
        }
        else if(typeNetwork == TypeNetwork.ADMOB){
            ah =  LayoutInflater.from(getContext()).inflate(R.layout.mna_native_ad_admob, (ViewGroup) getRootView(), false);
        }else{
               View main  =  LayoutInflater.from(getContext()).inflate(R.layout.mna_native_ad_facebook_banner, this, false);
            ah = main;
        }
        addView(ah);
        ah.setVisibility(GONE);
        return ah;
    }
    private void config(){



    }
}
