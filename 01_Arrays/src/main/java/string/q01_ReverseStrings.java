package string;

/**
 * Problem:
 *  Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 *  The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 *  For example,
 *      Given s = "the sky is blue",
 *      return "blue is sky the".
 *
 * Links:
 *  https://leetcode.com/problems/reverse-words-in-a-string/description/
 *  https://www.programcreek.com/2014/05/leetcode-reverse-words-in-a-string-ii-java/
 *
 * Complexity:
 *  Time is O(n)
 *  Space is O(1)
 */
public class q01_ReverseStrings {
    public static void main (String[] args) {
        q01_ReverseStrings rs = new q01_ReverseStrings();
        String input = "the sky is blue";
        char[] inputArr = input.toCharArray();
        rs.reverseWords(inputArr);
        System.out.printf("Input is '%s'\n", input);
        System.out.printf("Reversed string is '%s'", new String(inputArr));
    }

    public void reverseWords (char[] s) {
        int i=0;
        for(int j=0; j<s.length; j++) {
            if(s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j+1;
            }
        }
        //This reversal is done since there is no space at the end and the last word hasn't been reversed
        reverse(s, i, s.length-1);

        reverse(s, 0, s.length-1); //Do a complete reversal
    }

    public void reverse (char[] s, int i, int j) {
        while(i<j){
            char temp = s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }
}
