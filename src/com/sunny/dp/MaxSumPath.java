package com.sunny.dp;

/*
 * Given a matrix, find the max sum for a path starting on the top row and ending at the bottom row. 
 * You can move down, down diagonally left and diagonally right. 
 */
public class MaxSumPath {
	
	/*
	 * The brute force method involves finding every single path from the top row to each 
	 * cell on the bottom row. Assuming you can move in three directions from every cell, this 
	 * yields an O(nxm^3) solution i.e. for every cell you have 3 paths. Each column has m elements, 
	 * i.e. m^3 and n rows.
	 * 
	 * Instead of calculating every single path, we observe that if we begin from the bottom row and
	 * build up, keeping track of the max value between adjacent cells and using that value for the 
	 * set of paths above that row. 
	 */
	
	public static int pathSumMax(int[][] matrix) {
		int max = 0;
		
		return max;
	}
}
