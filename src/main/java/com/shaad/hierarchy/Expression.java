package com.shaad.hierarchy;

import com.shaad.enums.Operation;
import com.shaad.enums.ValueType;

import java.util.HashMap;
import java.util.Map;

/**
 * //todo: write annotation
 */
public class Expression {
    private String content;
    private String value;

    private ValueType valueType;

    public Expression(String expressionString) {
        content = expressionString;
        valueType = ValueType.NUMBER;
        value = compute();
    }

    //todo: try to refactor this method;
    private String compute() {
        String result = "";
        String expression = content;
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
                    return "#WrongArgument";
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
        HashMap<Operation, Integer> operations = new HashMap<>();
        for (Operation operation : Operation.values()) {
            if (str.contains(operation.toString())) {
                operations.put(operation, str.indexOf(operation.toString()));
            }
        }

        Map.Entry<Operation, Integer> first = null;
        for (Map.Entry<Operation, Integer> element : operations.entrySet()) {
            if (first == null || first.getValue() > element.getValue()) {
                first = element;
            }
        }
        return first != null ? first.getKey() : null;
    }

    public String executeOperation(Term term1, Term term2, Operation operation) throws IllegalArgumentException {
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
        //todo: handle exception
        return Integer.toString(result);
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

    public ValueType getValueType() {
        return valueType;
    }
}
