package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 *  so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *  Example 1:  Given words = ["bat", "tab", "cat"]         Return [[0, 1], [1, 0]]
 *              The palindromes are ["battab", "tabbat"]
 *  Example 2:  Given words = ["abcd", "dcba", "lls", "s", "sssll"]     Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 *              The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 *
 * Links:
 *  https://leetcode.com/problems/palindrome-pairs/description/
 *  https://www.programcreek.com/2014/05/leetcode-palindrome-pairs-java/
 *
 */
public class q63_PalindromePairs {
    public static void main(String[] args) {
        q63_PalindromePairs pp = new q63_PalindromePairs();
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(pp.palindromePairs(words));

        String[] words2 = {"a", ""};
        System.out.println(pp.palindromePairs(words2));
    }

    /**
     * Approach:
     *  The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of [“a”, “”];
     *  Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <.
     *  There may be duplicates in the output (consider test case [“abcd”, “dcba”]).
     *  Therefore I put a str2.length()!=0 to avoid duplicates.
     *  Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);
     *
     * Links:
     *  https://leetcode.com/problems/palindrome-pairs/discuss/79199/150-ms-45-lines-JAVA-solution/84141
     *
     * Complexity:
     *  Time is O(m * n ^ 2) where m is the length of the list and the n is the length of the word ??
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;

        Map<String, Integer> wordsMap = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            wordsMap.put(words[i], i);
        }

        for (int i=0; i<words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String substr1 = words[i].substring(0,j);
                String substr2 = words[i].substring(j);

                addPair(wordsMap, result, substr1, substr2, i ,false);

                if (substr2.length() != 0) {
                    addPair(wordsMap, result, substr2, substr1, i ,true);
                }
            }
        }
        return result;
    }

    private void addPair(Map<String, Integer> map, List<List<Integer>> result, String substr1, String substr2, int currWordIndex, boolean reverse) {
        //If substr1 is a palindrome and if reverse of substr2 is in map, combining 2 will produce a palindrome
        //The "reverse" flag applies the same logic for substr2 and if rev of substr1 is in map, combining two will produce palindrome
        if (isPalindrome(substr1)) {
            String revSubstr2 = new StringBuilder(substr2).reverse().toString();
            //Check if the revSubstr2 is in map and it is not the current word
            if (map.containsKey(revSubstr2) && map.get(revSubstr2) != currWordIndex) {
                List<Integer> pairs = new ArrayList<>();
                if (! reverse) {
                    //Reverse substring will come first and regular word will come 2nd
                    pairs.add(map.get(revSubstr2));
                    pairs.add(currWordIndex);
                } else {
                    //Word index will come first and then the index of the reversed substring
                    pairs.add(currWordIndex);
                    pairs.add(map.get(revSubstr2));
                }
                result.add(pairs);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) !=  str.charAt(right--)) return false;
        }
        return true;
    }
}
