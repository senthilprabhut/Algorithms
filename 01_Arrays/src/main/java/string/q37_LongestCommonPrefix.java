package string;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Write a function to find the longest common prefix string amongst an array of strings
 *  For example:
 *      {“a”,“a”,“b”} should give “” as there is nothing common in all the 3 strings.
 *      {“a”, “a”} should give “a” as a is longest common prefix in all the strings.
 *      {“abca”, “abc”} as abc
 *      {“ac”, “ac”, “a”, “a”} as a
 *
 * Links:
 *  https://leetcode.com/problems/longest-common-prefix/description/
 *  https://www.programcreek.com/2014/02/leetcode-longest-common-prefix-java/
 */
public class q37_LongestCommonPrefix {
    public static void main(String[] args) {
        q37_LongestCommonPrefix lcp = new q37_LongestCommonPrefix();
        System.out.println(lcp.longestCommonPrefix(new String[]{"a","a","b"})); //""
        System.out.println(lcp.longestCommonPrefix(new String[]{"a","a"}));  // a
        System.out.println(lcp.longestCommonPrefix(new String[]{"abca","abc"})); // abc
        System.out.println(lcp.longestCommonPrefix(new String[]{"ac","ac", "a", "a"})); // a
    }

    /**
     * Approach:
     *  Start at index 0
     *  Check each string if they have the same char at the index
     *  If all have the same char increment the index
     *  Terminate if any mismatch is found or any string has exceeded it’s length
     *
     * Links:
     *  https://leetcode.com/problems/longest-common-prefix/discuss/6926
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public String longestCommonPrefix(String[] strs) {
        int minLen=0; //serves both as index and also the length of the smallest string

        while (strs != null && strs.length > 0) {
            for(String str : strs) {
                if (str.length() == minLen || str.charAt(minLen) != strs[0].charAt(minLen))
                    return strs[0].substring(0,minLen);
            }
            minLen++;
        }
        return ""; //Takes care of 0 length input
    }
}
