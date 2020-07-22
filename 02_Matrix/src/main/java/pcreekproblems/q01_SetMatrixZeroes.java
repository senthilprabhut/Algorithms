package pcreekproblems;

import java.util.Arrays;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 *
 * Links:
 *  https://leetcode.com/problems/set-matrix-zeroes/description/
 *  https://www.programcreek.com/2012/12/leetcode-set-matrix-zeroes-java/
 */
public class q01_SetMatrixZeroes {
    public static void main(String[] args) {
        q01_SetMatrixZeroes sm = new q01_SetMatrixZeroes();
        int[][] matrix = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
//        sm.setZeroes1(matrix);
        sm.setZeroes2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * Approach:
     *  Optimized approach
     *  store states of each row in the first of that row, and store states of each column in the first of that column.
     *  Because the state of row0 and the state of column0 would occupy the same cell, I let it be the state of row0,
     *  and use another variable “col0” for column0.
     *
     *  In the first phase, use matrix elements to set states in a top-down way.
     *  In the second phase, use states to set matrix elements in a bottom-up way.
     *
     * Links:
     *  https://leetcode.com/problems/set-matrix-zeroes/discuss/26014/Any-shorter-O(1)-space-solution
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(1)
     */
    public void setZeroes1(int[][] matrix) {
        int col0=1, rows=matrix.length, cols=matrix[0].length;

        for (int i=0; i<rows; i++) {
            if (matrix[i][0] == 0) col0=0;
            for (int j=1; j<cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) matrix[i][0] = 0;
        }
    }

    /**
     * Approach:
     *  4 for loops
     *
     * Links:
     *  https://leetcode.com/problems/set-matrix-zeroes/discuss/26008/My-AC-java-O(1)-solution-(easy-to-read)
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(1)
     */
    public void setZeroes2(int[][] matrix) {
        boolean firstCol=false, firstRow=false;

        int rows=matrix.length, cols=matrix[0].length;

        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (matrix[i][j] == 0) {
                    if (i==0) firstRow=true;
                    if (j==0) firstCol=true;
                    //Set the flag on the row and col to be set 1s
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i=1; i<rows; i++) {
            for (int j=1; j<cols; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(firstRow) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if(firstCol) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
