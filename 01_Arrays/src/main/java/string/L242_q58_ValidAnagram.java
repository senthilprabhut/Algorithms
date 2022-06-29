package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 *  Given two strings s and t, write a function to determine if t is an anagram of s.
 *  <b> Example 1:</b>
 *  <code>
 *      Input: s = "anagram", t = "nagaram"
 *      Output: true
 *  </code>
 *  <b>Example 2:</b>
 *  <code>
 *      Input: s = "rat", t = "car"
 *      Output: false
 *  </code>
 *
 *
 * Constraints:
 *  <ul>
 *      <li>1 <= s.length, t.length <= 5 * 104</li>
 *      <li>s and t consist of lowercase English letters.</li>
 *  </ul>
 *
 * Follow up:  What if the inputs contain unicode characters? How would you adapt your solution to such case?
 *
 * Links:
 *  https://leetcode.com/problems/valid-anagram/description/
 *  https://www.programcreek.com/2014/05/leetcode-valid-anagram-java/
 */
public class L242_q58_ValidAnagram {
    public static void main(String[] args) {
        L242_q58_ValidAnagram va = new L242_q58_ValidAnagram();
        System.out.println(va.isAnagram1("anagram", "nagaram"));
        System.out.println(va.isAnagram3("anagram", "nagaram"));
        System.out.println(va.isAnagram1("car", "rat"));
        System.out.println(va.isAnagram3("car", "rat"));

        System.out.println(va.isAnagram2("做法", "法做"));
    }

    /**
     * Approach:
     *  Create a size 26 int array as buckets for each letter in alphabet.
     *  It increments the bucket value with String s and decrement with string t.
     *  So if they are anagrams, all buckets should remain with initial value which is zero.
     *  So just check that and return
     *
     * Links:
     *  https://leetcode.com/problems/valid-anagram/discuss/66484/Accepted-Java-O(n)-solution-in-5-lines
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] alphabetCount = new int[26];

        for(int i=0; i < s.length(); i++) {
            alphabetCount[s.charAt(i)-'a']++;
            alphabetCount[t.charAt(i)-'a']--;
        }

        for (int x : alphabetCount) {
            if (x != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach: Unicode Characters
     *  Use prime number array to calculate a hash for each string
     *  Problem is with Integer overflow during multiplication
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-valid-anagram-java/
     *  https://stackoverflow.com/questions/1527856/how-can-i-iterate-through-the-unicode-codepoints-of-a-java-string
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int offset = 0; offset < s.length(); ) {
            int codepoint1 = s.codePointAt(offset);
            map.compute(codepoint1, (key, value) -> {
                if (value == null) {
                    return 1;
                } else if (value == -1) {
                    return null; //Remove key
                } else {
                    return value + 1;
                }
            });

            int codepoint2 = t.codePointAt(offset);
            map.compute(codepoint2, (key,value) -> {
                if (value == null) {
                    return -1;
                } else if (value == 1) {
                    return null; //Remove key
                } else {
                    return value + 1;
                }
            });

            offset += Character.charCount(codepoint1);
        }

        return map.size() == 0;
    }


    /**
     * Approach:
     *  Use prime number array to calculate a hash for each string
     *  Problem is with Integer overflow during multiplication
     *
     * Links:
     *  https://leetcode.com/problems/valid-anagram/discuss/66828/3-solutions:-sort-hash-array-and-prime.
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    private static final int[] PRIMES = new int[] {3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 107};

    public boolean isAnagram3(String s, String t) {
        return hash(s) == hash(t);
    }

    private long hash(String s) {
        long hash = 1;
        for (int i = 0; i < s.length(); i++) {
            hash *= PRIMES[s.charAt(i) - 'a'];
        }
        return hash;
    }
}
