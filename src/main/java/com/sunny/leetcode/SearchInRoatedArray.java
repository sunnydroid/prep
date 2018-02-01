package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.Arrays;

public class SearchInRoatedArray {

    /**
     * Approach:
     *  Similar to binary search, but with a twist. Rather than just comparing the value at pivot and target values,
     *  look towards the edges to determine if to select left half or right half of the remaining array.
     *      e.g 1
     *      num[] = {2, 5, 7, 9, 10, 12, 1} and target = 12
     *
     *      hi = 6
     *      lo = 0
     *      mid = 3
     *      num[mid] = 9
     *
     *          num[lo] < num[mid] but num[hi] < num[mid] means lo and mid are on the same side, i.e. follow correct
     *          increasing pattern, therefore:
     *              if target > num[mid]:
     *                  search to the right of mid as all values left of mid will be less than value at mid
     *              else
     *                  search to the left of mid
     *
     *      e.g. 2
     *      num[] = {9, 10, 12, 1, 2, 5, 7} and target = 12
     *
     *      hi = 6
     *      lo = 0
     *      mid = 3
     *      num[mid] = 1
     *
     *          num[lo] > num[mid] but num[hi] > num[mid] means hi and mid are on the same side, i.e. follow correct
     *          increasing pattern, therefore:
     *              if target > num[mid] but target < num[hi]:
     *                  search to the right of mid as all values follow sorted increasing order
     *              else
     *                  search to the left of mid
     *
     * Algorithm:
     *  hi <- input array length - 1
     *  lo <- 0
     *  // lo and high have to cross over before you should stop
     *  while lo is less than or equals to high:
     *      mid = (high + low) / 2
     *      if value at mid equals target:
     *          return mid
     *      if mid and lo are on the same side, i.e. lo < mid but hi < mid as well:
     *          if target is greater than mid:
     *              // look to the right of mid
     *              low = mid + 1
     *          else:
     *              // look to the left of mid
     *              hi = mid
     *      else if:
     *          // mid and hi are on the same side, i.e. mid < hi but lo > mid
     *          if target is greater than mid:
     *              // look to the left of mid
     *              hi = mid
     *          else:
     *              // look to the right of mid
     *              low = mid + 1
     *      else:
     *          // no rotation, update hi, lo as you would in binary search
     *
     *  // not found
     *  return -1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {

        if(nums == null || nums.length == 0) {
            return -1;
        }

        int high = nums.length - 1;
        int low = 0;

        while(low <= high) {

            int mid = (high + low) / 2;

            if(nums[mid] == target) {
                return mid;
            }

            if(nums[mid] >=  nums[low] && nums[high] < nums[mid]) {
                /** numbers to the left of mid are sorted in their natural order since nums[low] < nums[mid] but
                 * nums[high] doesn't
                 */
                if (target > nums[mid]) {
                    /** look to the right of mid as all values to the left are smaller than nums[mid] */
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else if(nums[mid] <= nums[high] && nums[low] > nums[mid]) {
                /** numbers to the right of mid are sorted in their natural order since nums[mid] < nums[high] but
                 * nums[low] doesn't
                 */
                if (target < nums[mid]) {
                    /** look to the left as all values to the right are greater than nums[mid] */
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                /** input array hasn't been rotated, nums[low]<nums[mid]<nums[high] */
                if (target > nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 5, 7, 9, 10, 12, 1};
//        int[] nums = new int[]{9, 10, 12, 1, 2, 5, 7};
//        int[] nums = new int[]{1, 2, 5, 7, 9, 10, 12};
        int[] nums = new int[]{3, 1};
        int searchFor = 1;
        int index = search(nums, searchFor);

        Logger.log("Index of " + searchFor + " in array " + Arrays.toString(nums) + " = " + index);
    }

}
