package pcreekprobs;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Say you have an array for which the ith element is the price of a given stock on day i.
 *  If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *  Example 1:  Input: [7, 1, 5, 3, 6, 4]   Output: 5. Buy at 1 and sell at 6
 *  max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 *  Example 2:  Input: [7, 6, 4, 3, 1]  Output: 0
 *  In this case, no transaction is done, i.e. max profit = 0.
 *
 * Links:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * https://www.programcreek.com/2014/02/leetcode-best-time-to-buy-and-sell-stock-java/
 */
public class q08_BestTimetoBuyandSellStock1 {
    public static void main(String[] args) {
        q08_BestTimetoBuyandSellStock1 bt = new q08_BestTimetoBuyandSellStock1();
        System.out.println(bt.maxProfit1(new int[] {7, 1, 5, 3, 6, 4}));
        System.out.println(bt.maxProfit1(new int[] {7, 6, 4, 3, 1}));

        System.out.println(bt.maxProfit2(new int[] {7, 1, 5, 3, 6, 4}));
        System.out.println(bt.maxProfit2(new int[] {7, 6, 4, 3, 1}));
    }

    /**
     * Approach: Kadane's Algorithm
     *  The logic to solve this problem is same as “max subarray problem” using Kadane's Algorithm.
     *  All the straight forward solution should work, but if the interviewer twists the question slightly by giving the difference array of prices,
     *  Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, you might end up being confused.
     *
     *  Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, and
     *  find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
     *
     * Links:
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39038/Kadane's-Algorithm-Since-no-one-has-mentioned-about-this-so-far-:)-(In-case-if-interviewer-twists-the-input)
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxProfit1(int[] prices) {
        int maxCur=0, maxSoFar=0;

        //This problem morphs to max sub array of diff values
        for(int i = 1; i < prices.length; i++) {
            maxCur += prices[i] - prices[i-1];

            maxCur = Math.max(0, maxCur);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }

        return maxSoFar;
    }


    /**
     * Approach:
     *  Keep track of max and min values
     *
     * Links:
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39075/A-O(1*n)-solution - Comment jasontgi
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39062/My-jave-accepted-solution-with-O(N)-time-and-O(1)-space
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0 ;
        }

        int minSoFar=prices[0], maxProfit=0;

        for(int price : prices) {
            minSoFar = Math.min(minSoFar, price);
            maxProfit = Math.max(maxProfit, price-minSoFar);
        }
        return maxProfit;
    }

}
