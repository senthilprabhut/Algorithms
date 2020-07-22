package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a string, find the length of the longest substring without repeating characters.
 *  Examples:
 *      Given "abcabcbb", the answer is "abc", which the length is 3.
 *      Given "bbbbb", the answer is "b", with the length of 1.
 *      Given "pwwkew", the answer is "wke", with the length of 3.
 *  Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Links:
 *  https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *  https://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
 */
public class q27_LongestSubstringWithoutRepeatingChars {
    public static void main(String[] args) {
        q27_LongestSubstringWithoutRepeatingChars ls = new q27_LongestSubstringWithoutRepeatingChars();
        System.out.println(ls.lengthOfLongestSubstring("abcabbbabc"));
    }

    /**
     * Approach:
     *  The basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values,
     *  and keep two pointers which define the max substring. move the right pointer to scan through the string ,
     *  and meanwhile update the hashmap. If the character is already in the hashmap, then move the left pointer
     *  to the right of the same character last found. Note that the two pointers can only move forward.
     *
     * Link:
     *  https://discuss.leetcode.com/topic/8232/11-line-simple-java-solution-o-n-with-explanation
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        Map<Character,Integer> map = new HashMap<>();
        int maxLength=0, start=0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1); //ptr moves only in forward direction
            }
            map.put(c, i);
            maxLength = Math.max(maxLength,i-start+1);
        }
        return maxLength;
    }
}
