package pcreekprobs;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Say you have an array for which the ith element is the price of a given stock on day i.
 *  Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 *  (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions
 *  at the same time (ie, you must sell the stock before you buy again).
 *
 * Links:
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 *  https://www.programcreek.com/2014/02/leetcode-best-time-to-buy-and-sell-stock-ii-java/
 */
public class q08_BestTimetoBuyandSellStock2 {
    public static void main(String[] args) {
        q08_BestTimetoBuyandSellStock2 bt = new q08_BestTimetoBuyandSellStock2();
        System.out.println(bt.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
        System.out.println(bt.maxProfit(new int[] {7, 6, 4, 3, 1}));
    }

    /**
     * Approach:
     *  This problem can be viewed as finding all ascending sequences.
     *  For example, given {5, 1, 2, 3, 4}, buy at 1 & sell at 4 is the same as
     *  buy at 1 &sell at 2 & buy at 2& sell at 3 & buy at 3 & sell at 4.
     *  We can scan the array once, and find all pairs of elements that are in ascending order.
     *
     * Links:
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/39402/Is-this-question-a-joke
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxProfit(int[] prices) {
        int total=0;

        for(int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                total += prices[i] - prices[i-1]; //Do this only for the increasing sequence
            }
        }

        return total;
    }

}
