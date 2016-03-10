package com.shaad.util;

public class Util {
    private Util() {

    }

    public static int getLetterPosition(Character letter) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.indexOf(Character.toLowerCase(letter));
    }

    public static int getLargestCellLength(String[][] stringTable) {
        int maxSize = 0;

        for (String[] strings : stringTable) {
            for (String string : strings) {
                if (maxSize < string.length()) {
                    maxSize = string.length();
                }
            }
        }
        return maxSize;
    }
}
