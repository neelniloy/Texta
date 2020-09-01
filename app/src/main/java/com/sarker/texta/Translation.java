package com.sarker.texta;

import java.util.ArrayList;

public class Translation {

    public static final String namePrefix = "spoken_emoji";
    private String mName;
    private boolean isDoubleCodepoint = false;
    private String mDescription;
    private ArrayList<Integer> mCodepoints = new ArrayList<Integer>();

    Translation(String codepoint, String description) {
        StringBuilder name = new StringBuilder();
        name.append(namePrefix);

        for (int i = 0; i < codepoint.length(); ++i) {
            int current = codepoint.codePointAt(i);
            int charsToSkip = Character.charCount(current) - 1;

            if (current != 8205) {
                mCodepoints.add(current);
                name.append("_");
                name.append(Integer.toHexString(codepoint.codePointAt(i)));
            }
            i+=charsToSkip;
        }

        mName = name.toString();
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public boolean isDoubleCodepoint() {
        return isDoubleCodepoint;
    }

    public void setIsDoubleCodepoint(boolean isDouble) {
        this.isDoubleCodepoint = isDouble;
    }

    @Override
    public String toString() {
        return "<string name=\"" + mName + "\">" + mDescription + "</string>";
    }
}
