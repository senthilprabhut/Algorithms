/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 */

package pcreekproblems;

/**
 * Problem:
 *  Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *  For example:
 *  Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Links:
 *  https://leetcode.com/problems/add-digits/description/
 *  https://www.programcreek.com/2014/04/leetcode-add-digits-java/
 */
public class q18_AddDigits {
    public static void main(String[] args) {
        q18_AddDigits ad = new q18_AddDigits();
        System.out.printf("The reduced digits of %d is %d\n", 38, ad.addDigits1(38));
        System.out.printf("The reduced digits of %d is %d\n", 38, ad.addDigits2(38));
    }

    /**
     * Approach:
     *
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int addDigits1(int number) {
        int sum = 0;

        String str = String.valueOf(number);
        for(int i=0; i<str.length(); i++) {
            sum += str.charAt(i) - '0';
        }

        if(sum < 10) return sum; //Single digit

        return addDigits1(sum); //recurse
    }

    /**
     * Approach:
     *
     * Links:
     *  https://discuss.leetcode.com/topic/21498/accepted-c-o-1-time-o-1-space-1-line-solution-with-detail-explanations
     *  https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
     *
     * Complexity:
     *  Time Complexity is O(1)
     *  Space Complexity is O(1)
     */
    public int addDigits2(int num) {
        return 1 + (num - 1) % 9;
    }
}
