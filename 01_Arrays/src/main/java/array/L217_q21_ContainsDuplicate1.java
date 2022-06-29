package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an integer array nums, return true if any value appears at least twice in the array,
 *  and return false if every element is distinct.
 *
 *  Given an array of integers, find if the array contains any duplicates.
 *  Your function should return true if any value appears at least twice in the array, and
 *  it should return false if every value is distinct.
 *
 * Constraints:
 *  1 <= nums.length <= 105
 *  -109 <= nums[i] <= 109
 *
 * Links:
 *  https://leetcode.com/problems/contains-duplicate/description/
 *  https://www.programcreek.com/2014/05/leetcode-contains-duplicate-java/
 */
public class L217_q21_ContainsDuplicate1 {
    public static void main(String[] args) {
        int[] duplicates1 = new int[] {1,2,3,1};
        L217_q21_ContainsDuplicate1 cd = new L217_q21_ContainsDuplicate1();
        System.out.println(cd.containsDuplicate(duplicates1));

        int[] duplicates2 = new int[] {1,2,3,4};
        System.out.println(cd.containsDuplicate(duplicates2));

        int[] duplicates3 = new int[] {1,1,1,3,3,4,3,2,4,2};
        System.out.println(cd.containsDuplicate(duplicates3));
    }

    //Approach 1: Two loops. Time complexity: O(N^2), memory: O(1)

    //Approach 2: Sort and then iterate over array. Time complexity: O(N log N), memory: O(1) - not counting the memory used by sort

    /**
     * Approach 3:
     *  Use a datastructure like HashSet
     *
     * Complexity:
     *  Time complexity: O(N)
     *  Memory: O(N)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> store = new HashSet<>();
        for(int num : nums) {
            if(! store.add(num)) return true; //has duplicate
        }
        return false; //no duplicates
    }

}
