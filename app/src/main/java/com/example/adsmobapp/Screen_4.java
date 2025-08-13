package com.example.adsmobapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Screen_4 extends AppCompatActivity {

    private AdView mAdView;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        btnNext = findViewById(R.id.btnNextScreen4);

        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // Load Banner Ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Go to Screen5
        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(Screen_4.this, Screen_5.class));
        });
    }
}
