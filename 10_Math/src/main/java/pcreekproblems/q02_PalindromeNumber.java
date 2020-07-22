package pcreekproblems;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backwards as forward
 *  Solve it without converting the integer to a string
 *  Note: no extra space here means do not convert the integer to string, since string will be a copy of the integer and take extra space.
 *  Example 1:
 *      Input: 121, Output: true
 *  Example 2:
 *      Input: -121, Output: false
 *  Example 3:
 *      Input: 10, Output: false
 *
 * Links:
 *  https://leetcode.com/problems/palindrome-number/description/
 *  https://www.programcreek.com/2013/02/leetcode-palindrome-number-java/
 */
public class q02_PalindromeNumber {
    public static void main(String[] args) {
        q02_PalindromeNumber pn = new q02_PalindromeNumber();
        System.out.printf("Is palindrome %d: %b\n", 123, pn.isPalindrome(123));
        System.out.printf("Is palindrome %d: %b\n", -123, pn.isPalindrome(-123));
        System.out.printf("Is palindrome %d: %b\n", 10, pn.isPalindrome(10));
    }

    public boolean isPalindrome(int number) {
        //negative numbers are not palindrome
        if (number < 0) {
            return false;
        }

        //Initialize how many zeros for divisor
        int divisor = 1;
        while (number / divisor >= 10) {
            divisor = divisor * 10;
        }

        while (number != 0) {
            int left = number / divisor;
            int right = number % 10;
            if (left != right) {
                return false;
            }

            number = (number % divisor) / 10; //removes first and last digit
            divisor = divisor / 100; //reducing by 100 since we removed two digits
        }
        return true;
    }
}
