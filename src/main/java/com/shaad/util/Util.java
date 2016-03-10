package com.shaad.util;

public class Util {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private Util() {
    }

    public static int getLetterPosition(Character letter) {
        return alphabet.indexOf(Character.toLowerCase(letter));
    }

    public static int getAlphabetLength() {
        return alphabet.length();
    }
}
