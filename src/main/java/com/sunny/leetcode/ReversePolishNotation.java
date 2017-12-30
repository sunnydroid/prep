package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReversePolishNotation {

    /**
     * Approach - Use a stack to determine which elements to apply operation on
     *
     * Algorithm:
     *  push operands onto a stack
     *  push operators into a queue
     *  while queue is not empty:
     *      pop two elements and evaluate value using operator
     *      push new value into stack
     *
     *  return last value evaluated in the stack
     *
     * @param operands
     * @return
     */
    public static int evaluate(String[] operands) {

        /** track numbers using stack */
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        /** separate out operands and operators */
        while (index < operands.length) {
            /** add numbers to operandsQueue */
            String currentString = operands[index];
            /** determine if current character is a number or operator */
            switch (currentString) {
                case "/":
                case "*":
                case "-":
                case "+":
                    /** order of operand is important */
                    int operand1 = stack.pop();
                    int operand2 = stack.pop();
                    stack.add(evaluateOperation(currentString, operand2, operand1));
                    index++;
                    break;
                default:
                    stack.add(Integer.valueOf(currentString));
                    index++;
            }
        }

        /** last value in the operandsQueue is the result */
        return stack.pop();
    }

    public static int evaluateOperation(String operator, int operand1, int operand2) {
        switch (operator) {
            case "/":
                return operand1 / operand2;
            case "*":
                return operand1 * operand2;
            case "-":
                return operand1 - operand2;
            case "+":
                return operand1 + operand2;
            default:
                throw new IllegalArgumentException("Invalid operator");
            }
    }

    public static void main(String[] args) {
        String[] reversePolishNotation = {"2", "1", "+", "3", "*"};
//        String[] reversePolishNotation = {"4", "13", "5", "/", "+"};
        Logger.log("Value evaluated for notation " + Arrays.toString(reversePolishNotation) + " is " + evaluate(reversePolishNotation));
        Logger.log("5 / 2 => " + 5 / 2);
    }
}
