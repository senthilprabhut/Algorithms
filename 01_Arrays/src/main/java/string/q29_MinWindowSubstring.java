package string;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *   Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *   For example, S = "ADOBECODEBANC", T = "ABC", Minimum window is "BANC".
 *    Note: If there is no such window in S that covers all characters in T, return the empty string "".
 *    If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * Note:
 *  the same idea (maintaining a satisfied window) can be using to three problems in leetcode.
 *  “Longest Substring Without Repeating Characters”, “Substring with Concatenation of All Words” and “Minimum Window Substring”
 *
 * Links:
 *  https://leetcode.com/problems/minimum-window-substring/description/
 *  https://www.programcreek.com/2014/05/leetcode-minimum-window-substring-java/
 */
public class q29_MinWindowSubstring {
    public static void main(String[] args) {
        q29_MinWindowSubstring ls = new q29_MinWindowSubstring();
        System.out.println(ls.minWindow("ADOBECODEBANC", "ABC")); //"BANC"
    }

    /**
     * Approach:
     *  Sliding Window
     *  This problem is similar (almost the same) to Longest Substring Which Contains 2 Unique Characters.
     *  1. Use two pointers: start and end to represent a window.
     *  2. Move end to find a valid window.
     *  3. When a valid window is found, move start to find a smaller window.
     *
     * Link:
     *  https://leetcode.com/problems/minimum-window-substring/discuss/26808
     *
     * Complexity:
     *  Time is O(N)
     */
    public String minWindow(String sentence, String T) {
        if(T == null || T.length() == 0 || sentence  == null || sentence.length() == 0 || sentence.length() < T.length()) return "";

        //character counter for t
        int[] map = new int[128];
        for (char c : T.toCharArray())
            map[c] ++;

        //counter represents the number of chars of t to be found in s
        int start=0, end=0, minStart=0, minLen = Integer.MAX_VALUE, counter=T.length();

        //move end to find a valid window
        while (end < sentence.length()) {
            char c1 = sentence.charAt(end);

            //if char in s exists in t, decrease counter
            if (map[c1] > 0) counter--;

            //decrease map[c1]. If char does not exist in t, map[c1] will be negative
            map[c1]--;

            end++;

            //when we found a valid window, move start to find smaller window.
            while (counter == 0) {
                if (minLen > end-start) {
                    minLen = end-start;
                    minStart = start;
                }

                //when char exists in t, increase counter
                char c2 = sentence.charAt(start);
                map[c2]++;
                if(map[c2] > 0) counter++;

                //leave out the char at start
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : sentence.substring(minStart, minStart + minLen);
    }
}
