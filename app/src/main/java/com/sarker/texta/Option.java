package com.sarker.texta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import de.hdodenhof.circleimageview.CircleImageView;

public class Option extends AppCompatActivity implements View.OnClickListener {

    private long backPressedTime;
    private Toast backtoast;
    private CardView textRepeater,text2emoji,ascii,blank,random_sentence,emoji2text,emojiart,font;
    private CircleImageView about;

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
            super.onBackPressed();
            finish();
            return;
        } else {
            backtoast = Toast.makeText(Option.this, "Press Again To Exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }

}
