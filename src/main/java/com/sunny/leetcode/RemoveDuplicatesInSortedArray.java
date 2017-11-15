package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example,
 * Given input array nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesInSortedArray {

    /**
     * Approach:
     *  - Have 2 indices, one to track the current location and the other to look ahead until non repeating number
     *  is found.
     *  - Replace the duplicate value with first non repeating number found by look ahead index until look ahead
     *  index reaches the end. Keep track of number of unique numbers found; return that when look ahead index exceeds
     *  array length.
     *
     * Algorithm:
     *  i = 0, j = 1, uniqueNums = 1
     *  while j < array length:
     *      while j < array length AND numbers[i] equals numbers[j]:
     *          j++
     *
     *      // if j has exceeded index length already, all characters are repeated
     *      if j > array length:
     *          return uniqueNumbers
     *
     *      // otherwise copy over first non duplicate number found next to current location of i and repeat. Do this
     *      // only if there has been a duplicate found and j exceeds i by more than 1 distance
     *      if j > i + 1:
     *          numbers[i+1] = numbers[j]
     *      i++, j++
     *
     *  return uniqeNums
     *
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        /**  i tracks current location and number, j looks ahead for duplicates */
        int i = 0, j = 1, uniqueNums = 1;

        while (j < nums.length) {
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }

            /** if j is greater than array length, all characters found by j are duplicates */
            if (j == nums.length) {
                return uniqueNums;
            }

            /** if j is greater than 1 distance away from i, there were duplicates found, copy over the
             * first non duplicate number to location ahead of i
             */
            if (j > i + 1) {
                nums[i+1] = nums[j];
            }
            uniqueNums++;
            i++; j++;
        }

        return uniqueNums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1,1,1,1,1,1,2, 3};
        int nonDuplicateCount = removeDuplicates(nums);
        Logger.log("Non duplicate count = " + nonDuplicateCount);
    }
}
