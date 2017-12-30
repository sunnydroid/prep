package com.sunny.dp;

import com.sunny.common.Logger;

/**
 * Given an array write a function to print continuous subsequences in the array with highest sum.
 * e.g:
 * Input:
 * Array = [-1, -3, 4, 5, 4]
 * output:
 * 4, 5, 4
 */
public class MaxValueContiguousSum {

	public static void main(String[] args) {
		int[] input = {-7, 1, 2, 3, -4, 5};
		maxSubSequence(input);
	}
	/**
	 * Approach:
     *  Maintain a max sum, starting index for the max sum and ending index for the max sum. These two
     *  pointers are updated depending on values of the max sum, current sum and element at current index.
	 *  If current element adds to overall sum, include it. If max sum is less than current element, drop the previous
     *  element and set max sum to the value of current element, update max sum start/end indexes.
	 * 
	 *  If max sum is greater than the current element but current sum is less than max sum, we haven't found a new
     *  max, continue checking
     *
     * This is a dynamic programming problem where you have n smaller problems each of which takes O(1) time.
     *      M(j) = max{M(j-1) + A[j], A[j]}
     *
     * Algorithm:
     *  maxSumStartIndex = 0
     *  maxSumStopIndex = 0
     *  maxSum = input[0];
     *  currentSum = input[0]
     *
     *  for index 1 -> input length:
     *      currenSum -> currentSum + input[i]
     *      if currentSum > maxSum:
     *          maxSum -> currentSum
     *          maxSumStopIndex -> i
     *
     *      if maxSum + input[i] <= input[i]:
     *          // current input is greater than the max, drop the previous elements and start fresh from current element
     *          maxSum -> input[i]
     *          currentSum -> input[i]
     *          maxSumStartIndex -> i;
     *          maxSumEndIndex -> i;
     *
     *      // otherwise max sum is greater than current sum because current element did not contribute to max sum,
     *      // keep looking
     *
     * Runtime = O(N)
     * Space = O(1)
	 */

	public static void maxSubSequence(int[] input) {

		int maxSumStartIndex = 0;
		int maxSumStopIndex = 0;
		int maxSum = input[0];
		
		int currentMaxSum = maxSum;
		for(int i = 1; i < input.length; i++) {
			currentMaxSum += input[i]; 
			
			/** we could have done without the previous input as max sum is < element at current
			 * index e.g [-7, 1, 2] i.e. 1 > -7, drop -7 and update maxSum, start/end indexes
			 */
			 if((maxSum + input[i]) <= input[i]) {
			 	maxSum = input[i];
			 	maxSumStartIndex = i;
			 	maxSumStopIndex = i;
			 	currentMaxSum = maxSum;
			 }
			
			/** element at current index added to total sum, include it in the sub sequence */
			if(currentMaxSum > maxSum) {
				maxSum = currentMaxSum;
				maxSumStopIndex = i;
			}
		}
		
		Logger.log("Max Sum : " + maxSum);
		Logger.log("Sub sequence : ");
		for(int j = maxSumStartIndex; j <= maxSumStopIndex; j++) {
			Logger.logSameLine(input[j] + ", ");
		}
	}
}
