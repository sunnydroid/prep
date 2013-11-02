package com.sunny.intArray;

import com.sunny.common.Logger;
import com.sunny.common.Matrix;

public class IntegerArray {

	public static void main(String[] args) {
		int[] a = { -1, 2, 3, 5};
		int[] b = {4, 6, 7, 8, 9};
		
//		findMinDiff(a, b);
		
		int[][] mat = Matrix.buildMatrix(3, 3);
		Logger.log(" Before rotation " );
		Logger.log2DArray(mat);
		rotate90(mat);
		Logger.log(" After rotation " );
		Logger.log2DArray(mat);
	}
	
	/*
	 * Given a nxn array, rotate it by 90 degrees to the right
	 * 
	 * 1,1 1,2 1,3
	 * 2,1 2,2 2,3
	 * 3,1 3,2 3,3
	 * 
	 * becomes
	 * 
	 * 3,1 2,1 1,1
	 * 3,2 2,2 1,2
	 * 3,3 2,3 1,3
	 * 
	 */
	public static void rotate90(int[][] mat) {
		/* this is implemented by doing a 4 way swap, layer by layer, starting from the outside, working in */
		for(int layer = 0; layer < (mat.length - 1)/2 ; layer++) {
			int first = layer;
			int last = mat.length - 1 - layer;
			/* columns range from first to last. last is one before the last column */
			for(int i = 0; i < last; i++) {
				int offset = first - i; // column offset
				/* save top left */
				int save = mat[first][i];
				/* copy bottom left to top left */
				mat[first][i] = mat[last - offset][first];
				/* copy bottom right to bottm left */
				mat[last-offset][first] = mat[last][last-offset];
				/* copy top right to bottom right */
				mat[last][last-offset] = mat[i][last];
				/* copy back saved */
				mat[i][last] = save;
			}
		}
	}

	/*
	 * You have two arrays of integers, where the integers do not repeat and the
	 * two arrays have no common integers.
	 * 
	 * Let x be any integer in the first array, y any integer in the second.
	 * Find min(Abs(x-y)). That is, find the smallest difference between any of
	 * the integers in the two arrays.
	 * 
	 * Assumptions: Assume both arrays are sorted in ascending order.
	 */
	public static void findMinDiff(int[] a, int[] b) {
		/* keep a min tracker, select a random difference as base */
		int min = a[0] > b[0] ? a[0] - b[0] : b[0] - a[0];
		
		for(int i = 0, j = 0; i < a.length && j < b.length;) {
			/* increment i,j manually */
			if(a[i] <= b[j]) {
				min = Math.min(min, Math.abs(b[j] - a[i]));
				i++;
			} else {
				min = Math.min(min, Math.abs(a[i] - b[j]));
				j++;
			}
		}
		
		Logger.log("Minimum absolute difference = " + min);
	}

	/*
	 * Finds intersection elements in two arrays
	 */
	public static void findCommonElements(int[] a, int[] b) {

	}
}
