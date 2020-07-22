package pcreekprobs;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *  For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 *
 * Links:
 *  https://leetcode.com/problems/maximum-product-subarray/description/
 *  https://www.programcreek.com/2014/03/leetcode-maximum-product-subarray-java/
 *
 */
public class q04_MaximumSubarrayProduct {
    public static void main(String[] args) {

    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];

        // imax/imin stores the max/min product of subarray that ends with the current number A[i]
        for (int i=1, maxEndingHere=maxSoFar, minEndingHere=maxSoFar; i<nums.length; i++) {

            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (nums[i] < 0) {
                //swap minEndingHere & maxEndingHere
                int temp = minEndingHere;
                minEndingHere = maxEndingHere;
                maxEndingHere = temp;
            }

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            minEndingHere = Math.min(nums[i], minEndingHere * nums[i]);
            maxEndingHere = Math.max(nums[i], maxEndingHere * nums[i]);

            // the newly computed max value is a candidate for our global result
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
