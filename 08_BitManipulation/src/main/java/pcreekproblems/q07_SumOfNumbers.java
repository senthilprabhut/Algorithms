package pcreekproblems;

/**
 * Problem:
 *  Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -
 *  Example: Given a = 1 and b = 2, return 3
 *
 * Links:
 *  https://leetcode.com/problems/sum-of-two-integers/description/
 *  https://www.programcreek.com/2015/07/leetcode-sum-of-two-integers-java/
 */
public class q07_SumOfNumbers {
    public static void main(String[] args) {
        q07_SumOfNumbers son = new q07_SumOfNumbers();
        System.out.println(son.sum(3,5));
    }

    /**
     * Approach:
     *  Given two numbers a and b, a&b returns the number formed by '1' bits on a and b.
     *  When it is left shifted by 1 bit, it is the carry.
     *  a^b is the number formed by different bits of a and b
     */
    public int sum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            //b = (a & b) << 1;
            b = carry << 1;
        }
        return a;
    }
}
