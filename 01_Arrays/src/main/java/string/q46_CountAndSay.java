package string;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  The count-and-say sequence is the sequence of integers with the first five terms as following:
 *  Base case: n = 0 print "1". The sequence starts from 1
 *  1.     1
 *  2.     11
 *  3.     21
 *  4.     1211
 *  5.     111221
 *  1 is read off as "one 1" or 11.
 *  11 is read off as "two 1s" or 21.
 *  21 is read off as "one 2, then one 1" or 1211.
 *  Given an integer n, generate the nth term of the count-and-say sequence.
 *  Note: Each term of the sequence of integers will be represented as a string.
 *  Example 1:
 *      Input: 1
 *      Output: "1"
 *  Example 2:
 *      Input: 4
 *      Output: "1211"
 *
 * Links:
 *  https://leetcode.com/problems/count-and-say/description/
 *  https://www.programcreek.com/2014/03/leetcode-count-and-say-java/
 */
public class q46_CountAndSay {
    public static void main(String[] args) {
        q46_CountAndSay cs = new q46_CountAndSay();
        System.out.println(cs.countAndSay(4));
    }

    /*
     * Links:
     *  https://leetcode.com/problems/count-and-say/discuss/16000
     */
    public String countAndSay(int n) {
        if (n <= 0) return null;

        String result = "1"; //This counts as iteration 0
        for (int iter=1; iter<n; iter++) {
            StringBuilder sb = new StringBuilder();
            for (int j=1, count=1; j<=result.length(); j++) {
                if (j == result.length() || result.charAt(j-1) != result.charAt(j)) {
                    sb.append(count);
                    sb.append(result.charAt(j-1));
                } else {
                    count++;
                }
            }
            result = sb.toString();
        }
        return result;
    }
}
