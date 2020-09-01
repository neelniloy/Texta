package com.sarker.texta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class EmojiArt extends AppCompatActivity {

    private static final int ANTI_ALIAS_FLAG =537 ;
    private EditText etemoji,ettext;
    private Button btnsubmit;
    private TextView tvout;
    private static String result = "";
    private static String emoji ;
    private ImageView copy,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_emoji_art);

        copy = findViewById(R.id.copy3);
        share = findViewById(R.id.share3);

        copy.setVisibility(View.GONE);
        share.setVisibility(View.GONE);

        etemoji=findViewById(R.id.etemoji);
        ettext=findViewById(R.id.ettext);
        btnsubmit=findViewById(R.id.btnsubmit);
        tvout=findViewById(R.id.tvout);

        copy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ClipboardManager cb=(ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",result);
                cb.setPrimaryClip(cd);
                Toast.makeText(v.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, ""+ result.toString());
                v.getContext().startActivity(Intent.createChooser(share, "Share With Friends"));
            }
        });


        final Toast toast= Toast.makeText(getApplicationContext(),"Have some sense!! Without Text Or Emoji how can I Draw!?",Toast.LENGTH_LONG);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = ettext.getText().toString().trim();
                if (etemoji.getText().toString().trim().isEmpty() || text.isEmpty()) {
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    emoji = etemoji.getText().toString();

                    result = "";

                    char[] str = text.toCharArray();
                    printPattern(str);


                    tvout.setText(result);

                }
                if (!(result.isEmpty())){

                    copy.setVisibility(View.VISIBLE);
                    share.setVisibility(View.VISIBLE);
                }

            }
        });


    }
    static void a()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j <= 4; j++) {

                if ((j == 0 || j == 4) && i != 0 ||
                        i == 0 && j != 0 && j != 4 ||
                        i == 4)

                    result+=emoji;
                else
                    result+="     ";
            }

            result+="\n";
        }
    }

    // Function to print the letter B
    static void b()
    {
        result+="\n";
        for (int i=0;i<9 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0&&j!=4||j==0||j==4&&i!=0&&i!=4&&i!=8||i==4&&j!=4||i==8&&j!=4)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter C
    static void c()
    {
        result+="\n";
        for (int i=0;i<8 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0&&j!=0||i==7&&j!=0||j==0&&i!=0&&i!=7)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter D
    static void d()
    {
        result+="\n";
        for (int i=0;i<8 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0&&j!=4||i==7&&j!=4||j==0||j==4&&i!=0&&i!=7)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter E
    static void e()
    {
        result+="\n";
        for (int i=0;i<9 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0||i==8||i==4||j==0)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter F
    static void f()
    {
        result+="\n";
        for (int i=0;i<9 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0||i==4||j==0)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter G
    static void g()
    {
        result+="\n";
        int i, j;
        for(i = 0; i < 8; i++)
        {
            for(j = 0; j < 8; j++)
            {
                if((j == 1 && i != 0 && i != 7) ||
                        ((i == 0 || i == 7) && j > 1 &&
                                j < 6) || (i == (4) &&
                        j > 2 && j < 7) || (j == 6 &&
                        i != 0 && i >= (5) && i != 7))
                    result+=emoji;
                else
                    result+="     ";

            }
            result+="\n";
        }
    }

    // Function to print the letter H
    static void h()
    {
        result+="\n";
        for (int i=0;i<7 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if ((j==0||j==4)||i<=8&&i==3)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter I
    static void i()
    {
        result+="\n";
        for (int i=0;i<8 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0||i==7||j==2)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter J
    static void j()
    {
        result+="\n";
        for (int i=0;i<8 ;i++ )
        {
            for (int j=0;j<7 ;j++ )
            {
                if (i==0&&j!=0&&j!=6 || (i<6 && j==3) || (i==5&&j==0) || (i==6&&(j>0&&j<3)))
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter K
    static void k()
    {
        result+="\n";
        for (int i=0;i<8 ;i++ )
        {
            for (int j=0;j<=5 ;j++ )
            {
                if (4-j==i||3+j==i||j==0)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter L
    static void l()
    {
        result+="\n";
        for(int i = 0 ; i<8 ; i++) {

            for(int j = 0 ; j<8 ; j++) {

                if(j==1 || (i==7 && j!=0 && j<7)) {
                    result+=emoji;
                }
                else {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter M
    static void m()
    {
        result+="\n";

        for (int i = 0; i < 7; i++) {
            result+="     ";
            for (int j = 0; j < 7; j++) {
                if (j == 0 || j == 6) {
                    result+=emoji;
                }
                else if (i < 4
                        && (j == 6 - i || j == i)) {
                    result+=emoji;
                }
                else
                    result+="     ";
            }
            result+="\n";
        }
    }

    // Function to print the letter N
    static void n()
    {
        result+="\n";

        for (int i=0;i<7 ;i++ )
        {
            for (int j=0;j<7 ;j++ )
            {
                if (j==0 ||  j==6 || i==j)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter O
    static void o()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int o = 0; o < 8; o++) {
                if (i == 0 && (o <= 1 || o >= 6 - i))
                    result+="     ";
                else if (i == 1
                        && (o == 0 || o == 8 - i
                        || (o < 6 && o > 1)))
                    result+="     ";
                else if (i == 2
                        && (o == 1 || o == 8 - i
                        || (o < 6 && o > 1)))
                    result+="     ";
                else if ((i == 3 || i == 4 || i == 5)
                        && (o > 0 && o < 7))
                    result+="     ";
                else if (i == 6
                        && (o == 0 || o == 8 + 5 - i
                        || (o < 6 && o > 1)))
                    result+="     ";
                else if (i == 7
                        && (o <= 1 || o >= 6 - i + 7))
                    result+="     ";
                else {
                    result+=emoji;
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter P
    static void p()
    {
        result+="\n";
        for (int i=0;i<9 ;i++ )
        {
            for (int j=0;j<=4 ;j++ )
            {
                if (i==0&&j!=4||j==0||j==4&&i!=0&&i!=4&&i!=5&&i!=6&&i!=7&&i!=8||i==4&&j!=4)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter Q
    static void q()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int o = 0; o < 8; o++) {
                if (o == i)
                    result+=emoji;

                else if (i == 0
                        && (o <= 1 || o >= 6 - i))
                    result+="     ";
                else if (i == 1
                        && (o == 0 || o == 8 - i
                        || (o < 6 && o > 1)))
                    result+="     ";
                else if (i == 2
                        && (o == 1 || o == 8 - i
                        || (o < 6 && o > 1)))
                    result+="     ";
                else if ((i == 3 || i == 4 || i == 5)
                        && (o > 0 && o < 7))
                    result+="     ";
                else if (i == 6
                        && (o == 0 || o == 8 + 5 - i
                        || (o < 6 && o > 1)))
                    result+="     ";
                else if (i == 7
                        && (o <= 1 || o >= 6 - i + 7))
                    result+="     ";
                else {
                    result+=emoji;
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter R
    static void r()
    {
        result+="\n";
        for (int i=0;i<8 ;i++ )
        {
            for (int j=0;j<=6 ;j++ )
            {
                if (i==0&&j!=4&&j!=5&&j!=6&&j!=7||j==0||3+j==i||j==4&&i!=0&&i!=4&&i!=5&&i!=6&&i!=7&&i!=8||i==4&&j!=4&&j!=5&&j!=6&&j!=7)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter S
    static void s()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j > 0) {
                    result+=emoji;
                }
                else if (i > 0 && i < 3
                        && j < 1) {
                    result+=emoji;
                }
                else if (i == 3 && j > 0
                        && j < 6) {
                    result+=emoji;
                }
                else if (i > 3 && i < 6
                        && j > 5) {
                    result+=emoji;
                }
                else if (i == 6 && j < 6) {
                    result+=emoji;
                }

                else
                    result+="     ";
            }
            result+="\n";
        }
    }

    // Function to print the letter T
    static void t()
    {
        result+="\n";
        for (int i=0;i<7 ;i++ )
        {
            for (int j=0;j<=6 ;j++ )
            {
                if (i==0||j==3)
                {
                    result+=emoji;
                }
                else
                {
                    result+="     ";
                }
            }
            result+="\n";
        }
    }

    // Function to print the letter U
    static void u()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";

            for (int h = 0; h < 8; h++) {
                if (i < 7 && (h < 1 || h > 6)) {
                    result+=emoji;
                }
                else if (i == 7
                        && (h == 0 || h == 7))
                    result+="     ";
                else if (i > 6) {
                    result+=emoji;
                }
                else
                    result+="     ";
            }
            result+="\n";
        }
    }

    // Function to print the letter V
    static void v()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int v = 0; v < 8; v++)
                if ((v == 0 || v == 7)
                        && i < 4) {
                    result+=emoji;
                }
                else if ((v == i - 4 || v == 11 - i)
                        && i >= 4) {
                    result+=emoji;
                }
                else
                    result+="     ";
            result+="\n";
        }
    }

    // Function to print the letter W
    static void w()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int j = 0; j < 8; j++) {
                if (j == 0 || j == 7) {
                    result+=emoji;
                }
                else if (i > 3
                        && (j == 7 - i || j == i)) {
                    result+=emoji;
                }
                else
                    result+="     ";
            }
            result+="\n";
        }
    }

    // Function to print the letter X
    static void x()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int m = 0; m < 8; m++) {
                if (i == m || m == 7 - i) {
                    result+=emoji;
                }
                else
                    result+="     ";
            }

            result+="\n";
        }
    }

    // Function to print the letter Y
    static void y()
    {
        result+="\n";
        for (int i = 0; i < 8; i++) {
            result+="     ";

            for (int y = 0; y < 8; y++) {
                if (i < 4) {
                    if (y == i || y == 6 - i ) {
                        result+=emoji;
                    }
                    else
                        result+="     ";
                }
                else if (y == 3 ) {
                    result+=emoji;
                }
                else
                    result+="     ";
            }
            result+="\n";
        }
    }

    // Function to print the letter Z
    static void z()
    {
        result+="\n";

        for (int i = 0; i < 8; i++) {
            result+="     ";
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 7) {
                    result+=emoji;
                }
                else if (j == 7 - i) {
                    result+=emoji;
                }
                else
                    result+="     ";
            }
            result+="\n";
        }
    }

    static void printPattern(char[] str)
    {

        int in = 0;

        while (in<str.length ) {

            char ch = str[in];
            if (ch < 95)
                ch = (char) (ch + 32);
            switch (ch) {
                case 'a':
                    a();
                    break;
                case 'b':
                    b();
                    break;
                case 'c':
                    c();
                    break;
                case 'd':
                    d();
                    break;
                case 'e':
                    e();
                    break;
                case 'f':
                    f();
                    break;
                case 'g':
                    g();
                    break;
                case 'h':
                    h();
                    break;
                case 'i':
                    i();
                    break;
                case 'j':
                    j();
                    break;
                case 'k':
                    k();
                    break;
                case 'l':
                    l();
                    break;
                case 'm':
                    m();
                    break;
                case 'n':
                    n();
                    break;
                case 'o':
                    o();
                    break;
                case 'p':
                    p();
                    break;
                case 'q':
                    q();
                    break;
                case 'r':
                    r();
                    break;
                case 's':
                    s();
                    break;
                case 't':
                    t();
                    break;
                case 'u':
                    u();
                    break;
                case 'v':
                    v();
                    break;
                case 'w':
                    w();
                    break;
                case 'x':
                    x();
                    break;
                case 'y':
                    y();
                    break;
                case 'z':
                    z();
                    break;
                default:
                    break;
            }
            in++;
        }
    }

    public static Bitmap textAsBitmap(String text) {
        // adapted from https://stackoverflow.com/a/8799344/1476989
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(50);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);

        int trueWidth = width;
        if(width>height)height=width; else width=height;
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, width/2-trueWidth/2, baseline, paint);
        return image;
    }

}
