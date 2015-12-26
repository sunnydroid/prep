package com.sunny.graphs;

/**
 * Created by sunshah on 3/6/15.
 */
public class FillAlgorithm {
    /**
     * Given a canvas (integer matrix) that has a boundary of a shape and an x, y coordinate,
     * implement a fill method that will fill the enclosed area within the shape
     * void fill(int[][] matrix, int x, int y, int m, int, n)
     * Use the graph flood algorithm
     * Start from x, y, check if neighbors up, down, left and right are part of the boundary or edge of canvas.
     * If not edge/boundry, add that cell to a stack until stack is empty
     * x,y are location of click, m, n are the bounds.
     */

    public void fill(int[][] matrix, int xClick, int yClick, int height, int width) {

        /* validate input */
        if (xClick < 0 || xClick > width || yClick < 0 || yClick > height) {
            log("Input out of bounds" , "Error");
        }

        

    }

    public void log(String message, String level) {
        System.out.println("[" + level + "] = " + message);
    }

    private class Location {

        int x;
        int y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Location)) return false;

            Location location = (Location) o;

            if (x != location.x) return false;
            if (y != location.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
