package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 *  Given two strings s and t, determine if they are isomorphic.
 *  Two strings are isomorphic if the characters in s can be replaced to get t.
 *  All occurrences of a character must be replaced with another character while preserving the order of characters.
 *  No two characters may map to the same character but a character may map to itself.
 *  For example,
 *      Given "egg", "add", return true.
 *      Given "foo", "bar", return false.
 *      Given "paper", "title", return true.
 *  Note:
 *      You may assume both s and t have the same length.
 *
 * Links:
 *  https://leetcode.com/problems/isomorphic-strings/description/
 *  https://www.programcreek.com/2014/05/leetcode-isomorphic-strings-java/
 *
 */
public class q03_IsomorphicStrings {
    public static void main(String[] args) {
        q03_IsomorphicStrings is = new q03_IsomorphicStrings();
        System.out.println(is.isIsomorphic1("egg", "add"));
        System.out.println(is.isIsomorphic1("foo", "bar"));
        System.out.println(is.isIsomorphic1("paper", "title"));

        System.out.println(is.isIsomorphic2("egg", "add"));
        System.out.println(is.isIsomorphic2("foo", "bar"));
        System.out.println(is.isIsomorphic2("paper", "title"));
    }

    /**
     * Approach1:
     *  Using Hashmap
     */
    public boolean isIsomorphic1(String first, String second) {
        Map<Character, Character> charMap = new HashMap<>();
        for(int i=0; i<first.length(); i++) {
            //If the char is already in map, check against second. else add the mapping to map
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);
            if(charMap.containsKey(firstChar)) {
                if(charMap.get(firstChar) != secondChar) return false; //If map already exists, then current char in 2nd should match
            } else {
                if(charMap.containsValue(secondChar)) return false; //If value is mapped to a diff char already
                charMap.put(firstChar, secondChar);
            }
        }
        return true;
    }

    /**
     * Approach2:
     *  The main idea is to store the last seen positions of current (i-th) characters in both strings.
     *  If previously stored positions are different then we know that the fact they're occuring in the
     *  current i-th position simultaneously is a mistake.
     */
    public boolean isIsomorphic2(String first, String second) {
        int[] charMap = new int[512];

        for(int i=0; i<first.length(); i++) {
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);

            if(charMap[firstChar] != charMap[secondChar+256]) return false;
            charMap[firstChar] = charMap[secondChar+256] = i+1;
        }
        return true;
    }
}
