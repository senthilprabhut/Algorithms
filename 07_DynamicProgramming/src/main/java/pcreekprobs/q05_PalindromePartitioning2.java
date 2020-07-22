package pcreekprobs;

import java.util.stream.IntStream;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a string s, partition s such that every substring of the partition is a palindrome.
 *  Return the minimum cuts needed for a palindrome partitioning of s.
 *  For example, given s = "aab",
 *  Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Links:
 *  https://leetcode.com/problems/palindrome-partitioning-ii/description/
 *  https://www.programcreek.com/2014/04/leetcode-palindrome-partitioning-ii-java/
 */
public class q05_PalindromePartitioning2 {
    public static void main(String[] args) {
        q05_PalindromePartitioning2 pp = new q05_PalindromePartitioning2();
        System.out.println(pp.minCut1("abacc"));
        System.out.println(pp.minCut1("abcde"));

        System.out.println(pp.minCut2("abacc"));
        System.out.println(pp.minCut2("abcde"));

    }

    /**
     * Approach 1:
     *  This divide-and-conquer algorithm utilize the symmetry of palindromes, so there is no need to cache the result of whether s[i:j) is a palindrome.
     *  Say that it started at s[i] = 'b', and s[i-1,i+1] is a palindrome “aba”:
     *  The definition of ‘cut’ array is the minimum number of cuts of a sub string. More specifically, cut[n] stores the cut number of string s[0, n-1].
     *
     *  1. Initialize the ‘cuts’ array: For a string with n characters s[0, n-1], it needs at most n-1 cut.
     *     Therefore, the ‘cuts’ array is initialized as cut[i] = i
     *  2. Use two variables in two loops to represent a palindrome:
     *      The external loop variable ‘mid’ represents the center of the palindrome.
     *      The internal loop variables ‘start’ & 'end' represents the ‘radius’ of the palindrome.
     *      Iterate through all chars as mid point of palindrome
     *      This palindrome can then be represented as s[i-j, i+j].
     *      If this string is indeed a palindrome, then one possible value of cut[end] is cuts[start-1] + 1,
     *          where cut[start-1] corresponds to s[0, start-1] and 1 correspond to the palindrome s[start, end];
     *
     * Links:
     *  https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42198/My-solution-does-not-need-a-table-for-palindrome-is-it-right-It-uses-only-O(n)-space comment:meganlee
     *
     * Complexity:
     *  Time is O(n²) in worst case
     *  Space is O(n)
     */
    public int minCut1(String s) {
        // validate input
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        //We do this step here because the for loop changes the values in the cuts array for end values which are > mid
        int[] cuts = IntStream.range(0, n).toArray();  //DP array. cuts[i] = i

        for (int mid=1; mid<n; mid++) {
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start=mid, end=mid; start>=0 && end<n &&
                s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : cuts[start-1] + 1;  //+1 is for the current cut at start
                cuts[end] = Math.min(cuts[end], newCutAtEnd);
            }

            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start=mid-1,end=mid; start>=0 && end<n &&
                s.charAt(start) == s.charAt(end); start--,end++) {
                int newCutAtEnd = (start==0) ? 0 : cuts[start-1] + 1; //+1 is for the current cut at start
                cuts[end] = Math.min(cuts[end], newCutAtEnd);
            }
        }
        return cuts[n-1];
    }

    /**
     * Approach 2:
     *  This can be solved by two points:
     *  cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
     *  If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
     *
     * Links:
     *  https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36)
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(n²)
     */
    public int minCut2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] cuts = new int[n];

        //i doesn't have to start from 0 since 0,0 is always 0 for no of cuts which is the default value in cuts array
        for (int i=1; i<n; i++) {
            int minCut = i;
            for (int j=0; j<=i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i-j<=2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                    //j==0 returns minCut=0 because when the whole word is a palindrome, then there are no cuts reqd
                    minCut = (j==0) ? 0 : Math.min(minCut, 1 + cuts[j-1]);
                }
            }
            cuts[i] = minCut;
        }
        return cuts[n-1];
    }

    /**
     * Approach 3: Shortest path algorithm
     *  Build the directed acyclic graph: if substring s[i, …, j] is a palindrome, then there is an edge from i to j+1.
     *  Find the shortest path d from 0 to n. Then d - 1 is the mincut.
     *
     * Links:
     *  https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42257/Solved-by-shortest-path-algorithm-clear-and-straightforward-O(n2)
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is
     */
}
