package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given two arrays, write a function to compute their intersection.
 *  Example:    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 *  Note:
 *      Each element in the result should appear as many times as it shows in both arrays.
 *      The result can be in any order.
 *  Follow up:
 *      What if the given array is already sorted? How would you optimize your algorithm?
 *      What if nums1's size is small compared to nums2's size? Which algorithm is better?
 *      What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 *
 * Links:
 *  https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 *  https://www.programcreek.com/2014/05/leetcode-intersection-of-two-arrays-ii-java/
 */
public class q70_IntersectionOfTwoArrays2 {
    public static void main(String[] args) {

    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82241/AC-solution-using-Java-HashMap
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n)
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.merge(num, 1, (value, one) -> value + one);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.merge(num, -1, (value, minusOne) -> value + minusOne);
            }
        }

        int[] r = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }

        return r;
    }
}
