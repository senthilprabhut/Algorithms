package pcreekproblems;

/**
 * Problem:
 *  Given an integer n, return the number of trailing zeroes in n!.
 *  Note: Your solution should be in logarithmic time complexity.
 *
 * Links:
 *  https://leetcode.com/problems/factorial-trailing-zeroes/description/
 *  https://www.programcreek.com/2014/04/leetcode-factorial-trailing-zeroes-java/
 */
public class q09_FactorialTrailingZeroes {
    public static void main(String[] args) {
        q09_FactorialTrailingZeroes ftz = new q09_FactorialTrailingZeroes();
        System.out.printf("Trailing zeroes in %d factorial is %d\n", 10, ftz.trailingZeroes1(10));
        System.out.printf("Trailing zeroes in %d factorial is %d\n", 15, ftz.trailingZeroes2(15));
    }

    /**
     * Approach:
     *  All trailing 0 is from factors 5 * 2.
     *  But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors.
     *  In the n! operation, factors 2 is always ample.
     *  So we just count how many 5 factors in all number from 1 to n.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/6516/my-one-line-solutions-in-3-languages
     *
     * Complexity:
     *  Time Complexity is log n
     *  Space Complexity
     */
    public int trailingZeroes1(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes1(n / 5);
    }

    /**
     * Approach:
     *  All trailing 0 is from factors 5 * 2
     *
     * Links:
     *  https://www.programcreek.com/2014/04/leetcode-factorial-trailing-zeroes-java/
     *
     * Complexity:
     *  Time Complexity is log n
     *  Space Complexity
     */
    public int trailingZeroes2(int n) {
        if (n<0) return -1;

        int count = 0;
        for(long i=5; n/i >= 1; i*=5) { //i will be 5, 25, 125 ...
            count += n/i; //if n is 10, count will be 2
        }
        return count;
    }

}
