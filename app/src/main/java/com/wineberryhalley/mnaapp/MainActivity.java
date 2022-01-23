package com.wineberryhalley.mnaapp;

import androidx.ads.identifier.AdvertisingIdClient;
import androidx.ads.identifier.AdvertisingIdInfo;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InitializeListener;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;
import com.wineberryhalley.mna.net.AdManager;
import com.wineberryhalley.mna.net.NtUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements InitializeListener {

    private View showIntersUn, showIntersFrec;
    private TextView txt;
    private String clickfor = "Mostrar intersticial en ";

    @Override
    protected void onDestroy() {
        Log.e("MAIN", "onDestroy: jefue");
        super.onDestroy();

    }

    private int frecuenci = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUIDs();
        showIntersUn = findViewById(R.id.interstitial_un);
        showIntersFrec = findViewById(R.id.interstitial_frec);
        txt = findViewById(R.id.text_frec);

        txt.setText(getText());

        findViewById(R.id.reward_).setOnClickListener(clickReward());

        showIntersUn.setOnClickListener(clickInterstitialUn());
        showIntersFrec.setOnClickListener(clickInterstitialFrec());
        final LinearLayout lin = findViewById(R.id.linlay);
        AdManager.get().test(true);

    }

    private View.OnClickListener clickReward() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AdManager.get().checkIfLoad() && AdManager.get().manage().hasReward()){
                    AdManager.get().manage().showRewardAd(new RewardListener(){
                        @Override
                        public void onReward() {
                            Toast.makeText(MainActivity.this, "REWARDED! ;D", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void OnDismissed() {
                            Log.e("MAIN", "OnDismissed: dismiss" );
                        }
                    });
                }
            }
        };
    }

    private String TAG ="MAIN";
    private void showBanner(LinearLayout lin){
       Log.e("MAIN", "showBanner: " );
        AdManager.get().manage().showBannerAd(lin, new MListener(){
            @Override
            public void OnLoad() {
                Log.e(TAG, "OnLoad: loaded" );
            }

            @Override
            public void OnError(String erno) {
                Log.e("MAIN", "Banner err: "+erno );
            }
        });
/*
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AdManager.get().destroyBannerAdIn(lin);
                        Log.e(TAG, "run: rito" );
                    }
                });
            }
        }, 8000);
*/
    }

    private String getText(){
        String fin = clickfor + ((frecuenci - AdManager.get().actualFrecuencyInterstitial()) > 0 ? (frecuenci - AdManager.get().actualFrecuencyInterstitial()) : "YA");
        return fin;
    }

    private View.OnClickListener clickInterstitialUn(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AdManager.get().checkIfLoad() && AdManager.get().manage().hasIntersticial()){
AdManager.get().manage().showInterstitialAd(new InterstitialListener(){
    @Override
    public void OnError(String erno) {
        Log.e(TAG, "OnError Inters: "+erno );
    }

    @Override
    public void OnLoad() {

    }

    @Override
    public void OnDismissed() {
        Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
    }
});
                }
            }
        };
    }

    private View.OnClickListener clickInterstitialFrec(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AdManager.get().checkIfLoad() && AdManager.get().manage().hasIntersticial()){
                    AdManager.get().manage().showInterstitialAd(frecuenci,new InterstitialListener(){
                        @Override
                        public void OnError(String erno) {

                        }

                        @Override
                        public void OnLoad() {

                        }

                        @Override
                        public void OnDismissed() {
                            Log.e("MAIN", "OnDismissed: "+AdManager.get().actualFrecuencyInterstitial() );
                            txt.setText(getText());
                            Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
    }

    private void load(LinearLayout lin){
        if(AdManager.get().manage().hasBannerAds())
            showBanner(lin);



      //  Log.e(TAG, "load: "+(AdManager.get().manage().hasBannerNativeAds()) );





    }

    @Override
    public void OnInitialized() {
        Log.e("MAIN", "OnInitialized: alv" );
        AdManager.get().manage().loadNatives(new NtUtils.OnNativeLoadInterface() {
            @Override
            public void OnSuccess() {
                Log.e(TAG, "load: a "+(AdManager.get().manage().hasNativeAds()) );
                if(AdManager.get().manage().hasNativeAds()){

                    NativeMNA a = findViewById(R.id.native_ad);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   AdManager.get().manage().showNativeIn(a);
                               }
                           });
                        }
                    }, 8000);


                }
            }

            @Override
            public void OnFail(String ss, int pos) {
                Log.e(TAG, "nativos: "+ss );
               // load(findViewById(R.id.linlay));
            }

            @Override
            public void OnImpression() {

            }
        });


        AdManager.get().manage().loadBannerNatives(new NtUtils.OnNativeLoadInterface() {
            @Override
            public void OnSuccess() {

                if(AdManager.get().manage().hasBannerNativeAds()){
                    BannerNativeMNA a = findViewById(R.id.native_banner);

                    AdManager.get().manage().showBannerNativeIn(a);

                }
            }

            @Override
            public void OnFail(String ss, int pos) {
                Log.e(TAG, "OnFail: "+ss );
            }

            @Override
            public void OnImpression() {

            }
        });

        load(findViewById(R.id.linlay));
        //startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    public void OnInitializedError(String error) {
        Log.e("MAIN", "OnInitializedError: "+error );
    }


    void getUIDs()
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdInfo adInfo = AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this).get();
                    String myId = adInfo != null ? adInfo.getId() : "NULL MEN";

                    Log.e(TAG, myId);
                } catch (Exception e) {
                    Log.e(TAG, "run: "+e.getMessage() );
                }
            }
        });
    }

}