package pcreekproblems;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Reverse digits of an integer
 *  Example1: x = 123, return 321
 *  Example2: x = -123, return -321
 *
 * Links:
 *  https://leetcode.com/problems/reverse-integer/description/
 *  https://www.programcreek.com/2012/12/leetcode-reverse-integer/
 */
public class q01_ReverseInteger {
    public static void main(String[] args) {
        q01_ReverseInteger ri = new q01_ReverseInteger();
        System.out.printf("The reverse of %d is %d\n", 123, ri.reverse(123));
        System.out.printf("The reverse of %d is %d\n", -123, ri.reverse(-123));
    }

    /**
     * Approach:
     *  Divide and multiply
     */
    public int reverse(int number) {
        boolean flag = false;
        if (number < 0) {
            number = 0 - number;
            flag = true;
        }

        int result = 0;
        while (number > 0) {
            result = (result * 10) + (number % 10);
            number = number / 10;
        }

        if (flag) {
            result = 0 - result;
        }

        return result;
    }
}
