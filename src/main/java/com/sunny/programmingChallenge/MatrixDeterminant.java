package com.sunny.programmingChallenge;

import com.sunny.common.Logger;

/**
 * Implement a method to calculate the determinant of a square matrix
 *
 *
[[a,b],
[c,d]]
det = a*b - c*d

[[a,b,c],
[d,e,f],
[g,h,i]
]
a *det([[e,f],[h,i]) - b * det([[d,f], [g,i]])+ c * det([[d,e],[g,h]])

# assert(
#     det([
#         [1, 2],
#         [3, 4]
#     ]) == -2
# )
# assert(
#     det([
#         [1, 2, 3],
#         [4, 5, 6],
#         [7, 8, 9]
#     ]) == 0
# )
# assert(
#     det(
#         [[1, 2, 3, 4],
#         [5, 6, 7, 8],
#         [9, 10, 11, 12],
#         [13, 14, 15, 16]]
#     ) == 0
# )
# assert(
#     det(
#         [[5, 7, 8, 13],
#         [12, 22, 12, 29],
#         [93, 7, 1, 88],
#         [18, 3, 77, 9]
#     ]) == 484147
# )
 */
public class MatrixDeterminant {
    /**
     *
     * @param matrix
     * @return
     */
     public static int findDeterminant(int[][] matrix) {
        // check for valid matrix
        // base case where we end up with 2*2 matrix
        if(matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }

        int currentDeterminant = 0;

        for(int i = 0; i < matrix[0].length; i++) {
            int sign = (i%2) == 0 ? 1:-1;
            int currentInt = matrix[0][i];
            currentDeterminant += currentInt * sign * findDeterminant(getSubDeterminantMatrix(matrix, 0, i));
        }

        return currentDeterminant;

    }

    /**
     * For the matrix
     *  1, 2, 3
     *  4, 5, 6
     *  7, 8, 9
     *
     *  sub determinant matrix of an element is remainder of the matrix when row and column of that element is omitted
     *   sub matrix of 1 is
     *      [5, 6],
     *      [8, 9]
     *  sub matrix of 2 is
     *      [4, 6],
     *      [7, 9]
     *  sub matrix of 3 is
     *      [4, 5],
     *      [7, 8]
     *
     * @param matrix
     * @param row
     * @param col
     * @return
     */
    public static int[][] getSubDeterminantMatrix(int[][] matrix, int row, int col) {
        /** return masked matrix will have dimensions reduced by 1 */
        int[][] newMatrix = new int[matrix.length-1][matrix.length-1];

        /** Iterate through each element */
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                /** skip all elements in row and columns of the element for which we are trying to find the determinant
                 * sum matrix
                 */
                if(i == row || j == col) {
                    continue;
                }
                /** we need to map the current element from nxn to (n-1)x(n-1) matrix
                 * for sub matrix of 1,
                 *      [5, 6], 5 will have to be mapped from (1,1) -> (0,0)
                 *      [8, 9], 6 will have to be mapped from (1,2) -> (0,1)
                 *              8 will have to be mapped from (2,1) -> (1,0)
                 *              9 will have to be mapped from (2,2) -> (1,1)
                 */
                int curRow = i;
                int curCol = j;
                /** remap index of new row, each element will have to be shifted up */
                curRow = i-1;
                /** shift the col left by 1 only if the current column is to the right of the element for which we are
                 * trying to find the determinant. For example
                 *  sub matrix of 2 is
                 *      [4, 6], column [4, 7] are already to the left of the column that 2 is in, so no need to remap
                 *      [7, 9], column [6, 9] needs to be mapped to the left, hence the remap
                 *
                 */
                if(j > col) {
                    curCol = j - 1;
                }

                newMatrix[curRow][curCol] = matrix[i][j];
            }
        }

        return newMatrix;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, 7, 8, 13}, {12, 22, 12, 29}, {93, 7, 1, 88}, {18, 3, 77, 9}};
        Logger.log("Determinant is " + findDeterminant(matrix));
    }
}
