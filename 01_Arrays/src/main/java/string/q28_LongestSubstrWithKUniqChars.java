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
 *  https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters
 *  https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
 */
public class q28_LongestSubstrWithKUniqChars {
    public static void main(String[] args) {
        q28_LongestSubstrWithKUniqChars ls = new q28_LongestSubstrWithKUniqChars();
        System.out.println(ls.lengthOfLongestSubstringKDistinct1("abcbbbbcccbdddadacb", 2));
        System.out.println(ls.lengthOfLongestSubstringKDistinct2("abcbbbbcccbdddadacb", 2));
    }

    /**
     * Approach:
     *  In this solution, an array is used to track the unique elements in the map.
     *  When a third character is added to the map, the left pointer needs to move right.
     *  You can use "abac" to walk through this solution.
     *
     * Links:
     *  https://leetcode.com/problems/minimum-window-substring/discuss/26808
     */
    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        if (s.length() == 0) return 0;

        int[] map = new int[128];
        int begin=0, end=0, counter=0, maxLength=0, maxStart=0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map[c] == 0) counter++; //Encountered unique char
            map[c]++; //Count the occurrances
            end++;

            while (counter > k) {
                //Move the window from the beginning
                char tmp = s.charAt(begin);
                if (map[tmp] == 1) {
                    counter--; //If unique char is removed, decrease counter
                    maxStart=begin;
                }
                map[tmp]--; //Decrease the occurrances
                begin++;
            }
            maxLength = Math.max(maxLength, end-begin);
        }
        //System.out.println(s.substring(maxStart, maxStart+maxLength));
        return maxLength;
    }

    /**
     * Approach:
     *  In this solution, a hashmap is used to track the unique elements in the map.
     *  When a third character is added to the map, the left pointer needs to move right.
     *  You can use "abac" to walk through this solution.
     */
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if(k == 0 || s  == null || s.length() == 0) return 0;

        if(s.length() < k) return s.length();

        HashMap<Character,Integer> countMap = new HashMap<Character, Integer>();
        int left=0, maxLength=k;

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(countMap.containsKey(c))
                countMap.put(c, countMap.get(c)+1);
            else
                countMap.put(c, 1);

            if(countMap.size() > k) {
                maxLength = Math.max(maxLength, i-left); //We don't include the curr char - so no +1 here

                while (countMap.size() > k) {
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
        maxLength = Math.max(maxLength, s.length()-left);
        return maxLength;
    }
}
