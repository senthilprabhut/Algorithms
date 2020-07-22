package pcreekprobs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  The longest common subsequence (LCS) problem is the problem of finding the longest subsequence common to all
 *  sequences in a set of sequences (often just two sequences).
 *
 * Links:
 *  http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 *  http://www.programcreek.com/2014/04/longest-common-subsequence-java/
 */
public class q13_LongestCommonSubsequence {
    public static void main(String[] args) {
        q13_LongestCommonSubsequence lcs = new q13_LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence1("ABCD", "ABDE"));
        System.out.println(lcs.longestCommonSubsequence1("ABCDGHLQR", "AEDPHR"));
        System.out.println(lcs.longestCommonSubsequence2("ABCD", "ABDE"));
        System.out.println(lcs.longestCommonSubsequence2("ABCDGHLQR", "AEDPHR"));
        System.out.println(lcs.longestCommonSubsequence3("ABCD", "ABDE", 0, 0));
        System.out.println(lcs.longestCommonSubsequence3("ABCDGHLQR", "AEDPHR", 0, 0));
    }

    /**
     * Approach:
     *  Dynamic programming
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=NnD96abizww
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestCommonSubsequence.java
     *
     * Complexity:
     *  Time is O(nm)
     *  Space is O(m) where m is the length of string 2
     */
    public int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int[] dp = new int[s2.length()+1];
        int[] prev = new int[s2.length()+1];
        for (int i=1; i<=s1.length(); i++) {
            //Swap dp and temp - now dp becomes the prev for next iteration
            int[] temp = dp;
            dp = prev;
            prev = temp;

            for (int j=1; j<=s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[j] = 1 + prev[j-1];
                } else {
                    dp[j] = Math.max(dp[j-1], prev[j]);
                }
            }
        }

        return dp[s2.length()];
    }

    /**
     * Approach:
     *  Dynamic programming
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=NnD96abizww
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestCommonSubsequence.java
     *
     * Complexity:
     *  Time is O(nm)
     *  Space is O(nm)
     */
    public int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        /* Following steps build DP[m+1][n+1] in bottom up fashion. Note
         that DP[i][j] contains length of LCS of S1[0..i-1] and S2[0..j-1] */
        for (int i=1; i<=s1.length(); i++) {
            for (int j=1; j<=s2.length(); j++) {
                if (i==0 || j==0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1]; //1 + Max LCM without the current characters
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); //Carry over the prev LCM
                }
            }
        }

        printSubsequence(dp, s1, s2);
        return dp[s1.length()][s2.length()];
    }

    private void printSubsequence(int[][]dp, String s1, String s2) {
        int startX=s1.length(), startY=s2.length();

        Deque<Character> stack = new LinkedList<>();
        while (startY != 0) {

            if (dp[startX][startY] == dp[startX-1][startY]) {
                startX--; //Move left
            } else if (dp[startX][startY] == dp[startX][startY-1]) {
                startY--; //Move up
            } else {
                //If the current value doesn't come from top or left, move diagonally and add that character to result
                stack.push(s1.charAt(startX-1));
                startX--;
                startY--;
            }
        }

        System.out.println(stack.toString());
    }

    /**
     * Approach: Naive Recursion
     *  Generate all subsequences of both given sequences and find the longest matching subsequence.
     *  Time complexity of the naive recursive approach is O(2^n) in worst case and
     *  worst case happens when all characters of X and Y mismatch i.e., length of LCS is 0
     *
     * Links:
     *  https://www.geeksforgeeks.org/longest-common-subsequence/
     *
     * Complexity:
     *  Time is O(2ⁿ)
     *  Space is O(2ⁿ)
     */
    public int longestCommonSubsequence3(String s1, String s2, int firstIndex, int secondIndex) {
        if (firstIndex == s1.length() || secondIndex == s2.length()) {
            return 0;
        }

        //if characters match, add one and find lcm of subsequent characters
        if (s1.charAt(firstIndex) == s2.charAt(secondIndex)) {
            return 1 + longestCommonSubsequence3(s1,s2, firstIndex+1, secondIndex+1);
        }

        //if characters don't match, increment characters of one of them and find lcm
        return Math.max(longestCommonSubsequence3(s1,s2,firstIndex+1, secondIndex), longestCommonSubsequence3(s1,s2,firstIndex,secondIndex+1));
    }
}
