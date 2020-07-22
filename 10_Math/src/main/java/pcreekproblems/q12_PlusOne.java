package pcreekproblems;

import java.util.Arrays;

/**
 * Problem:
 *  Given a non-negative number represented as an array of digits, plus one to the number.
 *  The digits are stored such that the most significant digit is at the head of the list.
 *  You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * Links:
 *  https://leetcode.com/problems/plus-one/description/
 *  https://www.programcreek.com/2014/05/leetcode-plus-one-java/
 */
public class q12_PlusOne {
    public static void main(String[] args) {
        q12_PlusOne po = new q12_PlusOne();
        int[] number = new int[] {9,9,9};
        System.out.printf("Adding one to %d is %s%n",999, Arrays.toString(po.plusOne1(number)) );
        int[] number2 = new int[] {9,9,9};
        System.out.printf("Adding one to %d is %s%n",999, Arrays.toString(po.plusOne2(number2)) );

        int[] number3 = new int[] {1,5};
        System.out.printf("Adding one to %d is %s%n",15, Arrays.toString(po.plusOne1(number3)) );
        int[] number4 = new int[] {1,5};
        System.out.printf("Adding one to %d is %s%n",15, Arrays.toString(po.plusOne2(number4)) );
    }

    /**
     * Approach:
     *
     * Links:
     *  https://discuss.leetcode.com/topic/19149/simple-java-solution
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int[] plusOne1(int[] digits) {
        for(int i=digits.length-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i] ++; //plus one
                break;
            } else {
                digits[i] = 0;
            }
        }

        if(digits[0] == 0) { //Overflow - 999 + 1 case
            int[] newArray = new int[digits.length+1];
            System.arraycopy(digits,0,newArray,1, digits.length);
            newArray[0] = 1;
            return newArray;
        }
        return digits;
    }

    /**
     * Approach:
     *  we can use a flag to mark if the current digit needs to be changed.
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-plus-one-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int[] plusOne2(int[] digits) {
        if(digits==null||digits.length==0)
            return new int[0];

        int carry = 1; //Plus one value

        for(int i=digits.length-1; i>=0; i--) {
            int sum = digits[i] + carry;
            if(sum >= 10) carry = 1;
            else carry = 0;
            digits[i] = sum%10;
        }

        if(carry == 1) {
            int[] newArray = new int[digits.length+1];
            System.arraycopy(digits,0,newArray,1, digits.length);
            newArray[0] = 1;
            return newArray;
        }

        return digits;
    }
}
