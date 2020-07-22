package math;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of n positive integers and a positive integer s,
 *  find the minimal length of a contiguous subarray of which the sum ≥ s.
 *  If there isn't one, return 0 instead.
 *  For example, given the array [2,3,1,2,4,3] and s = 7,
 *  the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Links:
 *  https://leetcode.com/problems/minimum-size-subarray-sum/description/
 *  https://www.programcreek.com/2014/05/leetcode-minimum-size-subarray-sum-java/
 */
public class q15_MinSizeSubarray {
    public static void main(String[] args) {
        q15_MinSizeSubarray mss = new q15_MinSizeSubarray();
        int[] arr = new int[] {2,3,1,2,4,3};
        System.out.println(mss.minSubArrayLen(7, arr));
    }

    /**
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int minSubArrayLen(int expected, int[] nums) {
        if(nums == null | nums.length == 1) return 0; //validation

        int startIdx=0, currIdx=0, sum=0, minLen=Integer.MAX_VALUE;
        while (currIdx < nums.length) {
            sum += nums[currIdx++];  //currentIndex moves through all elements in the input array

            while (sum >= expected) { //if the new sum from currIndex is greater, calculate minLen and increment
                minLen = Math.min(minLen, currIdx-startIdx);
                sum -= nums[startIdx++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
