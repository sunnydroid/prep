package com.sunny.dp;

import com.sunny.common.Logger;

public class NumberOfPaths {
	
	/*
	 * A robot is located at the top-left corner of a m x n grid. 
	 * The robot can only move either down or right at any point in time. 
	 * The robot is trying to reach the bottom-right corner of the grid. 
	 * How many possible unique paths are there?
	 * 
	 * We can use a brute force method to try every possible path using
	 * backtracking
	 */
	
	public static void printAllPaths(int r, int c, int m, int n, String path) {
		if(r < 0 || c < 0 || r > m || c > n) {
			return;
		}
		
		if(r == m && c == n) {
			Logger.log(path);
		}
		
		String pathRight = path + " > right";
		String pathBelow = path + " v down";
		
		printAllPaths(r+1, c, m, n, pathRight);
		printAllPaths(r, c+1, m, n, pathBelow);
		
	}
	
	public static void main(String[] args) {
		printAllPaths(1, 1, 3, 4, "");
	}

}
