package com.shaad.entities;

import com.shaad.enums.Operation;
import com.shaad.enums.ValueType;

/**
 * Expression.
 */
public class Expression {
    /**
     * Input content of expression.
     */
    private String content;
    /**
     * Computed value of expression.
     */
    private String value;
    /**
     * Value type of expression.
     */
    private ValueType valueType;

    public Expression(String expressionString) {
        content = expressionString;
        valueType = ValueType.NUMBER;
        value = compute();
    }

    /**
     * Compute value.
     *
     * @return String value of expression.
     */
    private String compute() {
        String result = "";
        String expression = content;
        if (expression.isEmpty()) {
            valueType = ValueType.NUMBER;
            return "0";
        }
        Operation operation = findFirstOperation(expression);
        boolean firstOperation = true;
        if (null != operation) {
            while (null != operation) {
                int currentOperationIndex = expression.indexOf(operation.toString());
                Operation nextOperation = findFirstOperation(expression.substring(currentOperationIndex + 1));
                int nextOperationIndex = (null == nextOperation) ? expression.length() :
                        expression.indexOf(nextOperation.toString(), currentOperationIndex + 1);
                Term term1, term2;
                if (firstOperation) {
                    term1 = new Term(expression.substring(0, currentOperationIndex));
                    term2 = new Term(expression.substring(currentOperationIndex + 1, nextOperationIndex));
                    firstOperation = false;
                } else {
                    term1 = new Term(result);
                    term2 = new Term(expression.substring(currentOperationIndex + 1, nextOperationIndex));
                }
                try {
                    result = executeOperation(term1, term2, operation);
                } catch (IllegalArgumentException e) {
                    return "#IllegalArgument";
                } catch (ArithmeticException e) {
                    return "#DevideByZero";
                }
                expression = expression.replaceFirst("\\" + operation.toString(), "~");
                operation = nextOperation;
            }
        } else {
            Term resultTerm = new Term(expression);
            valueType = resultTerm.getValueType();
            result = resultTerm.getValue();
        }
        return result;
    }

    /**
     * Return index of first operation or null
     */
    public Operation findFirstOperation(String str) {
        for (char symb : str.toCharArray()) {
            if (symb == '+') {
                return Operation.PLUS;
            } else if (symb == '-') {
                return Operation.MINUS;
            } else if (symb == '*') {
                return Operation.MULTIPLY;
            } else if (symb == '/') {
                return Operation.DEVIDE;
            }
        }
        return null;
    }

    /**
     * Execute operation.
     *
     * @param term1     first term
     * @param term2     second term
     * @param operation operation
     * @return string value
     * @throws IllegalArgumentException thrown in case if one of terms isn't number
     * @throws ArithmeticException      in cases of devide by zero
     */
    public String executeOperation(Term term1, Term term2, Operation operation)
            throws IllegalArgumentException, ArithmeticException {
        if (term1.getValueType() == ValueType.TEXT || term2.getValueType() == ValueType.TEXT) {
            throw new IllegalArgumentException();
        }
        int term1Value = Integer.parseInt(term1.getValue());
        int term2Value = Integer.parseInt(term2.getValue());

        int result = 0;
        if (operation == Operation.PLUS) {
            result = term1Value + term2Value;
        } else if (operation == Operation.MINUS) {
            result = term1Value - term2Value;
        } else if (operation == Operation.MULTIPLY) {
            result = term1Value * term2Value;
        } else if (operation == Operation.DEVIDE) {
            result = term1Value / term2Value;
        }
        return Integer.toString(result);
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
