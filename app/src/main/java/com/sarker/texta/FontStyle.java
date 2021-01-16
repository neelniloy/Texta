package com.sarker.texta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class FontStyle extends AppCompatActivity {

    private ArrayList<FontInfo> fList = new ArrayList<>();

    private RecyclerView fRecyclerView;
    private FontAdapter fAdapter;
    private RecyclerView.LayoutManager fLayoutManager;
    private EditText input;

    private String[] font = {"Bubble","Currency","Magic","Knight","Antrophobia", "Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpius","Sagittarius","Capricorn","Aquarius","Pisces"};


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

    }


}