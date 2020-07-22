package string;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *  For example, "Red rum, sir, is murder" is a palindrome, while "Programcreek is awesome" is not.
 *
 * Links:
 *  https://leetcode.com/problems/valid-palindrome/discuss/
 *  https://www.programcreek.com/2013/01/leetcode-valid-palindrome-java/
 */
public class q18_ValidPalindrome {
    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";

        q18_ValidPalindrome vp = new q18_ValidPalindrome();
        System.out.println(vp.isValidPalindrome(str));
    }

    public boolean isValidPalindrome(String s) {
        if(s==null||s.length()==0) return false;
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        for(int i = 0; i < s.length() ; i++){
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }
}
