package com.sunny.programmingChallenge;

/**
 * Implement a function to print a pyramid of x of a given height
 *
 *    x    (row=0, x=1, _=3)   3 = (3) ? (0)
 *   xxx   (row=1, x=3, _=2)   2 = (3) ? (1)
 *  xxxxx  (row=2, x=5, _=1)   1 = (3) ? (2)
 * xxxxxxx (row=3, x=7, _=0)
 *
 */
public class BuildPyramid {

    public static void main(String[] args) {
//        printPyramid(0);
//        printPyramid(-1);
        printPyramid(5);
        printPyramid(4);
//        printPyramid(500);
//        printPyramid(1000);
    }

    /**
     * Approach: By looking at the number of xs and spaces, we can derive a pattern for number of xs and spaces
     *  for a height of 4
     *      row 1: 1 x, 3 spaces
     *      row 2: 3 x, 2 spaces
     *      row 4: 5 x, 1 spaces
     *      row 4: 7 x, 0
     *  By observing the pattern above, the number of xs follows a simple arithmetic progression and is dependent on
     *  the current row. Similarly the number of spaces decreases by 1 every row.
     *      number of x = level * 2 + 1
     *          row 0 = 0 x 2 + 1 = 1
     *          row 1 = 1 x 2 + 1 = 3
     *          row 2 = 2 x 2 + 1 = 5
     *          row 3 = 3 x 2 + 1 = 7
     *      number of spaces = max level - current level - 1 (offset for the 0 indexing)
     *          row 0 = 5 - 0 -1 = 4
     *          row 0 = 5 - 1 -1 = 3
     *          row 0 = 5 - 2 -1 = 2
     *          row 0 = 5 - 3 -1 = 1
     *          row 0 = 5 - 4 -1 = 0
     *
     * @param depth
     */
    public static void printPyramid(int depth) {
        int currentDepth = 0;
        while(currentDepth < depth) {
            int xCount = currentDepth * 2 + 1;
            //subtract 1 to account that actual depth is 0 indexed
            int spaceCount = depth - currentDepth - 1;
            System.out.println(getSpacedX(spaceCount, xCount));
            currentDepth++;
        }
    }

    public static String getSpacedX(int spaceCount, int xCount) {
        StringBuilder stringBuilder = new StringBuilder();
        while (spaceCount > 0) {
            stringBuilder.append(" ");
            spaceCount--;
        }

        while(xCount > 0) {
            stringBuilder.append("x");
            xCount--;
        }

        return stringBuilder.toString();
    }
}
