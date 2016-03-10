package com.shaad.entities;

import com.shaad.enums.ValueType;

/**
 * Table cell.
 */
public class Cell {
    private String content;
    private String value;
    private ValueType valueType;

    public Cell(String content) {
        this(content, false);
    }

    public Cell(String content, boolean referencedFromTerm) {
        valueType = ValueType.NUMBER;
        //todo: is it the best way to handle empty cell?
        if (null == content || content.isEmpty()) {
            if (!referencedFromTerm) {
                this.content = " ";
                this.value = " ";
            } else {
                this.content = "0";
                this.value = "0";
            }
        } else {
            this.content = content;
            this.value = compute();
        }
    }

    private String compute() {
        if (content.matches("[-]?[1-9][0-9]*|[0]")) {
            return content;
        } else if (content.charAt(0) == '\'') {
            valueType = ValueType.TEXT;
            return content.substring(1);
        } else if (content.charAt(0) == '=') {
            Expression resultExpression = new Expression(content.substring(1));
            valueType = resultExpression.getValueType();
            return resultExpression.getValue();
        } else {
            return "#SyntaxError";
        }
    }

    public String getContent() {
        return content;
    }

    public String getValue() {
        return value;
    }

    public ValueType getValueType() {
        return valueType;
    }

}
