package com.example.vizsga;

import java.io.Serializable;

public class Problem implements Serializable {
    private int a;
    private int b;
    private String op;

    public Problem(int a, int b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
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

    @Override
    public String toString() {
        return a + " " + op + " " + b;
    }
}