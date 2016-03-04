package com.shaad.hierarchy;

/**
 * Basic operations.
 */
public enum Operation {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DEVIDE("/");

    private String operation;

    Operation(String o) {
        this.operation = o;
    }

    @Override
    public String toString() {
        return operation;
    }
}
