package pcreekproblems;

import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *  If the fractional part is repeating, enclose the repeating part in parentheses.
 *  For example,
 *      Given numerator = 1, denominator = 2, return "0.5"
 *      Given numerator = 2, denominator = 1, return "2"
 *      Given numerator = 2, denominator = 3, return "0.(6)"
 *
 * Links:
 *  https://leetcode.com/problems/fraction-to-recurring-decimal/description/
 *  https://discuss.leetcode.com/topic/7876/my-clean-java-solution
 *  https://www.programcreek.com/2014/03/leetcode-fraction-to-recurring-decimal-java/
 */
public class q06_FractionToRecDecimal {
    public static void main(String[] args) {
        q06_FractionToRecDecimal ftd = new q06_FractionToRecDecimal();
        System.out.printf("Fraction to decimal of %d/%d is %s\n", 22, 7, ftd.fracToDecimal(22, 7));
        System.out.printf("Fraction to decimal of %d/%d is %s\n", 10, 3, ftd.fracToDecimal(10, 3));
        System.out.printf("Fraction to decimal of %d/%d is %s\n", 2, 1, ftd.fracToDecimal(2, 1));
        System.out.printf("Fraction to decimal of %d/%d is %s\n", 2, 0, ftd.fracToDecimal(2, 0));
    }

    public String fracToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        if(numerator == 0) return "0";
        if(denominator == 0) return "∞";

        if(numerator<0 || denominator < 0) result.append("-");

        numerator = Math.abs(numerator);
        denominator=Math.abs(denominator);

        int quotient = numerator/denominator;
        result.append(quotient);

        int remainder = numerator%denominator;
        if(remainder == 0) { //if exact division
            return result.toString();
        }

        //Fraction part
        result.append(".");
        Map<Integer,Integer> map = new HashMap<>();
        while (remainder > 0) {
            remainder = remainder * 10; //Adding a . adds a zero
            quotient = remainder/denominator;

            remainder = remainder%denominator;
            if(map.containsKey(remainder)) { //Add a bracket and stop
                int index = map.get(remainder);
                result.insert(index, "(");
                result.append(")");
                break;
            } else {
                result.append(quotient); //Append only if it is not repeating
                map.put(remainder, result.length()-1);
            }
        }
        return result.toString();
    }
}
