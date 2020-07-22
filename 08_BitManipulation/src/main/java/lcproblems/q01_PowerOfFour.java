/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 */

package lcproblems;

/**
 * Problem:
 *   Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *   Given num = 16, return true. Given num = 5, return false.
 *
 * Links:
 *  https://leetcode.com/problems/power-of-four/description/
 *
 */
public class q01_PowerOfFour {
    public static void main(String[] args) {
        q01_PowerOfFour pof = new q01_PowerOfFour();
        System.out.printf("Is %d power of 4: %b\n", 16, pof.isPowerOfFour(16));
        System.out.printf("Is %d power of 4: %b\n", 5, pof.isPowerOfFour(5));
    }

    public boolean isPowerOfFour(int num) {
        return num>0 && (num&(num-1)) == 0 &&  (num & 0x55555555) != 0;
        //0x55555555 is to get rid of those power of 2 but not power of 4
        //so that the single 1 bit always appears at the odd position

        //Each hexadecimal can be rewrote as a 4 digital binary. 0x means hexadecimal, 5 is 4+1 = 0101.
        //0x55555555 = 0b0101 0101 0101 0101 0101 0101 0101 0101 //32 bits with 1 in odd positions
    }
}
