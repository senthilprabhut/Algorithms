package pcreekprobs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Say you have an array for which the ith element is the price of a given stock on day i.
 *  Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *  Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again)
 *
 * Links:
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 *  https://www.programcreek.com/2014/03/leetcode-best-time-to-buy-and-sell-stock-iv-java/
 */
public class q08_BestTimetoBuyandSellStock4 {
    public static void main(String[] args) {
        q08_BestTimetoBuyandSellStock4 bt = new q08_BestTimetoBuyandSellStock4();

        int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};
        System.out.println(bt.maxProfit1(3, prices));
        System.out.println();
        System.out.println(bt.maxProfit2(3, prices));
        System.out.println();
        System.out.println(bt.maxProfit3(3, prices));

    }
    /*
     *                          ⎧  T[i][j-1] - not transacting on the Jth day
     *                          ⎪
     * Formula is T[i][j] = max ⎨
     * i = txn, j = no of days  ⎪  (price[j] - price[m]) + T[i-1][m], m is 0 ... j-1  - best you can get by completing txn on Jth day.
     *                          ⎩  i-1 is the no of pending transactions to be completed by m days
     * dp[0][0] = 0 because if we can't do any transactions, we cannot make any profit
     */

    /**
     * Approach 1:
     *  This is an improvement in space over Approach 2
     *  dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     *  dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
     *  dp[0, j] = 0; 0 transactions makes 0 profit
     *  dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=oDhu5uGq_ic&t=208s
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54113/A-Concise-DP-Solution-in-Java
     *
     * Complexity:
     *  Time is O(k * number of days)
     *  Space is O(number of days)
     */
    public int maxProfit1(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }

        int txnLength = k+1; //0 to k transactions
        int pricesLength = prices.length; //index 0 to length -1

        //when k>len/2, it means we can do as many transactions as we want. So, in case k>len/2, this problem is same to Best Time to Buy and Sell Stock II.
        if (k >= pricesLength/2) {
            return quickSolve(prices);
        }

        int[] dp = new int[pricesLength];
        int[] prev = new int[pricesLength];
        for (int i=1; i<=txnLength; i++) {
            int maxDiff = -prices[0];
            for (int j=1; j<pricesLength; j++) {
                dp[j] = Math.max(dp[j-1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, prev[j] - prices[j]);
            }

            for(int m=0; m<pricesLength; m++) {
                prev[m] = dp[m];
            }
        }
        return dp[pricesLength-1];
    }
    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    /**
     * Approach 2:
     *  This is a faster method which does optimization on slower method
     *  Formula is T[i][j] = max(T[i][j-1], prices[j] + maxDiff)
     *  maxDiff = max(maxDiff, T[i-1][j] - prices[j])
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=oDhu5uGq_ic&t=208s
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
     *
     * Complexity:
     *  Time is O(k * number of days)
     *  Space is O(k * number of days)
     */
    public int maxProfit2(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }

        int txnLength = k+1; //0 to k transactions
        int pricesLength = prices.length; //index 0 to length -1
        int[][] dp = new int[txnLength][pricesLength];
        for(int i=1; i< txnLength; i++) {
            int maxDiff = dp[i-1][0] - prices[0]; //same as -prices[0]
            for (int j = 1; j < pricesLength; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i-1][j] - prices[j]);
            }
        }
        printActualSolution(dp, prices);
        return dp[k][pricesLength-1];
    }


    /**
     * Approach 3:
     *  This is the slowest method but easier to understand.
     *  Formula is T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=oDhu5uGq_ic&t=208s
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
     *
     * Complexity:
     *  Time is O(k * number of days²)
     *  Space is O(k * number of days)
     */
    public int maxProfit3(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }

        int txnLength = k+1; //0 to k transactions
        int pricesLength = prices.length; //index 0 to length -1
        int[][] dp = new int[txnLength][pricesLength];
        for(int i=1; i< txnLength; i++) {
            for(int j=1; j<pricesLength; j++) {
                int maxProfit = 0;
                for (int m=0; m<j; m++) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[m] + dp[i-1][m]);
                }
                dp[i][j] = Math.max(dp[i][j-1], maxProfit);
            }
        }
        printActualSolution(dp, prices);
        return dp[k][pricesLength-1];
    }

    public void printActualSolution(int T[][], int prices[]) {
        int i = T.length - 1;
        int j = T[0].length - 1;

        Deque<Integer> stack = new LinkedList<>();
        while(true) {
            if(i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];
                for (int k = j-1; k >= 0; k--) {
                    if (T[i-1][k] - prices[k] == maxDiff) {
                        i = i - 1;
                        j = k;
                        stack.addFirst(j);
                        break;
                    }
                }
            }
        }

        while(!stack.isEmpty()) {
            System.out.println("Buy at price " + prices[stack.pollFirst()]);
            System.out.println("Sell at price " + prices[stack.pollFirst()]);
        }

    }
}
