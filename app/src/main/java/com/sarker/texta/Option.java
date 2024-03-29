package com.sarker.texta;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import de.hdodenhof.circleimageview.CircleImageView;

public class Option extends AppCompatActivity implements View.OnClickListener {

    private long backPressedTime;
    private Toast backtoast;
    private CardView textRepeater,text2emoji,ascii,blank,random_sentence,emoji2text,emojiart,font,t2b,b2t;
    private CircleImageView about;

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        textRepeater = findViewById(R.id.cv_text_repeater);
        text2emoji = findViewById(R.id.cv_text2emoji);
        ascii = findViewById(R.id.cv_ascii);
        blank = findViewById(R.id.cv_blank);
        random_sentence = findViewById(R.id.cv_random_sentence);
        emoji2text = findViewById(R.id.cv_emoji2text);
        emojiart = findViewById(R.id.cv_emojiart);
        font = findViewById(R.id.cv_font);
        t2b = findViewById(R.id.cv_t2b);
        b2t = findViewById(R.id.cv_b2t);

        textRepeater.setOnClickListener(this);
        text2emoji.setOnClickListener(this);
        ascii.setOnClickListener(this);
        blank.setOnClickListener(this);
        random_sentence.setOnClickListener(this);
        emoji2text.setOnClickListener(this);
        emojiart.setOnClickListener(this);

        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Option.this,About.class);
                startActivity(intent);
            }

        });

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Option.this,FontStyle.class);
                startActivity(intent);
            }

        });


        t2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Option.this,Text2Binary.class);
                startActivity(intent);

            }

        });

        b2t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Option.this,Binary2Text.class);
                startActivity(intent);

            }

        });

        MobileAds.initialize(this, initializationStatus -> {
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        handler = new Handler(Looper.getMainLooper());
        runnable = () -> InterstitialAd.load(this,"ca-app-pub-1276360114688784/9853001105", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
        handler.postDelayed(runnable, 15*1000L);


    }
    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){

            case R.id.cv_text_repeater : i = new Intent(this,Dashboard.class);startActivity(i) ;
                break;
            case R.id.cv_blank : i = new Intent(this,Blank.class);startActivity(i) ;
                break;
            case R.id.cv_text2emoji : i = new Intent(this,TextTranslator.class);startActivity(i);
                break;
            case R.id.cv_emoji2text : i = new Intent(this,Emoji.class);startActivity(i);
                break;
            case R.id.cv_ascii : i = new Intent(this,AsciiV2.class);startActivity(i);
                break;
            case R.id.cv_emojiart : i = new Intent(this,EmojiArt.class);startActivity(i);
                break;
            case R.id.cv_random_sentence : i = new Intent(this,RandomSentence.class);startActivity(i);
                break;
            default:
                break;
        }


    }


    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backtoast.cancel();
            if (mInterstitialAd != null) {
                mInterstitialAd.show(this);
                mInterstitialAd = null;
                super.onBackPressed();
                handler.removeCallbacks(runnable);
                finish();
            } else {
                super.onBackPressed();
                handler.removeCallbacks(runnable);
                finish();
            }

            return;
        } else {
            backtoast = Toast.makeText(Option.this, "Press Again To Exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }

}
