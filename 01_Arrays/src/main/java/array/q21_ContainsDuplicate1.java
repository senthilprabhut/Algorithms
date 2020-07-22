package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an array of integers, find if the array contains any duplicates.
 *  Your function should return true if any value appears at least twice in the array, and
 *  it should return false if every value is distinct.
 *
 * Links:
 *  https://leetcode.com/problems/contains-duplicate/description/
 *  https://www.programcreek.com/2014/05/leetcode-contains-duplicate-java/
 */
public class q21_ContainsDuplicate1 {
    public static void main(String[] args) {
        int[] duplicates1 = new int[] {1, 2, 5, 6,1};
        q21_ContainsDuplicate1 cd = new q21_ContainsDuplicate1();
        System.out.println(cd.containsDuplicate(duplicates1));

        int[] duplicates2 = new int[] {1, 2, 5, 6};
        System.out.println(cd.containsDuplicate(duplicates2));
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
