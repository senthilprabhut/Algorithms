package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *   Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *   The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *
 * Links:
 *  https://leetcode.com/problems/valid-parentheses/description/
 *  https://www.programcreek.com/2012/12/leetcode-valid-parentheses-java/
 */
public class q13_ValidParenthesis {
    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if(c == '(') stack.push(')');  // push the opposite bracket for each opening bracket
            else if(c == '{') stack.push('}');
            else if(c == '[') stack.push(']');
            else if(stack.isEmpty() || stack.pop() != c) return false; //check the closing bracket against one in stack
        }
        return stack.isEmpty();
    }
}
