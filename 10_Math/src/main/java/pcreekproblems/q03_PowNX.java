package pcreekproblems;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Implement pow(x, n) which calculates x raised to the power of n (xⁿ)
 *  Example: Input: 2.00000, 10  Output: 1024.00000
 *  Example: Input: 2.10000, 3  Output: 9.26100
 *  Note: -100.0 < x < 100.0, n is a 32 bit signed integer, within the range [-2³¹, 2³¹ - 1]
 *
 * Links:
 *  https://leetcode.com/problems/powx-n/description/
 *  https://www.programcreek.com/2012/12/leetcode-powx-n/
 *
 * TODO: Revise
 */
public class q03_PowNX {
    public static void main(String[] args) {
        q03_PowNX pow = new q03_PowNX();
        System.out.printf("The power of %f raised to %d is %f \n", 2.0, 10, pow.powerNXBitwise(2.0, 10));
        System.out.printf("The power of %f raised to %d is %f \n", 2.0, 10, pow.powerNXRec(2.0, 10));
        System.out.printf("The power of %f raised to %d is %f \n", 2.1, 3, pow.powerNXBitwise(2.1, 3));
        System.out.printf("The power of %f raised to %d is %f \n", 2.1, 3, pow.powerNXRec(2.1, 3));
    }


    /**
     * Approach:
     *  The basic idea is to decompose the exponent into powers of 2, so that you can keep dividing the problem in half.
     *  For example, lets say  N = 9 = 2^3 + 2^0 = 1001 in binary.
     *  Then: x^9 = x^(2^3) * x^(2^0)
     *  We can see that every time we encounter a 1 in the binary representation of N, we need to multiply
     *  the answer with x^(2^i) where i is the ith bit of the exponent.
     *  Thus, we can keep a running total of repeatedly squaring x - (x, x^2, x^4, x^8, etc) and multiply it by the answer when we see a 1.
     *
     *  To handle the case where N=INTEGER_MIN we use a long (64-bit) variable.
     *
     * Links:
     *  https://leetcode.com/problems/powx-n/discuss/19563/Iterative-Log(N)-solution-with-Clear-Explanation
     *  https://discuss.leetcode.com/topic/40546/iterative-log-n-solution-with-clear-explanation
     *
     * Complexity:
     *  Time: Since we keep dividing the number by half every iteration, it is O(logN)
     *      As N is fixed number of bits (32), you could view it as O(1) where max no of iterations is 32
     *  Space: O(1)
     */
    public double powerNXBitwise(double x, int n) {
        //Anything to power of 0 is 1
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double result = 1;
        //This loop with run max 32 times for 32 bits
        while (n > 0) {
            if ((n & 1) == 1) {
                result = result * x; //checking if the last bit is 1
            }
            n >>= 1; //right shift the n
            x = x * x; //keep squaring "x" for every bit
        }
        return result;
    }


    /**
     * Approach: Recursive
     *  If n is even, multiply x*x and divide n by 2 and do this recursively
     *
     * Links:
     *  https://discuss.leetcode.com/topic/5425/short-and-easy-to-understand-solution
     *
     * Complexity:
     *  Time: O(log n), since we keep dividing n by 2 every time
     *  Space: O(log n) due to recursion
     */
    public double powerNXRec(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return (n % 2 == 0) ? powerNXRec(x * x, n / 2) : x * powerNXRec(x * x, n / 2);
    }

}
