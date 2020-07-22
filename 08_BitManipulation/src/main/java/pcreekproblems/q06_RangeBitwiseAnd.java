package pcreekproblems;

/**
 * Problem:
 *   Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
 *   inclusive. For example, given the range [5, 7], you should return 4.
 *
 * Links:
 *  https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
 *  https://www.programcreek.com/2014/04/leetcode-bitwise-and-of-numbers-range-java/
 */
public class q06_RangeBitwiseAnd {
    public static void main(String[] args) {
        q06_RangeBitwiseAnd rba = new q06_RangeBitwiseAnd();
        System.out.println(rba.rangeBitwiseAnd1(5,7)); //4
        System.out.println(rba.rangeBitwiseAnd2(9,14)); //8
    }

    /**
     * Approach 1:
     *  The idea is to use a mask to find the leftmost common digits of m and n.
     *  Example: m=1110001, n=1110111, and you just need to find 1110000 and it will be the answer.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/12093/my-simple-java-solution-3-lines
     */
    public int rangeBitwiseAnd1(int start, int end) {
        int mask = Integer.MAX_VALUE;
        while ( (start&mask) != (end&mask)) mask <<= 1;
        return start&mask; //We need to return start & mask since mask may have 1 till the Most significant bit and
        // start may not have 1 till that point - start may be 00000110 and mask will be 11111110 - we need only 110 as output
    }

    /**
     * Approach 2:
     *  The key point: reduce n by removing the rightest '1' bit until n<=m;
     *  This gives the nearest 2 pow N common to both start and end numbers
     *
     * Links:
     *  https://discuss.leetcode.com/topic/20176/2-line-solution-with-detailed-explanation
     */
    public int rangeBitwiseAnd2(int start, int end) {
        while (start < end) {
            end = end & (end-1);
        }
        return end;
    }
}
