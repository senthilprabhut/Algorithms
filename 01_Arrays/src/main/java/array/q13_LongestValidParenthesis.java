package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 *  parentheses substring. For "(()", the longest valid parentheses substring is "()", which has length = 2.
 *  Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *
 * Links:
 *  https://leetcode.com/problems/longest-valid-parentheses/description/
 *  https://www.programcreek.com/2014/06/leetcode-longest-valid-parentheses-java/
 */
public class q13_LongestValidParenthesis {

    public static void main(String[] args) {
        q13_LongestValidParenthesis lvp = new q13_LongestValidParenthesis();
        System.out.println(lvp.validParenthesesLength("(()"));
        System.out.println(lvp.validParenthesesLength(")()())"));

        System.out.println(lvp.validParenthesesLength2("(()"));
        System.out.println(lvp.validParenthesesLength2("))()())()"));
    }

    /**
     * Approach 1:
     *  The workflow of the solution is as below.
     *  1. Scan the string from beginning to end.
     *  2. If current character is '(', push its index to the stack. If current character is ')' and the
     *  character at the index of the top of stack is '(', we just find a
     *  matching pair so pop from the stack.
     *  3. Otherwise, we push the index of ')' to the stack.
     *  After the scan is done, the stack will only contain the indices of characters which cannot be matched. Then
     *  let's use the opposite side - substring between adjacent indices should be valid parentheses.
     *  4. If the stack is empty, the whole input string is valid. Otherwise, we can scan the stack to get longest
     *  valid substring as described in step 3.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/2289/my-o-n-solution-using-a-stack
     *
     * Complexity:
     *  Time: O(n)
     *  Space: O(n) for extra stack
     */
    public int validParenthesesLength(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        //First pass
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && input.charAt(stack.peek()) == '(')
                    stack.pop(); //Matching pair found
                else
                    stack.push(i);
            }
        }
        if(stack.isEmpty()) return input.length(); //All are matching pairs
        else {
            int endLocation = input.length();
            int length = 0, maxLength = 0;
            while (!stack.isEmpty()) {
                int currLocation = stack.pop();
                length = endLocation - (currLocation+1);  //+1 to convert index to location
                if(length > maxLength) maxLength = length;
                endLocation = currLocation;
            }
            return maxLength;
        }
    }

    /**
     * Approach 2:
     *  DP solution
     *  1. If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.
     *  2. Else if s[i] is ')'
     *      If s[i-1] is '(', longest[i] = longest[i-2] + 2
     *      Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]
     *
     * Links:
     *  https://discuss.leetcode.com/topic/2426/my-dp-o-n-solution-without-using-stack
     *
     * Complexity:
     *  Time: O(n)
     *  Space: O(n) for the longest[n] array
     */
    public int validParenthesesLength2(String input) {
        int[] longest = new int[input.length()];
        int currMax=0;

        for (int i=1; i<input.length(); i++) {
            if(input.charAt(i) == ')'){
                if (input.charAt(i-1) == '(') {
                    longest[i] = (i - 2) >= 0 ? (longest[i - 2] + 2) : 2; //Open and Close - so size 2
                    currMax = Math.max(longest[i], currMax);
                }
                else {// if s[i-1] == ')' combine the previous length by checking if there was an open bracket b4 that
                    int prevIndex = (i-1) - longest[i-1];
                    if (prevIndex >= 0 && input.charAt(prevIndex) == '(') {
                        longest[i] = 2 + longest[i-1] + (prevIndex-1 >=0 ? longest[prevIndex-1] : 0);
                        currMax = Math.max(longest[i], currMax);
                    }
                }
            }
            //else if(input.charAt(i) == '(') skip it because longest[i] is 0 by default
        }
        return currMax;
    }
}
