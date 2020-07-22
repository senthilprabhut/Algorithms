package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *  Note: The input string may contain letters other than the parentheses ( and ).
 *  Examples:   "()())()" -> ["()()()", "(())()"]
 *              "(a)())()" -> ["(a)()()", "(a())()"]
 *              ")(" -> [""]
 *
 * Links:
 *  https://leetcode.com/problems/remove-invalid-parentheses/description/
 *  https://www.programcreek.com/2014/05/leetcode-remove-invalid-parentheses-java/
 */
public class q69_RemoveInvalidParentheses {
    public static void main(String[] args) {
        q69_RemoveInvalidParentheses rip = new q69_RemoveInvalidParentheses();
        System.out.println(rip.removeInvalidParentheses1("()())()"));
        System.out.println(rip.removeInvalidParentheses2("()())()"));
    }

    /**
     * Approach: DFS approach
     *  We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
     *  The counter will increase when it is ‘(‘ and decrease when it is ‘)’.
     *  Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
     *  To make the prefix valid, we need to remove a ‘)’. The problem is: which one? we restrict ourself to remove the first ) in a series of concecutive )s.
     *  After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string.
     *  However, we need to keep another information: the last removal position.
     *  If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
     *  For this, we keep tracking the last removal position and only remove ‘)’ after that.
     *  What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘? - reverse the string and reuse the code!
     * Explanation:
     *  https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
     *
     * Links:
     *  https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
     *
     * Complexity:
     *
     */
    public List<String> removeInvalidParentheses1(String s) {
        List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private void remove(String input, List<String> result, int last_i, int last_j, char[] par) {
        //Loop through the string and find the count of '(' and ')' brackets
        //If it goes -ve, it means we have more ')' than the '(' - so we need to remove the ')'
        for (int stack=0, i=last_i; i<input.length(); i++) {
            if (input.charAt(i) == par[0]) stack++;  //opening braces
            if (input.charAt(i) == par[1]) stack--;  //closing braces

            if (stack >= 0) continue; //as long as there are opening braces, continue. We remove only closing braces now

            //We now have more closing braces than opening braces - we remove it only till the char 'i' that we looked at till now
            for (int j=last_j; j<=i; j++) {
                //Make sure to remove first available ')' or the first in the consecutive ')'
                //For eg. in '()())', it'll first remove braces and result will be '(())' and '()()'. It'll not remove the 2nd consecutive ')' and the loop continues
                if (input.charAt(j) == par[1] && (j == last_j || input.charAt(j-1) != par[1]) ) {
                    //Remove char at j
                    remove(input.substring(0,j) + input.substring(j+1), result, i, j, par);
                }
            }
            return;
        }

        String reversed = new StringBuilder(input).reverse().toString();
        if (par[0] == '(') {  // finished left to right
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            // finished right to left
            result.add(reversed);
        }
    }

    /**
     * Approach: BFS approach
     *  The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ),
     *  check if they are valid, if found valid ones on the current level, put them to the final result list and we are done,
     *  otherwise, add them to a queue and carry on to the next level.
     *
     * Links:
     *  https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
     *
     * Complexity:
     *  Time is T(n) = n x C(n, n) + (n-1) x C(n, n-1) + … + 1 x C(n, 1) = n x 2^(n-1)
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> result = new ArrayList<>();

        // sanity check
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (isValid(curr)) {
                result.add(curr);
                continue;
            }

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                String t = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count=0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }
}
