package com.sunny.leetcode;

import com.sunny.common.Logger;

/**
 * Find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted
 * in increasing order. For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6
 * and LIS is {10, 22, 33, 50, 60, 80}
 *
 * This is a classic dynamic problem with sub optimal structure. Solution of the previous stage can be used to
 * calculate the next stage
 */
public class LongestIncreasingSubsequence {

    /**
     * Approach - Each element in the array is a sub-sequence. As we increment the index from start to end we need
     * to know how many previous elements are smaller than the current element. For each element before the current
     * index, we have already calculated the number of elements that are smaller in that sub-sequence, therefore the
     * number of elements that are smaller:
     *  - max of previously calculated solution at that index + 1 : if element is bigger
     *  - previously calculated solution for that index : if element is smaller or equals to
     * e.g
     * LIS is an array to track the longest increasing sub sequence till current index, it is initialized to all 1s as
     * each element is a single sub sequence
     *
     *  arr[] : 10  22  9   33  21  50  41  60
     *  LIS[] : 1   1   1   1   1   1   1   1
     *
     *  index = 1, arr[i] = 22; 10 < 22 therefore longest subsequence is MAX(LIS(j) + 1) where j < i i.e. maximum of
     *  previous longest sub-sequence smaller than current element + 1
     *  j 0 -> i-1
     *      arr[j] < 22 ? yes; LIS[i] = MAX(LIS[i], LIS[j] + 1)
     *          arr[0] -> 10 < 22 ? yes; LIS[1] = max(1, 1+1) = 2
     *  arr[] : 10  22  9   33  21  50  41  60
     *  LIS[] : 1   2   1   1   1   1   1   1
     *
     *  index = 2, arr[i] = 9; 9< 10 and 22 therefore current longest subsequence with index 2 is itself
     *      j 0 -> i-1
     *      arr[j] < 9 ? yes; LIS[i] = MAX(LIS[i], LIS[j] + 1)
     *          arr[0] -> 10 < 9 ? no; LIS[2] = LIS[2]
     *          arr[1] -> 22 < 9 ? no; LIS[3] = LIS[2]
     *  arr[] : 10  22  9   33  21  50  41  60
     *  LIS[] : 1   2   1   1   1   1   1   1
     *
     *  index = 3, arr[i] = 33; 10, 22, 9 < 33 therefore current longest subsequence will be calculated as:
     *      j 0 -> i-1
     *      arr[j] < 33 ? yes; LIS[i] = MAX(LIS[i], LIS[j] + 1)
     *          arr[0] -> 10 < 33 ? yes; LIS[3] = max(1, 1+1) = 2
     *          arr[1] -> 22 < 33 ? yes; LIS[3] = max(2, 2+1) = 3
     *          arr[2] -> 9 < 33 ? yes; LIS[3] = max(3, 1+1) = 3
     *  arr[] : 10  22  9   33  21  50  41  60
     *  LIS[] : 1   2   1   3   1   1   1   1
     *  and so on till we have check the longest sub sequence for each index
     *
     *  Final form of LIS is
     *  arr[] : 10  22  9   33  21  50  41  60
     *  LIS[] : 1   2   1   3   2   4   4   5
     *
     * Algorithm:
     *  initialize LIS array to the size of input with all 1s
     *  for index i = 1 -> array.length
     *      for index j = 0 -> i:
     *          if arr[i] > arr[j]
     *              LIS[i] = max(LIS[i], LIS[j] + 1)
     *
     *  return maximum value in LIS
     *
     *  Runtime = O(N^2)
     *  Space = O(N)
     *
     * @param input
     * @return
     */
    public static int findLongestIncreasingSubsequence(int[] input) {
        int[] lcs = new int[input.length];
        /** initialize all sub sequences to 1 since each element is a subsequence on its own */
        for(int i = 0; i < lcs.length; i++) {
            lcs[i] = 1;
        }

        /** start index from 1 and compare size of all previous elements to the current element using secondary
         * index j which runs from 0 to i
         */
        for(int i = 1; i < input.length; i++) {
            for(int j = 0; j < i; j++) {
                /** if current element is greater than previous element, chose the max of longest value of subsequence
                 * at lcs[j] + 1 or value a at lcs[i] which could be from a previous sub sequence
                 */
                if (input[i] > input[j]) {
                    lcs[i] = Math.max(lcs[j] + 1, lcs[i]);
                }
            }
        }

        /** find and return the maximum value in lcs array */
        int maxSubsequence = 1;
        for(int i = 0; i < lcs.length; i++) {
            if (lcs[i] > maxSubsequence) {
                maxSubsequence = lcs[i];
            }
        }

        return maxSubsequence;
    }

    public static void main(String[] args) {
        int[] input = new int[]{10, 22, 9, 33, 21, 50, 41, 60};
        Logger.log("Longest increasing sub sequence = " + findLongestIncreasingSubsequence(input));
    }
}
