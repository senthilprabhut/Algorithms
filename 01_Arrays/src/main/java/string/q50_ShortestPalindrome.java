package string;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 *  Find and return the shortest palindrome you can find by performing this transformation.
 *  For example:
 *      Given "aacecaaa", return "aaacecaaa".
 *      Given "abcd", return "dcbabcd".
 *
 * Links:
 *  https://leetcode.com/problems/shortest-palindrome/description/
 *  https://www.programcreek.com/2014/06/leetcode-shortest-palindrome-java/
 */
public class q50_ShortestPalindrome {
    public static void main(String[] args) {
        q50_ShortestPalindrome sp = new q50_ShortestPalindrome();
        System.out.println(sp.shortestPalindrome1("ababc"));   //cbababc
        System.out.println(sp.shortestPalindrome1("dababc"));  //cbabadababc
        System.out.println(sp.shortestPalindrome3("ababc"));   //cbababc
        System.out.println(sp.shortestPalindrome3("dababc"));  //cbabadababc
    }

    /**
     * Approach: Modified KMP Algorithm
     *  The key of KMP is to build a look up table that records the match result of prefix and postfix.
     *  Value in the table means the max len of matching substring that exists in both prefix and postfix.
     *  This problem asks us to add string before the input so the result string will be a palindrome.
     *  We can convert it to an alternative problem"find the longest palindrome substring starts from index 0".
     *  If we can get the length of such substring, then we can easily build a palindrome string by inserting
     *  the reverse part of substring after such substring before the original string.
     *
     *  We can solve it by using a trick + KMP. The trick is to build a temp string like this:  s + “#” + reverse(s)
     *  Then we run KMP on it (use the lookup table in KMP to find the palindrome), the value in last cell will be our solution.
     *  We add “#” here to force the match in reverse(s) starts from its first index
     *  What we do in KMP here is trying to find a match between prefix in s and a postfix in reverse(s).
     *  The match part will be palindrome substring.
     *
     * Explanation:
     *  https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
     *
     * Links:
     *  https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation - comment 2nd_round
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n)
     */
    public String shortestPalindrome1(String inputStr) {
        String tempString = inputStr + "#" + new StringBuilder(inputStr).reverse().toString();
        int[] kmp = getTable(tempString);
        int foundPalindromeLength = kmp[kmp.length-1];
        String stringToAppend = new StringBuilder(inputStr.substring(foundPalindromeLength)).reverse().toString();
        return stringToAppend + inputStr;
    }

    private int[] getTable(String tempString) {
        int index=0;
        int[] table = new int[tempString.length()];

        for (int i=1; i<tempString.length(); i++) {
            if (tempString.charAt(index) == tempString.charAt(i)) {
                table[i] = ++index; //increment index and store at table
            } else {
                if (index > 0) {
                    index = table[index - 1]; //start comparison from previously matched location
                } //else index is 0 and stays at 0
            }
        }
        return table;
    }


    /**
     * Approach 2: Recursion
     *  the core idea of this idea is to find or generate a core palindrome string from s[0] to s[j] and
     *  then put the rest s[j,n] around s[0,j] to construct the result.
     *
     *  The idea is to use two anchors j and i to compare the String from beginning and end.
     *  If j can reach the end, the String itself is Palindrome. Otherwise, we divide the String by j, and
     *  get mid = s.substring(0, j) and suffix.
     *  We reverse suffix as beginning of result and recursively call shortestPalindrome to get result of mid then
     *  append suffix to get result.
     *
     * Links:
     *  https://leetcode.com/problems/shortest-palindrome/discuss/60098/My-7-lines-recursive-Java-solution
     *
     * Complexity:
     *  Time is O(n²) Since each call scans the entire string (given to that call), it’s overall quadratic.
     */
    public String shortestPalindrome2(String s) {  //understand by running through "ababc"
        int beginCntr=0;
        for(int endCntr=s.length()-1; endCntr>=0; endCntr--) {
            if (s.charAt(beginCntr) == s.charAt(endCntr)) {
                beginCntr++;
            }
        }

        if (beginCntr == s.length()) return s; //the whole string is a palindrome

        String suffix = s.substring(beginCntr);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome2(s.substring(0, beginCntr)) + suffix;
    }


    /**
     * Approach 3: Iteration
     *  Iterative version of approach 2
     */
    public String shortestPalindrome3(String s) {
        StringBuilder res = new StringBuilder();
        int j=0, end = s.length();
        while(true){
            j=0;
            for(int i=end-1;i>=0;i--){
                if(s.charAt(i) == s.charAt(j)) j++;
            }
            if(j==end) break; //both j & end are the same value
            end = j; //end gives the start of non-palindrome character
        }
        //res.append(s.substring(end, s.length())).reverse().append(s.substring(0, end)).append(s.substring(end, s.length()));
        res.append(s.substring(end, s.length())).reverse().append(s);
        return res.toString();
    }

}
