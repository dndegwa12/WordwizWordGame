package com.deetechs.wordwiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class LevelSeparatorActivity  extends Activity{
    private InterstitialAd interstitial;
    InterstitialAd mInterstitialAd;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_separator);
       //handle next button
        Button next_level = (Button) findViewById(R.id.next_level);        
        //Listening to button event
        next_level.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent play = new Intent(getApplicationContext(), PlayGameActivity.class);              	
             	startActivity(play);  
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(LevelSeparatorActivity.this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });
}
    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}
