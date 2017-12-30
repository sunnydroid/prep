package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {

    /**
     * Approach: We have to maximize the area that can be formed between the vertical lines using the shorter line as
     *  length and the distance between the lines as the width of the rectangle forming the area. The two variables
     *  that affect the area are width and height, therefore we have to find the combination of width and height that
     *  will give us the max area. Using a two pointer approach, one starting from each end of the array will allow
     *  us to track width while moving the left pointer forwards or the right pointer backwards depending on the height
     *  of the current container will help find the containers combinations with the max heights and hence max area.
     *  Begin left pointer at beginning of array, right pointer at end of array until the two pointers meet. Calculate
     *  area and keep track of max area at each iteration. If left pointer height is less than or equals to the right
     *  pointer height move left pointer forwards else move right pointer backwards.
     *
     * Algorithm:
     *  max area -> 0
     *  left pointer -> 0
     *  right pointer -> array length - 1
     *  while left pointer is less than right pointer:
     *      height1 -> value at left pointer
     *      height2 -> value at right pointer
     *      current area = Min(height1, height2) * distance between pointer 1 and pointer 2
     *      if current area is greater than max area:
     *          max area = current area
     *      if left pointer height is less than or equals to the right pointer height
     *          increment left pointer
     *      else:
     *          decrement right pointer
     *
     *  return max area
     *
     *  Space - O(1)
     *  Time - O(n)
     *
     * @param containers
     * @return
     */
    public static int containerWithMostWater(int[] containers) {
        int maxArea = 0;
        if (containers == null) {
            return maxArea;
        }
        int leftPointer, rightPointer;

        leftPointer = 0;
        rightPointer = containers.length - 1;

        while (leftPointer < rightPointer) {
            int currentArea = Math.min(containers[leftPointer], containers[rightPointer]) * (rightPointer - leftPointer);

            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            if (containers[leftPointer] < containers[rightPointer]) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] containerHeights = new int[]{2, 8, 1, 8, 2};
        Logger.log("Container with most water has area of " + containerWithMostWater(containerHeights));
    }
}
