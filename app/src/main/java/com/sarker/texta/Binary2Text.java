package com.sarker.texta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sarker.texta.databinding.ActivityBinary2TextBinding;
import com.sarker.texta.databinding.ActivityText2BinaryBinding;

public class Binary2Text extends AppCompatActivity {
    private ActivityBinary2TextBinding binary2TextBinding;

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
                    Character c = (char) Integer.parseInt(string, 2);
                    finalResult += c.toString();
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



    }
}