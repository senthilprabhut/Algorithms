package math;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an array of size n, find the majority element.
 *  The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *  You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Links:
 *  https://leetcode.com/problems/majority-element/description/
 *  https://www.programcreek.com/2014/02/leetcode-majority-element-java/
 */
public class q34_MajorityElement1 {
    public static void main(String[] args) {
        int[] input = new int[]{3,3,4,6,7,3,3};
        q34_MajorityElement1 me = new q34_MajorityElement1();
        System.out.println(me.majorityElement(input));
    }

    /**
     * Approach 1:
     *  Linear Time Majority Vote Algorithm - http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
     *  https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int count=1, majElement=nums[0];
        for (int i=1; i < nums.length; i++) {
            if (count == 0)
                majElement = nums[i]; //Set the current num as majority element
            else if (nums[i] == majElement)
                count ++; //if the same number repeats, increment the counter
            else
                count--; //if a different no is encountered, decrement the counter
        }
        return majElement;
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
