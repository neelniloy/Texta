package com.sarker.texta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class About extends AppCompatActivity {

    private CircleImageView Facebook,Github,Linkdin,Youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Facebook = findViewById(R.id.facebook);
        Github = findViewById(R.id.github);
        Linkdin = findViewById(R.id.linkdin);
        Youtube = findViewById(R.id.youtube);

        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/neel.niloya";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

//                Intent chooserIntent = Intent.createChooser(intent, "Choose Action");
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent});
//                startActivity(chooserIntent);
            }
        });

        Github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/neelniloy";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

//                Intent chooserIntent = Intent.createChooser(intent, "Choose Action");
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent});
//                startActivity(chooserIntent);
            }
        });

        Linkdin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/in/niloysarker/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

//                Intent chooserIntent = Intent.createChooser(intent, "Choose Action");
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent});
//                startActivity(chooserIntent);
            }
        });

        Youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/channel/UCwt6QqoIq3CpaI-5dVtQLzg";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

//                Intent chooserIntent = Intent.createChooser(intent, "Choose Action");
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent});
//                startActivity(chooserIntent);
            }
        });


        MobileAds.initialize(this, initializationStatus -> {});

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
