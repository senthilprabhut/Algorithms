package pcreekproblems;

import java.util.Arrays;

/**
 * Problem:
 *  Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 *  calculate the number of 1's in their binary representation and return them as an array.
 *  Example:
 *      For num = 5 you should return [0,1,1,2,1,2].
 *
 * Links:
 *  https://leetcode.com/problems/counting-bits/description/
 *  https://www.programcreek.com/2015/03/leetcode-counting-bits-java/
 */
public class q08_CountingBits {

    public static void main(String[] args) {
        q08_CountingBits cb = new q08_CountingBits();
        System.out.println(Arrays.toString(cb.countBits1(7) )); //[0, 1, 1, 2, 1, 2, 2, 3]
        System.out.println(Arrays.toString(cb.countBits2(7) )); //[0, 1, 1, 2, 1, 2, 2, 3]
    }

    /**
     * Approach 1:
     *  An easy recurrence for this problem is f[n] = f[n / 2] + (n % 2).
     *  For an even number, the last bit is 0 and it is 1 for an odd number
     *  Also, i>>1 means get the no of 1's from the n/2 number and add 1 to it ONLY if the current no is ODD
     *  For eg, 5 in binary is 101 -  Divide 5 by 2 = 5/2 = 2 which in binary is 10 + 1 (since odd) resulting in 101
     *
     * Link:
     *  https://discuss.leetcode.com/topic/40162/three-line-java-solution
     *
     * Complexity:
     *  Time - O(n)
     *  Space - O(1)
     */
    public int[] countBits1(int num) {
        int[] f = new int[num+1]; //the index starts from 1
        for(int i=1; i<=num; i++) f[i] = f[i>>1] + (i & 1);
        return f;
    }

    /**
     * Approach 2:
     *  For number 2(10), 4(100), 8(1000), 16(10000), ..., the number of 1's is 1.
     *  Any other number can be converted to be 2^m + x
     *  For example, 9=8+1, 10=8+2. The number of 1's for any other number is 1 + # of 1's in x.
     *
     * Link:
     *  https://discuss.leetcode.com/topic/40162/three-line-java-solution/7
     *  https://www.programcreek.com/2015/03/leetcode-counting-bits-java/
     *
     * Complexity:
     *  Time - O(n)
     *  Space - O(1)
     */
    public int[] countBits2(int num) {
        int[] answer = new int[num + 1];
        int offset = 1;

        for (int i=1; i<=num; i++) {
            if(i == offset*2) offset *= 2;
            answer[i] = 1 + answer[i-offset];
        }
        return answer;
    }
}
