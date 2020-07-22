package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  A peak element is an element that is greater than its neighbors.
 *  Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 *  The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *  You may imagine that num[-1] = num[n] = -∞.
 *  For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 *
 * Links:
 *
 *
 */
public class q60_FindPeakElement {
    public static void main(String[] args) {
        q60_FindPeakElement pe = new q60_FindPeakElement();
        System.out.println(pe.findPeakElement(new int[] {1, 2, 3, 1})); //3
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/find-peak-element/discuss/50232/Find-the-maximum-by-binary-search-(recursion-and-iteration)?page=2
     *
     * Complexity:
     *  Time is O(log n)
     *  Space is O(1)
     */
    public int findPeakElement(int[] nums) {
        int low=0, high = nums.length-1;

        while (low < high) {
            int mid1 = low + (high - low)/2;
            int mid2 = mid1 + 1;

            if (nums[mid1] > nums[mid2]) {
                //Find the peak element on the left side
                high = mid1;
            } else {
                //Find the peak on the right side since mid2 > mid1
                low = mid2;
            }
        }
        return low;
    }
}
