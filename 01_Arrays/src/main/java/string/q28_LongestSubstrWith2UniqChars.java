package string;

import java.util.HashMap;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  This is a problem asked by Google.
 *  Given a string, find the longest substring that contains only two unique characters.
 *  For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".
 *
 * Links:
 *  https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters
 *  https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
 */
public class q28_LongestSubstrWith2UniqChars {
    public static void main(String[] args) {
        q28_LongestSubstrWith2UniqChars ls = new q28_LongestSubstrWith2UniqChars();
        System.out.println(ls.lengthOfLongestSubstringTwoDistinct1("abac"));
        System.out.println(ls.lengthOfLongestSubstringTwoDistinct2("abac"));
    }

    /**
     * Approach:
     *  In this solution, a array is used to track the unique elements in the map.
     *  When a third character is added to the map, the left pointer needs to move right.
     *  You can use "abac" to walk through this solution.
     *
     * Links:
     *  https://leetcode.com/problems/minimum-window-substring/discuss/26808
     */
    public int lengthOfLongestSubstringTwoDistinct1(String s) {
        if(s.length() == 0) return 0;

        int[] map = new int[128];
        int begin=0, end=0, maxLength=0, counter=0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map[c]==0) counter++; //Increment the counter when unique char is found
            map[c]++; //Track the no of occurrances of a character
            end++;

            while (counter>2) {
                char tmp = s.charAt(begin);
                if (map[tmp]==1) counter--; //Decrement the counter when unique char is found
                map[tmp]--; //Track the no of occurrances of a character
                begin++;
            }

            maxLength = Math.max(maxLength, end-begin);
        }
        return maxLength;
    }

    /**
     * Approach:
     *  In this solution, a hashmap is used to track the unique elements in the map.
     *  When a third character is added to the map, the left pointer needs to move right.
     *  You can use "abac" to walk through this solution.
     */
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        if(s.length() == 0) return 0;

        HashMap<Character,Integer> countMap = new HashMap<Character, Integer>();
        int left =0, maxLength=0;

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(countMap.containsKey(c))
                countMap.put(c, countMap.get(c)+1);
            else
                countMap.put(c, 1);

            if(countMap.size() > 2) {
                maxLength = Math.max(maxLength, i-left); //Doesn't count the curr value at i - so no +1 for length

                while (countMap.size() > 2) {
                    char ch = s.charAt(left);
                    int count = countMap.get(ch);
                    if (count > 1) {
                        countMap.put(ch, count-1);
                    } else {
                        countMap.remove(ch);
                    }
                    left++;
                }
            }
        }
        maxLength = Math.max(maxLength, s.length()- left);
        return maxLength;
    }
}
