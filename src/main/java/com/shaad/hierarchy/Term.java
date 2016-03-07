package com.shaad.hierarchy;

import com.shaad.Main;
import com.shaad.enums.ValueType;
import com.shaad.util.Util;

public class Term {
    private String content;
    private String value;
    /**
     * Number, reference to cell, or expression
     */
    private String executionType;

    /**
     * Text or number
     */
    private ValueType valueType;

    public Term(String termContent) {
        content = termContent;

        //todo: get rid of 'string types' of content
        if (content.matches("[a-zA-Z][1-9][0-9]*")) {
            this.executionType = "reference";
        } else if (content.matches("[-]?[1-9][0-9]*|[0]")) {
            this.executionType = "number";
            //todo: check if this really unnecessary
/*        } else if (content.matches("([a-zA-Z]|-)?[1-9][0-9]*([\\*//*+-][a-zA-Z]?[1-9][0-9]*)*")) {
            this.executionType = "expression";*/
        } else {
            this.executionType = "#SyntaxErr"; // todo: handle this error;
        }
        value = compute();
    }

    private String compute() {
        valueType = ValueType.NUMBER;
        if (executionType.equals("number")) {
            return content;
        }

        if (executionType.equals("reference")) {
            int rowNumber = Integer.parseInt(content.substring(1)) - 1;
            int columnNumber = Util.getLetterPosition(content.charAt(0));
            Cell referencedCell;
            //todo: handle 'Text' content in referenced cell;
            try {
                referencedCell = new Cell(Main.backendTable[rowNumber][columnNumber], true);
            } catch (StackOverflowError e) {
                return "#RecursiveReference";
            }
            valueType = referencedCell.getValueType();
            return referencedCell.getValue();
        }
/*           //todo: this too
            if (executionType.equals("expression")) {
                System.out.println("expression in term");
                return new Expression(content).getValue();
            }*/
        return null; // todo: handle error properly;

    }

    public String getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return content + "(" + executionType + ")";
    }
}
