package com.wineberryhalley.mnaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.wineberryhalley.mna.net.AdManager;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        LinearLayout l = findViewById(R.id.banner_container);

        if(AdManager.get().checkIfLoad()){
            AdManager.get().manage().showBannerAd(l);
        }

    }
}