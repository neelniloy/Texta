package com.sarker.texta;

public class GetEmoji {

    String emojiName;
    int emojiCode;

    public GetEmoji() {
    }

    public GetEmoji(String emojiName, int emojiCode) {
        this.emojiName = emojiName;
        this.emojiCode = emojiCode;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public void setEmojiName(String emojiName) {
        this.emojiName = emojiName;
    }

    public int getEmojiCode() {
        return emojiCode;
    }

    public void setEmojiCode(int emojiCode) {
        this.emojiCode = emojiCode;
    }
}
