package combination;

/**
 * Problem:
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 * This question is same as CombinationSum1 but just print no of combinations instead of values
 * <p>
 * This problem is similar to Coin Change. It's a typical dynamic programming problem.
 * <p>
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2) or (2, 1, 1) or (1, 2, 1)
 * (1, 3) or (3, 1)
 * (2, 2)
 *
 * Links:
 *  https://leetcode.com/problems/coin-change-2
 *  https://leetcode.com/problems/combination-sum-iv
 *  http://www.programcreek.com/2014/07/leetcode-combination-sum-iv-java/
 *  Solution 1 (DP) - https://www.youtube.com/watch?v=_fgjrs570YE
 *  Solution 2 (Space optimized DP) - https://www.youtube.com/watch?v=jaNZ83Q3QGc
 *  Solution 3 (Recursion) - http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 *
 * Complexity:
 *  Solution 1 - O(nm) Time and O(nm) Space
 *  Solution 2 - O(nm) Time and O(m) Space
 *  Solution 3 -
 */
public class CombinationSum4_DP_REC {

    public static void main(String[] args) {
        CombinationSum4_DP_REC a = new CombinationSum4_DP_REC();

        //Input 1
        int[] source1 = {1, 2, 5};
        int totalAmount = 5;
        System.out.println(a.dp1(source1, totalAmount));
        System.out.println(a.dp2(source1, totalAmount));
        System.out.println(a.recursion(source1, source1.length, totalAmount));
        System.out.println();

        //Input 2
        totalAmount = 3;
        int[] source2 = {2};
        System.out.println(a.dp1(source2, totalAmount));
        System.out.println(a.dp2(source2, totalAmount));
        System.out.println(a.recursion(source2, source2.length, totalAmount));
        System.out.println();

        //Input 3
        totalAmount = 10;
        int[] source3 = {10};
        System.out.println(a.dp1(source3, totalAmount));
        System.out.println(a.dp2(source3, totalAmount));
        System.out.println(a.recursion(source3, source3.length, totalAmount));
    }

    /**
     * Regular Dynamic Programming solution
     */
    public int dp1(int[] coinsArray, int totalAmt) {
        if (coinsArray == null || coinsArray.length == 0) return 0;

        //1. Increasing the coins index by 1 so that formula in for loop is readable
        //This 0 index is not used in calculation of combination - just added for convenience.
        int[] coins = new int[coinsArray.length + 1];
        System.arraycopy(coinsArray, 0, coins, 1, coinsArray.length);

        //2. Always the combinations array will have coins columns and total amount + 1 rows
        int[][] combinations = new int[coinsArray.length + 1][totalAmt + 1];

        //3. Initialize the value for 0 total amount - number of ways to achieve 0 is 1 [choosing no coins]
        for (int i = 0; i < coins.length; i++)
            combinations[i][0] = 1;

        //4. Apply the DP formula
        for (int i = 1; i <= coinsArray.length; i++) {
            for (int amount = 1; amount <= totalAmt; amount++) {
                if (coins[i] <= amount) { //Only if the coin is less than amount, it can participate in making this amount
                    combinations[i][amount] = combinations[i - 1][amount] + combinations[i][amount - coins[i]];
                } else {
                    //if the coin chosen is more than the required amount, this coin CANNOT be part of the combination.
                    //so, get the value from previous coin
                    combinations[i][amount] = combinations[i - 1][amount];
                }
            }
        }

        //printGrid(combinations, coins.length, totalAmt + 1);
        return combinations[coinsArray.length][totalAmt];
    }

    /**
     * Space efficient DP solution
     * If you look at above solution, in each step, the value gets changed only if the amount >= coin value
     * Otherwise, previous values are inherited - this means, we need to change array values only when amount >= coin value
     * Otherwise, array values stay the same (this step is same as else condition in prev solution - inheriting value from previous coin)
     */
    public int dp2(int[] coins, int totalAmt) {
        if (coins == null || coins.length == 0) return 0;

        int[] combinations = new int[totalAmt + 1]; //goes from 0 to totalAmt
        combinations[0] = 1; //No of ways to achieve 0 coins is 1 which is not to select any coins

        for (int i = 0; i < coins.length; i++) {
            for (int amount = 1; amount <= totalAmt; amount++) { //Note: Amount starts from 1
                if (coins[i] <= amount)
                    combinations[amount] = combinations[amount] + combinations[amount - coins[i]];
                //System.out.println(Arrays.toString(combinations));
            }
        }

        /* IF YOU ARE ASKED TO FIND TOTAL COMBINATION OF ANY ELEMENT IN THE ARRAY, JUST RETURN VALUE OF THAT INDEX*/
        return combinations[totalAmt];
    }

    /**
     * Implement the same in recursion
     * Combination Sum = Combination sum for n-1 coins for Amount m + Combination sum for nth coin for Amount m - coin value of n-1
     */
    public int recursion(int[] coins, int noOfCoins, int totalAmt) {
        // If totalAmt is 0 then there is 1 solution (do not include any coin)
        if (totalAmt==0)
            return 1;

        // If totalAmt is less than 0 then no solution exists
        if (totalAmt < 0)
            return 0;

        // If there are no coins and totalAmt is greater than 0, then no solution exist
        if (noOfCoins==0 && totalAmt>=1)
            return 0;

        // count is sum of solutions (i) including CombinationsOfArray[noOfCoins-1] (ii) excluding CombinationsOfArray[noOfCoins-1]
        return recursion(coins, noOfCoins-1, totalAmt) +
                recursion(coins, noOfCoins, totalAmt-coins[noOfCoins-1]);
    }

    public void printGrid(int[][] a, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%5d ", a[i][j]);
            }
            System.out.println();
        }
    }
}
