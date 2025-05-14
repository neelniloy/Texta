package com.sarker.texta;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.sarker.texta.databinding.ActivityText2BinaryBinding;

public class Text2Binary extends AppCompatActivity {

    private ActivityText2BinaryBinding text2BinaryBinding;

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text2BinaryBinding = ActivityText2BinaryBinding.inflate(getLayoutInflater());
        View view = text2BinaryBinding.getRoot();
        setContentView(view);


        text2BinaryBinding.btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byte[] bytes = text2BinaryBinding.inputText.getText().toString().getBytes();
                StringBuilder binary = new StringBuilder();
                for (byte b : bytes)
                {
                    int val = b;
                    for (int i = 0; i < 8; i++)
                    {
                        binary.append((val & 128) == 0 ? 0 : 1);
                        val <<= 1;
                    }
                    binary.append(' ');
                }

                text2BinaryBinding.Outputdisply.setText(""+binary);
                text2BinaryBinding.btnContainer.setVisibility(View.VISIBLE);

            }
        });

        text2BinaryBinding.Cpybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cb=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",text2BinaryBinding.Outputdisply.getText().toString());
                cb.setPrimaryClip(cd);
                Toast.makeText(Text2Binary.this, "Binary Copied", Toast.LENGTH_SHORT).show();

            }
        });



        text2BinaryBinding.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, "" + text2BinaryBinding.Outputdisply.getText().toString());
                startActivity(Intent.createChooser(share, "Share With Friends"));
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
    public void onDestroy() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
            mInterstitialAd = null;
        }
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

}