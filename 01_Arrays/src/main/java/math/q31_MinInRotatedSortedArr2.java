package math;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *  Find the minimum value. You may assume no duplicate exists in the array.
 *
 * Links:
 *  https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 *  https://www.programcreek.com/2014/03/leetcode-find-minimum-in-rotated-sorted-array-ii-java/
 */
public class q31_MinInRotatedSortedArr2 {
    public static void main(String[] args) {
        q31_MinInRotatedSortedArr2 mr = new q31_MinInRotatedSortedArr2();
        int[] arr = new int[] {4,5,5,5,6,6,7,1,1,2};
        System.out.println(mr.findMin(arr));
    }

    /**
     * Approach:
     *  Same as before but with one extra condition
     *  When value[mid] == value[hi], we aren't sure the position of minimum in mid’s left or right, so just let upper bound reduce one.
     *
     * Links:
     *  https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/48808
     */
    public int findMin(int[] num) {
        if(num == null || num.length == 0) return 0;

        if (num.length == 1) return num[0]; //only one value

        int start = 0, end = num.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (num[mid] > num[end]) start = mid+1;
            else if (num[mid] < num[end]) end = mid;
            else {
                // when value[mid] and value[end] are same
                end--;
            }
        }
        return num[start];
    }
}
