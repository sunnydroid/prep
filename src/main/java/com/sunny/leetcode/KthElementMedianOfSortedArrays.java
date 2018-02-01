package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 *
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class KthElementMedianOfSortedArrays {

    /**
     * Find the kth element in the combined list given 2 sorted arrays. You cannot use extra
     * space to come up with a new array and simply pick the kth element in new array
     *
     * Approach:
     *  Use binary search in both sorted arrays to get to correct element. Half the position to be searched for each
     *  iteration, e.g
     *      6th element
     *      6/2 - 1 = 2nd element
     *      2/2 - 1 = 0th element
     *  The start index for each sorted array will be updated accordingly to element at the mid position for that
     *  iteration
     *
     * Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[] respectively.
     * If m1 and m2 both are equal then we are done.
     *  return m1 (or m2)
     * If m1 is greater than m2, then median is present in one of the below two subarrays.
     *  From first element of ar1 to m1 (ar1[0…|_n/2_|])
     *  From m2 to last element of ar2 (ar2[|_n/2_|…n-1])
     * If m2 is greater than m1, then median is present in one of the below two subarrays.
     *  From m1 to last element of ar1 (ar1[|_n/2_|…n-1])
     *  From first element of ar2 to m2 (ar2[0…|_n/2_|])
     * Repeat the above process until size of both the subarrays becomes 2.
     * If size of the two arrays is 2 then use below formula to get the median.
     *  Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
     *
     * Algorithm:
     *  if start location for search is greater than or equals to length of that sorted array:
     *      return start position + (kth -1) element from other array
     *
     *  if k == 1:
     *      return minimum between nums[s1] and nums[s2]
     *
     *  indexToCompare1 = (s1 + k/2) - 1
     *  indexToCompare2 = (s2 + k/2) - 1
     *
     *  // indexToCompare cannot exceed length of the sorted array else, we run into OutOfBoundsException. If
     *  // indexToCompare is greater than array length, value to compare against will me Integer.MAX to force
     *  // us to continue searching in the other sorted array
     *  v1 = nums1[indexToCompare1] if indexToCompare1 < nums1.length else v1 = Integer.MAX
     *  v2 = nums2[indexToCompare2] if indexToCompare2 < nums2.length else v2 = Integer.MAX
     *
     *  if(v1 > v2):
     *      // keep starting position for nums1, change starting position in nums2
     *      recurse getKthElement(k/2, nums1, nums2, s1, indexToCompare2 + 1)
     *  else:
     *      recurse getKthElement(k/2, nums1, nums2, indexToCompare1 + 1, s2)
     *
     * @param k kth element to find
     * @param nums1 sorted array 1
     * @param nums2 sorted array 2
     * @param s1    starting search index for sorted array 1
     * @param s2    starting search index for sorted array 2
     * @return
     */
    public static int getKthElement(int k, int[] nums1, int[] nums2, int s1, int s2) {
        /** if search index for nums1 is greater than length, return element at index k offset by s2 */
        if (s1 >= nums1.length) {
            return nums2[s2 + k - 1];
        }

        if (s2 >= nums2.length) {
            return nums1[s1 + k - 1];
        }

        /** if it is the first element to search for from the start index, return the minimum value from the 2
         * arrays at that index location
         */
        if (k == 1) {
            return Math.min(nums1[s1], nums2[s2]);
        }

        /** determine index position for which to compare values offset by the starting location. Index location to be
         * compared will be k/2 - 1 until we get to the kth element in the correct sorted array
         */
        int indexToCompare1 = s1 + k/2 - 1;
        int indexToCompare2 = s2 + k/2 - 1;

        /** value to compare will be the value of the sorted at the index to compare. If indexToCompare exceeds length
         * the array, force to check the other array by setting this value to a large number
         */
        int valueToCompare1 = indexToCompare1 < nums1.length ? nums1[indexToCompare1] : Integer.MAX_VALUE;
        int valueToCompare2 = indexToCompare2 < nums2.length ? nums2[indexToCompare2] : Integer.MAX_VALUE;

        /** increment start index in the array with the smaller valueToCompare */
        if (valueToCompare1 > valueToCompare2) {
            return getKthElement(k - k / 2, nums1, nums2, s1, indexToCompare2 + 1);
        } else {
            return getKthElement(k - k / 2, nums1, nums2, indexToCompare1 + 1, s2);
        }
    }

    /**
     * Brute force approach of combining the two sorted arrays will take up O(n+m) time which
     * exceeds the constraint we are given O(log(m+n))
     * Approach:
     *  if combined length of the two arrays is even, find middle and middle + 1 elements and return the average
     *  else get the middle element
     *
     * @param arr1  sorted array 1
     * @param arr2  sorted array 2
     * @return  median joined sorted array
     */
    public static double findMedianOfSortedArrays(int[] arr1, int[] arr2) {
        int k = arr1.length + arr2.length;
        if (k % 2 == 0) {
            double center = getKthElement(k / 2, arr1, arr2, 0, 0);
            double center2 = getKthElement(k / 2 + 1, arr1, arr2, 0, 0);
            return (center + center2) / 2;
        } else {
            int center = getKthElement(k / 2 + 1, arr1, arr2, 0, 0);
            return center;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 8};
        int[] arr2 = {3,5,6};
        double median = findMedianOfSortedArrays(arr1, arr2);
        int k = 6;
        int kthElement = getKthElement(k, arr1, arr2, 0, 0);
        Logger.log("Median = " + median);
        Logger.log(k + "th term  = " + kthElement);
    }
}
