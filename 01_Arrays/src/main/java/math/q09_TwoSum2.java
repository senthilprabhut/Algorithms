package math;

import java.util.Arrays;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an array of integers that is already sorted in ascending order, find two numbers such that they add up
 *  to a specific target number. The function twoSum should return indices of the two numbers such that they add up
 *  to the target, where index1 must be less than index2.
 *  Please note that your returned answers (both index1 and index2) are not zero-based.
 *  You may assume that each input would have exactly one solution and you may not use the same value twice.
 *  Input: numbers={2, 7, 11, 15}, target=9
 *  Output: index1=1, index2=2
 *
 * Links:
 * 	https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 *  https://www.programcreek.com/2014/03/two-sum-ii-input-array-is-sorted-java/
 */
public class q09_TwoSum2 {
    public static void main(String[] args) {
        q09_TwoSum2 ts = new q09_TwoSum2();
        int[] input = new int[] {2, 7, 11, 15};
        System.out.println(Arrays.toString(ts.twoSum(input, 9)) );
    }

    /**
     * Approach 1:
     *  Two pointers i, j
     *
     * Complexity:
     *  Time Complexity - O(n)
     *  Space Complexity - O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int x = numbers[i] + numbers[j];
            if (x < target) {
                ++i;
            } else if (x > target) {
                j--;
            } else {
                return new int[] { i + 1, j + 1 };
            }
        }
        return new int[] { i, j };
    }
}
