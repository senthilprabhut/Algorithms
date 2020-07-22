package math;

import java.util.Arrays;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 *  Your algorithm's runtime complexity must be in the order of O(log n).
 *  If the target is not found in the array, return [-1, -1].
 *  For example,
 *      Given [5, 7, 7, 8, 8, 10] and target value 8,
 *      return [3, 4].
 *
 * Links:
 *  https://leetcode.com/problems/search-for-a-range/description/
 *  https://www.programcreek.com/2014/04/leetcode-search-for-a-range-java/
 */
public class q47_SearchForRange {
    public static void main(String[] args) {
        q47_SearchForRange sfr = new q47_SearchForRange();
        int[] input = new int[] {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString (sfr.searchRange(input, 8)) );
    }

    /**
     * Approach: Clean iterative solution with two binary searches
     *  The problem can be simply broken down as two binary searches for the begining and end of the range, respectively:
     *  First let’s find the left boundary of the range. We initialize the range to [i=0, j=n-1].
     *  In each step, calculate2 the middle element [mid = (i+j)/2].
     *  Now according to the relative value of A[mid] to target, there are three possibilities:
     *      If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
     *      If A[mid] > target, it means the range must begins on the left of mid (j = mid-1)
     *      If A[mid] = target, then the range must begins on the left of or at mid (j= mid). We can merge condition 2 and 3
     *      When the loop terminates, if A[i]==target, then i is the left boundary of the range; otherwise, just return -1;
     *
     *      If A[mid] > target, then the range must begins on the left of mid (j = mid-1)
     *      If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
     *      If A[mid] = target, then the range must begins on the right of or at mid (i= mid). We can again merge 2 & 3
     *      When we use mid = (i+j)/2, the mid is rounded to the lowest integer. In other words, mid is always biased towards the left.
     *      To make mid bias towards right, mid = (i + j) /2 + 1
     * Explanation:
     *  https://leetcode.com/problems/search-for-a-range/discuss/14699
     *
     * Links:
     *  https://leetcode.com/problems/search-for-a-range/discuss/14699
     */
    public int[] searchRange(int[] nums, int target) {
        int start=0, end=nums.length-1;
        int[] result = new int[] {-1, -1};

        //Binary search to the left:  < and = conditions are merged when searching left
        while (start < end) {
            int mid = (start+end)/2;
            if (target > nums[mid]) start = mid+1;
            else end=mid;  //target can be equal to or less than mid
        }
        if (nums[start] != target) return result;
        else result[0] = start;

        //Binary search to the right:  > and = conditions are merged when searching right
        end = nums.length-1; //Reset end alone. Start can continue from same position
        while (start < end) {
            int mid = (start+end)/2 + 1; //make mid bias towards right
            if (target < nums[mid]) end=mid-1;
            else start=mid;
        }
        result[1] = end;
        return result;
    }
}
