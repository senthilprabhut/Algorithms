package pcreekproblems;

import java.util.LinkedList;
import java.util.List;

/**
 * Problem:
 *  The gray code is a binary numeral system where two successive values differ in only one bit.
 *  Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
 *  A gray code sequence must begin with 0.
 *  For example, given n = 2, return [0,1,3,2]
 *
 * Links:
 *  https://leetcode.com/problems/gray-code/description/
 *  https://www.programcreek.com/2014/05/leetcode-gray-code-java/
 */
public class q10_GrayCode {
    public static void main(String[] args) {
        q10_GrayCode gc = new q10_GrayCode();
        System.out.println(gc.grayCode1(5)); //[0, 1, 3, 2, 6]
        System.out.println(gc.grayToBinary(gc.grayCode1(5)));
    }

    /**
     * Approach:
     *  The idea is simple. G(i) = i^ (i/2)
     *
     * Link:
     *  https://discuss.leetcode.com/topic/8557/an-accepted-three-line-solution-in-java
     *
     * Complexity:
     *  Time - O(n)
     *  Space - O(1)
     */
    public List<Integer> grayCode1(int n) {
        List<Integer> result = new LinkedList<>();
        for(int i=0; i<n; i++) {
            result.add( i ^ (i>>1));
        }
        return result;
    }

    public List<Integer> grayToBinary(List<Integer> grayList) {
        List<Integer> result = new LinkedList<>();
        for(int i=0; i<grayList.size(); i++) {
            int num = grayList.get(i);
            int mask = num;
            while (mask != 0) {
                mask = mask >> 1;
                num = num ^ mask;
            }
            result.add(num);
        }
        return result;
    }
}
