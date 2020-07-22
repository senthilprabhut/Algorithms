package pcreekproblems;

/**
 * Problem:
 *   Divide two integers without using multiplication, division and mod operator.
 *   If it is overflow, return MAX_INT.
 *
 * Links:
 *  https://leetcode.com/problems/divide-two-integers/description/
 *  https://www.programcreek.com/2014/05/leetcode-divide-two-integers-java/
 *  https://discuss.leetcode.com/topic/15568/detailed-explained-8ms-c-solution
 */
public class q13_DivideTwoIntegers {
    public static void main(String[] args) {
        q13_DivideTwoIntegers dti = new q13_DivideTwoIntegers();
        System.out.printf("Dividing %d by %d gives quotient %d%n", 15, 3, dti.divide(15, 3));
        System.out.printf("Dividing %d by %d gives quotient %d%n", 17, 4, dti.divide(17, 4));
    }

    /**
     * Approach:
     *
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;

        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        long dvd = Math.abs(dividend), dvs = Math.abs(divisor);
        int quotient = 0;
        while (dvd >= dvs) {
            long temp = dvs;
            int multiple=1;
            while(dvd >= (temp<<1)) {
                multiple <<= 1;
                temp <<= 1;
            }

            //We've counted the no of multiples of denominator less than numerator
            //Now, divide for the remaining value and find the multiplier
            dvd = dvd - temp;
            quotient += multiple;
        }

        return sign == 1 ? quotient : -quotient;
    }
}
