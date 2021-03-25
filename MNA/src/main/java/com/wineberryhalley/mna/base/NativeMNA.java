package com.wineberryhalley.mna.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.wineberryhalley.mna.R;

public class NativeMNA extends LinearLayout {
    public NativeMNA(Context context) {
        super(context);
        config();
    }

    public NativeMNA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        config();
    }

    public NativeMNA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config();
    }

    public NativeMNA(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        config();
    }


    private View ah;
    public View getView(TypeNetwork typeNetwork){
        ah = null;
        if(typeNetwork == TypeNetwork.ADMOB){

            ah =  LayoutInflater.from(getContext()).inflate(R.layout.mna_native_ad_admob, (ViewGroup) getRootView(), false);
            ah.setVisibility(GONE);
            addView(ah);
        }else{
            ah =  LayoutInflater.from(getContext()).inflate(R.layout.mna_native_ad_facebook, (ViewGroup) getRootView(), false);
            ah.setVisibility(GONE);
            addView(ah);
        }
        return ah;
    }

    private void config(){

    }
}
