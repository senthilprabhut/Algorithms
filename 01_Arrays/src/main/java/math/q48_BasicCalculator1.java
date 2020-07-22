package math;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Implement a basic calculator to evaluate a simple expression string.
 *  The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *  You may assume that the given expression is always valid.
 *  Some examples:
 *      "1 + 1" = 2
 *      " 2-1 + 2 " = 3
 *      "(1+(4+5+2)-3)+(6+8)" = 23
 *
 * Links:
 *  https://leetcode.com/problems/basic-calculator/description/
 *  https://www.programcreek.com/2014/06/leetcode-basic-calculator-java/
 */
public class q48_BasicCalculator1 {
    public static void main(String[] args) {
        q48_BasicCalculator1 bc1 = new q48_BasicCalculator1();
        System.out.println(bc1.calculate2(" 2-1 + 2 "));
        System.out.println(bc1.calculate1("(1+(4+5+2)-3)+(6+8)"));
    }

    /**
     * Approach 1:
     *  Similar to Approach 2
     *  Sign before '+' or '-' = This context sign
     *  Sign after '+' or '-' = This context sign * (1 or -1)
     *
     * Links:
     *  https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack - Comment fro yavinci
     */
    public int calculate1(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);

        int result=0, number=0, sign=1;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                if (number * 10 > (Integer.MAX_VALUE - (ch - '0')) )
                    number = Integer.MAX_VALUE;  //Handle Overflows
                else
                    number = number * 10 + (ch - '0');
            } else if (ch =='+' || ch == '-') {
                result +=  sign * number;
                sign = stack.peek() * (ch == '+' ? 1 : -1);
                number = 0;
            } else if (ch == '(') {
                stack.push(sign);
            } else if (ch == ')') {
                stack.pop();
            }
        }

        result += sign * number; //if number is 0, nothing is added
        return result;
    }

    /**
     * Approach 2:
     *  Simple iterative solution by identifying characters one by one.
     *  One important thing is that the input is valid, which means the parentheses are always paired and in order.
     *  Only 5 possible input we need to pay attention:
     *      digit: it should be one digit from the current number
     *      ‘+’: number is over, we can add the previous number and start a new number
     *      ‘-’: same as above
     *      ‘(’: push the previous result and the sign into the stack, set result to 0, just calculate2 the new result within the parenthesis.
     *      ‘)’: pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
     *      Finally if there is only one number, from the above solution, we haven’t add the number to the result, so we do a check see if the number is zero.
     * Links:
     *  https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack
     */
    public int calculate2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        int result = 0, number=0, sign=1;

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result = result + sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result = result + sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);

                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result = result + sign * number;
                number=0;

                result = result * stack.pop(); //stack.pop() is the sign before the parenthesis
                result = result + stack.pop();  //stack.pop() now is the result calculated before the parenthesis
            }
        }

        if (number != 0) result += sign * number;
        return result;
    }
}
