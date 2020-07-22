import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem:
 *  You are given coins of different denominations and a total amount of money.
 *  Write a function to compute the ##"number of combinations"## that make up that amount.
 *  You may assume that you have infinite number of each kind of coin.
 *  Note: You can assume that
 *    0 <= amount <= 5000
 *    1 <= coin <= 5000
 *    the number of coins is less than 500
 *    the answer is guaranteed to fit into signed 32-bit integer
 *  Example 1:
 *    Input: amount = 5, coins = [1, 2, 5]      Output: 4
 *    Explanation: there are four ways to make up the amount:
 *    5=5,  5=2+2+1,  5=2+1+1+1,  5=1+1+1+1+1
 *  Example 2:
 *    Input: amount = 3, coins = [2]
 *    Output: 0
 *    Explanation: the amount of 3 cannot be made up just with coins of 2.
 *  Example 3:
 *    Input: amount = 10, coins = [10]
 *    Output: 1
 *
 * Links:
 *  
 *  http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 *  Solution 1 - https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/Combination.java
 *  Solution 2 - https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/Combination.java
 */
public class CoinChange2 {
    public static void main(String[] args) {
        CoinChange2 cc2 = new CoinChange2();
        int[] coins = {2};
        int amount = 3;
        int noOfCoins = cc2.dp1(coins,amount);
        System.out.printf("Maximum no of combinations from %s to get to amount %d is %d \n", Arrays.toString(coins), amount, noOfCoins);

        int[] coins2 = {1, 2, 5};
        amount = 5;
        System.out.printf("Maximum no of combinations from %s to get to amount %d is %d \n", Arrays.toString(coins2), amount, cc2.dp1(coins2,amount));
    }

    //Check CombinationSum4_DP_REC.java for the space efficient DP implementation
    public int dp1(int[] coins, int totalAmount) {
        //Create a copy of coins with value of index 0 is 0
        //This is done so that the formula in for loop is readable - program works without this array too
        int[] newCoins = new int[coins.length+1];
        System.arraycopy(coins, 0, newCoins, 1, coins.length);

        int[][] dp = new int[coins.length+1][totalAmount+1];
        //3. Initialize the value for 0 total amount - number of ways to achieve 0 is 1 [choosing no coins]
        for (int i = 0; i <= coins.length; i++)
            dp[i][0] = 1;

        for(int i=1; i<=coins.length; i++) {
            for (int amount=1; amount<=totalAmount; amount++) {
                if(newCoins[i] <= amount)
                    dp[i][amount] = dp[i-1][amount] + dp[i][amount-newCoins[i]];
                else
                    dp[i][amount] = dp[i-1][amount];
            }
        }
        return dp[coins.length][totalAmount];
    }
}
