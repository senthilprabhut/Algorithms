package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given two arrays, write a function to compute their intersection.
 *  Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 *  Note:   Each element in the result must be unique. The result can be in any order.
 *
 * Links:
 *  https://leetcode.com/problems/intersection-of-two-arrays/description/
 *  https://www.programcreek.com/2015/05/leetcode-intersection-of-two-arrays-java/
 */
public class q70_IntersectionOfTwoArrays1 {
    public static void main(String[] args) {
        q70_IntersectionOfTwoArrays1 it = new q70_IntersectionOfTwoArrays1();
        System.out.println(Arrays.toString(it.intersect(new int[]{1, 2, 2, 1}, new int[]{2,2})));
    }

    /**
     * Approach:
     *  Use two hash sets
     *
     * Links:
     *  https://leetcode.com/problems/intersection-of-two-arrays/discuss/81969/Three-Java-Solutions
     *
     * Complexity:
     *  Time is O(n)
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.contains(num)) {
                intersect.add(num);
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }

    /**
     * Approach 2:
     *  Sort array and use two pointers
     *
     * Links:
     *  https://leetcode.com/problems/intersection-of-two-arrays/discuss/81969/Three-Java-Solutions
     *
     * Complexity:
     *  Time is O(n log n)
     */
}
