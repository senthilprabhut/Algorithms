package permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *  For example, given n = 3, a solution set is:
 *  "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 * To Study:
 *  The number of ways to write n pairs of properly matched parentheses is the nth Catalan number
 *  Catalan numbers - https://en.wikipedia.org/wiki/Catalan_number
 *
 * Links:
 *  https://leetcode.com/problems/generate-parentheses
 *  https://www.programcreek.com/2014/01/leetcode-generate-parentheses-java
 *
 */
public class q04_GenerateParentheses {
    public static void main(String[] args) {
        q04_GenerateParentheses gp = new q04_GenerateParentheses();
        System.out.println(gp.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        dfs(n,n,new StringBuilder(),result);
        return result;
    }

    /**
     * Approach 1:
     *  openRemaining and closeRemaining represents the remaining number of ( and ) that need to be added.
     *  When openRemaining > closeRemaining, there are more ")" placed than "(". Such cases are wrong and the method stops.
     *
     * Links:
     *  http://www.programcreek.com/2014/01/leetcode-generate-parentheses-java
     *
     * Complexity:
     *  Time complexity: Since we construct a recursive tree for all the parenthesis, we do a max of 2ⁿ-1 recursive calls.
     *      So time complexity is O(2ⁿ)
     *  Space complexity: O(2ⁿ) for the stack usage of the recursive calls
     */
    private void dfs(int openRemaining, int closeRemaining, StringBuilder current, List<String> results) {
        if(openRemaining > closeRemaining) return;

        if(openRemaining==0 && closeRemaining==0) {
            results.add(current.toString());
            return;
        }

        if(openRemaining>0) {
            current.append("(");
            dfs(openRemaining-1, closeRemaining, current, results);
            current.setLength(current.length()-1);
        }

        if(closeRemaining>0) {
            current.append(")");
            dfs(openRemaining,closeRemaining-1, current, results);
            current.setLength(current.length()-1);
        }
    }

    /**
     * Approach 2:
     *  The idea here is to only add '(' and ')' that we know will guarantee us a solution (instead of adding 1 too many close).
     *  Once we add a '(' we will then discard it and try a ')' which can only close a valid '('. Each of these steps are recursively called.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/8724/easy-to-understand-java-backtracking-solution      comment: yfcheng
     *
     * Complexity:
     *  Time complexity: Since we construct a recursive tree for all the parenthesis, we do a max of 2ⁿ-1 recursive calls.
     *      So time complexity is O(2ⁿ)
     *  Space complexity: O(2ⁿ) for the stack usage of the recursive calls
     */
    public List<String> generateParenthesis2(int n) {
        List<String> results = new ArrayList<String>();
        backtrack(0,0,n,new StringBuilder(),results);
        return results;
    }

    private void backtrack(int open, int close, int max, StringBuilder current, List<String> results) {
        if(current.length() == 2*max) {
            results.add(current.toString());
            return;
        }
        if(open < max) {
            current.append("(");
            backtrack(open+1, close, max, current, results);
            current.setLength(current.length()-1);
        }
        if(close < open) {
            current.append(")");
            backtrack(open, close+1, max, current, results);
            current.setLength(current.length()-1);
        }
    }

}
