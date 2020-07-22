package pcreekprobs;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 *  The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *  The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *  Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *  In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *  Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *  For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 *  -2 (K)	 -3	    3
 *  -5	    -10	    1
 *  10	     30	   -5 (P)
 *  Notes:
 *      The knight's health has no upper bound.
 *      Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 * Links:
 *  https://leetcode.com/problems/dungeon-game/description/
 *  https://www.programcreek.com/2014/03/leetcode-dungeon-game-java/
 *
 */
public class q09_DungeonGame {
    public static void main(String[] args) {
        q09_DungeonGame dg = new q09_DungeonGame();
        int[][] array = new int[][] {
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };
        System.out.println(dg.calculateMinimumHP2(array));
        System.out.println(dg.calculateMinimumHP3(array));
        System.out.println(dg.calculateMinimumHP1(array));
    }

    /**
     * Approach:
     *  In-place dp
     *
     * Links:
     *  https://leetcode.com/problems/dungeon-game/discuss/52805/Best-solution-I-have-found-with-explanations/53849
     *
     * Complexity:
     *  Time is O(mn)
     *  Space is O(1)
     */
    public int calculateMinimumHP1(int[][] dungeon) {
        int row=dungeon.length;
        int col=dungeon[0].length;

        for(int i=row-1; i>=0; i--) {
            for(int j=col-1; j>=0; j--) {
                if(i==row-1 && j==col-1) dungeon[i][j] = Math.max(1, 1-dungeon[i][j]);
                else if(i==row-1) dungeon[i][j] = Math.max(1, dungeon[i][j+1]-dungeon[i][j]);
                else if(j==col-1) dungeon[i][j] = Math.max(1, dungeon[i+1][j]-dungeon[i][j]);
                else dungeon[i][j]= Math.max(1, Math.min(dungeon[i+1][j], dungeon[i][j+1]) - dungeon[i][j]);
            }
        }

        return dungeon[0][0];
    }

    /**
     * Approach: DP with Rolling array
     *  Update from right to left and from bottom up.
     *
     * Links:
     *  https://leetcode.com/problems/dungeon-game/discuss/52887/Sharing-my-solution-with-O(n)-space-O(mn)-runtime
     *
     * Complexity:
     *  Time is O(nm)
     *  Space is O(n)
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        int rows=dungeon.length, cols=dungeon[0].length;

        int[] dp = new int[cols+1];
        dp[cols] = Integer.MAX_VALUE;
        dp[cols - 1] = 1;

        for (int i=rows-1; i>=0; i--) {
            for (int j=cols-1; j>=0; j--) {
                dp[j] = Math.max (1, Math.min(dp[j], dp[j+1]) - dungeon[i][j]);
            }
        }
        return dp[0];
    }

    /**
     * Approach:
     *  Use minInitHealth[i][j] to store the min health needed at position (i, j), then do the calculation from right-bottom to left-up.
     *  Note: adding dummy row and column would make the code cleaner.
     *  But we need to be careful when initializing the extra row and column, everything is initialized to Infinite
     *  except cell (m, n - 1) and (m - 1, n), which should be initialized to 1.
     *
     * Explanation:
     *  https://leetcode.com/problems/dungeon-game/discuss/52826/a-very-clean-and-intuitive-solution-with-explanation
     *
     * Links:
     *  https://leetcode.com/problems/dungeon-game/discuss/52774/C++-DP-solution
     *  https://leetcode.com/problems/dungeon-game/discuss/52790/My-AC-Java-Version-Suggestions-are-welcome
     *
     * Complexity:
     *  Time is O(nm)
     *  Space is O(nm)
     */
    public int calculateMinimumHP3(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }

        int rows=dungeon.length, cols = dungeon[0].length;

        // minHPReqd[i][j] represents the min health point needed before he enters position (i, j)
        int[][] minHPReqd = new int[rows+1][cols+1];

        //Initial condition - these are down and right steps from rows-1,cols-1
        for (int i = 0; i < rows + 1; i++) {
            minHPReqd[i][cols] = Integer.MAX_VALUE;
        }
        for (int j = 0; j < cols + 1; j++) {
            minHPReqd[rows][j] = Integer.MAX_VALUE;
        }
        minHPReqd[rows][cols - 1] = 1;
        minHPReqd[rows - 1][cols] = 1;

        //We start from the end Because it is known that the knight has to reach the end with at least one health point
        //and the health point with which the knight should start with is not known
        for (int row=rows-1; row >= 0; row--) {
            for (int col=cols-1; col >= 0; col--) {
                //We know what is the health point required in next step. If we have a -ve value in dungeon at this step,
                //we subtract this -ve value from the health point in next step which basically adds both the values
                //And give the health point required at this step to move to the next step
                int need = Math.min(minHPReqd[row + 1][col], minHPReqd[row][col + 1]) - dungeon[row][col];
                minHPReqd[row][col] = Math.max(need, 1);
            }
        }

        return minHPReqd[0][0];
    }

}
