package com.deetechs.wordwiz;

import com.deetechs.wordwiz.HowToPlayActivity;
import com.deetechs.wordwiz.PlayGameActivity;
import com.deetechs.wordwiz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WordWizAppActivity extends Activity {
	 /** Called when the activity is first created. 
     * Main entry with initial buttons*/
     private InterstitialAd interstitial;
     InterstitialAd mInterstitialAd;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
            
        
        Button btn_how_to_play = (Button) findViewById(R.id.how_to_play);        
        //Listening to button event
        btn_how_to_play.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent how_to_play = new Intent(getApplicationContext(), InstructionsActivity.class); 
                startActivity(how_to_play);
            }
        });
        Button play = (Button) findViewById(R.id.play);        
        //Listening to button event
        play.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent how_to_play = new Intent(getApplicationContext(), PlayGameActivity.class); 
                startActivity(how_to_play);
            }
        });        
        Button hard_play = (Button) findViewById(R.id.resume);        
        //Listening to button event
        hard_play.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View arg0) {
               
                String res= getScores();
                Log.d("List: ",res);
                String rs []=res.split(",");
                int count = Integer.parseInt(rs[1]);
                int level = Integer.parseInt(rs[2]);
                int wrong_tracker = Integer.parseInt(rs[3]);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("counter", count);
                dataBundle.putInt("levels", level);
                dataBundle.putInt("wrongs", wrong_tracker);
                Intent resume_intent = new Intent();
                resume_intent.setClass(WordWizAppActivity.this, ResumePlayActivity.class);
                resume_intent.putExtras(dataBundle);
                startActivity(resume_intent);
            }
        });        
        Button info = (Button) findViewById(R.id.info);        
        //Listening to button event
        info.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent how_to_play = new Intent(getApplicationContext(), HowToPlayActivity.class); 
                startActivity(how_to_play);
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(WordWizAppActivity.this);
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
    public String getScores(){
    		DatabaseHandler db = new DatabaseHandler(this);        
        // Get only one contact
           String res = db.getContact();
           return res;           
    }

    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}