package com.sunny.graphs;

import com.sunny.common.Logger;

/**
 * Given a canvas (integer matrix) that has a boundary of a shape and an x, y coordinate,
 * implement a fill method that will fill the enclosed area within the shape
 *
 * 0 0 0 0 0 0 0
 * 0 0 0 0 1 1 1
 * 0 0 0 1 0 0 0
 * 0 0 0 1 0 x 0
 * 0 0 0 0 1 0 0
 * 0 0 0 0 0 1 1
 *
 * If coordinate of click is given by x, all locations surround by the boundary of 1s should be filled in, i.e with 1
 * Created by sunshah on 3/6/15.
 */
public class FillAlgorithm {
    /**
     * Approach: Use the graph flood algorithm that is BFS
     *  Start from x, y, mark current location as filled, check if neighbors up, down, left and right are part of
     *  the boundary or edge of canvas. If not edge/boundary, recurse with new location
     *
     * Algorithm:
     *  mark location x, y as visited
     *  if x+1, y is not out of bounds or a boundary:
     *      recurse x+1, y
     *  if x-1, y is not out of bounds or a boundary:
     *      recurse with x-1, y
     *  if x, y+1 is not out of bounds or a boundary:
     *      recurse with x, y+1
     *  if x, y-1 is not out of bounds or a boundary:
     *      recurse with x, y-1
     */

    public static void fill(int[][] matrix, int x, int y) {
        /** fill location x, y */
        matrix[x][y] = 1;

        /** expand out */
        if (!isBoundry(matrix, x + 1, y)) {
            fill(matrix, x + 1, y);
        }
        if (!isBoundry(matrix, x - 1, y)) {
            fill(matrix, x - 1, y);
        }
        if (!isBoundry(matrix, x, y + 1)) {
            fill(matrix, x, y + 1);
        }
        if (!isBoundry(matrix, x, y - 1)) {
            fill(matrix, x, y - 1);
        }
    }

    public static boolean isBoundry(int[][] matrix, int x, int y) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (x < rows && y < cols && x >= 0 && y >= 0 && matrix[x][y] == 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] canvas = new int[][] {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0}};

        Logger.log("Before fill: ");
        Logger.log2DArray(canvas);
        fill(canvas, 2, 2);
        Logger.log("After fill: ");
        Logger.log2DArray(canvas);
    }

}
