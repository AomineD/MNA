package com.wineberryhalley.mnaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wineberryhalley.mna.base.BannerNativeMNA;
import com.wineberryhalley.mna.base.InterstitialListener;
import com.wineberryhalley.mna.base.MListener;
import com.wineberryhalley.mna.base.NativeMNA;
import com.wineberryhalley.mna.base.RewardListener;
import com.wineberryhalley.mna.net.AdManager;

public class MainActivity extends AppCompatActivity {

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

        showIntersUn = findViewById(R.id.interstitial_un);
        showIntersFrec = findViewById(R.id.interstitial_frec);
        txt = findViewById(R.id.text_frec);

        txt.setText(getText());

        findViewById(R.id.reward_).setOnClickListener(clickReward());

        showIntersUn.setOnClickListener(clickInterstitialUn());
        showIntersFrec.setOnClickListener(clickInterstitialFrec());
        final LinearLayout lin = findViewById(R.id.linlay);
        AdManager.get().test(true);

        if(AdManager.get().checkIfLoad()){
            load(lin);
         //   Toast.makeText(this, "Cargo", Toast.LENGTH_SHORT).show();
        }else{
  AdManager.get().load().reload().setListener(new MListener(){
      @Override
      public void OnLoad() {
         load(lin);
      }

      @Override
      public void OnError(String erno) {
          Log.e("MAIN", "OnError: "+erno );
      }
  });

        }

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

    private void showBanner(LinearLayout lin){
        AdManager.get().manage().showBannerAd(lin, new MListener(){
            @Override
            public void OnLoad() {

            }

            @Override
            public void OnError(String erno) {
                Log.e("MAIN", "OnError: "+erno );
            }
        });

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


        if(AdManager.get().manage().hasBannerNativeAds()){

            BannerNativeMNA a = findViewById(R.id.native_banner);

            AdManager.get().manage().showBannerNativeIn(a);

        }

        if(AdManager.get().manage().hasNativeAds()){

            NativeMNA a = findViewById(R.id.native_ad);

            AdManager.get().manage().showNativeIn(a);

        }
    }
}