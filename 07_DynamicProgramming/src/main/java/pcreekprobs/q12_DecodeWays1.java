package pcreekprobs;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *  'A' -> 1
 *  'B' -> 2
 *  ...
 *  'Z' -> 26
 *  Given an encoded message containing digits, determine the total number of ways to decode it.
 *  For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 *  The number of ways decoding "12" is 2
 *
 * Links:
 *  https://leetcode.com/problems/decode-ways/description/
 *  https://www.programcreek.com/2014/06/leetcode-decode-ways-java/
 */
public class q12_DecodeWays1 {
    public static void main(String[] args) {
        q12_DecodeWays1 dw = new q12_DecodeWays1();
//        System.out.println(dw.numDecodings("171"));  //3 ways
//        System.out.println(dw.numDecodings("12"));  //2 ways
//        System.out.println(dw.numDecodings("3456")); //1 way
//        System.out.println(dw.numDecodings("2304"));  //0 ways. Not a valid encoded message. 0 cannot come as part of encoding
        System.out.println(dw.numDecodings("01"));  //0 ways. Not a valid encoded message. 0 cannot come as part of encoding
    }

    //Approach wioth O(1) storage

    /**
     * Approach:
     *  dp[1] means the way to decode a string of size 1.
     *  dp[i] just means the ways of decoding s[0..i], no offset.
     *  I then check one digit and two digit combination and save the results along the way.
     *  In the end, dp[n] will be the end result.
     *
     * Links:
     *  https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation/115910
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n) for the DP array
     *
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];
        dp[0] = (s.charAt(0) > '0' && s.charAt(0) <='9') ? 1 : 0;

        for (int i=1; i<s.length(); i++) {
            //Look at 1 char at a time
            if (s.charAt(i) > '0' && s.charAt(i) <= '9') {
                dp[i] += dp[i-1];
            }

            //Look at 2 chars at a time
            int two = Integer.parseInt(s.substring(i-1,i+1));
            if (two >= 10 && two <= 26) {
                // if i-1..i are the first two digits, then this is actually the new base case: assign 1 rather than 0
                dp[i] += (i-2 < 0 ? 1 : dp[i-2]);
            }
        }

        return dp[s.length()-1];
    }
}
