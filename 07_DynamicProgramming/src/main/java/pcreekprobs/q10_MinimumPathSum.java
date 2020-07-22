package pcreekprobs;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *  Note: You can only move either down or right at any point in time.
 *  Example 1:
 *      [[1,3,1],
 *      [1,5,1],
 *      [4,2,1]]
 *  Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
 *
 * Links:
 *  https://leetcode.com/problems/minimum-path-sum/discuss/23471/My-java-solution-using-DP-and-no-extra-space
 *  https://www.programcreek.com/2014/05/leetcode-minimum-path-sum-java/
 *  Tushar - https://www.youtube.com/watch?v=lBRtnuxg-gU
 */
public class q10_MinimumPathSum {
    public static void main(String[] args) {
        q10_MinimumPathSum mps = new q10_MinimumPathSum();

        int[][] grid = new int[][] {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(mps.minPathSum(grid));
    }

    /**
     * Approach: DP with no extra space
     *  The first row and column has a special case
     *  The rest of the row,col has the formula  -> Math.min( dp[row][col-1], dp[row-1][col])
     *
     * Links:
     *  https://leetcode.com/problems/minimum-path-sum/discuss/23471/My-java-solution-using-DP-and-no-extra-space
     *
     * Complexity:
     *  Time is O(mn)
     *  Space is O(1)
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        for (int i=0; i<cols; i++) {
            for (int j=0; j<rows; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j-1];
                } else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i-1][j];
                } else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[rows-1][cols-1];
    }

}
