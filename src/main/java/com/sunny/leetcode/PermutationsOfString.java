package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given a string, write a program to print all permutations of the string
 * e.g. ABC
 * ABC
 * ACB
 * BAC
 * BCA
 * CAB
 * CBA
 */
public class PermutationsOfString {


    /**
     * This is a classic backtracking problem where each arrangement of the characters need to be explored
     * Approach:
     *  Create a new string by swapping the starting index with the remaining indexes till the length of the string.
     *  Recurse with the new string with starting at starting index + 1
     *  If starting index equals length of the string, we've reached the end of the string, print string
     *
     *  Recursive problems need to have the following properties:
     *      1) base case from which to break out of recursion
     *      2) each recursive call should reduce the problem into a smaller piece
     *      3) bubble up the result of sub calls to make decisions in the end and within each call
     *
     *
     * Algorithm:
     *  if starting index = string length:
     *      print current string;
     *  for index i -> starting index to string length
     *      new string = swap characters at starting index and i
     *      recurse with new string and starting index = starting index + 1
     *
     *
     *
     * @param input
     * @param startingIndex
     */
    public static void printPermutations(String input, int startingIndex) {
        if (startingIndex == input.length()) {
            Logger.log(input);
        }

        /** Vary the character at startingIndex with remaining characters */
        for(int i = startingIndex; i < input.length(); i++) {
            char[] inputCharacters = input.toCharArray();
            swap(inputCharacters, i, startingIndex);
            /** recurse with swapped string and increment starting index by 1 */
            printPermutations(new String(inputCharacters), startingIndex+1);
        }
    }

    /**
     * Helper method to swap characters at the given indexes
     * @param inputCharacters
     * @param index1
     * @param index2
     */
    public static void swap(char[] inputCharacters, int index1, int index2) {
        char temp = inputCharacters[index1];
        inputCharacters[index1] = inputCharacters[index2];
        inputCharacters[index2] = temp;
    }

    public static void main(String[] args) {
        printPermutations("ABC", 0);
    }


}
