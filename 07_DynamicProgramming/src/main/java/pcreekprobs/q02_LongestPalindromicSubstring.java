package pcreekprobs;

import utils.DPUtils;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *  Example:    Input: "babad"  Output: "bab"   Note: "aba" is also a valid answer.
 *  Example:    Input: "cbbd"   Output: "bb"
 *
 * Links:
 *  https://leetcode.com/problems/longest-palindromic-substring/description/
 *  https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
 */
public class q02_LongestPalindromicSubstring {
    public static void main(String[] args) {
        q02_LongestPalindromicSubstring lps = new q02_LongestPalindromicSubstring();
        System.out.println(lps.longestPalindrome2("bananas"));
        System.out.println(lps.longestPalindrome3("bananas"));
        System.out.println(lps.longestPalindrome4("bananas"));
    }

    /**
     * Approach 1: Manacher's algorithm
     *
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=V-sEwsca1ak
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/string/LongestPalindromeSubstring.java
     *
     * Complexity:
     *  Time is O(N)
     *  Space is O(N)
     */
    //public String longestPalindrome1(String s) {
    //
    //}

    /**
     * Approach 2:
     *  Key idea, every time we move to right, we only need to consider whether using this new character as tail could
     *  produce new palindrome string of length (current length +1) or (current length +2)
     *
     *  Example: "xxxbcbxxxxxa", (x is random character, not all x are equal) now we are dealing with the last character 'a'.
     *  The current longest palindrome is "bcb" with length 3.
     *  1. check "xxxxa" so if it is palindrome we could get a new palindrome of length 5.
     *  2. check "xxxa" so if it is palindrome we could get a new palindrome of length 4.
     *  3. do NOT check "xxa" or any shorter string since the length of the new string is no bigger than current longest length.
     *  4. do NOT check "xxxxxa" or any longer string because if "xxxxxa" is palindrome then "xxxx" got  from cutting off
     *  the head and tail is also palindrom. It has length > 3 which is impossible.'
     *
     * Links:
     *  https://leetcode.com/problems/longest-palindromic-substring/discuss/3060/(AC)-relatively-short-and-very-clear-Java-solution comment: niniq
     *
     * Complexity:
     *  Time is O(N²) in worstcase
     *  Space is O(1)
     */
    public String longestPalindrome2(String s) {
        int maxLen=1, palindromeBeginsAt = 0; //index where the longest palindrome begins
        int start=0;

        //i is the right boundry of the palindrome substring
        for (int i=0; i<s.length(); i++) {
            //Length b/w two indices will be i - (maxLen - 1), where i is the end index and maxLen-1 is the start index
            //Since we go 2 chars back in the first comparison, start becomes  i - maxLen + 1 - 2 = i - maxLen -1
            if ( (start = i-maxLen-1) >=0 && DPUtils.isPalindrome(s, start, i)) {
                palindromeBeginsAt = start;
                maxLen += 2;
            }
            //Since we go 1 char back in the 2nd comparison, start becomes i - maxLen +1 -1 = i- maxLen
            else if ((start = i-maxLen) >=0 && DPUtils.isPalindrome(s, start, i)) {
                palindromeBeginsAt = start;
                maxLen += 1;
            }
        }
        return s.substring(palindromeBeginsAt, palindromeBeginsAt+maxLen);
    }

    /**
     * Approach 3: Dynamic programming
     *  Find all substrings of size 1 to N and get the longest one
     *  a) First and last chars should match
     *  b) Substring (excluding first and last chars) should be a palindrome
     *
     * Links:
     *  IDeserve - https://www.youtube.com/watch?v=obBdxeCx_Qs
     *  https://github.com/IDeserve/learn/blob/master/LongestPalindromicSubstring.java
     *
     * Complexity:
     *  Time is O(N²)
     *  Space is O(N²)
     */
    public String longestPalindrome3(String s) {
        int maxLength=1, palindromeBeginsAt = 0; //index where the longest palindrome begins
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];

        //Trivial case: single letter palindromes - they are all true by default
        for (int i=0; i<n; i++) {
            palindrome[i][i] = true;
        }

        //Finding palindromes of two characters
        for (int i=0; i<n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                palindrome[i][i+1] = true;
                palindromeBeginsAt = i;
                maxLength = 2;
            }
        }

        //Finding palindromes of length 3 to n and saving the longest
        for (int curLen=3; curLen <= n; curLen++) {
            for (int j=0; j<n-(curLen-1); j++) {
                int lastIndex = j+ (curLen-1);
                if (s.charAt(j) == s.charAt(lastIndex) && //1. The first and last characters should match
                        palindrome[j+1][lastIndex-1]) { //2. Rest of the substring should be a palindrome
                    palindrome[j][lastIndex] = true;
                    palindromeBeginsAt = j;
                    maxLength = curLen;
                }
            }
        }
        return s.substring(palindromeBeginsAt, maxLength + palindromeBeginsAt);
    }

    /**
     * Approach 4: Brute force
     *  Find all substrings of size 1 to N and check if each substring is a palindrome
     *
     * Links:
     *  IDeserve - https://www.youtube.com/watch?v=obBdxeCx_Qs
     *  https://github.com/IDeserve/learn/blob/master/LongestPalindromicSubstring.java
     *
     * Complexity:
     *  Time is O(N³)
     *  Space is O(1)
     */
    public String longestPalindrome4(String s) {
        String maxPalindrome = "";
        for (int length=1; length<s.length(); length++) {
            for (int j=0; j+length<=s.length(); j++) {
                if (length > maxPalindrome.length() && DPUtils.isPalindrome(s, j, j+length-1)) {
                    maxPalindrome = s.substring(j, j+length);
                }
            }
        }
        return maxPalindrome;
    }


}
