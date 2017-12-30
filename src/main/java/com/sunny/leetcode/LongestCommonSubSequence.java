package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence
 * that appears in the same relative order, but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”,
 * ”acefg”, .. etc are subsequences of “abcdefg”. So a string of length n has 2^n different possible subsequences.
 *
 * This algorithm is the basis of diff (a file comparison program that outputs the differences between two files),
 * and has applications in bioinformatics
 *
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 */
public class LongestCommonSubSequence {

    /**
     * Approach: This is a classic dynamic programming problem which has both properties:
     *
     *  1) Overlapping subproblems : solution of the same sub problem are needed over again and again to build solutions
     *  to increasing problems
     *      - for input sequences X[0...n-1] and Y[0...m-1] the LCS table can be populated in incrementally by
     *          comparing sub strings of different length:
     *              LCS(X[0], Y[0])
     *              LCS(X[0], Y[1]) .........LCS(X[0], Y[m-1]
     *              .
     *              .
     *              .
     *              LCS(X[n-1], Y[1]) .........LCS(X[n-1], Y[m-1]
     *
     *              A G G T A B
     *            G 0 1 1 1 1 1
     *            X 0 1 1 1 1 1
     *            T 0 1 1 2 2 2
     *            X 0 1 1 2 2 2
     *            A 1 1 1 2 3 3
     *            Y 1 1 1 2 3 3
     *            B 1 1 1 2 3 4
     *
     *      - if the 2 characters are the same, take the maximum LCS at previous length of both inputs X[i-1], Y[j-1]
     *      (left top corner) + 1
     *      - if the 2 characters are not the same, take the maximum of X[i-1], Y[j-1] (left cell or top cell).
     *      Intuitively, this translates to taking the max LCS at current index from the two strings
     *      - these precomputed sub problems can be stored using either memoization (top-down) or tabulation (bottom-up)
     *
     *  2) Optimal substructure: optimal solution to the problem can be obtained by using optimal solutions to its
     *  subproblems
     *      - to obtain the optimal solution, we look at the last cell of the table, which will contain the length of
     *          longest common subsequence.
     *
     * @param string1
     * @param string2
     * @return
     */
    public static int[][] buildLcsArray(char[] string1, char[] string2) {
        /** lcs table is initialized to all zeros */
        int[][] lcs = new int[string1.length][string2.length];

        /** Build the table by comparing the two strings at different lengths */
        for(int i = 0; i < string1.length; i++) {
            for(int j = 0; j < string2.length; j++) {
                setLcsValue(lcs, i, j, string1[i], string2[j]);
            }
        }

        return lcs;
    }

    /**
     * Helper method to determine value of lcs based on input values
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public static void setLcsValue(int[][] lcs, int rowIndex, int columnIndex, char c1, char c2) {
        /** if characters are the same, lcs = 1 + lcs value at previous length i.e. top left corner
         * if top left corner is not available (i.e. for first row and first column) select 1 +
         * value at column before or row before
         */
        if (c1 == c2) {
            if (rowIndex == 0 && columnIndex == 0) {
                /** very first cell, return 1 */
                lcs[rowIndex][columnIndex] = 1;
            } else if (rowIndex - 1 < 0) {
                /** top row, only 1 character compared so far, max length of subsequence = 1 */
                lcs[rowIndex][columnIndex] = 1;
            } else if (columnIndex - 1 < 0) {
                /** left most column, only 1 character compared so far, max length of subsequence = 1 */
                lcs[rowIndex][columnIndex] = 1;
            } else{
                /** select value at top left corner + 1 */
                lcs[rowIndex][columnIndex] = lcs[rowIndex-1][columnIndex-1] + 1;
            }
        } else {
            /** choose the max of lcs at previous length of either string, i.e. string1[rowIndex - 1] or
             * string2[columnIndex - 1] (max of top cell or left cell)
             */
            if (rowIndex == 0 && columnIndex == 0) {
                lcs[rowIndex][columnIndex] = 0;
            } else if (rowIndex - 1 < 0) {
                /** top row, select value at previous column */
                lcs[rowIndex][columnIndex] = lcs[rowIndex][columnIndex - 1];
            } else if (columnIndex - 1 < 0) {
                /** left most column, select value at previous row */
                lcs[rowIndex][columnIndex] = lcs[rowIndex - 1][columnIndex];
            } else{
                /** select max of left and top cells */
                lcs[rowIndex][columnIndex] = Math.max(lcs[rowIndex-1][columnIndex], lcs[rowIndex][columnIndex-1]);
            }
        }
    }

    /**
     * Method to find the characters that make up the longest common subsequence from the LCS array
     * Approach:
     *  LCS count increments only when both the strings have the same character. Start at the bottom right and make your
     *  way up to the top left cell. If both the top and left cells have value 1 less than the current value, we've
     *  found a common character. If left cell is lower than top cell, or vice versa, choose the direction of traversal
     *  towards the cell with the higher value
     *
     *   A G G T A B
     * G 0 1 1 1 1 1
     * X 0 1 1 1 1 1
     * T 0 1 1 2 2 2
     * X 0 1 1 2 2 2
     * A 1 1 1 2 3 3
     * Y 1 1 1 2 3 3
     * B 1 1 1 2 3 4
     *
     * Start from the bottom right
     *  4 -> (record B) -> diagonally up -> 3 -> up -> 3 -> (record A) -> diagonally up -> 2 -> up -> 2 -> (record T) ->
     *  move diagonally up -> 1 -> move left -> 1 -> move top -> 1 (record G) -> move left
     *
     * Algorithm:
     *  new char array of length = max lcs length, i.e. value at lcs[n-1][m-1]
     *  array[length - 1] = lcs[n-1][m-1]
     *  int charIndex = array.length - 1
     *
     *  int i = n-1;
     *  int j = m-1;
     *  while i > 0:
     *      while j > 0:
     *          if lcs[i-1] greater than lcs[j-1]
     *              // move left
     *              i = i-1
     *          else if lcs [j-1] greater than lcs[i-1]
     *              // move top
     *              j = j-1
     *          else
     *              // move diagonally up if value at current cell is bigger than both (common char found), and add it
     *              // to the return array else choose either left or top as traversal direction
     *              if lcs[i][j] greater than lcs[i-1][j] AND lcs[i][j] greater than lcs[i][j-1]:
     *                  array[charIndex] = string1.charAt(i)
     *                  charIndex--;
     *                  i = i-1
     *                  j = j-1
     *              else
     *                  j = j-1
     *
     *   // check if we are on the top column or top row but still need to move 1 index to get to {0, 0}
     *   while(j > 0):
     *      // check if value changed while traversing up, if so, we've found a common character
     *      if lcs[i][j] > lcs[i]j-1]:
     *          array[charIndex] = string2.charAt(j)
     *      j--
     *
     *   while(i > 0):
     *      // check if value changed while traversing left, if so, we've found a common character
     *      if lcs[i][j] > lcs[i-1]j]:
     *          array[charIndex] = string1.charAt(i)
     *      i--
     *
     *   return array
     *
     * @param lcs
     * @param string1
     * @param string2
     * @return
     */
    public static String findCharsInLcs(int[][] lcs, char[] string1, char[] string2) {
        /** initialize common characters to the length of lcs = lcs[n-1][m-1] */
        int i = string1.length - 1;
        int j = string2.length - 1;

        char[] commonChars = new char[lcs[i][j]];
        /** add characters in revers order as we travers from the bottom right to the top left of the lcs matrix */
        int charIndex = commonChars.length - 1;

        while (i > 0 && j > 0) {
            /** check if common character found by comparing left and top cells */
            if (lcs[i][j] > lcs[i - 1][j] && lcs[i][j] > lcs[i][j - 1]) {
                commonChars[charIndex] = string1[i];
                charIndex--;
                /** move diagonally up */
                i--;
                j--;
            } else if (lcs[i - 1][j] > lcs[i][j - 1]) {
                /** move left */
                i--;
            } else if (lcs[i - 1][j] < lcs[i][j - 1]) {
                /** move top */
                j--;
            } else {
                /** left and top values are the same as current value, can move in any direction */
                j--;
            }
        }
        /** if we reach the top row or left most column, the above loop will break, travers top or left check for
         * changes in cell value. Change in cell value means common character found.
         */
        while (i < 0) {
            if (lcs[i][j] > lcs[i - 1][j]) {
                /** common character found */
                commonChars[charIndex] = string1[i];
                charIndex--;
            }
            i--;
        }

        while (j < 0) {
            if (lcs[i][j] > lcs[i][j - 1]) {
                /** common character found */
                commonChars[charIndex] = string2[j];
                charIndex--;
            }
            j--;
        }

        return new String(commonChars);
    }


    public static void main(String[] args) {
//        String input1 = "GXTXAYB";
//        String input2 = "AGGTAB";
        String input1 = "ABCDGH";
        String input2 = "AEDFHR";
        int[][] lcs = buildLcsArray(input1.toCharArray(), input2.toCharArray());
        String chars = findCharsInLcs(lcs, input1.toCharArray(), input2.toCharArray());
        Logger.log("Longest common subsequence length for " + input1 + " and " + input2 + " is " + lcs[input1.length() -1][input2.length()-1]);
        Logger.log("Characters are " + chars);
    }
}
