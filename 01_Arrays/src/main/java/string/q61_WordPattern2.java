package string;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Difficulty Level: HARD (LOCKED)
 *
 * Problem:
 *  Given a pattern and a string str, find if str follows the same pattern.
 *  Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 *  Examples:
 *      pattern = "abab", str = "redblueredblue" should return true.
 *      pattern = "aaaa", str = "asdasdasdasd" should return true.
 *      pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 * Links:
 *  https://leetcode.com/problems/word-pattern-ii
 *  https://www.programcreek.com/2014/07/leetcode-word-pattern-ii-java/
 */
public class q61_WordPattern2 {
    public static void main(String[] args) {
        q61_WordPattern2 sp2 = new q61_WordPattern2();
        System.out.println(sp2.wordPatternMatch("abab", "redblueredblue")); //true
        System.out.println(sp2.wordPatternMatch("aaaa", "asdasdasdasd"));  //true
        System.out.println(sp2.wordPatternMatch("aabb", "xyzabcxzyabc"));  //false
    }

    /**
     * Approach:
     *  The problem becomes much more difficult after the spaces are removed.
     *  Now we need to determine which part matchs which part by ourselves.
     *
     *  We can solve this problem using backtracking, we just have to keep trying to use a character in the pattern to
     *  match different length of substrings in the input string, keep trying till we go through the input string and the pattern.
     *  
     * Links:
     *  http://massivealgorithms.blogspot.com/2015/10/leetcode-291-word-pattern-ii.html
     *
     * Complexity:
     *
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    private boolean isMatch(String str, int i, String pattern, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pattern.length()) return true;
        if (i == str.length() || j == pattern.length()) return false;

        // get current pattern character
        char c = pattern.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pattern, j + 1, map, set);
        } else {
            // pattern character does not exist in the map
            for (int k = i; k < str.length(); k++) {
                String newSubstr = str.substring(i, k+1);

                if (set.contains(newSubstr)) continue; //We've already processed this substring for this loop

                // create or update it
                map.put(c, newSubstr);
                set.add(newSubstr);

                if (isMatch(str, k+1, pattern, j+1, map, set)) {
                    return true;
                }

                //backtracking
                map.remove(c);
                set.remove(newSubstr);
            }
        }

        // we've tried our best but still no luck
        return false;
    }

}
