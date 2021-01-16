package com.sarker.texta;

public class FontInfo {
    public String fontName,fontStyle;

    public FontInfo (){

    }

    public FontInfo(String fontName, String fontStyle) {
        this.fontName = fontName;
        this.fontStyle = fontStyle;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }
}
