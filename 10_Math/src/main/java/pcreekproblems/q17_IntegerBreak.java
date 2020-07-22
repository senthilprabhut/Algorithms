package pcreekproblems;

/**
 * Problem:
 *  Given a positive integer n, break it into the sum of at least two positive integers and
 *  maximize the product of those integers. Return the maximum product you can get.
 *  For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 *
 * Links:
 *  https://leetcode.com/problems/integer-break/description/
 *  https://www.programcreek.com/2015/04/leetcode-integer-break-java/
 */
public class q17_IntegerBreak {
    public static void main(String[] args) {
        q17_IntegerBreak ib = new q17_IntegerBreak();
        System.out.printf("Integer break for %d is %d\n", 10, ib.integerBreak1(10));
        System.out.printf("Integer break for %d is %d\n", 11, ib.integerBreak2(11));
    }

    /**
     * Approach:
     *  Dynamic programming
     *  Let dp[i] to be the max product value for breaking the number i.
     *  Since dp[i+j] can be i*j, dp[i+j] = max(max(dp[i], i) * max(dp[j], j)), dp[i+j]).
     *
     * Complexity:
     *  Time Complexity is O(n²)
     *  Space Complexity is O(n)
     */
    public long integerBreak1(int number) {
        if(number < 2) return -1;

        long[] dp = new long[number+1];
        for(int i=1; i<number; i++) {
            for(int j=1; j<=i; j++) {
                if(i+j <= number) {
                    //Max product can be either
                    // already existing value at dp[i+j] eg. 2*3 will be > 1*4
                    // the product of i,j
                    // the product of dp[i], dp[j]
                    long newProduct = Math.max(dp[i], i) * Math.max(dp[j], j);
                    dp[i+j] = Math.max(dp[i+j], newProduct);
                }
            }
        }
        return dp[number];
    }

    /**
     * Approach:
     *  Using Regularities
     *  We only need to find how many 3's we can get when n> 4.
     *  If n%3==1, we do not want 1 to be one of the broken numbers, we want 4.
     *
     *  If we see the breaking result for some numbers, we can see repeated pattern like the following:
     *  2 -> 1*1
     *  3 -> 1*2
     *  4 -> 2*2
     *  5 -> 3*2
     *  6 -> 3*3
     *  7 -> 3*4
     *  8 -> 3*3*2
     *  9 -> 3*3*3
     *  10 -> 3*3*4
     *  11 -> 3*3*3*2
     *
     * Complexity:
     *  Time Complexity is O(1)
     *  Space Complexity is O(1)
     */
    public long integerBreak2(int number) {
        if(number < 2) return -1;

        if(number == 2) return 1;
        if (number == 3) return 2;
        if (number == 4) return 4;

        int result = 1;
        if (number%3 == 0) {
            int quotient = number/3;
            result = (int) Math.pow(3, quotient);
        } else if (number%3 == 2) {
            int quotient = number/3;
            result = ((int) Math.pow(3, quotient)) * 2;
        } else if (number%3 == 1) {
            int quotient = (number-4)/3;
            result = ((int) Math.pow(3, quotient)) * 4;
        }
        return result;
    }
}
