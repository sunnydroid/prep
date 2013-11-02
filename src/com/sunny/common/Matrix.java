package com.sunny.common;

public class Matrix {

	public static int[][] buildMatrix(int row, int col) {
		int[][] mat = new int[row][col];
		int num = 0;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				mat[i][j] = num;
				num++;
			}
		}
		
		return mat;
	}
}
