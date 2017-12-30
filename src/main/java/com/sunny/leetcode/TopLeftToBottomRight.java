package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The problem is to print all the possible paths from top left to bottom right of a mXn matrix with the
 * constraints that from each cell you can either move only to right or down.
 */
public class TopLeftToBottomRight {

    /**
     * Approach:
     *  This is a recursive problem where each path that can be traversed is explored.
     *  Recursive property is:
     *      1) Base condition where recursion ends:
     *          - we have reached the bottom right
     *              print paths till now
     *          - we don't have a path to the right and we don't have a path below
     *      2) Break the problem into a smaller piece during each recursion:
     *          - increment either column or row
     *      3) Float results up
     *          - results are not floated up in this case, we just need to print out results once
     *          destination is reached
     *
     * Algorithm:
     *  if x == row - 1 and y = column - 1:
     *      print paths till now
     *  if can traverse right:
     *      create new list from paths till now and append new int array {x, y}
     *      recurse with x, y+1, rows, column, new list
     *  if can traverse below:
     *      create new list from paths till now and append new int array {x, y}
     *      recurse with x+1, y, rows, column, new list
     *
     * @param x
     * @param y
     * @param rows
     * @param columns
     * @param pathTillNow
     */
    public static void pathToBottom(int x, int y, int rows, int columns, List<int[]> pathTillNow) {
        /** base base, we've reached the bottom of the matrix, print all paths till now */
        if (x == rows - 1 && y == columns - 1) {
            pathTillNow.add(new int[] {x,y});
            printPaths(pathTillNow);
            return;
        }

        if (canTraverseRight(y, columns)) {
            List<int[]> newPathTillNow = new ArrayList<>(pathTillNow);
            newPathTillNow.add(new int[] {x, y});
            /** recurse with new cell added to the path and column incremented */
            pathToBottom(x, y+1, rows, columns, newPathTillNow);
        }

        if (canTraverseDown(x, rows)) {
            List<int[]> newPathTillNow = new ArrayList<>(pathTillNow);
            newPathTillNow.add(new int[] {x, y});
            /** recurse with new cell added to the path and row incremented */
            pathToBottom(x+1, y, rows, columns, newPathTillNow);
        }
    }

    /**
     * Utility method to check if cell to the right is within bounds
     * @param y
     * @param columns
     * @return
     */
    public static boolean canTraverseRight(int y, int columns) {
        return (y + 1 < columns);
    }

    /**
     * Utility method to check if cell below is within bounds
     * @param x
     * @param rows
     * @return
     */
    public static boolean canTraverseDown(int x, int rows) {
        return (x + 1 < rows);
    }

    /**
     * Utility method to print paths
     * @param paths
     */
    public static void printPaths(List<int[]> paths) {
        for (int[] cell : paths) {
            Logger.logSameLine(Arrays.toString(cell) + "->");
        }
        Logger.log("|");
    }

    public static void main(String[] args) {
        List<int[]> paths = new ArrayList<>();
        pathToBottom(0, 0, 3, 3, paths);
    }

}
