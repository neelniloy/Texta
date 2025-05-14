package com.sarker.texta;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

public class FontStyle extends AppCompatActivity {

    private ArrayList<FontInfo> fList = new ArrayList<>();

    private RecyclerView fRecyclerView;
    private FontAdapter fAdapter;
    private RecyclerView.LayoutManager fLayoutManager;
    private EditText input;

    private String[] font = {"Bubble","Currency","Magic","Knight","Antrophobia", "Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpius","Sagittarius","Capricorn","Aquarius","Pisces"};


    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_style);

        fRecyclerView = findViewById(R.id.recycler_view_font);
        input = findViewById(R.id.inputfont);
        fRecyclerView.setHasFixedSize(true);
        fLayoutManager = new LinearLayoutManager(this);
        fAdapter = new FontAdapter(this,fList);
        fRecyclerView.setLayoutManager(fLayoutManager);
        fRecyclerView.setAdapter(fAdapter);

        input.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {

                if(input.getText().toString().trim().length()>0){

                    fRecyclerView.setVisibility(View.VISIBLE);

                    String text = input.getText().toString().trim();

                    fList.clear();
                    fAdapter.notifyDataSetChanged();

                    for (int i = 0 ; i < 15; i++){

                        fList.add(new FontInfo(font[i], text));
                    }

                    fAdapter.notifyDataSetChanged();


                }else {
                    fRecyclerView.setVisibility(View.GONE);
                }



            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
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
    public void onDestroy() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
            mInterstitialAd = null;
        }
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

}