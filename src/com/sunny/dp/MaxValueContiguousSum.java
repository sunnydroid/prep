package com.sunny.dp;

import com.sunny.common.Logger;

/*
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
		maxSubSequence2(input);
	}
	/*
	 * Algorithm: maintain a max sum, starting index for the max sum and ending index for the max sum
	 * If current element adds to overall sum, include it. If max sum is less than current element, set max
	 * sum to the value of current element and continue
	 * 
	 * If max sum is not less than the current element but current sum is less than max sum, keep moving
	 */

	public static void maxSubSequence(int[] input) {

		int maxSumStartIndex = 0;
		int maxSumStopIndex = 0;
		int maxSum = input[0];
		
		int currentMaxSum = maxSum;
		for(int i = 1; i < input.length; i++) {
			currentMaxSum += input[i]; 
			
			/* we could have done without the previous input as max sum is < element at current
			 * index e.g [-7, 1, 2]
			 */
			 if((maxSum + input[i]) <= input[i]) {
			 	maxSum = input[i];
			 	maxSumStartIndex = i;
			 	maxSumStopIndex = i;
			 	currentMaxSum = maxSum;
			 }
			
			/* element at current index added to total sum */
			if(currentMaxSum > maxSum) {
				maxSum = currentMaxSum;
				maxSumStopIndex = i;
			}
		}
		
		Logger.log("Max Sum : " + maxSum);
		for(int j = maxSumStartIndex; j <= maxSumStopIndex; j++) {
			Logger.log(input[j] + ", ");
		}
	}
	
	public static void maxSubSequence2(int[] input) {
		/*
		 * You are trying to find the max sum from index i to j 
		 * Using dynamic programming, extend the previous window or start a new window, i.e. only 
		 * 2 cases. Runtime O(n), you have n smaller problems each of which takes 1 time.
		 * M(j) = max{M(j-1) + A[j], A[j]}
		 */
		int start = 0;
		int end = 0;
		int maxSum = input[0];
		
		for(int i = 0; i < input.length; i++) {
			if(maxSum + input[i] > input[i]) {
				/* extend the window and include ith element as part of sequence */
				maxSum += input[i];
				end = i;
			} else {
				/* start a new window */
				maxSum = input[i];
				start = i;
				end = i;
			}
		}
		
		Logger.log("Max Sum : " + maxSum);
		for(int j = start; j <= end; j++) {
			Logger.log(input[j] + ", ");
		}
	}

}
