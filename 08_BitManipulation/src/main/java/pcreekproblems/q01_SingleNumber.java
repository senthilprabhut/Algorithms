package pcreekproblems;

/**
 * Problem:
 *  Given an array of integers, every element appears twice except for one. Find that single one.
 *
 * Links:
 *  https://www.programcreek.com/2012/12/leetcode-solution-of-single-number-in-java/
 */
public class q01_SingleNumber {
    public static void main(String[] args) {
        int[] arr = new int[] {23, 166, 53, 23, 77, 53, 166};

        q01_SingleNumber sn = new q01_SingleNumber();
        System.out.println(sn.getSingleNumber(arr));
    }

    /**
     * Approach:
     *  The key to solve this problem is bit manipulation.
     *  XOR will return 1 only on two different bits.
     *  So if two numbers are the same, XOR will return 0.
     *  Finally only one number left.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getSingleNumber(int[] input) {
        int x=0;
        for(int number : input)
            x = x ^ number;

        return x;
    }
}
