package pcreekprobs;

import utils.TimeWatch;

import java.util.concurrent.TimeUnit;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  The longest common substring problem is to find the longest string that is a substring of two or more strings.
 *
 * Links:
 *  https://www.programcreek.com/2015/04/longest-common-substring-java/
 */
public class q14_LongestCommonSubstring {
    public static void main(String[] args) {
        q14_LongestCommonSubstring lcs = new q14_LongestCommonSubstring();
        TimeWatch watch = TimeWatch.start();
        System.out.println(lcs.longestCommonSubstring1("abcdef", "zcdemf") + ":" + watch.time(TimeUnit.MICROSECONDS));

        watch = watch.reset();
        System.out.println(lcs.longestCommonSubstring2("abcdef", "zcdemf") + ":" + watch.time(TimeUnit.MICROSECONDS));

        watch = watch.reset();
        System.out.println(lcs.longestCommonSubstring3("abcdef", "zcdemf", 0, 0, false)  + ":" + watch.time(TimeUnit.MICROSECONDS));
    }

    /**
     * Approach 1: Space Efficient DP
     *  This is a similar problem like longest common subsequence.
     *  The difference of the solution is that for this problem when a[i]!=b[j], dp[i][j] are all zeros by default.
     *  However, in the longest common subsequence problem, dp[i][j] values are carried from the previous values, i.e., dp[i-1][j] and dp[i][j-1].
     *
     * Links:
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestCommonSubstring.java
     *
     * Complexity:
     *  Time is O(nm)
     *  Space is O(m)
     */
    public int longestCommonSubstring1(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int[] dp = new int[s2.length()+1];
        int max = 0;

        for (int i=1; i<=s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[j] = 1 + dp[j-1];
                }
                if (dp[j] > max) {
                    max = dp[j];
                }
            }
        }
        return max;
    }

    /**
     * Approach 2: DP
     *  This is a similar problem like longest common subsequence.
     *  The difference of the solution is that for this problem when a[i]!=b[j], dp[i][j] are all zeros by default.
     *  However, in the longest common subsequence problem, dp[i][j] values are carried from the previous values, i.e., dp[i-1][j] and dp[i][j-1].
     *
     * Links:
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestCommonSubstring.java
     *
     * Complexity:
     *  Time is O(nm)
     *  Space is O(nm)
     */
    public int longestCommonSubstring2(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int max = 0;

        for (int i=0; i<=s1.length(); i++) {
            for (int j=0; j<=s2.length(); j++) {
                if (i==0 || j==0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1]; //add to prev substring match
                }

                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    /**
     * Approach 3: Recursion
     *
     * Links:
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestCommonSubstring.java
     *
     * Complexity:
     *  Time is O(2ⁿ) ??
     *  Space is O(2ⁿ) ??
     */
    public int longestCommonSubstring3(String s1, String s2, int firstIndex, int secondIndex, boolean equalsCheck) {
        if (firstIndex == s1.length() || secondIndex == s2.length()) {
            return 0;
        }

        int returnVal = 0;
        if (s1.charAt(firstIndex) == s2.charAt(secondIndex)) {
            returnVal = 1 + longestCommonSubstring3(s1, s2, firstIndex+1, secondIndex+1, true);
        }

        if (equalsCheck) {
            return returnVal;
        } else {
            return Math.max(returnVal, Math.max(longestCommonSubstring3(s1, s2, firstIndex+1, secondIndex, false),
                    longestCommonSubstring3(s1, s2, firstIndex, secondIndex+1, false)));
        }
    }
}
