package com.example.adsmobapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Screen_3 extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        btnNext = findViewById(R.id.btnNextScreen3);

        loadInterstitialAd();

        btnNext.setOnClickListener(v -> {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(Screen_3.this);
            } else {
                // If ad not ready, go directly
                goToNextScreen();
            }
        });
    }

    /** Load Interstitial Ad **/
    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/1033173712", // Test Ad Unit ID
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        // Ad close listener
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd = null;
                                goToNextScreen();
                                loadInterstitialAd(); // Preload for next time
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                mInterstitialAd = null;
                                goToNextScreen();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Toast.makeText(Screen_3.this, "Ad failed to load", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    /** Navigate to Screen4 **/
    private void goToNextScreen() {
        Intent intent = new Intent(Screen_3.this, Screen_4.class);
        startActivity(intent);
    }
}
