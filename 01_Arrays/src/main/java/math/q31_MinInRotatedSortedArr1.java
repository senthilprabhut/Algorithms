package math;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *  Find the minimum value. You may assume no duplicate exists in the array.
 *
 * Links:
 *  https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 *  https://www.programcreek.com/2014/02/leetcode-find-minimum-in-rotated-sorted-array/
 */
public class q31_MinInRotatedSortedArr1 {
    public static void main(String[] args) {
        q31_MinInRotatedSortedArr1 mr = new q31_MinInRotatedSortedArr1();
        int[] arr = new int[] {4,5,6,7,1,2};
        System.out.println(mr.findMin(arr));
    }

    /**
     * Approach:
     *  The minimum value must satisfy one of two conditions: 1) If rotate, A[min] < A[min - 1]; 2) If not, A[0].
     *  Therefore, we can use binary search: check the middle value, if it is less than previous one, then it is minimum
     *  If not, there are 2 conditions as well: If it is greater than both left and right value,
     *  then minimum value should be on its right, otherwise on its left.
     *
     * Links:
     *  https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/discuss/48487
     */
    public int findMin(int[] num) {
        if(num == null || num.length == 0) return 0;

        if (num.length == 1) return num[0]; //only one value

        int start = 0, end = num.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mid > 0 && num[mid] < num[mid-1]) return num[mid];

            if (num[start] <= num[mid] && num[mid] > num[end]) start = mid+1;
            else end = mid - 1;
        }
        return num[start];
    }
}
