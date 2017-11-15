package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 */
public class ZigZagStringCoding {

    /**
     * Approach: Next letter for each row is dependent on the number or rows above/below current row
     * and the position of the current row. Top and bottom rows will be special cases as there aren't
     * any rows above/below them.
     * Algorithm:
     * Let n = # of rows
     * i = current row
     * j = number of center rows, excluding top and bottom. For n=2, j=0; For n=3, j=1; For n=5, j=3
     * u = number of center rows above current row
     * d = number or center rows below current row
     *  for i = 0 -> n
     *      next index = i
     *      u = i - 1 (first center row is second row, i.e. index 1. index 0 = top row)
     *      d = j - i (last center row is second from last row)
     *      while next index < string length, append to string build character at current index:
     *          case: i = 0 OR i = n-1
     *              next index = i + (2 x j) + 2
     *          case: all other rows
     *              next index = i + (2 x d) + 2 (finding the next index on the down portion of the zig)
     *              next index = i + (2 x u) + 2 (finding the next index on the up portion of the zag)
     *
     * Complexity: Space = O(n) - String to buffer characters, Time = O(n) where each character is visited only once
     * @param text  Text to convert to zigzag pattern
     * @param numRows Number of fows in the zig zag pattern
     * @return  The converted string in zig zag pattern
     */
    public static String convertZigZag(String text, int numRows) {
        /* Default case, where numRows is < 2 or numRows >= number of characters, return original string */
        if (numRows < 2 || numRows >= text.length()) {
            return text;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < numRows; i++) {
            /* find number of middle rows, rows above and below */
            int middleRowsCount = (numRows - 2) < 0 ? 0 : (numRows - 2);
            int numRowsAbove = (i - 1) < 0 ? 0 : (i - 1);
            int numRowsBelow = (middleRowsCount - i) < 0 ? 0 : (middleRowsCount - i);

            int currentIndex = i;
            /* iterate until current index is less than string length */

            /* first or last row */
            if (i == 0 || i == numRows - 1) {
                while (currentIndex < text.length()) {
                    stringBuilder.append(text.charAt(currentIndex));
                    currentIndex = currentIndex + (middleRowsCount * 2) + 2;
                }
            } else {
                while (currentIndex < text.length()) {
                    stringBuilder.append(text.charAt(currentIndex));
                    currentIndex = currentIndex + (numRowsBelow * 2) + 2;
                    if(currentIndex < text.length()) {
                        stringBuilder.append(text.charAt(currentIndex));
                        currentIndex = currentIndex + (numRowsAbove * 2) + 2;
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String zigZagString = convertZigZag("paypalishiring", 20);
        Logger.log(zigZagString);
    }
}
