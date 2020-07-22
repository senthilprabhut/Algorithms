package string;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *  Below is one possible representation of s1 = "great":
 *      great
 *     /    \
 *    gr    eat
 *   / \    /  \
 *  g   r  e   at
 *            / \
 *           a   t
 *  To scramble the string, we may choose any non-leaf node and swap its two children.
 *  For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *            rgeat
 *           /    \
 *          rg    eat
 *         / \    /  \
 *        r   g  e   at
 *                  / \
 *                 a   t
 *  We say that "rgeat" is a scrambled string of "great".
 *  Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *            rgtae
 *           /    \
 *          rg    tae
 *         / \    /  \
 *        r   g  ta  e
 *              / \
 *             t   a
 *  We say that "rgtae" is a scrambled string of "great".
 *  Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * Links:
 *  https://leetcode.com/problems/scramble-string/description/
 *  https://www.programcreek.com/2014/05/leetcode-scramble-string-java/
 */
public class q65_ScrambledString {
    public static void main(String[] args) {
        q65_ScrambledString ss = new q65_ScrambledString();
        System.out.println(ss.isScramble("great", "rgtae"));
    }

    /**
     * Approach:
     *  The 1st IF is to check the LEFT child of S1 is scramble of LEFT child of S2 AND RIGHT child of S1 is also a scramble of RIGHT child of s2.
     *  When this fails, it means the left and right substrings are swapped.
     *  The 2nd IF statement check for the swap case with LEFT child of S1 and RIGHT child of S2 AND RIGHT child of S1 and LEFT child of S2.
     *
     * Links:
     *  https://leetcode.com/problems/scramble-string/discuss/29387/Accepted-Java-solution
     *
     * Complexity:
     *  Time is O(4ⁿ) because one problem are almost divided into 4 sub problem ??
     */
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;

        //Check if all the chars exist in both strings - if not, they are not scrambled words
        int[] chars = new int[26];
        int len = s1.length();
        for (int i=0; i<len; i++) {
            chars[s1.charAt(i) -'a']++;
            chars[s2.charAt(i) -'a']--;
        }
        for (int i=0; i<26; i++) {
            if (chars[i] != 0) return false;
        }

        for (int i=1; i<len; i++) {
            //LEFT child of S1 is scramble of LEFT child of s2 && RIGHT child of S1 is scramble of RIGHT side of S2
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0,i), s2.substring(len-i)) && isScramble(s1.substring(i), s2.substring(0,len-i))) return true;
        }
        return false;
    }


    /**
     * Approach:
     *  Iterative DP approach
     *
     * Links:
     *  https://leetcode.com/problems/scramble-string/discuss/29396/Simple-iterative-DP-Java-solution-with-explanation
     *
     * Complexity:
     *
     */
}

