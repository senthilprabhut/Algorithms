package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given a pattern and a string str, find if str follows the same pattern.
 *  Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *  Examples:
 *      1. pattern = "abba", str = "dog cat cat dog" should return true
 *      2. pattern = "abba", str = "dog cat cat fish" should return false
 *      3. pattern = "aaaa", str = "dog cat cat dog" should return false
 *      4. pattern = "abba", str = "dog dog dog dog" should return false
 *
 * Links:
 *  https://leetcode.com/problems/word-pattern/description/
 *  https://www.programcreek.com/2014/05/leetcode-word-pattern-java/
 *  https://www.youtube.com/watch?v=SCW3RzhEdvk
 */
public class q61_WordPattern1 {
    public static void main(String[] args) {
        q61_WordPattern1 wp = new q61_WordPattern1();
        System.out.println(wp.wordPattern("abba", "dog cat cat dog"));
        System.out.println(wp.wordPattern("abba", "dog cat cat fish"));
        System.out.println(wp.wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(wp.wordPattern("abba", "dog dog dog dog"));
    }

    /**
     * Approach:
     *  I go through the pattern letters and words in parallel and compare the indexes where they last appeared.
     *
     * Links:
     *  https://leetcode.com/problems/word-pattern/discuss/73402/8-lines-simple-Java?page=2
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");

        if (words.length != pattern.length()) return false;

        Map index = new HashMap();
        for (Integer i=0; i<words.length; i++) {
            //this checks if the prev values returned from index match between words and pattern
            //If no prev value, null will be returned
            if (index.put(words[i], i) != index.put(pattern.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }

}
