package com.sarker.texta;

public class Item {
    private String mText1;
    private String mText2;
    public Item(String text1, String text2) {

        mText1 = text1;
        mText2 = text2;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
