package com.shaad.hierarchy;

/**
 * Table cell.
 */
public class Cell {
    private String stringContent;
    private String computedContent;

    public Cell() {
        this("");
    }

    public Cell(String content) {
        //todo: is it the best way to handle empty cell?
        stringContent = (content.isEmpty()) ? "0" : content;
        //stringContent = content;
        computedContent = compute();
    }

    private String compute() {
        //todo: check for proper regexp for natural numbers
        if (stringContent.matches("[0-9]*")) {
            return stringContent;
        } else if (stringContent.charAt(0) == '\'') {
            return stringContent.substring(1);
        } else if (stringContent.charAt(0) == '=') {
            //todo: parse multiple expressions
            return new Expression(stringContent.substring(1)).getResult();
        } else {
            return "#Error"; //todo: create proper exception
        }
    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    public String getComputedContent() {
        return computedContent;
    }

    public void setComputedContent(String computedContent) {
        this.computedContent = computedContent;
    }

}
