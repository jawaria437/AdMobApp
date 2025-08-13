package com.example.adsmobapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Screen_5 extends AppCompatActivity {

    private AdView mAdView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);

        btnBack = findViewById(R.id.btnBackToScreen1);

        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // Load Banner Ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Navigate back to Screen1
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(Screen_5.this, Screen_1.class));
        });
    }
}
