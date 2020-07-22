package pcreekprobs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine
 *  if s can be segmented into a space-separated sequence of one or more dictionary words.
 *  You may assume the dictionary does not contain duplicate words.
 *  For example, given      s = "leetcode",     dict = ["leet", "code"].
 *  Return true because "leetcode" can be segmented as "leet code".
 *
 * Links:
 *
 *
 */
public class q03_WordBreak1 {
    public static void main(String[] args) {
        List<String> dict = new ArrayList<String>() {
            {add("leet"); add("code");}
            {add("so"); add("y"); add("bean");}
            {add("as"); add("is"); add("test"); add("testo"); add("ne");}
        };
        q03_WordBreak1 wb = new q03_WordBreak1();
        System.out.println(wb.wordBreak1("leetcode", dict));
        System.out.println(wb.wordBreak1("leetcode1", dict));
        System.out.println(wb.wordBreak1("soybean", dict));
        System.out.println(wb.wordBreak1("asistestone", dict));
        System.out.println("--------");
        System.out.println(wb.wordBreak2("leetcode", dict));
        System.out.println(wb.wordBreak2("leetcode1", dict));
        System.out.println(wb.wordBreak2("soybean", dict));
        System.out.println(wb.wordBreak2("asistestone", dict));
    }

    /**
     * Approach: DP
     *
     * Links:
     *  https://leetcode.com/problems/word-break/discuss/43790/java-implementation-using-dp-in-two-ways
     *
     * Complexity:
     *  Time is O(n²), where n is the length of the string.
     *  We can consider s.substring function to be O(1) since it uses native memory copy for array copy
     *  Space is O(n), n is the length of the string
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict); //O(n) to traverse and create a new dict

        if ((s.length() == 1 && dict.contains(s)) || s.length() < 1) {
            return true;
        }

        boolean[] breakUptoHereInDict = new boolean[s.length() + 1];
        breakUptoHereInDict[0] = true; //no word is present in dict :-)
        for (int i=1; i<=s.length(); i++) {
            for (int j=i-1; j>= 0; j--) {
                if (breakUptoHereInDict[j] == true && dict.contains(s.substring(j,i))) {
                    breakUptoHereInDict[i] = true;
                    break;
                }
            }
        }
        return breakUptoHereInDict[s.length()];
    }

    /**
     * Approach: Naive approach
     *
     * Links:
     *  https://www.programcreek.com/2012/12/leetcode-solution-word-break/
     *
     * Complexity:
     *  Time is O(n²), where n is the length of the string
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        return wordbreakHelper(s, dict, 0);
    }

    private boolean wordbreakHelper(String s, Set<String> dict, int start) {
        if (start == s.length()) {
            return true;
        }

        if (dict.contains(s.substring(start))) {
            return true;
        }

        for (int j=start+1; j<=s.length(); j++) {
            if (dict.contains(s.substring(start,j)) && wordbreakHelper(s, dict, j)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Approach: Graph and BFS
     *  We can use a graph to represent the possible solutions.
     *  The vertices of the graph are simply the positions of the first characters of the words and each edge actually represents a word.
     *
     * Links:
     *  https://leetcode.com/problems/word-break/discuss/43797/A-solution-using-BFS
     *
     * Complexity:
     *  Time complexity is O(n^2)
     *  Space complexity is O(n)
     */
}
