package com.shaad.hierarchy;

import java.util.HashMap;
import java.util.Map;

/**
 * //todo: write annotation
 */
public class Expression {
    private String textContent;
    private String result;

    public Expression() {
        this("");
    }

    public Expression(String expressionString) {
        textContent = expressionString;
        result = compute();
    }

    //todo: try to refactor this method;
    private String compute() {
        String result = "";
        String expression = textContent;
        Operation operation = findFirstOperation(expression);
        boolean firstOperation = true;
        if (null != operation) {
            while (null != operation) {
                int currentOperationIndex = expression.indexOf(operation.toString());
                Operation nextOperation = findFirstOperation(expression.substring(currentOperationIndex + 1));
                int nextOperationIndex = (null == nextOperation) ? expression.length() :
                        expression.indexOf(nextOperation.toString(), currentOperationIndex + 1);
                if (firstOperation) {
                    Term term1 = new Term(expression.substring(0, currentOperationIndex));
                    Term term2 = new Term(expression.substring(currentOperationIndex + 1, nextOperationIndex));
                    result = executeOperation(term1, term2, operation);
                    firstOperation = false;
                } else {
                    result = executeOperation(new Term(result), new Term(expression.substring(currentOperationIndex + 1,
                            nextOperationIndex)), operation);
                }
                expression = expression.replaceFirst("\\" + operation.toString(), "~");
                operation = nextOperation;
            }
        } else {
            result = new Term(expression).getValue();
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

    public String executeOperation(Term term1, Term term2, Operation operation) {
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

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getResult() {
        return result;
    }
}
