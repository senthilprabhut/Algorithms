package pcreekprobs;

import java.util.Arrays;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *  For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *
 * Links:
 *  https://leetcode.com/problems/perfect-squares/description/
 *  https://www.programcreek.com/2014/05/leetcode-perfect-squares-java/
 *
 */
public class q17_PerfectSquares {
    public static void main(String[] args) {
        q17_PerfectSquares ps = new q17_PerfectSquares();
        System.out.println(ps.numSquares1(12));  //3
        System.out.println(ps.numSquares1(13));  //2

        System.out.println(ps.numSquares2(12));  //3
        System.out.println(ps.numSquares2(13));  //2
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/perfect-squares/discuss/71488/Summary-of-4-different-solutions-(BFS-DP-static-DP-and-mathematics)
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n)
     */
    public int numSquares1(int n) {
        if (n<=0) return 0;

        //dp[i] = the least number of perfect square numbers which sum to i. Note that dp[0] is 0.
        int[] dp = new int[n+1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // For each i, "min"" must be the sum of some number (i - j*j) and a perfect square number (j*j).
            for (int j=1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]); //1 here is added for the perfect square j*j
            }
        }
        return dp[n];
    }

    /**
     * Approach:
     *  Based on Lagrange's Four Square theorem, there are only 4 possible results: 1, 2, 3, 4.
     *  Lagrange’s Four Square theorem - Limit the result to <= 4:  https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
     *  This article, in which you can also find the way to present a number as a sum of four squares: http://www.alpertron.com.ar/4SQUARES.HTM
     *
     * Links:
     *  https://leetcode.com/problems/perfect-squares/discuss/71618/4ms-c-code-solve-it-mathematically
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int numSquares2(int n) {
        // If n is a perfect square, return 1.
        if(is_square(n)) return 1;

        // The result is 4 if and only if n can be written in the form of 4^k*(8*m + 7).
        // Please refer to Legendre's three-square theorem
        while ((n & 3) == 0) //n%4 == 0
            n >>= 2;

        if ((n & 7) == 7) return 4; //n % 8 == 7

        // Check whether 2 is the result.
        int sqrt_n = (int) Math.sqrt(n);
        for(int i = 1; i<= sqrt_n; i++){
            if (is_square(n-i*i)) return 2;  //eg. is 13
        }
        return 3;
    }

    private boolean is_square(int n){
        int temp = (int) Math.sqrt(n);
        return temp * temp == n;
    }
}
