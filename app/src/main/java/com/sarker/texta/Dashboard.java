package com.sarker.texta;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {


    LinearLayout btnContainer;
    CheckBox addLine,addIndex;
    int limit = 0;
    TextView outputdisplay;
    EditText inputtext;
    StringBuilder s = null;
    String rptTXT;
    String txt ="";
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Button generatebtn=(Button)findViewById(R.id.generatebtn);
        addLine = (CheckBox)findViewById(R.id.newLine);
        addIndex = (CheckBox)findViewById(R.id.addIndex);
        Button cpybtn=(Button)findViewById(R.id.Cpybtn);
        Button sharebtn=(Button)findViewById(R.id.sharebtn);
        inputtext =(EditText)findViewById(R.id.inputext);
        final EditText inputlimit=(EditText)findViewById(R.id.inputlimit);
        outputdisplay =(TextView)findViewById(R.id.Outputdisply);
        btnContainer = (LinearLayout)findViewById(R.id.btnContainer);
        btnContainer.setVisibility(View.GONE);
        outputdisplay.setVisibility(View.GONE);


        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt = "";
                outputdisplay.setText("");

                if(inputtext.getText().toString().isEmpty()){
                    btnContainer.setVisibility(View.GONE);
                    outputdisplay.setVisibility(View.GONE);
                    Toast.makeText(Dashboard.this, "Have Some Sense! Without text how can I repeat it?", Toast.LENGTH_SHORT).show();
                }else if(inputlimit.getText().toString().isEmpty() || inputlimit.getText().toString().equals("0")){
                    btnContainer.setVisibility(View.GONE);
                    outputdisplay.setVisibility(View.GONE);
                    Toast.makeText(Dashboard.this, "Have Some Sense! How can I repeat ZERO Times?", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(inputlimit.getText().toString()) > 10000){
                    Toast.makeText(Dashboard.this, "Enter limit less than 10000", Toast.LENGTH_SHORT).show();
                }else{
                    //txt = "";
                    rptTXT = inputtext.getText().toString();
                    limit = Integer.parseInt(inputlimit.getText().toString());
                    if(addLine.isChecked() && addIndex.isChecked()){
                        stringBuilder = new StringBuilder();
                        for(int i = 0; i<limit; i++){
                            int val = i+1;
                            stringBuilder.append(val+". "+rptTXT+"\n");
                            //txt = (i+1)+". " +txt + rptTXT + "\n";
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
                            //txt = val+". " +txt + rptTXT + " ";  //1.
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
                Toast.makeText(Dashboard.this, "Text Copied", Toast.LENGTH_SHORT).show();

            }
        });



        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);

                share.setType("text/plain");

                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "Utsha coming at you at supersonic speed <3");

                share.putExtra(Intent.EXTRA_TEXT, "" + outputdisplay.getText().toString());

                startActivity(Intent.createChooser(share, "Share With Friends"));
            }
        });
    }

    @Override

    public void onBackPressed() {
            super.onBackPressed();
            finish();
    }
}
