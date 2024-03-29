package com.wineberryhalley.mna.net;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import androidx.annotation.RestrictTo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.wineberryhalley.mna.BuildConfig;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.TypeNetwork;
import com.wineberryhalley.mna.cons.Cons;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static com.wineberryhalley.mna.cons.Cons.nendmoete;

@RestrictTo(LIBRARY)
public class Jedleto {

    private final Context context;
    private RequestQueue queue;
    private String a_ = "";
    private String k_ = "";
    protected MListener l;
    static boolean isLoaded = false;
    static String id_network;
    protected Jedleto(){
context = ChalaEdChala.context;
        try {
queue = Volley.newRequestQueue(context);

       // Log.e(TAG, "init: multi" );


            Class<?> klass = Class.forName(BuildConfig.LIBRARY_PACKAGE_NAME+".BuildConfig");
            ApplicationInfo app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = app.metaData;
            a_ = bundle.getString("com.mna.hot");

            a_ = Cons.getD(a_);

            if(!a_.endsWith(nendmoete())){
                a_ = a_+nendmoete();
            }
          //  Log.e(TAG, "Jedleto: "+a_ );

            Field fa = klass.getField(Cons.e_mw_a);
            Field fielda = klass.getDeclaredField(String.valueOf(fa.get(null)));
            k_ = String.valueOf(fielda.get(null));

        } catch (Exception e) {
       //     Log.e("MAIN", "Ecapdamond: "+e );
            e.printStackTrace();
            AdManager.isInitializedAlready = true;
        }
    }

    protected void loadData(){
        AdManager.isInitializedAlready = false;


        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, a_, responsea -> {

       //   Log.e("MAIN", "MultiResponse "+responsea );
            try {
                JSONObject response = new JSONObject(responsea);
               // Log.e("MAIN", "onResponse: "+response.has("status") );
                if(success(response)) {
                    TypeNetwork  network = getOf(response.getString("type"));
                    AdManager.natives_network = getOf(response.getString("native_type"));
                  id_network = response.getString("type");

                   // Log.e("MAIN", "onResponse: "+network.name() );
                    if(network == TypeNetwork.UNITYADS && response.has("app_id")){
                        AdManager.appId = response.getString("app_id");
                    }else if(network == TypeNetwork.IRON_SOURCE && response.has("app_id") ){
                        AdManager.appId = response.getString("app_id");
                    }

                    if(response.has("app_openad")){
                        SubManager.open_ad = response.getJSONObject("app_openad").getString("value");
                        if(AdManager.testAds) {
                            Log.e(TAG, "onResponse: HAS OPEN AD -> " + SubManager.open_ad);
                        }
                        }
                        else if(AdManager.testAds){
                        Log.e(TAG, "onResponse: dont have open ad "+response );
                    }

                   AdManager.network = network;
                        if(AdManager.testAds){
                            Log.e(TAG, "onResponse: Before array" );
                        }
                        ArrayList<AdMNA> array =  configAds(response.getJSONArray("data"));
                    if(AdManager.testAds){
                        Log.e(TAG, "onResponse: After array -> "+array.size() );
                    }
                        if(response.has("native_ads")){
                            array.addAll(configAds(response.getJSONArray("native_ads")));
                        }

                        AdManager.addAds(array);

               OnLoad();
                    isLoaded = true;
                    AdManager.isInitializedAlready = true;

                    if(AdManager.network == TypeNetwork.UNITYADS){
                        UnityMNA.initialize();

                    }else if(AdManager.network == TypeNetwork.MOPUB){
                              if(array.size() > 0)
                     MopubMNA.initialize(array.get(0).getValue());
                    }else if(AdManager.network == TypeNetwork.APPLOVIN){
                        AppLovinMNA.initialize();
                    }
                    else{



                        if(network == TypeNetwork.IRON_SOURCE){
                            IronMNA.initializeIron();
                        }
                        AdMNA.initializeNormal();

                    }


                }else{
                    AdManager.isInitializedAlready = true;
                    OnError(response.getString("data"));
                }

            } catch (JSONException e) {
                AdManager.isInitializedAlready = true;
                OnError(e.getMessage());

            }


            queue.getCache().clear();
        }, error -> {
        //    AdMNA.initializeError(error.getMessage());
            AdManager.isInitializedAlready = true;
            OnError(error.getMessage());
            queue.getCache().clear();

        }){
            @Override
            protected Map<String, String> getParams() {
Map<String, String>  a = map();
a.put("get_ads", "a");
                return a;
            }
        };

        queue.add(jsonArrayRequest);

    }

    protected void addImpression(String id_ad){

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, a_, responsea -> {

            //    Log.e("MAIN", "onResponse: "+responsea );
            try {
                JSONObject response = new JSONObject(responsea);
                // Log.e("MAIN", "onResponse: "+response.has("status") );
                if(success(response)) {


                    OnLoad();
                }else{
                    OnError(response.getString("data"));
                }

            } catch (JSONException e) {
                //Log.e("MAIN", "onResponse BY GENRE: "+e.getMessage());
                //  e.printStackTrace();
                OnError(e.getMessage());
            }






            queue.getCache().clear();
        }, error -> {
            OnError(error.getMessage());
            queue.getCache().clear();
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  a = map();
                a.put("addimpr", "a");
                a.put("_usr", ChalaEdChala.getUniqueId());
                a.put("_ad", id_ad);
                a.put("_network", id_network);
                String def = Locale.getDefault().getDisplayCountry();
                a.put("_ctry", def);
                return a;
            }
        };

        queue.add(jsonArrayRequest);

    }


    protected void addLoad(String id_ad){

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, a_, responsea -> {

            try {
                JSONObject response = new JSONObject(responsea);
                // Log.e("MAIN", "onResponse: "+response.has("status") );
                if(success(response)) {


                    OnLoad();
                }else{
                    OnError(response.getString("data"));
                }

            } catch (JSONException e) {
                OnError(e.getMessage());
            }






            queue.getCache().clear();
        }, error -> {
            OnError(error.getMessage());
            queue.getCache().clear();
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  a = map();
                a.put("addload", "a");
                a.put("_usr", ChalaEdChala.getUniqueId());
                a.put("_ad", id_ad);
                a.put("_network", id_network);
                String def = Locale.getDefault().getDisplayCountry();
                a.put("_ctry", def);
                return a;
            }
        };

        queue.add(jsonArrayRequest);

    }

    private boolean success(JSONObject a) throws JSONException {
        if(a.getString("status").equals("success")){
            return true;
        }
        return false;
    }
    private Map<String, String> map(){
        HashMap<String, String> a = new HashMap<>();
      //  Log.e("MAIN", "map: "+k_+" "+a_ );
        a.put(k_, context.getPackageName());

        return a;
    }

    private void setAT(MListener listener){
        this.l = listener;
    }

    private void OnLoad(){
        if(l != null){
            l.OnLoad();
        }
    }

    private String TAG= "MAIN";
    private void OnError(String erno){
        Log.e(TAG, "OnError: "+erno );
        if(l != null)
            l.OnError(erno);

        AdMNA.initializeError(erno);
    }

    private<T> ArrayList<T> configAds(JSONArray objectArray) throws JSONException {

        ArrayList<AdMNA> adMNAS = new ArrayList<>();
        if(AdManager.testAds){
            Log.e(TAG, "configAds: arraySize -> "+objectArray.length() );
        }
        for (int i = 0; i < objectArray.length(); i++) {
            JSONObject jsonObject = objectArray.getJSONObject(i);

            if(AdManager.testAds){
                Log.e(TAG, "configAds: index: "+i+" jsonObject -> "+jsonObject.toString() );
            }
            AdMNA adMNA = new Gson().fromJson(jsonObject.toString(), AdMNA.class);
            if(AdManager.testAds){
                Log.e(TAG, "configAds: Type -> "+ adMNA.getAd_type() +" ID -> "+adMNA.getValue());
            }
            if(adMNA.getValue() != null && !adMNA.getValue().isEmpty()){

                adMNAS.add(adMNA);
            }
        }

        return (ArrayList<T>) adMNAS;
    }

    private TypeNetwork getOf(String response) {
        TypeNetwork network = TypeNetwork.AUDIENCE;
        switch (response){
            case "1":
                network = TypeNetwork.AUDIENCE;
                break;
            case "2":
                network = TypeNetwork.UNITYADS;
                break;
            case "3":
                network = TypeNetwork.ADMOB;
                break;
            case "4":
                network = TypeNetwork.MOPUB;
                break;
            case "5":
                network = TypeNetwork.APPLOVIN;
                break;
            case "6":
                network = TypeNetwork.IRON_SOURCE;
                    break;
        }
        return network;
    }

    private TypeNetwork getMediation(String response) {
        TypeNetwork network = TypeNetwork.ADMOB;
        if ("1".equals(response)) {
            network = TypeNetwork.AUDIENCE;
        }
        return network;
    }
}
