package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * The n-Queen problem:
 *
 *  n queens problem of placing n non-attacking queens on an n×n chessboard, for which solutions exist for all
 *  natural numbers n with the exception of n=2 and n=3
 *
 *  This is the epitome of a recursive back tracking problem. Any recursive function has to have the following
 *  properties
 *      1) base case, a.k.a the terminating condition
 *      2) each recursion breaks the problem into smaller chunks
 *      3) results from each recursion should be used to make decisions further up the chain
 */
public class NQueens {

    /**
     * Approach:
     *  For each location on the board place 1st queen, mark that location with 1, recurse with the 2nd queen
     *  by leveling up.
     *  Using helper function to determine if location is in column, row or diagonal (upper left, upper right, lower
     *  left, lower right) path of any of the queens (1s) already on the board.
     *  Find location suitable for 2nd queen, mark that location with 1 and recurse with the third queen and repeat
     *  process as with the second queen
     *  If a suitable spot is not found, return false and backtrack to the second queen and find next suitable spot
     *  for the 2nd queen before attempting to place the 3rd queen.
     *  Example
     *
     *  1 0 0 0      1 0 0 0     1 0 0 0                    1 0 0 0     (still no               0 1 0 0
     *  0 0 0 0   -> 0 0 1 0  -> 0 0 1 0  -> (no solution   0 0 0 0  ->  solution, try    ->    0 0 0 1
     *  0 0 0 0      0 0 0 0     0 0 0 0       backtrack    0 1 0 0      moving 1st queen       1 0 0 0
     *  0 0 0 0      0 0 0 0     0 1 0 0       2nd queen )  0 0 0 0      to new position)       0 0 1 0
     * @param board
     * @param level
     * @param size
     */
    public static boolean nQueens(int[][] board, int level, int size) {
        /** default case, exit from recursive condition */
        if (level == size) {
            return true;
        }

        for(int i = level; i < size; i++) {
            for(int j = 0; j < size; j++) {
                /** check if position is can be attacked */
                if (isAttackable(i, j, board)) {
                    continue;
                }
                /** we've found a position that cannot be attacked by queens already on the board,
                 * place new queen there
                 */
                board[i][j] = 1;
                /** if all subsequent recursions return true, we have found a solution*/
                if (nQueens(board, level + 1, size)) {
                    return true;
                }
                /** other wise backtrack previous step and continue checking other cells */
                board[i][j] = 0;
            }
        }
        /** we did not find any positions that could not be attacked, return false */
        return false;
    }

    /**
     * Utility function that check if given a cell by x, y, can the location be attached by queens already
     * on the board.
     * The queen can attack along the length of the row, column or diagonals in any direction. The row and
     * column checks are easy. The diagonal checks are easy as well because they have a standard property
     * with respect to the cell being tested for. Example
     * 0 0 0 0
     * 0 0 x 0
     * 0 0 0 0
     * 0 0 0 0
     *
     * for the cell above (1, 2)
     *  columns = (1, 0 - N-1) can be attacked
     *  rows = (0 - N-1, 2) can be attacked
     *  major diagonal cells (0, 1) and (2, 3) can be attached
     *  minor diagonal cells (3, 0), (2, 1) and (0, 3) can be attacked
     *
     *  By observing the diagonal cells, we can derive a pattern to check if it falls along a cell's diagonal path
     *  For major diagonal all cells conform to (y-x) = (2-1) = 1
     *      (0, 1) => 1 - 0 = 1
     *      (2, 3) => 3 - 2 = 1
     *  Similarly for the minor diagonal, all cells conform to (x+y) = (2+1) = 3
     *      (3, 0) => 3 + 0 = 3
     *      (2, 1) => 2 + 1 = 3
     *      (0, 3) => 0 + 3 = 3
     *
     * @param x
     * @param y
     * @param board
     * @return
     */
    public static boolean isAttackable(int x, int y, int[][] board) {
        /** need to go through the board to find all the 1s (queen locations
         * and check if the given cell can be attacked by that queen
         */
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    /** check rows and colums */
                    if (x == i || y == j) {
                        return true;
                    }
                    /** check diagonals */
                    if ((j - i) == (y - x) || (i + j) == (x + y)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int size = 6;
        int[][] board = new int[size][size];
        Logger.log(board);
        nQueens(board, 0, size);
        Logger.log("After n-queen solution");
        Logger.log(board);
    }
}
