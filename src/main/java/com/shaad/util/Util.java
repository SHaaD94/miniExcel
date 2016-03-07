package com.shaad.util;

public class Util {
    private Util() {

    }

    public static int getLetterPosition(Character letter) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.indexOf(Character.toLowerCase(letter));
    }
}
