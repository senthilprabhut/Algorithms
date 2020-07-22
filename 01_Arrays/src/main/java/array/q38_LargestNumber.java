package array;

import java.util.Arrays;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a list of non negative integers, arrange them such that they form the largest number.
 *  For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 *  Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * Links:
 *  https://leetcode.com/problems/largest-number/description/
 *  https://www.programcreek.com/2014/02/leetcode-largest-number-java/
 */
public class q38_LargestNumber {
    public static void main(String[] args) {
        q38_LargestNumber ln = new q38_LargestNumber();
        int[] input = new int[] {3, 30, 34, 5, 9};
        System.out.println(ln.largestNumber(input));
    }

    /**
     * Approach:
     *  The idea here is basically implement a String comparator to decide which String should come first during concatenation.
     *  Because when you have 2 numbers (let’s convert them into String), you’ll face only 2 cases:
     *  For example:
     *      String s1 = "9";
     *      String s2 = "31";
     *      String case1 =  s1 + s2; // 931
     *      String case2 = s2 + s1; // 319
     *
     * Links:
     *  https://leetcode.com/problems/largest-number/discuss/53158
     *
     * Complexity:
     *  The length of input array is n, average length of Strings in array is k,
     *  The typical sort takes O(nlogn) when the comparison between two elements takes O(1).
     *  Then in this case each comparison takes O(k), I guess the overall time complexity should be O(knlogn)
     *  Appending to StringBuilder takes O(n). So total will be O(NK logN) + O(N) = O(NKlogN).
     */
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0)
            return "";

        // Convert int array to String array, so we can sort later on
        String[] array = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);

        // Comparator to decide which string should come first in concatenation
        Arrays.sort(array, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));

        // When you have a bunch of 0 in your int array
        return Arrays.stream(array).reduce( (x,y) -> x=="0" ? y : x+y).get();

    }
}
