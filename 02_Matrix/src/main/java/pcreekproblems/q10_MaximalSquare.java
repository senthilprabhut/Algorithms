package pcreekproblems;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *  For example, given the following matrix:
 *      1 0 1 0 0
 *      1 0 1 1 1
 *      1 1 1 1 1
 *      1 0 0 1 0
 *  Return 4.
 *
 * Links:
 *  https://leetcode.com/problems/maximal-square/description/
 *  https://www.programcreek.com/2014/06/leetcode-maximal-square-java/
 *  https://www.youtube.com/watch?v=_Lf1looyJMU
 */
public class q10_MaximalSquare {
    public static void main(String[] args) {
        q10_MaximalSquare ms = new q10_MaximalSquare();
        char[][] input = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(ms.maximalSquare1(input));
        System.out.println(ms.maximalSquare2(input));
    }

    /**
     * Approach:
     *  Space efficient DP
     *
     * Links:
     *  https://leetcode.com/problems/maximal-square/discuss/61803/Easy-DP-solution-in-C++-with-detailed-explanations-(8ms-O(n2)-time-and-O(n)-space)
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(n)
     */
    public int maximalSquare1(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int rows = matrix.length, cols=matrix[0].length, result=0;

        int[] dp = new int[cols+1];
        int prev = 0;

        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=cols; j++) {
                int temp = dp[j];
                if(matrix[i-1][j-1] == '1') {
                    dp[j] = 1 + Math.min(dp[j-1], Math.min(prev, dp[j])); //Max of left, diag and top
                    result = Math.max(result, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return result * result;
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/maximal-square/discuss/61802/Extremely-Simple-Java-Solution-:)
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(n²)
     */
    public int maximalSquare2(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int rows = matrix.length, cols=matrix[0].length, result=0;

        int[][] dp = new int[rows+1][cols+1];

        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=cols; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j])); //Max of left, diag and top
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result * result;
    }
}
