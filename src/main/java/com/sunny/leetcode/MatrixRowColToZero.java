package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class MatrixRowColToZero {

    /**
     * Approach:
     * The naive approach would be to traverse all the cells and keep track of all the zeros. This would require
     * additional space of O(nxn) if the matrix has all zeros.
     * Another approach could be to traverse all cells and when we encounter a zero, we set the column and row for
     * that cell to a specific value, possibly INT_MIN or INT_MAX. On a second iteration, all values that have the
     * predefined value are set to zero. However this approach fails if the predefined value is present in the matrix
     *
     * A fool proof method would be to
     *
     * Algorithm:
     *  for index i = 0 to m:
     *      for index j = 0 to n:
     *          if matrix[m][n] equals zero:
     *              set row to INT_MIN
     *              set col to INT_MIN
     *
     *  for index i = 0 to m:
     *      for index j = 0 to n:
     *          if matrix[m][n] equals INT_MIN:
     *              matrix[m][n] -> 0
     *
     *  In the utility functions to set rows/cols to zero, skip if cell contains a zero
     *
     * @param matrix
     */
    public static void setRowsColsToZero(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    setRowsToValue(matrix, i, Integer.MIN_VALUE);
                    setColsToZeroToValue(matrix, j, Integer.MIN_VALUE);
                }
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == Integer.MIN_VALUE) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    /**
     * Utility method to set all rows to specified value
     * @param matrix
     * @param row
     * @param value
     */
    public static void setRowsToValue(int[][] matrix, int row, int value) {
        for(int i = 0; i < matrix[row].length; i++) {
            if (matrix[row][i] != 0) {
                matrix[row][i] = value;
            }
        }
    }

    /**
     * Utility method to set all columns to specified value
     * @param matrix
     * @param col
     * @param value
     */
    public static void setColsToZeroToValue(int[][] matrix, int col, int value) {
        for(int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] != 0) {
                matrix[i][col] = value;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 3, 4}, {1, 0, 3, 4}, {3, 8, 9, 2}, {2, 9, 1, 6}};
        Logger.log("Matrix before : ");
        Logger.log2DArray(matrix);
        setRowsColsToZero(matrix);
        Logger.log("Matrix after : ");
        Logger.log2DArray(matrix);
    }
}
