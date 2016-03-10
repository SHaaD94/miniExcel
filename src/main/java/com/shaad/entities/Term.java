package com.shaad.entities;

import com.shaad.Runner;
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
            executionType = "reference";
        } else if (content.matches("[-]?[1-9][0-9]*|[0]")) {
            executionType = "number";
        } else {
            executionType = "#SyntaxError"; // todo: handle this error;
        }

        value = !executionType.equals("#SyntaxError") ? compute() : executionType;
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
            try {
                referencedCell = new Cell(Runner.backendTable[rowNumber][columnNumber], true);
            } catch (StackOverflowError e) {
                return "#RecursiveReference";
            }
            valueType = referencedCell.getValueType();
            return referencedCell.getValue();
        }

        return null;
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
