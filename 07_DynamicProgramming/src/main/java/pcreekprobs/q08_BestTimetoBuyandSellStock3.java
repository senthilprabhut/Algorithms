package pcreekprobs;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Say you have an array for which the ith element is the price of a given stock on day i.
 *  Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *  Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Links:
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 *  https://www.programcreek.com/2014/02/leetcode-best-time-to-buy-and-sell-stock-iii-java/
 *
 * To Study:
 *  Generalize to K Txn - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39608/A-clean-DP-solution-which-generalizes-to-k-transactions
 */
public class q08_BestTimetoBuyandSellStock3 {
    public static void main(String[] args) {
        q08_BestTimetoBuyandSellStock3 bt = new q08_BestTimetoBuyandSellStock3();
        System.out.println(bt.maxProfit1(new int[] {1,4,5,7,6,3,2,9}));
        System.out.println(bt.maxProfit2(new int[] {1,4,5,7,6,3,2,9}));
        System.out.println(bt.maxProfit3(new int[] {1,4,5,7,6,3,2,9}));
    }

    /**
     * Approach 1: DP solution without saving redundant values
     *  Four variables to represent Profit/Loss states
     *  These four variables represent your profit after executing corresponding transaction
     *  In the beginning, your profit is 0.
     *  When you buy a stock ,the profit will be deducted by the price of stock.
     *
     * Links:
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39653/2ms-Java-DP-Solution   comment: Horanol
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxProfit1(int[] prices) {
        int firstBuy = Integer.MAX_VALUE;

        //profit after buy/sell
        int afterFirstSell = 0;
        int afterSecondBuy = Integer.MIN_VALUE;
        int afterSecondSell = 0;

        for (int curPrice : prices) {
            firstBuy = Math.min(firstBuy, curPrice); //for first buy price ,the lower,the better
            afterFirstSell = Math.max(afterFirstSell ,curPrice-firstBuy); // the profit after first sell ,the higher,the better
            afterSecondBuy = Math.max(afterSecondBuy ,afterFirstSell - curPrice);//the profit left after second buy,the higher,the better
            afterSecondSell = Math.max(afterSecondSell ,afterSecondBuy + curPrice); // the profit left after second sell ,the higher,the better
        }
        return afterSecondSell ; // afterSecondSell will be the max profit ultimately
    }

    /**
     * Approach 2: Minor variation of approach 1
     *  Four variables to represent Profit/Loss states
     *
     * Links:
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39653/2ms-Java-DP-Solution   comment: ronhuafeng
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxProfit2(int[] prices) {
        // these four variables represent your profit after executing corresponding transaction
        // in the beginning, your profit is 0.
        // when you buy a stock ,the profit will be deducted of the price of stock.
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;


        // (-firstBuy) is min value beteen [0, curPrice.index], firstBuy itself is a negative value

        // (firstSell) is max profit between [0, current.index], before update it is max profit between [0, current.index-1], after update is max(firstSell.before, curPrice + firstBuy(e.g. - minValue[0, curPrice.index]))

        // (secondBuy) is max profit between [0,curPrice.index] under seen prices if you hold/buy a stock between[0, curPrice.index] and haven't sell it yet.

        // (secondSell) is max profit between [0,curPrice.index] under seen prices if you buy a second stock between [0,curPrice.index];

        for (int curPrice : prices) {
            if (firstBuy < -curPrice) firstBuy = -curPrice; // the max profit after you buy first stock
            if (firstSell < firstBuy + curPrice) firstSell = firstBuy + curPrice; // the max profit after you sell it
            if (secondBuy < firstSell - curPrice) secondBuy = firstSell - curPrice; // the max profit after you buy the second stock
            if (secondSell < secondBuy + curPrice) secondSell = secondBuy + curPrice; // the max profit after you sell the second stock
        }

        return secondSell; // secondSell will be the max profit after passing the prices
    }

    /**
     * Approach 3:
     *  Have 2 DP arrays - find max profit from L to R and from R to L
     *
     * Links:
     *  Jikai Tang - https://www.youtube.com/watch?v=2U4ooBziFOY
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n)
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int len = prices.length;
        int[] dp = new int[len+1];

        //Max profit from left to right
        //left = [0, 3, 4, 6, 6, 6, 6, 8]
        int valley = prices[0];  //first element
        for(int i = 2; i < len+1; i++) {
            dp[i] = Math.max(dp[i-1], prices[i-1] - valley);
            valley = Math.min(valley, prices[i-1]);
        }

        //Max profit from right to left
        //right= [8, 7, 7, 7, 7, 7, 7, 0]
        int peak = prices[len-1]; //last element
        for (int i=len-2; i>=0; i--) {
            dp[i] += Math.max(0, peak-prices[i]);  //Add to the profit calculated in prev step
            peak = Math.max(peak, prices[i]);
        }

        int maxProfit=0;
        for(int num : dp) {
            if (num > maxProfit) {
                maxProfit = num;
            }
        }
        return maxProfit;
    }
}
