package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an array of integers and an integer k, return true if and only if there are two distinct indices i and j
 *  in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
 *
 * Links:
 *  https://leetcode.com/problems/contains-duplicate-ii/description/
 *  https://www.programcreek.com/2014/05/leetcode-contains-duplicate-ii-java/
 */
public class q21_ContainsDuplicate2 {
    public static void main(String[] args) {
        int[] duplicates1 = new int[] {1, 2, 5, 6,1};
        q21_ContainsDuplicate2 cd = new q21_ContainsDuplicate2();
        System.out.println(cd.containsNearbyDuplicate(duplicates1, 3));

        int[] duplicates2 = new int[] {1, 2, 5, 1, 6};
        System.out.println(cd.containsNearbyDuplicate(duplicates2, 3));
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
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> store = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(i>k) store.remove(nums[i-k-1]); //Remove the elements from set which are >k distance apart
            if(! store.add(nums[i])) return true; //has duplicate
        }
        return false; //no duplicates
    }

}
