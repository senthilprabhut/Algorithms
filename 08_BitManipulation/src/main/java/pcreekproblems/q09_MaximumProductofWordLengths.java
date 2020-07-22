package pcreekproblems;

/**
 * Problem:
 *   Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words
 *   do not share common letters. You may assume that each word will contain only lower case letters.
 *   If no such two words exist, return 0.
 *
 *   Example 1:
 *      Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 *      Return 16
 *      The two words can be "abcw", "xtfn".
 *   Example 2:
 *      Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 *      Return 4
 *      The two words can be "ab", "cd".
 *   Example 3:
 *      Given ["a", "aa", "aaa", "aaaa"]
 *      Return 0
 *      No such pair of words.
 *
 * Links:
 *  https://leetcode.com/problems/maximum-product-of-word-lengths/description/
 *  https://www.programcreek.com/2014/04/leetcode-maximum-product-of-word-lengths-java/
 */
public class q09_MaximumProductofWordLengths {
    public static void main(String[] args) {
        q09_MaximumProductofWordLengths mpwl = new q09_MaximumProductofWordLengths();
        System.out.println(mpwl.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0)
            return 0;

        int total = words.length;
        int[] value = new int[total];

        for (int i = 0; i < total; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                value[i] |= 1 << (tmp.charAt(j) - 'a'); //Set the bit to 1 for each alphabet that occurs
            }
        }

        int maxProduct = 0;
        for (int i = 0; i < total; i++)
            for (int j = i + 1; j < total; j++) {
                if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
                    maxProduct = words[i].length() * words[j].length();
            }
        return maxProduct;
    }
}
