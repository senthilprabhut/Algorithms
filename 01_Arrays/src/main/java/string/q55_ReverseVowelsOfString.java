package string;

import java.util.HashSet;
import java.util.Set;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Write a function that takes a string as input and reverse only the vowels of a string.
 *  Example 1:  Given s = "hello", return "holle".
 *  Example 2:  Given s = "leetcode", return "leotcede".
 *  Note:   The vowels does not include the letter "y".
 *
 * Links:
 *  https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 *  https://www.programcreek.com/2015/04/leetcode-reverse-vowels-of-a-string-java/
 */
public class q55_ReverseVowelsOfString {
    public static void main(String[] args) {
        q55_ReverseVowelsOfString rv = new q55_ReverseVowelsOfString();
        System.out.println(rv.reverseVowels("hello"));
        System.out.println(rv.reverseVowels("leetcode"));
    }

    /**
     * Approach:
     *  In the inner while loop, don’t forget the condition “start less than end” while incrementing start and decrementing end.
     *
     * Links:
     *  https://leetcode.com/problems/reverse-vowels-of-a-string/discuss/81225/Java-Standard-Two-Pointer-Solution
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public String reverseVowels(String s) {
        if (s == null || s.length()==0) {
            return s;
        }

        Set<Character> vowels = new HashSet<Character>() {
            {
                this.add('a'); this.add('e'); this.add('i'); this.add('o'); this.add('u');
                this.add('A'); this.add('E'); this.add('I'); this.add('O'); this.add('U');
            }
        };

        char[] chars = s.toCharArray();
        int start = 0, end = s.length() - 1;
        while (start < end) {
            //Increment start until you find a vowel. If no vowel is found, start will be equal to end
            while (start<end && !vowels.contains(chars[start])) {
                start++;
            }

            //Decrement end until you find a vowel
            while (start<end && !vowels.contains(chars[end])) {
                end--;
            }

            //Swap the vowels
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }

        return new String(chars);
    }

}
