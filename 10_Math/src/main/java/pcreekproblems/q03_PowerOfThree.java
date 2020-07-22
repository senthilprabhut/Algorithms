package pcreekproblems;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an integer, write a function to determine if it is a power of three
 *  Can you do it without loop or recursion
 *
 * Links:
 *  https://leetcode.com/problems/power-of-three/description/
 *  https://www.programcreek.com/2014/04/leetcode-power-of-three-java/
 */
public class q03_PowerOfThree {
    public static void main(String[] args) {
        q03_PowerOfThree pot = new q03_PowerOfThree();
        System.out.printf("Is %d a power of 3: %b\n", 8, pot.isPowerOfThreeIter(8));
        System.out.printf("Is %d a power of 3: %b\n", 8, pot.isPowerOfThreeRec(8));
        System.out.printf("Is %d a power of 3: %b\n", 8, pot.isPowerOfThreeMathLog10(8));
        System.out.printf("Is %d a power of 3: %b\n", 8, pot.isPowerOfThreeMathLog(8));

        System.out.printf("Is %d a power of 3: %b\n", 9, pot.isPowerOfThreeIter(9));
        System.out.printf("Is %d a power of 3: %b\n", 9, pot.isPowerOfThreeRec(9));
        System.out.printf("Is %d a power of 3: %b\n", 9, pot.isPowerOfThreeMathLog10(9));
        System.out.printf("Is %d a power of 3: %b\n", 9, pot.isPowerOfThreeMathLog(9));

        System.out.printf("Is %d a power of 3: %b\n", -2, pot.isPowerOfThreeIter(-2));
        System.out.printf("Is %d a power of 3: %b\n", -2, pot.isPowerOfThreeRec(-2));
        System.out.printf("Is %d a power of 3: %b\n", -2, pot.isPowerOfThreeMathLog10(-2));
        System.out.printf("Is %d a power of 3: %b\n", -2, pot.isPowerOfThreeMathLog(-2));
    }

    /**
     * Approach: Iteration
     *
     * Links:
     *  https://leetcode.com/problems/power-of-three/discuss/77876
     *
     * Complexity:
     *  Time: O(n), n is the power  or O(log number)
     *  Space: O(1)
     */
    private boolean isPowerOfThreeIter(int number) {
        if (number > 1) {
            while (number % 3 == 0) {
                number /= 3;
            }
        }
        return number == 1;
    }

    /**
     * Approach: Recursion
     *
     * Links:
     *  https://leetcode.com/problems/power-of-three/discuss/77876
     *
     * Complexity:
     *  Time: O(n), n is the power
     *  Space: O(n), n is the power
     */
    private boolean isPowerOfThreeRec(int number) {
        if (number == 1) {
            return true;
        }

        if (number > 1) {
            return (number % 3 == 0) && isPowerOfThreeRec(number / 3);
        }

        return false;
    }

    /**
     * Approach:
     *  Math:  logₓⁿ = log₁₀ⁿ / log₁₀ˣ. This returns a double with 0 after decimal point
     *  You cannot use natural log here because it'll generate round off error for n=243 (coincidence)
     *  This happens because log(3) is slightly larger than its true value due to round off which makes the ratio smaller
     *
     * Links:
     *  https://leetcode.com/problems/power-of-three/discuss/77876
     *
     * Complexity:
     *  Time: O(n), n is the power
     *  Space: O(1)
     */
    private boolean isPowerOfThreeMathLog10(int number) {
        //Check if digit after decimal point is 0
        return number > 0 && ( (Math.log10(number) / Math.log10(3)) % 1 == 0);
    }

    /**
     * Approach:
     *  Math:  logₓⁿ = log₂ⁿ / log₂ˣ
     *  Make a check again if we can get original number with the root and 3
     *
     * Links:
     *  https://leetcode.com/problems/power-of-three/discuss/77876
     *
     * Complexity:
     *  Time: O(n), n is the power
     *  Space: O(1)
     */
    private boolean isPowerOfThreeMathLog(int number) {
        int root = (int) Math.round(Math.log10(number) / Math.log10(3));
        return number == Math.pow(3, root);
    }
}
