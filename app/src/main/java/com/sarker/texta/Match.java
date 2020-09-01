package com.sarker.texta;

public class Match {
    public String emojiName;
    public Double match;

    public Match() {
    }

    public Match(String emojiName, Double match) {
        this.emojiName = emojiName;
        this.match = match;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public void setEmojiName(String emojiName) {
        this.emojiName = emojiName;
    }

    public Double getMatch() {
        return match;
    }

    public void setMatch(Double match) {
        this.match = match;
    }
}
