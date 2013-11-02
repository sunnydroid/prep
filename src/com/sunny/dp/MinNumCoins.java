package com.sunny.dp;

import com.sunny.common.Logger;

/*
 * Given an array of coins, find the minimum number of coins need to add up to a sum
 */
public class MinNumCoins {

	public static void main(String[] args) {
		int[] coins = {1, 2, 3, 4};
		int targetSum = 10;
		
		int[][] solution = new int[coins.length][targetSum + 1];
		
		for(int i = 0; i < coins.length; i++) {
			int currentCoin = coins[i];
			for(int j = 1; j <= targetSum; j++) {
				int currentTargetSum = j;
				if(currentTargetSum < currentCoin) {
					/* denomination too large to be included in solution, use previous optimal solution */
					if(i == 0) {
						/* no previous optimal solution */
						solution[i][j] = 0;
					} else {
						/* use previous optimal solution at current targetSum */
						solution[i][j] = solution[i-1][j];
					}
				} else {
					/* we can use the current coin, find how many times we can use it, if there is a remainder, find optimal solution 
					 * for the remainder and add it to the number of times we were able to use current coin to find optimal solution 
					 * for current target sum
					 */
					int numCoinsUsed = 0;
					if(currentTargetSum % currentCoin == 0) {
						/* current coin is a multiple of target sum */
						numCoinsUsed =  currentTargetSum / currentCoin;
					} else {
						int remainder = currentTargetSum % currentCoin;
						numCoinsUsed = (currentTargetSum - remainder) / currentCoin;
						/* find optimal solution for the remainder */
						numCoinsUsed += solution[i-1][remainder];
					}
					solution[i][j] = numCoinsUsed;
				}
			}
		}
		
		/* print solutions array */
		Logger.log2DArray(solution);
	}
}
