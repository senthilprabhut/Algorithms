package math;

import java.util.ArrayList;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *  The algorithm should run in linear time and in O(1) space.
 *
 * Links:
 *  https://leetcode.com/problems/majority-element-ii/description/
 *  https://www.programcreek.com/2014/07/leetcode-majority-element-ii-java/
 */
public class q34_MajorityElement2 {
    public static void main(String[] args) {
        int[] input = new int[]{3,3,4,4,4,4,6,7,3,3};
        q34_MajorityElement2 me = new q34_MajorityElement2();
        System.out.println(me.majorityElement(input));
    }

    /**
     * Approach 1:
     *  Linear Time Majority Vote Algorithm - http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
     *  https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
     *  Since the requirement is finding the majority for more than ceiling of [n/3],
     *  the answer would be less than or equal to two numbers.
     *  So we can modify the algorithm to maintain two counters for two majorities.
     *
     *  consider 3 cases:
     *  1. there are no elements that appears more than n/3 times, then whatever the algorithm
     *  got from 1st round wound be rejected in the second round.
     *  2. there are only one elements that appears more than n/3 times, after 1st round one of
     *  the candicate must be that appears more than n/3 times(<2n/3 other elements could only
     *  pair out for <n/3 times), the other candicate is not necessarily be the second most frequent
     *  but it would be rejected in 2nd round.
     *  3. there are two elments appears more than n/3 times, candicates would contain both of
     *  them. (<n/3 other elements couldn't pair out any of the majorities.)
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        int count1=1, count2=0, majElement1=nums[0], majElement2=nums[0];
        for (int i=1; i < nums.length; i++) {
            if (nums[i] == majElement1)
                count1 ++;
            else if (nums[i] == majElement2)
                count2 ++;
            else if (count1 == 0) {
                majElement1 = nums[i];
                count1 ++;
            }
            else if (count2 == 0) {
                majElement2 = nums[i];
                count2 ++;
            }
            else {
                count1--;
                count2--;
            }
        }


        count1=0;
        count2=0;
        for(int num : nums) {
            if (num == majElement1) count1++;
            else if (num == majElement2) count2++;
        }

        if (count1 > nums.length/3) result.add(majElement1);
        if (count2 > nums.length/3) result.add(majElement2);
        return result;
    }

    /**
     * Approach 2:
     *  Since the element occurs more than n/2 times, sort the array and return the middle element
     *
     * Complexity:
     *  Time is O(N logN)
     *  Space is O(1)
     */
}
