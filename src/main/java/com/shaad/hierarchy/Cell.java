package com.shaad.hierarchy;

/**
 * Table cell.
 */
public class Cell {
    private String content;
    private String value;

    public Cell() {
        this("");
    }

    public Cell(String content) {
        //todo: is it the best way to handle empty cell?
        this.content = (null == content || content.isEmpty()) ? "0" : content;
        value = compute();
    }

    private String compute() {
        //todo: check for proper regexp for natural numbers
        if (content.matches("[-]?[1-9][0-9]*|[0]")) {
            return content;
        } else if (content.charAt(0) == '\'') {
            return content.substring(1);
        } else if (content.charAt(0) == '=') {
            //todo: parse multiple expressions
            return new Expression(content.substring(1)).getValue();
        } else {
            return "#Error"; //todo: create proper exception
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
