package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class LongestValidParentheses {


    /**
     * Approach:
     *  Use a stack to push the index of a valid open parenthesis '('. Anytime we encounter a close bracket ')'
     *  and character at the top of the stack is an open bracket '(', we pop the matching opening bracket. If there the
     *  character at the top of the stack is not '(', push the ')' to the stack.
     *
     *  All matching braces will be popped out and the stack will contain indices of all unmatched brackets. From the
     *  stack of unmatched sequences we can find the length of the longest matched sequence by working in reverse.
     *  first sequence length = string length - index of first bracket popped
     *  second sequence length = index of first bracket popped to index of second bracket popped
     *  and so on. Find the sequence with the longest length
     *
     *  If the stack is empty, the entire length of the string is valid
     *
     * Algorithm:
     *  stack = new integer stack
     *  char at top of stack = null;
     *  for each character in input:
     *      if char equals '(':
     *          push index onto stack
     *          char at top of stack -> '('
     *      else:
     *          if char at top equals '(':
     *              pop
     *          else:
     *              push index onto stack
     *              char at top -> ')'
     *
     *  if stack is empty:
     *      return length of string
     *
     *  current index -> stack.pop
     *  longest sequence length = length of string - current index
     *
     *  while stack is not empty:
     *      next index = stack.pop
     *      if distance between current index and next index > longest sequence length:
     *          update longest sequence length
     *
     *      current index -> next index
     *
     *  if distance between the current index, i.e the first unmatched bracket and beginning of string
     *  is greater than longest sequence length:
     *      update longest sequence length
     *
     *  return longest sequence length
     *
     * @param input
     * @return
     */
    public static int longestValidParentheses(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);
            if (curChar == '(') {
                stack.push(i);
            } else {
                /** current char is ')', check if we have a matching '(' on top of the stack */
                if (!stack.isEmpty() && input.charAt(stack.peek()) == '(') {
                    /** we've found a matching bracket */
                    stack.pop();
                } else {
                    /** no matching bracket found, push current index in */
                    stack.push(i);
                }
            }
        }

        /** if stack is empty, all open and close brackets were matched, the entire length of the input is valid */
        if (stack.isEmpty()) {
            return input.length();
        }

        int currentIndex = stack.pop();
        /** unmatched bracket indices are popped in reverse, i.e. first popped index is closest to the end of the
         * string
         */
        int longestMatchingSequence = input.length() - currentIndex - 1;
        /** find distances between unmatched elements, start and end of the input string */
        while (!stack.isEmpty()) {
            int previousIndex = stack.pop();
            /** currentIndex - previousIndex - 1 because we don't want length between the two indices not including
             * the indices themselves
             */
            if((currentIndex - previousIndex - 1) > longestMatchingSequence) {
                longestMatchingSequence = currentIndex - previousIndex - 1  ;
            }
            /** update current index to point to the previous index */
            currentIndex = previousIndex;
        }

        /** find the longest matching sequence between start of the string and the last index to be popped off */
        if (currentIndex > longestMatchingSequence) {
            longestMatchingSequence = currentIndex;
        }

        return longestMatchingSequence;
    }

    public static void main(String[] args) {
//        String testString = ")("; // 0
        String testString = ")()())()()(";  // 4
        Logger.log("Longest valid parenthesis sequence is of length = " + longestValidParentheses(testString));
    }
}
