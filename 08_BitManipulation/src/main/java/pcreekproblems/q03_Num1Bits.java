package pcreekproblems;

/**
 * Problem:
 *  Write a function that takes an unsigned integer and returns the number of ’1' bits it has
 *  (also known as the Hamming weight).
 *
 * Links:
 *  https://www.programcreek.com/2014/03/leetcode-number-of-1-bits-java/
 *  http://traceformula.blogspot.in/2015/08/number-of-1-bits.html
 */
public class q03_Num1Bits {
    public static void main(String[] args) {
        q03_Num1Bits sn = new q03_Num1Bits();
        System.out.println(sn.getNumBits1(9));
        System.out.println(sn.getNumBits2(7));
    }


    /**
     * Approach 1:
     *  we iteratively check the right most bit if it is 1,
     *  then shift all the bits to the right 1 bit (i.e divide it by 2).
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getNumBits1(int input) {
        int count = 0;

        while (input !=0) {
            if ((input & 1) == 1) count++;
            input >>>= 1;
        }
        return count;
    }

    /**
     * Approach 1:
     *  we iteratively check the right most bit if it is 1,
     *  then shift all the bits to the right 1 bit (i.e divide it by 2).
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getNumBits2(int input) {
        int count = 0;

        while (input != 0) {
            input &= (input-1);
            count++;
        }
        return count;
    }
}
