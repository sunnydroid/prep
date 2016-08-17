package com.sunny.problemSet;

import java.util.Scanner;

/**
 * Created by sunshah on 7/21/16.
 */
public class FunnyString {
    /**
     * Suppose you have a String, S, of length N that is indexed from 0 to N-1. You also have some String, R, that is the reverse of String S. S is funny if the condition
     * |S[i] - S[i-1]| = |R[i] - R[i-1]| is true for every character from 1 to N-1.
     *
     * Note: For some String S, S[i] denotes the ASCII value of the ith 0-indexed character in S. The absolute value of an integer, x, is written as |x|.
     *
     * Input Format
     * The first line contains an integer, T (the number of test cases).
     * Each line i of the T subsequent lines contain a string, S.
     *
     * Constraints
     * 1 <= T <= 10
     * 0 <= i <= T-1
     * 2 <= length of S <= 10000
     *
     * Output Format
     * For each string print whether it is "Funny" or "Not Funny" on a new line.
     *
     * Sample input
     * 2
     * acxz
     * bcxz
     *
     * Sample output
     *
     * Funny
     * Not Funny
     *
     * Explanation
     *
     * |c-a| = |z-x|
     * |x-c| = |x-c| and so on
     *
     * This is not true for the second string hence "Not Funny" result
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for(int i = 0; i < cases; i++) {
            String input = scanner.next();
            isFunny(input);
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
    /**
     * Algorithm:
     *  if input is null or empty, print Not Funny
     *  for i = 0, j = input.length -1, i < j; i++, j--
     *      if Abs of input[i] - input[i+1] != input[j] - input[j-1]
     *          print Not Funny
     *          return
     *  print funny
     */
    public static void isFunny(String input) {
        int i, j;
        if(input == null || input.length() < 2) {
            System.out.println("Not Funny");
            return;
        }
        for(i = 0, j = input.length()-1; i < j; i++, j--) {
            if(Math.abs(input.charAt(i) - input.charAt(i+1)) != Math.abs(input.charAt(j) - input.charAt(j-1))) {
                System.out.println("Not Funny");
                return;
            }
        }
        System.out.println("Funny");
    }
}
