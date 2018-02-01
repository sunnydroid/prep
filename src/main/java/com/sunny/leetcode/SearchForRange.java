package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.Arrays;

/**
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class SearchForRange {

    /**
     * Approach - We need to first determine if the target is present in the array. If it is, we can recursively, using
     *  binary search and the first found location to find the upper and lower start ranges.
     *  Find the first index of the target. This will be the start and end index for the range. If we find indices lower
     *  than the current start or higher than the end, we update them accordingly.
     *
     * Algorithm:
     *  startIndex <- -1
     *  endIndex <- -1
     *  high = array length - 1
     *  low = 0
     *  mid = binary search with high = length -1, low = 0, target
     *  if mid equals -1:
     *      target is not present, return [-1, -1]
     *  // otherwise have 2 search ranges start to mid-1 and mid+1 to end
     *  low1 = 0
     *  high1 = mid -1
     *  low2 = mid + 1
     *  high2 = length - 1
     *
     *  // find the start index
     *  while true:
     *      mid1 = binary search with low = low1, high = high1
     *      if mid 1 equals - 1:
     *          break
     *      else
     *          startIndex = mid1
     *          // reduce the size of array to search by log(n)
     *          high1 = mid - 1
     *
     *  // find the end index
     *  while true:
     *      mid2 = binary search with low = low2, high = high2
     *      if mid2 equals -1:
     *          break
     *      else
     *          endIndex = mid2
     *          // reduce the size of array to be searched by log(n)
     *          low2 = mid2 + 1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        /** first check if target exists */
        int mid = binarySearch(nums, 0, nums.length - 1, target);
        if(mid == -1){
            /** not found */
            return new int[]{start, end};
        } else {
            /** set start/end range to mid value */
            start = mid;
            end = mid;
        }
        /** recursively perform binary search for lower start index */
        int lowerStart = 0, lowerEnd = mid - 1;
        int upperStart = mid + 1, upperEnd = nums.length - 1;
        while(true) {
            int lowerMid = binarySearch(nums, lowerStart, lowerEnd, target);
            if(lowerMid == -1) {
                break;
            } else {
                // found target, updated lower index range
                start = lowerMid;
                /** search lower half of remaining array */
                lowerEnd = lowerMid - 1;
            }
        }

        while(true) {
            int upperMid = binarySearch(nums, upperStart, upperEnd, target);
            if(upperMid == -1) {
                break;
            } else {
                // found target, update upper index range
                end = upperMid;
                /** search upper half of remaining array */
                upperStart = upperMid + 1;
            }
        }

        return new int[]{start, end};
    }

    public static int binarySearch(int[] nums, int start, int end, int target) {
        if(nums == null || nums.length == 0 || start < 0 || end > nums.length - 1) {
            return -1;
        }

        while(start <= end) {
            int mid = (start + end) / 2;
            if(target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 8, 8, 8, 9,};
        Logger.log(Arrays.toString(searchRange(nums, 8)));
    }
}
