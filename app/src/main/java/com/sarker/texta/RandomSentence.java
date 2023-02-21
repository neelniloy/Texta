package com.sarker.texta;

import static android.content.ContentValues.TAG;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class RandomSentence extends AppCompatActivity {

    Button btngen;
    TextView tvresult;
    ImageView copy,share;
    String options[]={
            "Do you have a name or can I call you mine?",
            "Roses are red, my face is too, that only happens when I’m around you.",
            "You may fall from the sky, you may fall from a tree, but the best way to fall… is in love with me.",
            "Can I follow you? Cause my mom told me to follow my dreams.",
            "There’s only one thing I want to change about you. Your last name.",
            "If being in love was illegal, will you be my partner in crime?",
            "Are you a camera? Because every time I look at you, I smile.",
            "I’m no organ donor but I’d be happy to give you my heart.",
            "I heard you’re good in algebra, can you replace my X without asking Y",
            "Guess what I’m wearing? The smile you gave me!",
            "You’re That “Nothing” When People Ask Me What I’m Thinking About.",
            "Are you a keyboard? Because you’re just my type.",
            "Are you a bank loan? Because you got my interest",
            "If I had to rate you out of 10 I’d rate you a 9… because I am the one that you are missing!",
            "Are you a time traveler? Cause I see you in my future!",
            "Do you know if there are any police around? Cause I’m about to steal your heart.",
            "There isn’t a word in the dictionary to describe how beautiful you are.",
            "My friends bet I can’t talk to the prettiest girl. Wanna use their money to buy drinks?",
            "Of all your beautiful curves, your smile is my favourite.",
            "Roses are red violets are blue, I can’t rhyme but can I date you?",
            "You know what’s beautiful? Read the first word.",
            "I would take you to the movies but they don’t allow snacks",
            "I should call you Google, because you have everything I’m looking for.",
            "Do you have a band aid? Cause I scrapped my knees falling for you.",
            "I lost my teddy bear can i sleep with you tonight?",
            "If I were a cat i’d spend all 9 lives with you.",
            "Can you touch me? I want to tell my friends I was touched by an Angel.",
            "I don’t really believe in love at first sight, until I saw you.",
            "Are you the sun? Because you’re so beautiful it’s blinding me.",
            "Are you made of copper and tellurium? Because you’re CuTe!",
            "I just had to come talk with you. Sweetness is my weakness",
            "Do you play soccer? Because you’re a keeper!",
            "Give her 12 roses. 11 real, 1 fake and say “I will stop loving you, when all the roses die”",
            "You really shouldn’t wear makeup. You’re messing with perfection!",
            "I’m no electrician, but I can light up your day.",
            "If you were a potato you’d be a sweet one.",
            "If I was an octopus, all my 3 hearts would beat for you\uFEFF.",
            "You dropped something! [What?] Your smile",
            "Did you die recently? Cause girl, you look like an angel to me.",
            "Is your body from McDonald’s? Cause I’m loving it!",
            "Are you a 45 degree angle? Because you’re acute-y!",
            "Are you the ocean? Cuz baby I want to swim in you all day",
            "You look cold. Want to use me as a blanket?",
            "I might be ugly but I’ll treat you right!",
            "Are you netflix? Because i could watch you for hours.",
            "Can I borrow a quarter? [“What for?”] I want to call my mom and tell her I just met the man/woman of my dreams.",
            "Do you like star wars? Because yoda only one for me!",
            "You can’t be my first, but you can be my last",
            "I’m afraid of the dark… Will you sleep with me tonight?",
            "You can’t be my first, but you could be my next.",
            "Spoon me like your favorite ice cream!",
            "Are you a magician? Because whenever I look at you, everyone else disappears!",
            "Do I know you? ‘Cause you look a lot like my next girlfriend/boyfriend.",
            "Do you know what my shirt is made of? U material?",
            "Are you religious? Because you’re the answer to all my prayers.",
            "I seem to have lost my phone number. Can I have yours?",
            "I’m lost. Can you give me directions to your heart?",
            "Are you a parking ticket? ‘Cause you’ve got fine written all over you.",
            "Was your dad a boxer? Because damn, you’re a knockout!",
            "I was wondering if you had an extra heart. Mine was just stolen.",
            "Would you grab my arm, so I can tell my friends I’ve been touched by an angel?",
            "There’s only one thing I want to change about you, and that’s your last name.",
            "Do you believe in love at first sight or should I pass by again?",
            "Is your dad a terrorist? Cause you’re the bomb.",
            "Did the sun come out or did you just smile at me?",
            "Kiss me if I’m wrong, but dinosaurs still exist, right?",
            "Hey, you’re pretty and I’m cute. Together we’d be Pretty Cute.",
            "Did it hurt? When you fell from heaven?",
            "Hello, I’m a thief, and I’m here to steal your heart.",
            "I may not be a genie, but I can make your dreams come true.",
            "If nothing lasts forever, will you be my nothing?",
            "There must be something wrong with my eyes, I can’t take them off you.",
            "Was you father an alien? Because there’s nothing else like you on Earth!",
            "Was your father a thief? ‘Cause someone stole the stars from the sky and put them in your eyes.",
            "Do you have a pencil? Cause I want to erase your past and write our future.",
            "I’d say God Bless you, but it looks like he already did.",
            "I must be in a museum, because you truly are a work of art.",
            "Can you take me to the doctor? Because I just broke my leg falling for you.",
            "Are you a dictionary? Cause you’re adding meaning to my life.",
            "You remind me of a magnet, because you sure are attracting me over here!",
            "Somebody call the cops, because it’s got to be illegal to look that good!",
            "Is it hot in here or is it just you?",
            "Even if there wasn't gravity on earth, I'd still fall for you.",
            "If you were a vegetable you'd be a cute-cumber.",
            "Let me tie your shoes, cause I don't want you falling for anyone else.",
            "I don't have a library card, but do you mind if I check you out?",
            "Are you an orphanage? Cause I wanna give you kids.",
            "Can I take your picture to prove to all my friends that angels do exist?",
            "Is your daddy a Baker? Because you've got some nice buns!",
            "Do you want to see a picture of a beautiful person? (hold up a mirror)",
            "Nice hair, wanna mess it up?",
            "When God made you, he was showing off.",
            "Your lips look so lonely.... Would they like to meet mine?",
            "My love for you is like diarrhea, I just can't hold it in.",
            "Can I borrow a kiss? I promise I'll give it back.",
            "Does your father sell diamonds? Because you are FLAWLESS!",
            "Life without you would be like a broken pencil... pointless.",
            "You are like a candy bar: half sweet and half nuts.",
            "Excuse me, I just noticed you noticing me and I just wanted to give you notice that I noticed you too.",
            "Somebody better call God, cuz heaven's missing an angel!",
            "If you were a tear in my eye I would not cry for fear of losing you.",
            "Are you my phone charger? Because without you, I'd die.",
            "Are you a cat? Cause you are purrrfect",
            "Are you a vampire? Cause you looked a little thirsty when you looked at me.",
            "You are the reason men fall in love.",
            "Come live in my heart, and pay no rent.",
            "Hey, my name's Microsft. Can I crash at your place tonight?",
            "If I could rearrange the alphabet, I’d put ‘U’ and ‘I’ together.",
            "People are catching Coronavirus but the only thing I’m catching is feelings for you.",
            "I’d never play hide and seek with you because someone like you is impossible to find",
            "Covid 19 cancelling everything, except my feelings for you.",
            "If the Coronavirus doesn’t take you out, can I?",
            "Are you Coronavirus? Because i wouldn’t mind spending two weeks in bed with you.",
            "I got tested for the Coronavirus… doctor said, i’m fine i’m just missing one thing, Vitamin U",
            "If you were a Youtube ad I wouldn’t skip.",
            "Smoking is hazardous to your health… and baby, you’re killing me!",
            "Are you terms and conditions? Cause whatever you say I’ll always agree with you.",
            "You can’t spell virus without us, so what’s up?",
            "Are you Coronovirus coz I’m panicking and not sure how to approach you.",
            "Are you Covid 19? Because you take my breath away!",
            "Did the Coronavirus give you a temperature? Because you’re hot!",
            "Wow, you’re so beautiful I wish we could be in quarantine together!",
            "Are you a poster? Because I want to pin you on a wall",
            "I’m not too good at algebra, but doesn’t U+I = 69?",
            "Life is like a dick. When it gets hard, “Fuck it”.",
            "Girl, I’m jealous of your heart. ‘Cause it’s pumping inside you and I’m not.",
            "If your heart was a prison, I would like to be sentenced for life.",
            "What do you call a Bengali Girl who just asked you,\"Biscuit ki diye khabo?\"[what], Sadia Khan -_-",
            "What do you call a Bengali Girl who just served tea?,[what], Sadia Aslam -_-",
            "I’m not a photographer, but I can picture me and you together."};

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_sentence);

        btngen = (Button) findViewById(R.id.btngen);
        tvresult = (TextView)findViewById(R.id.tvresult);
        copy = findViewById(R.id.copy1);
        share = findViewById(R.id.share1);

        copy.setVisibility(View.GONE);
        share.setVisibility(View.GONE);

        btngen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                copy.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);

                Random randGen = new Random();
                final int rands = randGen.nextInt(options.length);
                tvresult.setText(options[rands]);

            }
        });
        copy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ClipboardManager cb=(ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",tvresult.getText().toString());
                cb.setPrimaryClip(cd);
                Toast.makeText(v.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, ""+ tvresult.getText().toString());
                v.getContext().startActivity(Intent.createChooser(share, "Share With Friends"));
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