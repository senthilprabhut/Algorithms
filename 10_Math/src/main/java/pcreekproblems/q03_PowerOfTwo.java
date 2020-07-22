package pcreekproblems;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an integer, write a function to determine if it is a power of two.
 *
 * Links:
 *  https://www.programcreek.com/2014/07/leetcode-power-of-two-java/
 */
public class q03_PowerOfTwo {
    public static void main(String[] args) {
        q03_PowerOfTwo pot = new q03_PowerOfTwo();
        System.out.printf("Is %d a power of 2: %b\n", 8, pot.isPowerOfTwo1(8));
        System.out.printf("Is %d a power of 2: %b\n", 8, pot.isPowerOfTwo2(8));

        System.out.printf("Is %d a power of 2: %b\n", 9, pot.isPowerOfTwo1(9));
        System.out.printf("Is %d a power of 2: %b\n", 9, pot.isPowerOfTwo2(9));

        System.out.printf("Is %d a power of 2: %b\n", -2, pot.isPowerOfTwo1(-2));
        System.out.printf("Is %d a power of 2: %b\n", -2, pot.isPowerOfTwo2(-2));
    }

    /**
     * Approach: Bitwise
     *  If a power of 2, n & n-1 will be zero
     *
     * Complexity:
     *  Time: O(1)
     *  Space: O(1)
     *
     */
    public boolean isPowerOfTwo1(int number) {
        return number > 0 && (number & number - 1) == 0;
    }

    /**
     * Approach: Iterative
     *  If a power of 2, and for numbers >= 2,
     *  one right shift and one left shift should always result in the same no
     *
     * Complexity:
     *  Time: O(1). Since we keep dividing the number by 2 (Rt shift), it is O(logN) but the Loop runs 32 times for the 32 bits and so O(1) constant time
     *  Space: O(1)
     */
    public boolean isPowerOfTwo2(int number) {
        if (number <= 0) return false;

        while (number > 2) {
            int temp = number>>1;
            int orig = temp<<1;

            if(number-orig != 0) {
                return false;
            }
            number = number >> 1;
        }

        return true;
    }
}
