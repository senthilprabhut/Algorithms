/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 */

package pcreekproblems;

/**
 * Problem:
 *   Write a program to check whether a given number is an ugly number.
 *   Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *   For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 *   Note that 1 is typically treated as an ugly number.
 *
 * Links:
 *  https://leetcode.com/problems/ugly-number/description/
 *  https://discuss.leetcode.com/topic/21785/2-4-lines-every-language
 *  https://www.programcreek.com/2014/05/leetcode-ugly-number-java/
 */
public class q19_UglyNumber {
    public static void main(String[] args) {
        q19_UglyNumber un = new q19_UglyNumber();
        System.out.printf("Is %d ugly: %b\n", 14, un.isUgly(14));
        System.out.printf("Is %d ugly: %b\n", 15, un.isUgly(15));
    }

    public boolean isUgly(int num) {
        if (num <= 0) return false;

        for(int i=2; i<=5; i++) {
            if(i == 4) continue;

            while (num%i == 0)  //Repeatedly divide by i, i = 2, 3, 5
                num = num/i;
        }
        return num == 1;
    }
}
