package com.sarker.texta;

import static android.content.ContentValues.TAG;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Blank extends AppCompatActivity {

    LinearLayout btnContainer;
    CheckBox addLine,addIndex;
    int limit = 0;
    TextView outputdisplay;
    StringBuilder s = null;
    String rptTXT;
    String txt ="";
    StringBuilder stringBuilder;

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        Button generatebtn= findViewById(R.id.generatebtn);
        addLine = findViewById(R.id.newLine);
        addIndex = findViewById(R.id.addIndex);
        Button cpybtn= findViewById(R.id.Cpybtn);
        Button sharebtn= findViewById(R.id.sharebtn);
        final EditText inputlimit= findViewById(R.id.inputlimit);
        outputdisplay = findViewById(R.id.Outputdisply);
        btnContainer = findViewById(R.id.btnContainer);
        btnContainer.setVisibility(View.GONE);
        outputdisplay.setVisibility(View.GONE);


        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt = "";
                outputdisplay.setText("");

                if(inputlimit.getText().toString().isEmpty() || inputlimit.getText().toString().equals("0")){
                    btnContainer.setVisibility(View.GONE);
                    outputdisplay.setVisibility(View.GONE);
                    Toast.makeText(Blank.this, "Have Some Sense! How can I repeat ZERO Times?", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(inputlimit.getText().toString()) > 10000){
                    Toast.makeText(Blank.this, "Enter limit less than 10000", Toast.LENGTH_SHORT).show();
                }else{
                    rptTXT = " ";
                    limit = Integer.parseInt(inputlimit.getText().toString());
                    if(addLine.isChecked() && addIndex.isChecked()){
                        stringBuilder = new StringBuilder();
                        for(int i = 0; i<limit; i++){
                            int val = i+1;
                            stringBuilder.append(val+". "+rptTXT+"\n");
                            txt = String.valueOf(stringBuilder);
                        }
                    } else if(addLine.isChecked()){
                        for(int i = 0; i< limit; i++){
                            txt = txt + rptTXT + "\n";
                        }
                    }else if(addIndex.isChecked()){
                        stringBuilder = new StringBuilder();
                        for(int i = 0; i<limit; i++){
                            int val = i+1;
                            stringBuilder.append(val+". "+rptTXT+" ");
                            txt = String.valueOf(stringBuilder);
                        }
                    }else{
                        for(int i = 0; i<limit; i++){
                            txt = txt + rptTXT + " ";
                        }
                    }
                    outputdisplay.setText(txt);
                    btnContainer.setVisibility(View.VISIBLE);
                    outputdisplay.setVisibility(View.VISIBLE);
                }

            }
        });
        cpybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cb=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",outputdisplay.getText().toString());
                cb.setPrimaryClip(cd);
                Toast.makeText(Blank.this, "Text Copied", Toast.LENGTH_SHORT).show();

            }
        });


        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);

                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, "" + outputdisplay.getText().toString());
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
