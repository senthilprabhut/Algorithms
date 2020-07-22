package pcreekprobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 *  add spaces in s to construct a sentence where each word is a valid dictionary word.
 *  You may assume the dictionary does not contain duplicate words.
 *  Return all such possible sentences.
 *  For example, given      s = "catsanddog",
 *      dict = ["cat", "cats", "and", "sand", "dog"].
 *      A solution is ["cats and dog", "cat sand dog"].
 *
 * Links:
 *  https://leetcode.com/problems/word-break-ii/description/
 *  https://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 *  Tushar - https://www.youtube.com/watch?v=WepWFGxiwRs
 */
public class q03_WordBreak2 {
    public static void main(String[] args) {
        List<String> dict = new ArrayList<String>() {
            {
                add("cat");
                add("cats");
                add("and");
                add("sand");
                add("dog");
            }
        };
        q03_WordBreak2 wb = new q03_WordBreak2();
        System.out.println(wb.wordBreak("catsanddog", dict));
    }

    /**
     * Approach: DFS
     *  Using DFS directly will lead to TLE, so I just used HashMap to save the previous results to prune duplicated branches
     *
     * Links:
     *  https://leetcode.com/problems/word-break-ii/discuss/44299/Java-6ms-simple-solution-beating-88
     *
     * Complexity:
     *
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);   //O(n)

        int maxLength = -1;
        for(String ss : wordDict) {                   //O(n)
            maxLength = Math.max(maxLength, ss.length());
        }

        return wordbreakHelper(s, dict, new HashMap<>(), 0, maxLength);
    }

    private List<String> wordbreakHelper(String s, Set<String> dict, HashMap<Integer,List<String>> dp, int start, int max) {
        List<String> words = new ArrayList<>();
        if (start == s.length()) {
            words.add("");
            return words;
        }

        //There is no point looking for substring whose length is > max length in dictionary
        for (int j=start+1; j<=s.length() && j <= start+ max; j++) {
            String curr = s.substring(start, j);
            if (dict.contains(curr)) {
                //check if the remaining string is already processed
                List<String> remaining;

                if (dp.containsKey(j)) {
                    remaining = dp.get(j);
                } else {
                    remaining = wordbreakHelper(s, dict, dp, j, max);
                }

                for(String remStrings : remaining) {
                    words.add(curr + (remStrings.equals("") ? "" : " ") + remStrings);
                }
            }
        }

        dp.put(start, words);
        return words;
    }
}
