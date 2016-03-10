package com.shaad.entities;

import com.shaad.enums.TermExecutionType;
import com.shaad.enums.ValueType;
import com.shaad.util.Util;

/**
 * Term of cell expressions.
 */
public class Term {
    /**
     * Input content.
     */
    private String content;
    /**
     * Computed value of content.
     */
    private String value = null;
    /**
     * Execution type.
     */
    private TermExecutionType executionType;
    /**
     * Text or number.
     */
    private ValueType valueType;

    public Term(String termContent) {
        content = termContent;

        //todo: get rid of 'string types' of content
        if (content.matches("[a-zA-Z][1-9][0-9]*")) {
            executionType = TermExecutionType.REFERENCE;
        } else if (content.matches("[-]?[1-9][0-9]*|[0]")) {
            executionType = TermExecutionType.NUMBER;
        } else {
            value = "#SyntaxError";
            return;
        }

        //value = !executionType.equals("#SyntaxError") ? compute() : executionType;
        value = compute();
    }

    private String compute() {
        valueType = ValueType.NUMBER;
        if (executionType == TermExecutionType.NUMBER) {
            return content;
        }

        if (executionType == TermExecutionType.REFERENCE) {
            int rowNumber = Integer.parseInt(content.substring(1)) - 1;
            int columnNumber = Util.getLetterPosition(content.charAt(0));
            Cell referencedCell;
            try {
                referencedCell = new Cell(TableHolder.getInstance().getBackendTable()[rowNumber][columnNumber], true);
            } catch (StackOverflowError e) {
                return "#RecursiveReference";
            } catch (ArrayIndexOutOfBoundsException e) {
                return "#CellDoesNotExist";
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
