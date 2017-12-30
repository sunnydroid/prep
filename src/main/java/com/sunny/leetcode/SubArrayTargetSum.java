package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given a non-empty array numbers contains positive integers and a positive integer target.
 * Find the first subarray in nums that sums up to target and return the begin and end index
 * of this subarray. If there is no such subarray, return [-1, -1].
 *
 * For example:
 *
 * input: nums=[4, 3, 5, 7, 8], target = 12
 * return: [0, 2]
 * explain: 4 + 3 + 5 = 12. Although 5 + 7 = 12, [4, 3, 5] is the first subarray that sums up to 12.
 *
 * input: nums[1, 2, 3, 4], target = 15
 *
 * return: [-1, -1]
 * explain: There is no such subarray that sums up to 15.
 */
public class SubArrayTargetSum {

    /**
     * Approach:
     *  The brute force approach can produce a solution in O(n^2) time. This can be improved on with slight
     *  modification to scan sliding window both forward and backwards.
     *
     *  Search begins at index i = 0 and advances secondary index j=i+1 until the target is found or missed
     *  Rather than incrementing the index i and restarting sub array search if the sum exceeds the target as in
     *  the brute force approach, start subtracting values from the beginning of the subarray until sum is equals
     *  to or less than target. If sum is less than target, stop subtracting and continue adding to sub array search
     *  from the right index.
     *  e.g. given input [4, 3, 5, 6, 9] and target 15:
     *      - 4 + 3 + 5 + 6 =  18
     *      - sum exceeds target, begin removing index from the beginning
     *      - 3 + 5 + 6 = 14
     *      - sum is less than target, incorporate next element if any from the right
     *      - 3 + 5 + 6 + 9 = 23
     *      - sum exceeds target, begin removing index from the beginning
     *      - 5 + 6 + 9 = 20
     *      - sum exceeds target, remove the next element from the beginning
     *      - 6 + 9 = 15
     *      - target found, return indices.
     *
     * Algorithm:
     *  sum = 0
     *  i = 0, j = 0
     *  sum = sum + num[i]
     *  if sum equals target, return {i, j}
     *  j = i + 1
     *  while index j less than number of elements:
     *      sum = sum + num[j]
     *      if sum equals target:
     *          return {i, j}
     *      if sum less than target:
     *          // expand window size to the right
     *          j = j + 1
     *      if sum greater than target:
     *          // reduce window size from the left
     *          while sum greater than target:
     *              sum = sum - num[i]
     *              i++
     *              if sum equals target :
     *                  return {i, j}
     *          j++
     *  sub array not found:
     *     return {-1, -1}
     *
     * Runtime: O(n)
     * Space: O(1)
     *
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] findTargetSum(int[] numbers, int target) {
        int sum = 0, i = 0, j = 0;
        /** check if first element is the target */
        sum = sum + numbers[i];
        /** keep track of number of loops */
        int iterations = 0;
        if (sum == target) {
            return new int[] {i, j, iterations};
        }
        /** expand window to the right with j */
        j = i+1;
        while (j < numbers.length) {
            iterations++;
            sum = sum + numbers[j];
            if (sum == target) {
                return new int[]{i, j, iterations};
            }
            if (sum < target) {
                j++;
                continue;
            }
            while (sum > target) {
                iterations++;
                sum = sum - numbers[i];
                /** retract window from the left with i */
                i++;
                if (sum == target) {
                    return new int[]{i, j, iterations};
                }
            }
            j++;
        }

        /** target sum not found in sub array */
        return new int[]{-1, -1, iterations};
    }

    public static int[] findTargetSum2(int[] nums, int target) {
        int startIndex, endIndex, sum;
        startIndex = 0;
        endIndex = 0;
        sum = nums[startIndex];

        while (endIndex < nums.length) {
            if (sum == target) {
                return new int[]{startIndex, endIndex};
            }

            while (endIndex < nums.length && sum < target) {
                sum += nums[endIndex];
                endIndex++;
            }
            while (startIndex < nums.length && sum > target) {
                sum -= nums[startIndex];
                startIndex++;
            }
            /**
             * handle case for when startIndex is greater than endIndex because very first element
             * is greater than the target
             */
//            if (endIndex < startIndex) {
//                endIndex = startIndex;
//            }
        }

        return new int[] {-1, -1};
    }


    public static void main(String[] args) {
        int[] testArray = {12, 13, 11, 5, 6, 9};
        int[] subArray = findTargetSum2(testArray, 11);

        Logger.logArray(subArray);
    }
}
