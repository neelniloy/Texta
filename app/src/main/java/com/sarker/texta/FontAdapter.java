package com.sarker.texta;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.ViewHolder> {

    private Context nContext;
    private ArrayList<FontInfo> fList;



    public FontAdapter(Context context, ArrayList<FontInfo> fLists) {
        nContext = context;
        fList = fLists;
    }

    @NonNull
    @Override
    public FontAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(nContext).inflate(R.layout.show_font, parent, false);
        return new FontAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FontInfo info = fList.get(position);


        holder.name.setText(info.getFontName());
        //holder.style.setText(info.getFontStyle());

        StringBuilder strBld = new StringBuilder(info.getFontStyle());
        switch (position) {
            case 0:
                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharFirst(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 1:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharSecond(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 2:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharThird(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;
            case 3:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharFourth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 4:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharFifth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;
            case 5:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharSixth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 6:

                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharSeventh(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;

            case 7:
                for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {
                    char a = strBld.charAt(charOne);
                    char newCh = getSpecialCharEighth(a);
                    strBld.setCharAt(charOne, newCh);

                }
                break;


            case 8:
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♥');
                        }


                    }

                } else if (!info.getFontStyle().contains("♥")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '♥');
                        }


                    }
                }
                break;


            case 9:

                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (charOne == 0) {
                            strBld.insert(charOne, '【');
                            ++charOne;
                        }


                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '】');
                            strBld.insert(++charOne, '【');


                        }

                        if (strBld.charAt(charOne) == ' ') {
                            strBld.deleteCharAt(--charOne);
                            strBld.insert(++charOne, '【');
                        }

                        if (strBld.length() - 1 == charOne) {
                            strBld.deleteCharAt(charOne);
                        }

                    }
                } else if (!info.getFontStyle().contains("【") && !info.getFontStyle().contains("】")) {

                    for (int charOne = 0; charOne <= strBld.length() - 1; charOne++) {

                        if (charOne == 0) {
                            strBld.insert(charOne, '【');
                            ++charOne;
                        }

                        if (strBld.length() - 1 != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '】');
                            strBld.insert(++charOne, '【');


                        } else if (strBld.length() - 1 == charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '】');
                        }

                        if (strBld.charAt(charOne) == ' ' && charOne != 0) {
                            strBld.deleteCharAt(--charOne);
                            strBld.insert(++charOne, '【');
                        } else if (strBld.length() - 1 == charOne && strBld.charAt(charOne) == ' ' && charOne != 0) {
                            strBld.deleteCharAt(++charOne);
                        }

                    }

                }

                break;


            case 10:
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☆');
                        }


                    }

                } else if (!info.getFontStyle().contains("☆")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☆');
                        }


                    }
                }
                break;

            case 11:
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❦');
                        }


                    }

                } else if (!info.getFontStyle().contains("❦")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❦');
                        }


                    }
                }
                break;
            case 12:
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '⁂');
                        }


                    }

                } else if (!info.getFontStyle().contains("⁂")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '⁂');
                        }


                    }
                }
                break;

            case 13 :
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '✾');
                        }


                    }

                } else if (!info.getFontStyle().contains("✾")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '✾');
                        }


                    }
                }
                break;

            case 14 :
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❤');
                        }


                    }

                } else if (!info.getFontStyle().contains("❤")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '❤');
                        }


                    }
                }
                break;

            case 15 :
                if ("Preview text".equals(info.getFontStyle())) {
                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☘');
                        }


                    }

                } else if (!info.getFontStyle().contains("☘")) {

                    for (int charOne = 0; charOne < strBld.length(); charOne++) {
                        if (strBld.charAt(charOne) == ' ' && strBld.length()-1 != charOne) {
                            ++charOne;

                        }

                        if (strBld.length() != charOne && strBld.charAt(charOne) != ' ') {
                            strBld.insert(++charOne, '☘');
                        }


                    }
                }
                break;

        }
        holder.style.setText(strBld.toString());




        holder.copy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ClipboardManager cb=(ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd=ClipData.newPlainText("Label",holder.style.getText().toString());
                cb.setPrimaryClip(cd);
                Toast.makeText(v.getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Thanks For Sharing <3");
                share.putExtra(Intent.EXTRA_TEXT, ""+ holder.style.getText().toString());
                v.getContext().startActivity(Intent.createChooser(share, "Share With Friends"));
            }
        });


    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return fList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, style;

        public ImageView copy,share;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.font_name);
            style = itemView.findViewById(R.id.font_style);
            copy = itemView.findViewById(R.id.copy);
            share = itemView.findViewById(R.id.share);


        }

    }

    private char getSpecialCharFirst(char a) {
        char ch = a;

        if (ch == 'A') {
            ch = 'Ⓐ';
        } else if (ch == 'B') {
            ch = 'Ⓑ';
        } else if (ch == 'C') {
            ch = 'Ⓒ';
        } else if (ch == 'D') {
            ch = 'Ⓓ';
        } else if (ch == 'E') {
            ch = 'Ⓔ';
        } else if (ch == 'F') {
            ch = 'Ⓕ';
        } else if (ch == 'G') {
            ch = 'Ⓖ';
        } else if (ch == 'H') {
            ch = 'Ⓗ';
        } else if (ch == 'I') {
            ch = 'Ⓘ';
        } else if (ch == 'J') {
            ch = 'Ⓙ';
        } else if (ch == 'K') {
            ch = 'Ⓚ';
        } else if (ch == 'L') {
            ch = 'Ⓛ';
        } else if (ch == 'M') {
            ch = 'Ⓜ';
        } else if (ch == 'N') {
            ch = 'Ⓝ';
        } else if (ch == 'O') {
            ch = 'Ⓞ';
        } else if (ch == 'P') {
            ch = 'Ⓟ';
        } else if (ch == 'Q') {
            ch = 'Ⓠ';
        } else if (ch == 'R') {
            ch = 'Ⓡ';
        } else if (ch == 'S') {
            ch = 'Ⓢ';
        } else if (ch == 'T') {
            ch = 'Ⓣ';
        } else if (ch == 'U') {
            ch = 'Ⓤ';
        } else if (ch == 'V') {
            ch = 'Ⓥ';
        } else if (ch == 'W') {
            ch = 'Ⓦ';
        } else if (ch == 'X') {
            ch = 'Ⓧ';
        } else if (ch == 'Y') {
            ch = 'Ⓨ';
        } else if (ch == 'Z') {
            ch = 'Ⓩ';
        } else if (ch == 'a') {
            ch = 'ⓐ';
        } else if (ch == 'b') {
            ch = 'ⓑ';
        } else if (ch == 'c') {
            ch = 'ⓒ';
        } else if (ch == 'd') {
            ch = 'ⓓ';
        } else if (ch == 'e') {
            ch = 'ⓔ';
        } else if (ch == 'f') {
            ch = 'ⓕ';
        } else if (ch == 'g') {
            ch = 'ⓖ';
        } else if (ch == 'h') {
            ch = 'ⓗ';
        } else if (ch == 'i') {
            ch = 'ⓘ';
        } else if (ch == 'j') {
            ch = 'ⓙ';
        } else if (ch == 'k') {
            ch = 'ⓚ';
        } else if (ch == 'l') {
            ch = 'ⓛ';
        } else if (ch == 'm') {
            ch = 'ⓜ';
        } else if (ch == 'n') {
            ch = 'ⓝ';
        } else if (ch == 'o') {
            ch = 'ⓞ';
        } else if (ch == 'p') {
            ch = 'ⓟ';
        } else if (ch == 'q') {
            ch = 'ⓠ';
        } else if (ch == 'r') {
            ch = 'ⓡ';
        } else if (ch == 's') {
            ch = 'ⓢ';
        } else if (ch == 't') {
            ch = 'ⓣ';
        } else if (ch == 'u') {
            ch = 'ⓤ';
        } else if (ch == 'v') {
            ch = 'ⓥ';
        } else if (ch == 'w') {
            ch = 'ⓦ';
        } else if (ch == 'x') {
            ch = 'ⓧ';
        } else if (ch == 'y') {
            ch = 'ⓨ';
        } else if (ch == 'z') {
            ch = 'ⓩ';
        } else if (ch == '0') {
            ch = '⓪';
        } else if (ch == '1') {
            ch = '①';
        } else if (ch == '2') {
            ch = '②';
        } else if (ch == '3') {
            ch = '③';
        } else if (ch == '4') {
            ch = '④';
        } else if (ch == '5') {
            ch = '⑤';
        } else if (ch == '6') {
            ch = '⑥';
        } else if (ch == '7') {
            ch = '⑦';
        } else if (ch == '8') {
            ch = '⑧';
        } else if (ch == '9') {
            ch = '⑨';
        }

        return ch;
    }

    private char getSpecialCharSecond(char a) {
        char ch = a;

        if (ch == 'A') {
            ch = '₳';
        } else if (ch == 'B') {
            ch = '฿';
        } else if (ch == 'C') {
            ch = '₵';
        } else if (ch == 'D') {
            ch = 'Đ';
        } else if (ch == 'E') {
            ch = 'Ɇ';
        } else if (ch == 'F') {
            ch = '₣';
        } else if (ch == 'G') {
            ch = '₲';
        } else if (ch == 'H') {
            ch = 'Ⱨ';
        } else if (ch == 'I') {
            ch = 'ł';
        } else if (ch == 'K') {
            ch = '₭';
        } else if (ch == 'L') {
            ch = 'Ⱡ';
        } else if (ch == 'M') {
            ch = '₥';
        } else if (ch == 'N') {
            ch = '₦';
        } else if (ch == 'O') {
            ch = 'Ø';
        } else if (ch == 'P') {
            ch = '₱';
        } else if (ch == 'R') {
            ch = 'Ɽ';
        } else if (ch == 'S') {
            ch = '₴';
        } else if (ch == 'T') {
            ch = '₮';
        } else if (ch == 'U') {
            ch = 'Ʉ';
        } else if (ch == 'W') {
            ch = '₩';
        } else if (ch == 'X') {
            ch = 'Ӿ';
        } else if (ch == 'Y') {
            ch = 'Ɏ';
        } else if (ch == 'Z') {
            ch = 'Ⱬ';
        } else if (ch == 'a') {
            ch = '₳';
        } else if (ch == 'b') {
            ch = '฿';
        } else if (ch == 'c') {
            ch = '₵';
        } else if (ch == 'd') {
            ch = 'Đ';
        } else if (ch == 'e') {
            ch = 'Ɇ';
        } else if (ch == 'f') {
            ch = '₣';
        } else if (ch == 'g') {
            ch = '₲';
        } else if (ch == 'h') {
            ch = 'Ⱨ';
        } else if (ch == 'i') {
            ch = 'ł';
        } else if (ch == 'j') {
            ch = 'J';
        } else if (ch == 'k') {
            ch = '₭';
        } else if (ch == 'l') {
            ch = 'Ⱡ';
        } else if (ch == 'm') {
            ch = '₥';
        } else if (ch == 'n') {
            ch = '₦';
        } else if (ch == 'o') {
            ch = 'Ø';
        } else if (ch == 'p') {
            ch = '₱';
        } else if (ch == 'q') {
            ch = 'Q';
        } else if (ch == 'r') {
            ch = 'Ɽ';
        } else if (ch == 's') {
            ch = '₴';
        } else if (ch == 't') {
            ch = '₮';
        } else if (ch == 'u') {
            ch = 'Ʉ';
        } else if (ch == 'v') {
            ch = 'V';
        } else if (ch == 'w') {
            ch = '₩';
        } else if (ch == 'x') {
            ch = 'Ӿ';
        } else if (ch == 'y') {
            ch = 'Ɏ';
        } else if (ch == 'z') {
            ch = 'Ⱬ';
        }

        return ch;
    }

    private char getSpecialCharThird(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'Ꭺ';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'b';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'Ꮯ';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'Ꭰ';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'Ꭼ';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'f';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'Ꮆ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'h';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'Ꭵ';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'j';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'Ꮶ';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'Ꮮ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'm';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'Ꮑ';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'Ꮎ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'Ꮲ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'q';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'Ꮢ';
        } else if (ch == 'S' || ch == 's') {
            ch = 's';
        } else if (ch == 'T' || ch == 't') {
            ch = 'Ꮖ';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'u';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'Ꮙ';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'Ꮃ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'x';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'Ꮍ';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'Ꮓ';
        }

        return ch;
    }

    private char getSpecialCharFourth(char a) {
        char ch = a;

        if (ch == 'A') {
            ch = 'Ḁ';
        } else if (ch == 'B') {
            ch = 'Ḃ';
        } else if (ch == 'C') {
            ch = 'Ḉ';
        } else if (ch == 'D') {
            ch = 'Ḋ';
        } else if (ch == 'E') {
            ch = 'Ḕ';
        } else if (ch == 'F') {
            ch = 'Ḟ';
        } else if (ch == 'G') {
            ch = 'Ḡ';
        } else if (ch == 'H') {
            ch = 'Ḧ';
        } else if (ch == 'I') {
            ch = 'Ḭ';
        } else if (ch == 'K') {
            ch = 'Ḳ';
        } else if (ch == 'L') {
            ch = 'Ḷ';
        } else if (ch == 'M') {
            ch = 'Ṁ';
        } else if (ch == 'N') {
            ch = 'Ṇ';
        } else if (ch == 'O') {
            ch = 'Ṏ';
        } else if (ch == 'P') {
            ch = 'Ṗ';
        } else if (ch == 'R') {
            ch = 'Ṙ';
        } else if (ch == 'S') {
            ch = 'Ṡ';
        } else if (ch == 'T') {
            ch = 'Ṯ';
        } else if (ch == 'U') {
            ch = 'Ṳ';
        } else if (ch == 'V') {
            ch = 'Ṽ';
        } else if (ch == 'W') {
            ch = 'Ẇ';
        } else if (ch == 'X') {
            ch = 'Ẍ';
        } else if (ch == 'Y') {
            ch = 'Ẏ';
        } else if (ch == 'Z') {
            ch = 'Ẓ';
        } else if (ch == 'a') {
            ch = 'Ḁ';
        } else if (ch == 'b') {
            ch = 'ḃ';
        } else if (ch == 'c') {
            ch = 'ḉ';
        } else if (ch == 'd') {
            ch = 'Ḋ';
        } else if (ch == 'e') {
            ch = 'ḕ';
        } else if (ch == 'f') {
            ch = 'ḟ';
        } else if (ch == 'g') {
            ch = 'Ḡ';
        } else if (ch == 'h') {
            ch = 'ḧ';
        } else if (ch == 'i') {
            ch = 'ḭ';
        } else if (ch == 'k') {
            ch = 'Ḳ';
        } else if (ch == 'l') {
            ch = 'Ḷ';
        } else if (ch == 'm') {
            ch = 'ṁ';
        } else if (ch == 'n') {
            ch = 'Ṇ';
        } else if (ch == 'o') {
            ch = 'ṏ';
        } else if (ch == 'p') {
            ch = 'Ṗ';
        } else if (ch == 'r') {
            ch = 'ṙ';
        } else if (ch == 's') {
            ch = 'Ṡ';
        } else if (ch == 't') {
            ch = 'Ṯ';
        } else if (ch == 'u') {
            ch = 'ṳ';
        } else if (ch == 'v') {
            ch = 'Ṽ';
        } else if (ch == 'w') {
            ch = 'ẇ';
        } else if (ch == 'x') {
            ch = 'Ẍ';
        } else if (ch == 'y') {
            ch = 'ẏ';
        } else if (ch == 'z') {
            ch = 'Ẓ';
        }

        return ch;
    }

    private char getSpecialCharFifth(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'α';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'в';
        } else if (ch == 'C' || ch == 'c') {
            ch = '¢';
        } else if (ch == 'D' || ch == 'd') {
            ch = '∂';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'є';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'f';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'g';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'н';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'ι';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'נ';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'к';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'ℓ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'м';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'и';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'σ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'ρ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'q';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'я';
        } else if (ch == 'S' || ch == 's') {
            ch = 's';
        } else if (ch == 'T' || ch == 't') {
            ch = 'т';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'υ';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'ν';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'ω';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'χ';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'у';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'z';
        }

        return ch;
    }

    private char getSpecialCharSixth(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'Ã';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'β';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'Č';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'Ď';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'Ẹ';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'Ƒ';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'Ğ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'Ĥ';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'Į';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'Ĵ';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'Ќ';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'Ĺ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'ϻ';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'Ň';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'Ỗ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'Ƥ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'Ǫ';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'Ř';
        } else if (ch == 'S' || ch == 's') {
            ch = 'Ŝ';
        } else if (ch == 'T' || ch == 't') {
            ch = 'Ť';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'Ǘ';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'ϋ';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'Ŵ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'Ж';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'Ў';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'Ż';
        }

        return ch;
    }

    private char getSpecialCharSeventh(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'A';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'Ɓ';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'Ƈ';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'Ɗ';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'Є';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'Ƒ';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'Ɠ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'Ӈ';
        } else if (ch == 'I' || ch == 'i') {
            ch = 'Ɩ';
        } else if (ch == 'J' || ch == 'j') {
            ch = 'ʆ';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'Ƙ';
        } else if (ch == 'L' || ch == 'l') {
            ch = 'Լ';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'M';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'Ɲ';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'Ơ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'Ƥ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'Ƣ';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'Ʀ';
        } else if (ch == 'S' || ch == 's') {
            ch = 'Ƨ';
        } else if (ch == 'T' || ch == 't') {
            ch = 'Ƭ';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'Ʋ';
        } else if (ch == 'V' || ch == 'v') {
            ch = 'Ɣ';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'Ɯ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'Ҳ';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'Ƴ';
        } else if (ch == 'Z' || ch == 'z') {
            ch = 'Ȥ';
        }

        return ch;
    }

    private char getSpecialCharEighth(char a) {
        char ch = a;

        if (ch == 'A' || ch == 'a') {
            ch = 'ꋫ';
        } else if (ch == 'B' || ch == 'b') {
            ch = 'ꃲ';
        } else if (ch == 'C' || ch == 'c') {
            ch = 'ꉓ';
        } else if (ch == 'D' || ch == 'd') {
            ch = 'ꃸ';
        } else if (ch == 'E' || ch == 'e') {
            ch = 'ꑾ';
        } else if (ch == 'F' || ch == 'f') {
            ch = 'ꄘ';
        } else if (ch == 'G' || ch == 'g') {
            ch = 'ꁅ';
        } else if (ch == 'H' || ch == 'h') {
            ch = 'ꃄ';
        } else if (ch == 'I' || ch == 'i') {
            ch = '꒐';
        } else if (ch == 'J' || ch == 'j') {
            ch = '꒑';
        } else if (ch == 'K' || ch == 'k') {
            ch = 'ꀗ';
        } else if (ch == 'L' || ch == 'l') {
            ch = '꒒';
        } else if (ch == 'M' || ch == 'm') {
            ch = 'ꂵ';
        } else if (ch == 'N' || ch == 'n') {
            ch = 'ꁹ';
        } else if (ch == 'O' || ch == 'o') {
            ch = 'ꄱ';
        } else if (ch == 'P' || ch == 'p') {
            ch = 'ꉣ';
        } else if (ch == 'Q' || ch == 'q') {
            ch = 'ꋟ';
        } else if (ch == 'R' || ch == 'r') {
            ch = 'ꋪ';
        } else if (ch == 'S' || ch == 's') {
            ch = 'ꇘ';
        } else if (ch == 'T' || ch == 't') {
            ch = '꓅';
        } else if (ch == 'U' || ch == 'u') {
            ch = 'ꌇ';
        } else if (ch == 'V' || ch == 'v') {
            ch = '꒦';
        } else if (ch == 'W' || ch == 'w') {
            ch = 'ꅏ';
        } else if (ch == 'X' || ch == 'x') {
            ch = 'ꋋ';
        } else if (ch == 'Y' || ch == 'y') {
            ch = 'ꌥ';
        } else if (ch == 'Z' || ch == 'z') {
            ch = '꒗';
        }

        return ch;
    }



}
