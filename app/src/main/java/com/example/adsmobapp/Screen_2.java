package com.example.adsmobapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Screen_2 extends AppCompatActivity {

    private AdView mAdView;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        btnNext = findViewById(R.id.btnNextScreen2);

        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // Load banner ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Navigate to Screen 3
        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(Screen_2.this, Screen_3.class));
        });
    }
}
