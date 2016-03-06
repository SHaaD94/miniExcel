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

/*
    public Expression(String expressionString) {
        textContent = expressionString;
        Operation operation = findFirstOperation();
        if (null != operation) {
            Term term1 = new Term(textContent.substring(0, textContent.indexOf(operation.toString())));
            Term term2 = new Term(textContent.substring(textContent.indexOf(operation.toString()) + 1));

            result = executeOperation(term1, term2, operation);
        } else {
            result = new Term(textContent).getValue();
        }
    }
*/

    public Expression(String expressionString) {
        textContent = expressionString;
        Operation operation = findFirstOperation(textContent);
        boolean firstOperation = true;
        result = "";
        if (null != operation) {
            while (null != operation) {
                int operationSymbolIndex = textContent.indexOf(operation.toString());
                Operation nextOperation = findFirstOperation(textContent.substring(operationSymbolIndex + 1));
                int nextOperationIndex = (null == nextOperation) ? textContent.length() : textContent.indexOf(nextOperation.toString());
                if (firstOperation) {
                    Term term1 = new Term(textContent.substring(0, operationSymbolIndex));
                    Term term2 = new Term(textContent.substring(operationSymbolIndex + 1, nextOperationIndex));
                    result = executeOperation(term1, term2, operation);
                    firstOperation = false;
                } else {
                    result = executeOperation(new Term(result), new Term(textContent.substring(operationSymbolIndex + 1,
                            nextOperationIndex)), operation);
                }
                operation = nextOperation;
            }
        } else {
            result = new Term(textContent).getValue();
        }
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
