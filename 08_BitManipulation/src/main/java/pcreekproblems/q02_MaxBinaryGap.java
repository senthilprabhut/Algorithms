package pcreekproblems;

import bits.LogBase2;

/**
 * Problem:
 *  For example, 9's binary form is 1001, the gap is 2
 *
 * Links:
 *  https://www.programcreek.com/2013/02/twitter-codility-problem-max-binary-gap/
 */
public class q02_MaxBinaryGap {
    public static void main(String[] args) {
        q02_MaxBinaryGap mbg = new q02_MaxBinaryGap();
        System.out.println(mbg.getBinaryGap1(9)); //Answer is 2   -  00001001
        System.out.println(mbg.getBinaryGap1(11)); //Answer is 1  -  00001011
        System.out.println(mbg.getBinaryGap1(12)); //Answer is 0  -  00001100

        System.out.println(mbg.getBinaryGap2(9)); //Answer is 2
        System.out.println(mbg.getBinaryGap2(11)); //Answer is 1
        System.out.println(mbg.getBinaryGap2(12)); //Answer is 0
    }

    /**
     * Approach 1:
     *  An integer x & 1 will get the last digit of the integer
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getBinaryGap1(int number) {
        int count=-1, max=0, MASK=1;

        while (number > 0) {
            if( (number&MASK) == 0 && count >= 0) count++;

            if ((number&MASK) == 1) {
                max = (count > max) ? count : max;
                count = 0;
            }

            number >>>= 1;
        }
        return max;
    }

    /**
     * Approach 2:
     *  https://www.quora.com/In-programming-what-does-n-n-return
     *  n & -n will give the location of rightmost 1 bit
     *  -n is 2's complement - 1's complement + 1
     *
     * Complexity:
     *  Time Complexity is O(log n)
     *  Space Complexity is O(1)
     */
    public int getBinaryGap2(int number) {
        int prevOneIndex=-1, maxLen=0;

        while (number > 0) {
            int rightmostOneBit = number & (-number); //Will be 1, 2, 4 or 8 ... (1, 10, 100, 1000 etc)
            int currentOneIndex = LogBase2.getLogBase2(rightmostOneBit);

            if (prevOneIndex != -1 && Math.abs(currentOneIndex - prevOneIndex) > maxLen) {
                maxLen = Math.abs(currentOneIndex - prevOneIndex) - 1;
            }
            prevOneIndex = currentOneIndex;

            number = number & (number - 1); //Turn off the rightmost 1 bit
        }
        return maxLen;
    }
}
