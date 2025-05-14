package com.sarker.texta;

import static android.content.ContentValues.TAG;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class TextTranslator extends AppCompatActivity {

    EditText emojiET;
    TextView translateTV;
    ImageView copy,share;
    List<GetEmoji> emojiList;

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_translator);

        copy = findViewById(R.id.copy2);
        share = findViewById(R.id.share2);

        copy.setVisibility(View.GONE);
        share.setVisibility(View.GONE);

        emojiET = findViewById(R.id.emojiET);

        translateTV = findViewById(R.id.translatedTV);
        emojiET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                translate2Emoji();
            }
        });

        copy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ClipboardManager cb=(ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",translateTV.getText().toString());
                cb.setPrimaryClip(cd);
                Toast.makeText(v.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, translateTV.getText().toString());
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

    public void translate2Emoji(){
        emojiList = getEmojiList();
        String[] words = splitSentence(emojiET.getText().toString().trim());
        List<String> newSentence = new ArrayList<>();
        for (int i = 0; i<words.length ;i++){
            String trimWord = getLetters(words[i]);
            String trimSymbols = getSymbols(words[i]);
            Log.v("Output",trimWord + "_" + trimSymbols);
            int emojiCode = isEmoji(emojiList, trimWord);
            if (emojiCode != 0){
                newSentence.add(getEmoji(emojiCode) + trimSymbols);
            }else {
                newSentence.add(words[i]);
            }
        }

        displayText(newSentence);
    }

    private void displayText(List<String> newSentence) {
        String text = "";
        for (String word : newSentence){
            text = text + " " + word;
        }

        translateTV.setText(text);

        translateTV.setVisibility(View.VISIBLE);
        if(!(emojiET.getText().toString().isEmpty())){
            copy.setVisibility(View.VISIBLE);
            share.setVisibility(View.VISIBLE);
        }
        else {
            copy.setVisibility(View.GONE);
            share.setVisibility(View.GONE);
        }

    }

    private int isEmoji(List<GetEmoji> emojiList, String word) {
//        word = similarWord(emojiList,word);
        Log.v("SimilarWord ", word);
        for (GetEmoji emoji: emojiList){
            if (emoji.getEmojiName().equalsIgnoreCase(word)){
                return emoji.getEmojiCode();
            }
        }
        return 0;
    }

    private String[] splitSentence(String sentence){

        return  sentence.split( " ");

    }

    private String getSymbols(String sentence){
        Pattern pattern = Pattern.compile("[^a-zA-Z]+");
        Matcher matcher = pattern.matcher(sentence);
        if (matcher.find()){
            Log.v(" Found: " , matcher.group());
            return sentence.substring(matcher.start());
        }
        return "";
    }

    private String getLetters(String sentence){
        Pattern pattern = Pattern.compile("[^a-zA-Z]+");
        Matcher matcher = pattern.matcher(sentence);
        if (matcher.find()){
            Log.v(" Found: " , matcher.group());
            return sentence.substring(0,matcher.start());
        }
        return sentence;
    }

    private List<GetEmoji> getEmojiList() {
        final List<GetEmoji> list = new ArrayList<>();
        list.add(new GetEmoji("Car",0x1F3CE));
        list.add(new GetEmoji("Grin",0x1F605));
        list.add(new GetEmoji("House",0x1F3E0));
        list.add(new GetEmoji("Card",0x1F0CF	));
        list.add(new GetEmoji("Smile",0x263A));
        list.add(new GetEmoji("OMG",0x1F631));
        list.add(new GetEmoji("LOL",0x1F602));
        list.add(new GetEmoji("Wonder",0x1F914));
        list.add(new GetEmoji("Love",0x2764));
        list.add(new GetEmoji("Kiss",0x1f618));
        list.add(new GetEmoji("Joker",0x1f921));
        list.add(new GetEmoji("Hug",0x1f917));
        list.add(new GetEmoji("Robot",0x1f916));
        list.add(new GetEmoji("Hush",0x1f92b));
        list.add(new GetEmoji("Expressionless",0x1f611));
        list.add(new GetEmoji("Sunglasses",0x1f60e));
        list.add(new GetEmoji("Devil",0x1f608));
        list.add(new GetEmoji("SMIRKING",0x1f60f));
        list.add(new GetEmoji("Tired",0x1f62a));
        list.add(new GetEmoji("Sleep",0x1f634));
        list.add(new GetEmoji("Angry",0x1f621));
        list.add(new GetEmoji("Fear",0x1f62c));
        list.add(new GetEmoji("Shock",0x1f632));
        list.add(new GetEmoji("hihihi",0x21f601));
        list.add(new GetEmoji("Hahaha",0x1f923));
        list.add(new GetEmoji("Forgive",0x1f64f));
        list.add(new GetEmoji("Baby",0x1f476));
        list.add(new GetEmoji("Globe",0x1f30d));
        list.add(new GetEmoji("World",0x1f30e));
        list.add(new GetEmoji("Earth",0x1f30f));
        list.add(new GetEmoji("Shit",0x1f4a9));
        list.add(new GetEmoji("Fart",0x1f4a8));
        list.add(new GetEmoji("Chat",0x1f4ac));
        list.add(new GetEmoji("Conversation",0x1f5eb));
        list.add(new GetEmoji("Ghost",0x1f5eb));
        list.add(new GetEmoji("Eye",0x1f5eb));
        list.add(new GetEmoji("Eyes",0x1f440));
        list.add(new GetEmoji("Vampire",0x1f9db));
        list.add(new GetEmoji("Alien",0x1f47d));
        list.add(new GetEmoji("UFO",0x1f6f8));
        list.add(new GetEmoji("Dangerous",0x1f571));
        list.add(new GetEmoji("Tooth",0x1f9b7));
        list.add(new GetEmoji("Tongue",0x1f445));
        list.add(new GetEmoji("Lip",0x1f445));
        list.add(new GetEmoji("Mouth",0x1f444));
        list.add(new GetEmoji("Nose",0x1f443));
        list.add(new GetEmoji("Ear",0x1f442));
        list.add(new GetEmoji("Bride",0x1f470));
        list.add(new GetEmoji("Pregnant",0x1f930));
        list.add(new GetEmoji("Like",0x1f930));
        list.add(new GetEmoji("Dislike",0x1f44e));
        list.add(new GetEmoji("Hi",0x1f44b));
        list.add(new GetEmoji("Hello",0x1f44b));
        list.add(new GetEmoji("Clap",0x1f44f));
        list.add(new GetEmoji("Clapping",0x1f44f));
        list.add(new GetEmoji("Yo",0x1f918));
        list.add(new GetEmoji("You",0x1f449));
        list.add(new GetEmoji("Fuck",0x1f595));
        list.add(new GetEmoji("Beautiful",0x1f44c));
        list.add(new GetEmoji("Pray",0x1f932));
        list.add(new GetEmoji("Stop",0x270b));
        list.add(new GetEmoji("I",0x1f64b));
        list.add(new GetEmoji("Handshake",0x1f91d));
        list.add(new GetEmoji("Yummy",0x1f60b));
        list.add(new GetEmoji("Spoon",0x1f944));
        list.add(new GetEmoji("Feeder",0x1f37c));
        list.add(new GetEmoji("Coffee",0x2615));
        list.add(new GetEmoji("Milk",0x1f95b));
        list.add(new GetEmoji("Wine",0x1f377));
        list.add(new GetEmoji("Beer",0x1f37a));
        list.add(new GetEmoji("Chocolate",0x1f36b));
        list.add(new GetEmoji("Candy",0x1f36c));
        list.add(new GetEmoji("IceCream",0x1f366));
        list.add(new GetEmoji("Cake",0x1f382));
        list.add(new GetEmoji("Birthday",0x1f382));
        list.add(new GetEmoji("Peanut",0x1f95c));
        list.add(new GetEmoji("Aubergine",0x1f346));
        list.add(new GetEmoji("Chili",0x1f336));
        list.add(new GetEmoji("Mushroom",0x1f344));
        list.add(new GetEmoji("Apple",0x1f344));
        list.add(new GetEmoji("Coconut",0x1f965));
        list.add(new GetEmoji("Mango",0x1f96d));
        list.add(new GetEmoji("Pineapple",0x1f34d));
        list.add(new GetEmoji("Banana",0x1f34c));
        list.add(new GetEmoji("Lemon",0x1f34b));
        list.add(new GetEmoji("Watermelon",0x1f349));
        list.add(new GetEmoji("Juice",0x1f379));
        list.add(new GetEmoji("Strawberry",0x1f353));
        list.add(new GetEmoji("Chicken",0x1f357));
        list.add(new GetEmoji("Bread",0x1f35e));
        list.add(new GetEmoji("Egg",0x1f95a));
        list.add(new GetEmoji("Cooking",0x1f373));
        list.add(new GetEmoji("Broken Heart",0x1f494));
        list.add(new GetEmoji("18+",0x1f51e));
        list.add(new GetEmoji("Ring",0x1f48d));
        list.add(new GetEmoji("Fire",0x1f525));
        list.add(new GetEmoji("Couple",0x1f48));
        list.add(new GetEmoji("Selfie",0x1f48));
        list.add(new GetEmoji("Crown",0x1f451));
        list.add(new GetEmoji("Handbag",0x1f45c));
        list.add(new GetEmoji("Shoe",0x1f45f));
        list.add(new GetEmoji("Jeans",0x1f456));
        list.add(new GetEmoji("Shirt",0x1f455));
        list.add(new GetEmoji("Bra",0x1f459));
        list.add(new GetEmoji("Mouse",0x1f42d));
        list.add(new GetEmoji("Dog",0x1f436));
        list.add(new GetEmoji("Tiger",0x1f42f));
        list.add(new GetEmoji("Lion",0x1f981));
        list.add(new GetEmoji("Cow",0x1f42e));
        list.add(new GetEmoji("Frog",0x1f438));
        list.add(new GetEmoji("Monkey",0x1f435));
        list.add(new GetEmoji("Snake",0x1f40d));
        list.add(new GetEmoji("Whale",0x1f40b));
        list.add(new GetEmoji("Cat",0x1f431));
        list.add(new GetEmoji("Sun",0x1f31e));
        list.add(new GetEmoji("Moon",0x1f319));
        list.add(new GetEmoji("Rainbow",0x1f308));
        list.add(new GetEmoji("Football",0x26bd));
        list.add(new GetEmoji("Run",0x1f3c3));
        list.add(new GetEmoji("Badminton",0x1f3f8));
        list.add(new GetEmoji("House",0x1f3e1));
        list.add(new GetEmoji("Home",0x1f3e0));
        list.add(new GetEmoji("Smoke",0x1f6ac));
        list.add(new GetEmoji("Ambulance",0x1f691));
        list.add(new GetEmoji("Bus",0x1f68c));
        list.add(new GetEmoji("Bike",0x1f3cd));
        list.add(new GetEmoji("Train",0x1f686));
        list.add(new GetEmoji("Rocket",0x1f680));
        list.add(new GetEmoji("Mobile",0x1f4f1));
        list.add(new GetEmoji("Network",0x1f4f6));
        list.add(new GetEmoji("Camera",0x1f4f8));
        list.add(new GetEmoji("Video",0x1f3a5));
        list.add(new GetEmoji("Headphone",0x1f3a7));
        list.add(new GetEmoji("Hand mic",0x1f4e2));
        list.add(new GetEmoji("Tv",0x1f4fa));
        list.add(new GetEmoji("Mouse",0x1f5b1));
        list.add(new GetEmoji("Radio",0x1f4fb));
        list.add(new GetEmoji("Laptop",0x1f4bb));
        list.add(new GetEmoji("Computer",0x1f5a5));
        list.add(new GetEmoji("Battery",0x1f50b));
        list.add(new GetEmoji("Music",0x1f3b5));
        list.add(new GetEmoji("Think",0x1f914));
        list.add(new GetEmoji("Sad",0x1f61e));
        list.add(new GetEmoji("Senti",0x1f643));
        list.add(new GetEmoji("Mask",0x1f637));
        list.add(new GetEmoji("Cry",0x1f62d));
        list.add(new GetEmoji("Crying",0x1f62d));
        list.add(new GetEmoji("Emotional",0x1f97a));
        list.add(new GetEmoji("No",0x1f645));
        list.add(new GetEmoji("Brain",0x1f9e0));
        list.add(new GetEmoji("Zombie",0x1f9df));
        list.add(new GetEmoji("Smile",0x263a));
        list.add(new GetEmoji("Ee",0x1f601));
        list.add(new GetEmoji("My",0x1f917));
        list.add(new GetEmoji("Mine",0x1f917));
        list.add(new GetEmoji("Rain",0x1f327));
        list.add(new GetEmoji("Your",0x1f449));
        list.add(new GetEmoji("How",0x1f937));
        return list;
    }

    private String getEmoji(int emojicode) {
        return new String(Character.toChars(emojicode));
    }


    private boolean isLetter(char character){
        return Character.isLetter(character);
    }

    private char[] word2Char(String word){
        return word.toCharArray();
    }

    private String similarWord(List<GetEmoji> emojiList, String word){
        char[] chkLetters = word2Char(word);
        List<Match> matches = new ArrayList<>();
        Match bestMatch = null;

        for (GetEmoji emoji : emojiList){
            char[] letters = word2Char(emoji.getEmojiName().toLowerCase());

            if ((chkLetters.length>0) && (chkLetters.length >= letters.length) && (letters[0] == chkLetters[0])){
                double check = 0;
                for(int i = 0; i<letters.length; i++){
                    if (letters[i] == chkLetters[i]){
                        check = check + 1;
                    }
                }
                Log.v("Check ", "" + String.valueOf(check/letters.length));
                matches.add(new Match(emoji.getEmojiName(),check/letters.length));
            }
        }

        for (int i = 0; i<matches.size(); i++){
            for (int j = 0; j<matches.size(); j++){
                if (matches.get(i).getMatch() >= matches.get(j).getMatch()){
                    bestMatch = matches.get(i);
                }else {
                    bestMatch = matches.get(j);
                }
            }
        }
        if (bestMatch != null){
            if (bestMatch.getMatch() >= 0.9){
                return bestMatch.getEmojiName();
            }
        }
        return word;
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
