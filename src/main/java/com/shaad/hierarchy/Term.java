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
        //todo: check for circular reference
        //todo: get rid of 'string types' of content
        if (content.matches("[a-zA-Z][1-9][0-9]*")) {
            this.type = "reference";
        } else if (content.matches("[-]?[1-9][0-9]*|[0]")) {
            this.type = "number";
        } else if (content.matches("[a-zA-Z]?[-]?[1-9][0-9]*([\\/*+-][a-zA-Z]?[1-9][0-9]*)*")) {
            this.type = "expression";
        } else {
            this.type = "ERROR"; // todo: handle this error;
        }
    }

    public String getValue() {
        if (type.equals("number")) {
            return content;
        }

        if (type.equals("reference")) {
            int rowNumber = Util.getLetterPosition(content.charAt(0));
            int columnNumber = Integer.parseInt(content.substring(1)) - 1;

            //todo: handle 'Text' content in referenced cell;
            return new Cell(Main.tempTable[rowNumber][columnNumber]).getValue();
        }

        if (type.equals("expression")) {
            //todo: handle 'Text' content in referenced cell;
            return new Expression(content).getValue();
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
