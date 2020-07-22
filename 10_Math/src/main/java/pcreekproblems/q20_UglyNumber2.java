/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 */

package pcreekproblems;

/**
 * Problem:
 *   Write a program to find the n-th ugly number.
 *   Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *   For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 *   Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 *
 * Links:
 *  https://leetcode.com/problems/ugly-number-ii/description/
 *  https://www.programcreek.com/2014/05/leetcode-ugly-number-ii-java/
 */
public class q20_UglyNumber2 {
    public static void main(String[] args) {
        q20_UglyNumber2 un = new q20_UglyNumber2();
        System.out.printf("Get the %dth ugly number: %d\n", 5, un.getNthUglyNo(5));
        System.out.printf("Get the %dth ugly number: %d\n", 6, un.getNthUglyNo(6));
    }

    /**
     * Approach:
     *  We have an array k of first n ugly number. We only know, at the beginning, the first one, which is 1. Then
     *  k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to 1. Then we test:
     *  k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. Be careful about the cases such as 6, in which we need to forward both pointers of 2 and 3.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */

    public int getNthUglyNo(int num) {
        if (num <= 0) return -1;
        if (num == 1) return 1; //Base case

        int p2 = 0, p3 = 0, p5 = 0; //pointers for 2, 3, 5
        int[] dp = new int[num+1];
        dp[0] = 1;

        for(int i=1; i<num; i++) {
            int min = Math.min(dp[p3]*3,dp[p5]*5);
            dp[i] = Math.min(dp[p2] * 2, min);

            if(dp[i] == dp[p2]*2) p2++;
            if(dp[i] == dp[p3]*3) p3++;
            if(dp[i] == dp[p5]*5) p5++;
        }
        return dp[num];
    }
}
