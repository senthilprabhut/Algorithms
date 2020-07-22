package array;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  You are playing the following Bulls and Cows game with your friend:
 *  You write down a number and ask your friend to guess what the number is.
 *  Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your
 *  secret number exactly in both digit and position (called "bulls") and how many digits match the secret number
 *  but locate in the wrong position (called "cows").
 *
 *  Your friend will use successive guesses and hints to eventually derive the secret number. For example:
 *  Secret number:  "1807"      Friend's guess: "7810"
 *  Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 *  Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls
 *  and B to indicate the cows. In the above example, your function should return "1A3B".
 *
 *  Please note that both secret number and friend's guess may contain duplicate digits, for example:
 *  Secret number:  "1123"      Friend's guess: "0111"
 *  In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 *  You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 *
 * Links:
 *  https://leetcode.com/problems/bulls-and-cows/description/
 *  https://www.programcreek.com/2014/05/leetcode-bulls-and-cows-java/
 */
public class q35_BullsAndCows {
    public static void main(String[] args) {
        q35_BullsAndCows bc = new q35_BullsAndCows();
        System.out.println(bc.getHint("1807", "7810")); //1A3B
        System.out.println(bc.getHint("1123", "0111")); //1A1B
        System.out.println(bc.getHint("2315", "1101")); //0A1B
    }

    /**
     * Approach:
     *  The idea is to iterate over the numbers in secret and in guess and count all bulls right away.
     *  For cows maintain an array that stores count of the number appearances in secret (++) and in guess (--).
     *  Increment cows when either number from secret was already seen in guest or vice versa.
     *
     * Links:
     *  https://leetcode.com/problems/bulls-and-cows/discuss/74621
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1). The numbers array is finite size.
     */
    public String getHint(String secret, String guess) {
        int bulls=0, cows=0;
        int[] numbers = new int[10]; //to hold 0 to 9
        for(int i=0; i<secret.length(); i++) {
            int sec = Character.getNumericValue(secret.charAt(i));
            int gss = Character.getNumericValue(guess.charAt(i)); //same as charAt(i) - '0'

            if (sec == gss) bulls++;
            else {
                //check if the char in secret is already seen in guess - if seen it would be -ve
                if (numbers[sec] < 0) cows++;
                //check if the char in guess is already seen in secret - if seen it would be +ve
                if (numbers[gss] > 0) cows++;
                numbers[sec]++;
                numbers[gss]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
