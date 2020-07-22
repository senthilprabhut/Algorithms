package string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *   You are given a string, s, and a list of words, words, that are all of the same length.
 *   Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 *   and without any intervening characters.
 *   For example, given:  s: "barfoothefoobarman"   words: ["foo", "bar"]
 *   You should return the indices: [0,9].  (order does not matter).
 *
 * Note:
 *  the same idea (maintaining a satisfied window) can be using to three problems in leetcode.
 *  “Longest Substring Without Repeating Characters”, “Substring with Concatenation of All Words” and “Minimum Window Substring”
 *
 * Links:
 *  https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 *  https://www.programcreek.com/2014/06/leetcode-substring-with-concatenation-of-all-words-java/
 */
public class q28_SubstrWithConcatOfAllWords {
    public static void main(String[] args) {
        q28_SubstrWithConcatOfAllWords ls = new q28_SubstrWithConcatOfAllWords();
        System.out.println(ls.findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
    }

    /**
     * Approach:
     *  Sliding Window
     *  This problem is similar (almost the same) to Longest Substring Which Contains 2 Unique Characters.
     *  Since each word in the dictionary has the same length, each of them can be treated as a single character.
     *
     *  Travel all the words combinations to maintain a window. There are wl(word len) times travel
     *  Each time, n/wl words, mostly 2 times travel for each word
     *
     * Link:
     *  https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/13656/
     *
     * Complexity:
     *  Time is O(kN) where k is the length of each word in the word array.
     *      Here is an easy way to understand the overall runtime. What did you do essentially?
     *      You tried to find/update each possible substring of length k with the help of a hash map.
     *      Outer forloop runs k times, the inner forloop runs N/k times and Hashmap takes O(k) times in worst case
     *      There are at most kN/k substrings of length k, and to check each substr in hashmap you need to spend O(k) in worst case,
     *      so your final (worstcase) runtime comes to O(k) * O(kN/k) = O(kN).
     */
    public List<Integer> findSubstring(String sentence, String[] words) {
        //Ask interviewer if words is empty, should I return empty list
        List<Integer> result = new LinkedList<>();
        if(words == null || words.length == 0 || sentence  == null || sentence.length() < words.length*words[0].length()) return result;

        //frequency of words
        Map<String,Integer> possibleWords = new HashMap<>();
        for(String word : words) {
            possibleWords.merge(word, 1, (one, count) -> count+one); //If the word is not present, will get 1. Else increment by 1
        }

        //travel all sub string combinations
        int wordSize=words[0].length();
        HashMap<String,Integer> window = new HashMap<>();
        for(int offset=0; offset<wordSize; offset++) {
            //Reset window to offset
            window.clear();
            int left = offset; //start index of Left pointer
            int qualWordCount = 0; //total qualified words so far

            for(int start=offset; start<=sentence.length()-wordSize; start+=wordSize) {
                String word = sentence.substring(start, start+wordSize); //Word at start

                // not a valid word, reset all vars
                if(! possibleWords.containsKey(word)) {
                    //Reset window == next iteration
                    window.clear();
                    left = start + wordSize;
                    qualWordCount = 0;
                } else {
                    //consumeRight - set freq in current map
                    window.merge(word, 1, (one, currCount) -> one + currCount);
                    qualWordCount++;

                    //too many words - advance the window to left until the extra word is balanced
                    while (window.get(word) > possibleWords.get(word)) {
                        //advance one word (shrink window) from left
                        String leftStr = sentence.substring(left, left + wordSize);
                        window.put(leftStr, window.get(leftStr) - 1); //
                        left += wordSize;
                        qualWordCount--;
                    }
                    //a valid word, accumulate results
                    if(qualWordCount == words.length) {
                        result.add(left); // [left, left + count * wordSize) is a matching substring

                        //advance one word (shrink window) from left
                        String leftStr = sentence.substring(left, left + wordSize);
                        window.put(leftStr, window.get(leftStr) - 1);
                        left += wordSize;
                        qualWordCount--;
                    }
                }
            }
        }
        return result;
    }
}
