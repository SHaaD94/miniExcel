package com.shaad.hierarchy;

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
        Operation operation = findOperation();
        if (null != operation) {
            Term term1 = new Term(textContent.substring(0, textContent.indexOf(operation.toString())));
            Term term2 = new Term(textContent.substring(textContent.indexOf(operation.toString()) + 1));

            result = executeExpression(term1, term2, operation);
        } else {
            result = new Term(textContent).getValue();
        }
    }

    /**
     * if null, it is only term.
     */
    public Operation findOperation() {
        for (Operation operation : Operation.values()) {
            if (textContent.contains(operation.toString())) {
                return operation;
            }
        }
        return null;
    }

    //todo: implement sane execution; (int)
    public String executeExpression(Term term1, Term term2, Operation operation) {
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
