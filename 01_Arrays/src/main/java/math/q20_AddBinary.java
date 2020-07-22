package math;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given two binary strings, return their sum (also a binary string).
 *  For example, a = "11", b = "1"  Return "100".
 *
 * Links:
 *  https://leetcode.com/problems/add-binary/description/
 *  https://www.programcreek.com/2014/05/leetcode-add-binary-java/
 */
public class q20_AddBinary {
    public static void main(String[] args) {
        q20_AddBinary ab = new q20_AddBinary();
        System.out.println(ab.addBinary("11", "1"));
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length()-1, j = b.length()-1, carry = 0;

        while (i >=0 || j >= 0) {
            int sum = carry;

            if(i>=0) sum += a.charAt(i--) - '0';
            if(j>=0) sum += b.charAt(j--) - '0';

            sb.append(sum%2); //the added digit - may be 0 or 1
            carry = sum/2; //carry over is based on the quotient
        }
        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
