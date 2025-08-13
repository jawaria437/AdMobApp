package com.example.adsmobapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class AppOpenManager implements Application.ActivityLifecycleCallbacks {

    private static final String LOG_TAG = "AppOpenManager";
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921"; // Test ID

    private AppOpenAd appOpenAd = null;
    private Activity currentActivity;
    private long loadTime = 0;

    private final MyApplication myApplication;

    public AppOpenManager(MyApplication myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        fetchAd();
    }

    /** Lifecycle methods **/
    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = activity;

        // Show ad if available
        if (isAdAvailable()) {
            showAdIfAvailable();
        } else {
            fetchAd();
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {}
    @Override
    public void onActivityStopped(@NonNull Activity activity) {}
    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}
    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {}

    /** Check if ad is available */
    private boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    /** Time check for fresh ads */
    private boolean wasLoadTimeLessThanNHoursAgo(long hours) {
        long dateDifference = (new Date()).getTime() - loadTime;
        long numMilliSecondsPerHour = 3600000L;
        return (dateDifference < (numMilliSecondsPerHour * hours));
    }

    /** Load an App Open Ad */
    public void fetchAd() {
        if (isAdAvailable()) {
            return;
        }

        AdRequest request = new AdRequest.Builder().build();

        AppOpenAd.load(
                myApplication,
                AD_UNIT_ID,
                request,
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd ad) {
                        appOpenAd = ad;
                        loadTime = (new Date()).getTime();
                        Log.d(LOG_TAG, "App Open Ad Loaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(LOG_TAG, "Failed to load App Open Ad: " + loadAdError.getMessage());
                    }
                }
        );
    }

    /** Show App Open Ad */
    public void showAdIfAvailable() {
        if (!isAdAvailable()) {
            fetchAd();
            return;
        }

        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                appOpenAd = null;
                fetchAd();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                appOpenAd = null;
            }

            @Override
            public void onAdShowedFullScreenContent() {
                appOpenAd = null;
            }
        });

        appOpenAd.show(currentActivity);
    }
}
