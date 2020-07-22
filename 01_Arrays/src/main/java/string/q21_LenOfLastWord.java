package string;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 *  return the length of last word in the string. If the last word does not exist, return 0.
 *  Note: A word is defined as a character sequence consists of non-space characters only.
 *  Example:
 *      Input: "Hello World"
 *      Output: 5
 *
 * Links:
 *  https://leetcode.com/problems/length-of-last-word/description/
 *  https://www.programcreek.com/2014/05/leetcode-length-of-last-word-java/
 */
public class q21_LenOfLastWord {
    public static void main(String[] args) {
        q21_LenOfLastWord lw = new q21_LenOfLastWord();
        System.out.println( lw.lengthOfLastWord("Hello World") );
    }

    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;

        boolean isCharStarted = false;
        int length = 0;
        for(int i=s.length()-1; i>=0; i--) {
            char c = s.charAt(i);

            if( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'z') ) {
                isCharStarted = true;
                length++;
            } else { //If a non-alphabet is encountered
                if (isCharStarted == true) return length;
            }
        }
        return length;
    }
}
