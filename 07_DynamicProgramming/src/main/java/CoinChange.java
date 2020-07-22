import java.util.Arrays;

/**
 * Problem:
 *  You are given coins of different denominations and a total amount of money amount.
 *  Write a function to compute the fewest number of coins that you need to make up that amount.
 *  If that amount of money cannot be made up by any combination of the coins, return -1.
 *  Example 1:
 *    coins = [1, 2, 5], amount = 11
 *    return 3 (11 = 5 + 5 + 1)
 *  Example 2:
 *    coins = [2], amount = 3
 *    return -1.
 *  Note:
 *    You may assume that you have an infinite number of each kind of coin.
 *
 * Links:
 *  https://leetcode.com/problems/coin-change
 *  http://www.programcreek.com/2015/04/leetcode-coin-change-java/
 *  http://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 *  
 *  Solution 1 - http://www.programcreek.com/2015/04/leetcode-coin-change-java/
 *  Solution 2 - http://www.programcreek.com/2015/04/leetcode-coin-change-java/
 *  Solution 3 - https://discuss.leetcode.com/topic/32489/java-both-iterative-and-recursive-solutions-with-explanations
 *
 * Complexity:
 *  Solution 1 -
 *  Solution 2 -
 *  Solution 3 -
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {2};
        int amount = 3;
//        int[] coins = {7, 3, 2, 6};
//        int amount = 13;

        int noOfCoins = cc.dp1(coins,amount);
        //Arrays.sort(coins); //Sorted array is reqd only to trace back and print the coins
        //int noOfCoins = cc.dp2(coins,amount);
        System.out.printf("Minimum no of coins from %s to get to amount %d is %d", Arrays.toString(coins), amount, noOfCoins);
    }

    private int dp1(int[] coins, int totalAmount) {
        //Create a copy of coins with value of index 0 is 0
        //This is done so that the formula in for loop is readable - program works without this array too
        int[] newCoins = new int[coins.length+1];
        System.arraycopy(coins, 0, newCoins, 1, coins.length);

        int[][] dp = new int[coins.length+1][totalAmount+1];

        //initialize first row of matrix - the fewest coins needed for 0 coins is 0
        //dp[0][0] = 0;
        for(int cntr=0; cntr<=totalAmount; cntr++)
            dp[0][cntr] = Integer.MAX_VALUE;

        for(int i=1; i<=coins.length; i++) {
            for(int amount=1; amount<=totalAmount; amount++) {
                //The max value check is done so that we don't add 1 to a MAX_VALUE.
                //The MAX_VALUE is meant to be a placeholder and not participate in calculations
                if(newCoins[i] <= amount && dp[i][amount-newCoins[i]] != Integer.MAX_VALUE) {
                    int minValue = Math.min((1 + dp[i][amount - newCoins[i]]), dp[i-1][amount]);
                    dp[i][amount] = minValue;
                }
                else
                    dp[i][amount] = dp[i-1][amount];
            }
        }

        printCoinCombination1(dp, coins, totalAmount);

        if (dp[coins.length][totalAmount] == Integer.MAX_VALUE) return -1;
        return dp[coins.length][totalAmount];
    }

    //Space efficient version of above dp
    private int dp2(int[] coins, int totalAmount) {
        if (coins == null || coins.length == 0) return 0;

        int[] R = new int[totalAmount + 1];
        int[] dp = new int[totalAmount+1];  //dp[0]=0 by default. fewest coins needed for 0 coins is 0
        for(int cntr=1; cntr<=totalAmount; cntr++) {
            dp[cntr] = Integer.MAX_VALUE;
            R[cntr] = -1;
        }

        for(int i=0; i<coins.length; i++) {
            for (int amount=1; amount<=totalAmount; amount++) {
                if(coins[i] <= amount && dp[amount-coins[i]] != Integer.MAX_VALUE) {
                    if ((1 + dp[amount-coins[i]]) < dp[amount]) //Check min coins to get amount b/w prev coin and current coin
                        dp[amount] = 1 + dp[amount-coins[i]];
                        R[amount] = i;
                }
            }
        }

        printCoinCombination2(R, coins);

        if (dp[totalAmount] == Integer.MAX_VALUE) return -1;
        return dp[totalAmount];
    }

    private void printCoinCombination1(int[][] dp, int[] coins, int totalAmount) {
        if(dp[coins.length][totalAmount] == Integer.MAX_VALUE) {
            System.out.println("No solution is possible");
            return;
        }

        System.out.printf("Denominations making up %d are \n", totalAmount);
        int startX = coins.length, startY=totalAmount;
        while (startY != 0) {
            if (dp[startX][startY] == dp[startX-1][startY]) {
                startX = startX-1;
            } else {
                System.out.println(coins[startX-1]); //print the coin participating in the min coin combination
                startY = startY - coins[startX-1];
            }
        }
    }

    private void printCoinCombination2(int R[], int coins[]) {
        if (R[R.length - 1] == -1) {
            System.out.println("No solution is possible");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins used to form total ");
        while ( start != 0 ) {
            int index = R[start];
            System.out.print(coins[index] + " ");
            start = start - coins[index];
        }
        System.out.print("\n");
    }

    //Recursive version
//    public int coinChange2(int[] coins, int amount) {
//        if(amount<1) return 0;
//        return helper(coins, amount, new int[amount]);
//    }
//
//    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
//        if(rem<0) return -1; // not valid
//        if(rem==0) return 0; // completed
//        if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
//        int min = Integer.MAX_VALUE;
//        for(int coin : coins) {
//            int res = helper(coins, rem-coin, count);
//            if(res>=0 && res < min)
//                min = 1+res;
//        }
//        count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
//        return count[rem-1];
//    }

}
