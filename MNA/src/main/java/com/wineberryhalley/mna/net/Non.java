package com.wineberryhalley.mna.net;

import com.wineberryhalley.mna.base.MListener;

public class Non extends Jedleto {

    private int a = 0;
    protected Non(){

    }

    public Non setListener(MListener listener){
        this.l = listener;
        return this;
    }

    public boolean isLoaded(){
        return isLoaded;
    }

    public Non reload(){
        loadData();
        return this;
    }

    static void addImpressiont(String id){
       new Non().addImpression(id);
    }
    static void addLoaded(String id){
        new Non().addLoad(id);
    }
}
