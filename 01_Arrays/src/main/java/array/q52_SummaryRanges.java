package array;

import java.util.LinkedList;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a sorted integer array without duplicates, return the summary of its ranges.
 *  Example 1:
 *      Input: [0,1,2,4,5,7]
 *      Output: ["0->2","4->5","7"]
 *  Example 2:
 *      Input: [0,2,3,4,6,8,9]
 *      Output: ["0","2->4","6","8->9"]
 *
 * Links:
 *  https://leetcode.com/problems/summary-ranges/description/
 *  https://www.programcreek.com/2014/07/leetcode-summary-ranges-java/
 */
public class q52_SummaryRanges {
    public static void main(String[] args) {
        q52_SummaryRanges sr = new q52_SummaryRanges();
        System.out.println(sr.summaryRanges(new int[] {0,1,2,4,5,7}));
        System.out.println(sr.summaryRanges(new int[] {0,2,3,4,6,8,9}));
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/summary-ranges/discuss/63219/Accepted-JAVA-solution-easy-to-understand
     *
     * Complexity:
     *  Time is O(1)
     *  Space is O(1)
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new LinkedList<>();

        for (int i=0; i<nums.length; i++) {
            int start = i;

            //check if the current number and next number are in sequence
            while ( i+1 < nums.length && nums[i+1]-nums[i] == 1) {
                i++;
            }

            if (nums[i] != nums[start]) {
                result.add(nums[start] + "->" + nums[i]);
            } else {
                result.add(Integer.toString(nums[i]));
            }
        }

        return result;
    }

}
