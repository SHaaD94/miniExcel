package com.shaad.hierarchy;

import com.shaad.Main;

public class Term {
    private String content;
    private String type;

    public Term() {
        this("");
    }

    public Term(String termContent) {
        content = termContent;
        //todo: get rid of 'string types' of content
        if (content.matches("[a-zA-Z][0-9]*")) {
            this.type = "reference";
        } else if (content.matches("[0-9]*")) {
            this.type = "number";
        } else {
            this.type = "ERROR"; // todo: handle this error;
        }
    }

    private int getLetterPosition(Character letter) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.indexOf(Character.toLowerCase(letter));
    }

    public String getValue() {
        if (type.equals("number")) {
            return content;
        }

        if (type.equals("reference")) {
            int rowNumber = getLetterPosition(content.charAt(0));
            int columnNumber = Integer.parseInt(content.substring(1)) - 1;

            //todo: handle 'Text' content in referenced cell;
            return new Cell(Main.tempTable[rowNumber][columnNumber]).getComputedContent();
        }
        return null; // todo: handle error properly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content + "(" + type + ")";
    }
}
