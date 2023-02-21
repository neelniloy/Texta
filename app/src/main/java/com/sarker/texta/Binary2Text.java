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
import com.sarker.texta.databinding.ActivityBinary2TextBinding;
import com.sarker.texta.databinding.ActivityText2BinaryBinding;

public class Binary2Text extends AppCompatActivity {
    private ActivityBinary2TextBinding binary2TextBinding;

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binary2TextBinding = ActivityBinary2TextBinding.inflate(getLayoutInflater());
        View view = binary2TextBinding.getRoot();
        setContentView(view);


        binary2TextBinding.btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] singleBinaryArray = binary2TextBinding.inputText.getText().toString().split("\\s");
                String finalResult = "";
                for (String string : singleBinaryArray) {

                    try {
                        Character c = (char) Integer.parseInt(string, 2);
                        finalResult += c.toString();
                    }catch (Exception e){
                        Toast.makeText(Binary2Text.this, "Invalid Binary Number", Toast.LENGTH_SHORT).show();
                    }

                }

                binary2TextBinding.Outputdisply.setText(""+finalResult);
                binary2TextBinding.btnContainer.setVisibility(View.VISIBLE);

            }
        });

        binary2TextBinding.Cpybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cb=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",binary2TextBinding.Outputdisply.getText().toString());
                cb.setPrimaryClip(cd);
                Toast.makeText(Binary2Text.this, "Text Copied", Toast.LENGTH_SHORT).show();

            }
        });



        binary2TextBinding.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, "" + binary2TextBinding.Outputdisply.getText().toString());
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
    public void onBackPressed() {

        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
            mInterstitialAd = null;
            super.onBackPressed();
            handler.removeCallbacks(runnable);
        } else {
            super.onBackPressed();
            handler.removeCallbacks(runnable);
        }
    }
}