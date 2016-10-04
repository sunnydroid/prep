package com.sunny.matrices;

import com.sunny.common.Logger;

import java.util.Scanner;

/**
 * Created by sunshah on 9/1/16.
 */
public class DiagnolDiff {

    public static void main(String[] args) {
        calculateDiagnolDifference();
    }

    public static void calculateDiagnolDifference() {
        /* scanner uses space as the default delimiter */
        Scanner scanner = new Scanner(System.in);
        int dimension;
        Logger.log("Input square matrix dimension");
        dimension = scanner.nextInt();
        Logger.log("Input matrix row by row, each element separated by space and each row separated by a newline");

        int[][] matrix = new int[dimension][dimension];

        /* iterate through all input to build matrix */
        for (int i = 0; i < dimension; i++) {
            int[] row = new int[dimension];
            for (int j = 0; j < dimension; j++) {
                row[j] = scanner.nextInt();
            }
            matrix[i] = row;
        }

        int mainDiagonalSum = 0;
        int inverseDiagonalSum = 0;

        for(int k = 0; k < dimension; k++) {
            mainDiagonalSum += matrix[k][k];
            /* make dimension zero index by subtracting 1  */
            inverseDiagonalSum += matrix[dimension - 1 - k][k];
        }

        Logger.log("Diagonal difference = " + Math.abs(mainDiagonalSum - inverseDiagonalSum));

//        Logger.log2DArray(matrix);
    }
}
