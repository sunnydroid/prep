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
public class MedianOfSortedArrays {

    /**
     * Brute force approach of combining the two sorted arrays will take up O(n+m) time which
     * exceeds the constraint we are given O(log(m+n))
     * Approach:
     *  There are 2 scenarios: intersecting and no intersecting arrays
     *
     *  If the 2 arrays do not intersect, it is a simple case of finding the combined length
     *  and selecting the elements from the correct array for the calculated median index/indices
     *      median index = length - 1 / 2 for odd length arrays
     *      median indices = length/2 and length/2 - 1 -> median = average of the 2 values
     *
     *  If the arrays intersect, we can attempt to find the median without explicitly determining
     *  the combined list, i.e. indirectly. The median value of the two sorted arrays has to satisfy the following rules:
     *      value at index is greater than or equal to value on left of index in both arrays
     *      value at index is less than or equal to value on right of index in both arrays
     *
     *      Find median individually of the 2 sorted arrays.
     *          if m + n = even
     *              if medians are same - that is the median
     *              if different decrement index for higher median and increment index for lower
     *              median until value of index satisfies the median criteria defined above
     *                  take the average of value at that index and the index next to it that satisfies the
     *                  above criteria.
     *
     *          if m + n = odd
     *              if medians are same - that is the median
     *              if different, decrement index for higher median and increment index for lower
     *              median until value of index satisfies median criteria
     *
     * Algorithm:
     *  Determine intersecting or non intersecting:
     *      if biggest value in array 1 is < less than smallest value in array2 or vice versa, arrays are
     *      non intersecting
     *
     *  if non intersecting:
     *      find combined length of arrays
     *          if even, indices of median = total_lenght/2 and total_length/2 - 1
     *          if odd, index of median = (total_length - 1) / 2
     *
     *          if length of smaller array < median index
     *              median index = length of smaller array - median index
     *              return value at new median index from bigger array
     *          else
     *              return value at median from smaller array
     *
     *  else:
     *      determine median for both arrays.
     *      if medians are the same, return value
     *      else
     *          increase index of lower median and decrease index of bigger median until the following
     *          conditions are satisfied
     *              value to the left of current index is lower or equals to value at current index in both arrays
     *              value to the right of current index is higher or equals to the value at current index in both arrays
     *
     *          if combined length is even
     *              get average of values at current index and previous index (this could be in a different array)
     *          else
     *              return value at current index
     *
     * @param arr1  sorted array 1
     * @param arr2  sorted array 2
     * @return  median joined sorted array
     */
    public static double findMedianOfSortedArrays(int[] arr1, int[] arr2) {
        /** handle base cases */
        if (arr1 == null || arr1.length == 0) {
            if (arr2 == null || arr2.length == 0) {
                throw new IllegalArgumentException("both arrays cannot be empty");
            }
            else {
                return getMedian(arr2);
            }
        }

        if (arr2 == null || arr2.length == 0) {
            return getMedian(arr1);
        }

        int combinedLength = arr1.length + arr2.length;
        boolean evenCombinedLength = ((arr1.length + arr2.length) % 2 == 0);
        int[] smallArray, bigArray;
        int bigMedianIndex, smallMedianIndex;

        /** case 1, where arrays are non intersecting */
        if(arr2[0] > arr1[arr1.length -1] || arr1[0] > arr2[arr2.length - 1]) {
            /** determine smaller/bigger array */
            if (arr1[0] > arr2[arr2.length - 1]) {
                smallArray = arr2;
                bigArray = arr1;
            } else {
                smallArray = arr1;
                bigArray = arr2;
            }
            /** determine median based on even/odd length  */
            if(evenCombinedLength) {
                /** median is the average of values at indices n/2 and n/2 - 1 */
               int median1Index = combinedLength/2;
               int median2Index = median1Index - 1;
               int median1, median2;

               /** determine which array the median indices fall in if the arrays were combined */
               if (smallArray.length <= median1Index) {
                    median1 = bigArray[median1Index - smallArray.length];
               } else {
                    median1 = smallArray[median1Index];
               }
               if (smallArray.length <= median2Index) {
                    median2 = bigArray[median2Index - smallArray.length];
               } else {
                    median2 = smallArray[median2Index];
               }

               /** convert median values to double before average operation to preserve decimal */
               return ((double) median1 + (double) median2) / 2;

            } else {
                int medianIndex = (combinedLength - 1) / 2;
                if (smallArray.length <= medianIndex) {
                    return bigArray[medianIndex - smallArray.length];
                } else {
                    return smallArray[medianIndex];
                }
            }
        } else {
            /** arrays intersect */
            double median1, median2;
            int median1Index, median2Index;

            /** find median of each array. If even length, use smaller median index */
            if (arr1.length % 2 == 0) {
                median1 = ((double) arr1[arr1.length/2] + (double) arr1[arr1.length/2 -1])/2;
                /** return the smaller median index as a starting point when determining index */
                median1Index = arr1.length/2 - 1;
            } else {
                median1Index = (arr1.length - 1)/2;
                median1 = arr1[median1Index];
            }

            if (arr2.length % 2 == 0) {
                median2 = ((double) arr2[arr2.length/2] + (double) arr2[arr2.length/2 -1])/2;
                median2Index = arr2.length/2 - 1;
            } else {
                median2Index = (arr2.length - 1)/2;
                median2 = arr2[median2Index];
            }

            /** if median values are equal, that is the median */
            if ((median1 - median2 < 0.1) && (median2 - median2 < 0.1)) {
                return median1;
            }

            /** determine which array has bigger/smaller median value so as to determine which direction
             * to advance/retract the index
             */

            if (median1 < median2) {
                smallArray = arr1;
                bigArray = arr2;
                smallMedianIndex = median1Index;
                bigMedianIndex = median2Index;
            } else {
                smallArray = arr2;
                bigArray = arr1;
                smallMedianIndex = median2Index;
                bigMedianIndex = median1Index;
            }

            int medianIndex = 0;
            int[] medianArray = null;

            /** advance medianIndices for both arrays in their corresponding directions */
            while(smallMedianIndex < smallArray.length && bigMedianIndex > 0) {
                /** Test for median condition that satisfies both arrays */
                if(smallArray[smallMedianIndex] >= bigArray[bigMedianIndex - 1] && smallArray[smallMedianIndex] <= bigArray[bigMedianIndex]) {
                    medianIndex = smallMedianIndex;
                    medianArray = smallArray;
                    break;
                }

                if (bigArray[bigMedianIndex] >= smallArray[smallMedianIndex] && bigArray[bigMedianIndex] <= smallArray[smallMedianIndex + 1]) {
                    medianIndex = bigMedianIndex;
                    medianArray = bigArray;
                    break;
                }
                smallMedianIndex++;
                bigMedianIndex--;
            }

            /** now that we have determined the median index and which array it is in, determine median index based on combined array length */

            if (evenCombinedLength) {
                return ((double) medianArray[medianIndex] + (double) medianArray[medianIndex - 1]) / 2;
            } else {
                return medianArray[medianIndex];
            }
        }

    }

    public static int getMedian(int[] arr) {
        if (arr.length % 2 == 0) {
            return (arr[arr.length/2] + arr[arr.length/2 - 1])/2;
        }
        return arr[(arr.length - 1)/2];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4};
        int[] arr2 = {3,5,6};
        double median = findMedianOfSortedArrays(arr1, arr2);
        Logger.log("Median = " + median);
    }
}
