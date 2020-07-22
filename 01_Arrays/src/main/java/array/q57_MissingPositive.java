package array;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given an unsorted integer array, find the first missing positive integer.
 *  For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 *  Your algorithm should run in O(n) time and uses constant space.
 *
 * Links:
 *  https://leetcode.com/problems/first-missing-positive/description/
 *  https://www.programcreek.com/2014/05/leetcode-first-missing-positive-java/
 */
public class q57_MissingPositive {
    public static void main(String[] args) {
        q57_MissingPositive mp = new q57_MissingPositive();
        System.out.println(mp.firstMissingPositive(new int[] {1,2,0}));
        System.out.println(mp.firstMissingPositive(new int[] {3,4,-1,1}));
    }

    /**
     * Approach:
     *  Put each number in its right place. For example: When we find 5, then swap it with A[4] (A[5-1] - converting 5 to index).
     *  At last, the first place where its number is not right, return the place + 1 (convert index to number).
     *
     * Links:
     *  https://leetcode.com/problems/first-missing-positive/discuss/17071/My-short-c++-solution-O(1)-space-and-O(n)-time
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int firstMissingPositive(int[] nums) {

        // no need to swap when ith element is out of range [0,n] or when it is a duplicate element
        for (int i=0; i<nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) {
                // swap elements
                int tempNo = nums[i];
                nums[i] = nums[tempNo-1];
                nums[tempNo-1] = tempNo;
            }
        }
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return -1;
    }
}
