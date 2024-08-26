package com.example.vizsga;

import java.io.Serializable;

public class Problem implements Serializable {
    private final int a;
    private final int b;
    private final String op;
    private boolean solved;

    public Problem(int a, int b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    // Method to compute the correct answer
    public int calculateAnswer() {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }
    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
    @Override
    public String toString() {
        return a + " " + op + " " + b;
    }
}