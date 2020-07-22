package pcreekprobs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  You are given coins of different denominations and a total amount of money amount.
 *  Write a function to compute the fewest number of coins that you need to make up that amount.
 *  If that amount of money cannot be made up by any combination of the coins, return -1.
 *  Example 1:  coins = [1, 2, 5], amount = 11      return 3 (11 = 5 + 5 + 1)
 *  Example 2:  coins = [2], amount = 3             return -1.
 *  Note:   You may assume that you have an infinite number of each kind of coin.
 *
 * Links:
 *  https://leetcode.com/problems/coin-change/description/
 *  https://www.programcreek.com/2015/04/leetcode-coin-change-java/
 *  http://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 */
public class q16_CoinChange1 {
    public static void main(String[] args) {
        q16_CoinChange1 cc = new q16_CoinChange1();

        int[] coin1 = {1, 2, 5};
        int[] coin2 = {2};
        System.out.println(cc.coinChange1(coin1, 11));
        System.out.println(cc.coinChange1(coin2, 3));
        System.out.println("-----");
        System.out.println(cc.coinChange2(coin1, 11));
        System.out.println(cc.coinChange2(coin2, 3));
        System.out.println("-----");
        System.out.println(cc.coinChange3(coin1, 11));
        System.out.println(cc.coinChange3(coin2, 3));
    }


    /**
     * Approach 1: Space Efficient DFS
     *
     * Links:
     *  http://www.programcreek.com/2015/04/leetcode-coin-change-java/
     *
     * Complexity:
     *  Time is O(amount * coins)
     *  Space is O(amount)
     */
    public int coinChange1(int[] coins, int totalAmount) {
        if (coins == null || coins.length == 0 || totalAmount < 0) {
            return -1;
        }

        if (totalAmount == 0) {
            return 0; //0 coins would be required to make an totalAmount of 0
        }

        int[] dp = new int[totalAmount+1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int currAmt=1; currAmt<=totalAmount; currAmt++) {
                if (coin <= currAmt && dp[currAmt-coin] != Integer.MAX_VALUE) {
                    dp[currAmt] = Math.min(1 + dp[currAmt - coin], dp[currAmt]);
                }
            }
        }
        return dp[totalAmount] > totalAmount ? -1 : dp[totalAmount];
    }

    /**
     * Approach 2: DFS
     *
     * Links:
     *  http://www.programcreek.com/2015/04/leetcode-coin-change-java/
     *
     * Complexity:
     *  Time is O(amount * coins)
     *  Space is O(amount * coins)
     */
    public int coinChange2(int[] coins, int totalAmount) {
        if (coins == null || coins.length == 0 || totalAmount < 0) {
            return -1;
        }

        if (totalAmount == 0) {
            return 0; //0 coins would be required to make an totalAmount of 0
        }

        int n = coins.length;
        int[][] dp = new int[n+1][totalAmount+1];

        //initialize first row of matrix with MaxValue
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int coinIdx=1; coinIdx<n+1; coinIdx++) {
            for (int currAmt=1; currAmt<totalAmount+1; currAmt++) {
                //if (coin == 0 || currAmt == 0) {
                //    dp[coin][currAmt] = 0;
                //}

                //if curr coin can contribute to totalAmount
                int coinVal = coins[coinIdx-1];
                if (coinVal <= currAmt && dp[coinIdx][currAmt-coinVal] != Integer.MAX_VALUE) {
                    //if you pick up this coin, find out the dp for the remaining totalAmount

                    //If you choose this coin, then add 1 for this coin and find the coins reqd for the remaining totalAmount
                    //Since we repeat coins, we choose the coin from the same row itself
                    //If curr coin is not chosen, then see if the same totalAmount is achieved using prev coin
                    dp[coinIdx][currAmt] = Math.min(1 + dp[coinIdx][currAmt-coinVal], dp[coinIdx-1][currAmt]);
                } else {
                    //If curr coin cannot contribute to the amount, carry over the value from the prev row
                    dp[coinIdx][currAmt] = dp[coinIdx-1][currAmt];
                }
            }
        }

        printCoins(dp, coins, totalAmount);
        return dp[n][totalAmount] > totalAmount ? -1 : dp[n][totalAmount];
    }

    private void printCoins(int[][] dp, int[] coins, int totalAmount) {
        int startY = totalAmount, startX=coins.length;
        if(dp[startX][startY] == Integer.MAX_VALUE) {
            System.out.print("No solution is possible: ");
            return;
        }

        Deque<Integer> stack = new LinkedList<>();
        while (startY != 0) {
            //If not participated
            if (dp[startX][startY] == dp[startX-1][startY]) {
                startX--;
            } else {
                stack.push(coins[startX-1]);
                startY = startY - coins[startX-1];
            }
        }
        System.out.print(stack.toString() +": ");
    }

    /**
     * Approach 3: Recursion
     *
     * Links:
     *  https://discuss.leetcode.com/topic/32489/java-both-iterative-and-recursive-solutions-with-explanations
     *
     * Complexity:
     *  Time is O()
     *  Space is O()
     */
    public int coinChange3(int[] coins, int totalAmount) {
        if (coins == null || coins.length == 0 || totalAmount < 0) {
            return -1;
        }

        if (totalAmount == 0) {
            return 0; //0 coins would be required to make an totalAmount of 0
        }

        int[] minCoinsForAmt = new int[totalAmount];
        Arrays.fill(minCoinsForAmt, -1);
        return coinchangeHelper(coins, totalAmount, minCoinsForAmt);
    }

    private int coinchangeHelper(int[] coins, int amount, int[] minCoinsForAmt) {
        if (amount == 0) {
            return 0;
        }

        if (minCoinsForAmt[amount-1] != -1) {
            return minCoinsForAmt[amount-1]; //Already computed for this amount
        }

        int minForRemAmt = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int retMin = coinchangeHelper(coins, amount-coin, minCoinsForAmt);
                if (retMin >=0 && (1+retMin) < minForRemAmt) {
                    minForRemAmt = 1 + retMin;
                }
            }
        }

        //Add one for the current coin chosen
        minCoinsForAmt[amount-1] = (minForRemAmt == Integer.MAX_VALUE) ? -1 : minForRemAmt;
        return minCoinsForAmt[amount-1];
    }
}
