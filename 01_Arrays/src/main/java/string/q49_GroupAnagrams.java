package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of strings, group anagrams together.
 *  For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], Return:
 *  [
 *      ["ate", "eat","tea"],
 *      ["nat","tan"],
 *      ["bat"]
 *  ]
 *  Note: All inputs will be in lower-case.
 *
 * Links:
 *  https://leetcode.com/problems/group-anagrams/description/
 *  https://www.programcreek.com/2014/04/leetcode-anagrams-java/
 */
public class q49_GroupAnagrams {
    public static void main(String[] args) {
        q49_GroupAnagrams ga = new q49_GroupAnagrams();
        String[] arr = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(ga.groupAnagrams1(arr));
        System.out.println(ga.groupAnagrams2(arr));
    }

    /**
     * Approach 1:
     *  Sort the individual characters in each of the strings and create a key for the map
     *  This will be unique for all the anagrams of a word
     *
     * Links:
     *  https://leetcode.com/problems/group-anagrams/discuss/19176/Share-my-short-JAVA-solution
     *
     * Complexity:
     *  Time Complexity is O(N*M logM), m is the avg length of the strings and n is the string count
     *  Space Complexity is O(N*M), where M is the space required for the intermediate char array and N is the string count
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String str : strs) {
            //split the string to char array and sort it
            char[] chars = str.toCharArray(); //O(M) because of array copy
            Arrays.sort(chars); //O(M logM)
            anagramMap.computeIfAbsent(String.valueOf(chars), (key) -> new ArrayList<>())
                    .add(str);
        }
        return new ArrayList<List<String>>(anagramMap.values()); //O(N)
    }

    /**
     * Approach 2:
     *  How feasible is prime number multiplication for large inputs.
     *  For strings of size 20 or 50 or 100. Here is a O(M*N) solution without sort and without costly prime multiplication
     *
     * Links:
     *  https://leetcode.com/problems/group-anagrams/discuss/19233/O(M-*-N)-algorithm-using-hash-without-sort() - comment mrsiva26
     *
     * Complexity:
     *  Time Complexity is O(N*M), M is the avg length of the strings and N is the string count
     *  Space Complexity is O(N*1), where 1 is the space required for the constant 26 size counter array
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String str : strs) {
            //only lower-case letters. so array of size 26 is enough
            int[] counter = new int[26];

            //Count the occurrances of each character
            //Iterate the string and increment corresponding index
            //char - 'a' , so the index will be between 0 and 25
            for (int i=0; i<str.length(); i++) {    // Loop O(M) times, M is the avg length of string
                counter[str.charAt(i) - 'a']++;
            }

            //Now iterate thorugh the counter array and construct the hash
            //Eg: cat becomes 1a1c1t. caabbt becomes 2a2b1c1t
            StringBuilder sb = new StringBuilder();  //Loop is constant times
            for (int j=0; j<26; j++) {
                if(counter[j]>0){
                    sb.append(counter[j]);
                    sb.append((char) ('a'+j));
                }
            }
            String key = sb.toString();

            anagramMap.computeIfAbsent(key, (mapKey) -> new ArrayList<>())
                    .add(str);
        }

        return new ArrayList<List<String>>(anagramMap.values()); //O(N) to copy map values to arraylist
    }
}
