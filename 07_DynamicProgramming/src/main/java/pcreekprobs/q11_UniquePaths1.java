package pcreekprobs;

import java.util.Arrays;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *  The robot can only move either down or right at any point in time.
 *  The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *  How many possible unique paths are there?
 *  Note: m and n will be at most 100.
 *
 * Links:
 *  https://leetcode.com/problems/unique-paths/description/
 *  https://www.programcreek.com/2014/05/leetcode-unique-paths-java/
 *
 */
public class q11_UniquePaths1 {
    public static void main(String[] args) {
        q11_UniquePaths1 up1 = new q11_UniquePaths1();
        System.out.println(up1.uniquePaths1(3,7));
        System.out.println(up1.uniquePaths2(3,7));
        System.out.println(up1.uniquePaths3(3,7));
    }

    /**
     * Approach:
     *  Since the robot can only move right and down, when it arrives at a point, there are only two possibilities:
     *      It arrives at that point from above (moving down to that point);
     *      It arrives at that point from left (moving right to that point).
     *  So, P[i][j] = P[i - 1][j] + P[i][j - 1]
     *
     *  The boundary conditions of the above equation occur at the leftmost column (P[i][j - 1] does not exist) and the uppermost row (P[i - 1][j] does not exist).
     *  These conditions can be handled by initialization (pre-processing) — initialize P[0][j] = 1, P[i][0] = 1 for all valid i, j.
     *  Note the initial value is 1 instead of 0
     *
     * Explanation:
     *  https://leetcode.com/problems/unique-paths/discuss/22954/0ms-5-lines-DP-Solution-in-C++-with-Explanations
     *
     * Links:
     *  https://leetcode.com/problems/unique-paths/discuss/22954/0ms-5-lines-DP-Solution-in-C++-with-Explanations
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n)
     */
    public int uniquePaths1(int m, int n) {
        if (m > n) return uniquePaths1(n, m);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                dp[j] = dp[j] + dp[j-1];
            }
        }

        return dp[n-1];
    }

    /**
     * Approach: DP with prev array
     *
     * Links:
     *  https://leetcode.com/problems/unique-paths/discuss/22954/0ms-5-lines-DP-Solution-in-C++-with-Explanations
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(n)
     */
    public int uniquePaths2(int m, int n) {
        if (m > n) {
            return uniquePaths2(n, m);
        }

        int[] prev = new int[n];
        Arrays.fill(prev, 1);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            //Swap the two arrays
            int[] temp = dp;
            dp = prev;
            prev = temp;

            for (int j = 1; j < n; j++) {
                dp[j] = prev[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }


    /**
     * Approach 3: Unoptimized DP
     *
     * Links:
     *  https://leetcode.com/problems/unique-paths/discuss/22954/0ms-5-lines-DP-Solution-in-C++-with-Explanations
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(n²)
     */
    public int uniquePaths3(int m, int n) {
        int[][] path = new int[m][n];

        for (int i=0; i<n; i++) {
            path[0][i] = 1;
        }

        for (int i=0; i<m; i++) {
            path[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[m - 1][n - 1];
    }
}

//We start from the end Because it is known that the knight has to reach the end with at least one health point
//and the health point with which the knight should start with is not known

//We know what is the health point required in next step. If we have a -ve value in dungeon at this step,
//we subtract this -ve value from the health point in next step which basically adds both the values
//And give the health point required at this step to move to the next step