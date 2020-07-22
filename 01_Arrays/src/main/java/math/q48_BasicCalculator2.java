package math;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Implement a basic calculator to evaluate a simple expression string.
 *  The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
 *  The integer division should truncate toward zero.
 *  You may assume that the given expression is always valid. Some examples:
 *      "3+2*2" = 7
 *      " 3/2 " = 1
 *      " 3+5 / 2 " = 5
 * Links:
 *  https://leetcode.com/problems/basic-calculator-ii/description/
 *  https://www.programcreek.com/2014/05/leetcode-basic-calculator-ii-java/
 */
public class q48_BasicCalculator2 {
    public static void main(String[] args) {
        q48_BasicCalculator2 bc1 = new q48_BasicCalculator2();
        System.out.println(bc1.calculate1("3+2*2"));
    }

    /**
     * Approach 1:
     *  The idea is to perform multiplication and division first (they are on the lower level in the expression tree), then subtract and addition.
     *  So basically this is the bottom-up apporach to evaluate the implicit expression tree.
     *
     * Links:
     *  https://leetcode.com/problems/basic-calculator-ii/discuss/63003/Share-my-java-solution - Comment fro cdai
     */
    public int calculate1(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result=0, number=0;

        char prevOp = '+';  //points to the previous operation
        for (int i=0; i<s.length(); i++) {  //op is the last operator
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                if (number * 10 > (Integer.MAX_VALUE - (ch - '0')) )
                    number = Integer.MAX_VALUE;  //Handle Overflows
                else
                    number = number * 10 + (ch - '0');
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) {
                if (prevOp == '+' || prevOp == '-') {
                    int currNumber = (prevOp == '+' ? number : -number);
                    stack.push(currNumber);
                    result += currNumber;
                } else {
                    result -= stack.peek(); //subtract top before mul/div. Coz mul or div takes precedence over prev add/subtract
                    int currNumber = (prevOp == '*' ? stack.pop() * number : stack.pop() / number);
                    stack.push(currNumber);
                    result += currNumber;
                }
                prevOp = ch; //store the current sign to operation
                number = 0;
            }
        }
        return result;
    }
}
