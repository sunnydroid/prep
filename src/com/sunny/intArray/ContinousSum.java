package com.sunny.intArray;

import com.sunny.common.Logger;

/*
 * Given an array write a function to print continuous subsequences in the array with highest sum.
 * e.g:
 * Input:
 * Array = [-1, -3, 4, 5, 4]
 * output:
 * 4, 5, 4
 */
public class ContinousSum {

	public static void main(String[] args) {
		int[] input = {-7, 1, 2, 3, -4, 5};
		maxSubSequence(input);
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

}
