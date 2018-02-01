package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation
 * is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class MinimumEditDistance {

    /**
     * Approach:
     *  This is a dynamic programming problem where the edit distance between X[1...i] and Y[1....j], i.e between
     *  the first i characters in X and the first j characters in Y need to be calculated and stored in a mnemonic table.
     *
     *  We begin by calculating the edit distance to convert X[1....i] to the null string e.g. "abcd" -> "" requires
     *  4 deletions or n modifications where n is the length of X. Similarly the number of changes required to convert
     *  "abdeg" -> "" is 5 deletions or m modifications where m is the length of Y.
     *
     *  If we have a n x m matrix to track the distance
     *       "" a b c d
     *     "" 0 1 2 3 4  <- convert string of length Y[1...j] to null length string
     *      a 1
     *      b 2
     *      d 3
     *      e 4
     *      g 5
     *        ^- convert string of length X[1....i] to a null length string
     *
     *  Extending this concept, edit distance between strings of length X[1..i] and Y[1..j]:
     *      D(i,j) =  minimum of (
     *                      1) D(i-1, j) i.e. the cell to the left + 1
     *                      2) D(i, j-1) i.e. the cell to the top + 1
     *                      3) D(i-1, j-1) i.e. the cell diagonally top left : if they characters are the same or
     *                                                                              + 1 if they are different
     *                  )
     *
     *       "" a b c d
     *     "" 0 1 2 3 4
     *      a 1 0 <- min(1+1, 1+1, 0) = 0 i.e. 0 edits required to change a -> a
     *      b 2 1 <- min(2+1, 0+1, 1+1) = 1, i.e 1 edit required (deletion) to change ab -> a
     *      d 3 1 <- min(3+1, 2+1, 2+1) = 2, i.e 2 edit required (deletion) to change abc -> a
     *      e 4
     *      g 5
     *
     *      filling out more of the table
     *
     *       "" a b c d
     *     "" 0 1 2 3 4
     *      a 1 0 1 <- min(0+1, 2+1, 1+1) = 0 i.e. 1 edit required (addition) to change a -> ab
     *      b 2 1 0 <- min(1+1, 1+1, 0) = 0, i.e 0 edits required to change ab -> ab
     *      d 3 1 1 <- min(1+1, 0+1, 1+1) = 1, i.e 1 edit required (deletion) to change abd -> ab
     *      e 4
     *      g 5
     *
     *       "" a b c d
     *     "" 0 1 2 3 4
     *      a 1 0 1 2 <- min(1+1, 3+1, 2+1) = 2 i.e. 2 edits required (addition) to change a -> abc
     *      b 2 1 0 1 <- min(0+1, 2+1, 1+1) = 1, i.e 1 edit required (addition) to change ab -> abc
     *      d 3 1 1 1 <- min(1+1, 1+1, 0+1) = 1, i.e 1 edit required (substitution) to change abd -> abc
     *      e 4
     *      g 5
     *
     *      By observing "where" the minimum edit distance comes from, we can tell weather it is an addition, deletion or
     *      substitution
     *          (i-1) + 1 = addition
     *          (j-1) + 1 = deletion
     *          (i-1)(j-1) + 0 = no change, characters match
     *          (i-1)(j-1) + 1 = substitution
     *
     *      filling out rest of the table
     *       "" a b c d
     *     "" 0 1 2 3 4
     *      a 1 0 1 2 3 <- 3 edits required (addition) to change a -> abcd
     *      b 2 1 0 1 2 <- 2 edits required (addition) to change ab -> abcd
     *      d 3 2 1 1 2 <- 2 edits required (substitution + addition) to change abd -> abcd
     *      e 4 3 2 2 2 <- 2 edits required (substitution + addition) to change abde -> abcd
     *      g 5 4 3 3 3 <- 3 edits required (substitution + addition + deletion) to change abdeg -> abcd
     *
     * Algorithm:
     *  // allocate one extra row and column to calculate distances to the null string
     *  distance matrix <- new int matrix of size n+1, m+1
     *  // populated the distances to the null string, i.e. the first row and first column
     *  for i = 0 -> length of word1:
     *      distance matrix[i][0] <- i
     *
     *  for j = 0 -> length of word2
     *      distance matrix[0][j] <- j
     *
     *  for i = 1 to length of word1:
     *      for j = 1 to length of word2:
     *          distance matrix[i][j] = minimum(dist mat[i-1][j] + 1,
     *                                          dist mat[i][j-1] + 1,
     *                                          dist mat[i-1][j-1] + 0 if characters are same, else 1
     *
     *  return value at dist mat[n][m]
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minimumEditDistance(String word1, String word2) {
        if (word1 == null && word2 == null || word1.isEmpty() && word2.isEmpty() || word1.equalsIgnoreCase(word2)) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();

        /** allocate 1 additional row and column to calculate transformations to the null string */
        int[][] distanceMatrix = new int[n+1][m+1];

        /** populate transformation values from word1 and word2 to empty string*/
        for(int i = 0; i < n+1; i++) {
            distanceMatrix[i][0] = i;
        }

        for(int j = 0; j < m+1; j++) {
            distanceMatrix[0][j] = j;
        }

        /** populate distance matrix
         * indices for i, j begin from 1 not 0, since index 0 tracks edit distance to the empty from the current
         * substring X[1...i] or Y[1...j]
         */
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                /** calculate min between the left and top cells */
                int minValue = Math.min(
                        distanceMatrix[i-1][j] + 1,
                        distanceMatrix[i][j-1] + 1);
                /** calculate distance for the left diagonal cell
                 * add 1 if the characters are the same, else 0
                 * NOTE!: indexing is offset by 1, adjust for this offset when accessing characters in the inputs
                 */
                int diagCornerCost = word1.charAt(i-1) == word2.charAt(j-1) ? distanceMatrix[i-1][j-1] : distanceMatrix[i-1][j-1] + 1;

                minValue = Math.min(minValue, diagCornerCost);
                distanceMatrix[i][j] = minValue;
            }
        }

        /** return the value in the bottom left cell as the total edit distance */

        return distanceMatrix[n][m];
    }

    public static void main(String[] args) {
        String input1 = "abcd";
        String input2 = "abdg";

        Logger.log("Edit distance betwee " + input1 + " and " + input2 + " is " + minimumEditDistance(input1, input2));
    }
}
