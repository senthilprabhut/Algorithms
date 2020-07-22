package string;

/**
 * Difficulty Level: HARD  - NOT THE BEST SOLUTION. ERRORS FOR VARIOUS INPUTS
 *
 * Problem:
 *   Implement wildcard pattern matching with support for '?' and '*'.
 *   '?' Matches any single character.
 *   '*' Matches any sequence of characters (including the empty sequence).
 *   The matching should cover the entire input string (not partial).
 *   The function prototype should be:
 *   bool isMatch(const char *s, const char *p)
 *
 *   Some examples:
 *   isMatch("aa","a") → false
 *   isMatch("aa","aa") → true
 *   isMatch("aaa","aa") → false
 *   isMatch("aa", "*") → true
 *   isMatch("aa", "a*") → true
 *   isMatch("ab", "?*") → true
 *   isMatch("aab", "c*a*b") → false
 *
 * Links:
 *  https://leetcode.com/problems/wildcard-matching/description/
 *  https://www.programcreek.com/2014/06/leetcode-wildcard-matching-java/
 */
public class q06_WildcardMatching {
    public static void main(String[] args) {
        q06_WildcardMatching wcm = new q06_WildcardMatching();
        //System.out.println(wcm.comparison("ab", "*"));
        //System.out.println(wcm.comparison("aa", "a*"));
        System.out.println(wcm.comparison("ab*cdec","ab*c"));
    }

    /**
     * Approach 1:
     *  The basic idea is to have one pointer for the string and one pointer for the pattern.
     *  This algorithm iterates at most length(string) + length(pattern) times, for each iteration, at least one pointer advance one step.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/3040/linear-runtime-and-constant-space-solution
     */
    public boolean comparison(String str, String pattern) {
        int s=0, p=0, match=0, starIdx=-1;

        while(s < str.length()) {
            // advancing both the pointers
            if ( p<pattern.length() && pattern.charAt(p) == '?' || (pattern.charAt(p) != '*' && str.charAt(s) == pattern.charAt(p)) ) {
                p++;
                s++;
            }
            // * found, only advancing pattern pointer
            else if (p<pattern.length() && pattern.charAt(p) == '*') {
                starIdx=p;
                //if there is * in the end and string still present
                if(p+1 == pattern.length()) return true;
                p++;
                match=s;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s=match;
            }

            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
}
