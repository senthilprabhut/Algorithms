package math;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *  You are given a target value to search. If found in the array return its index, otherwise return -1.
 *  Duplicate are allowed in the array.
 *
 * Links:
 *  https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 *  https://www.programcreek.com/2014/06/leetcode-search-in-rotated-sorted-array-ii-java/
 */
public class q32_SearchInRotatedSortedArr2 {
    public static void main(String[] args) {
        q32_SearchInRotatedSortedArr2 mr = new q32_SearchInRotatedSortedArr2();
        int[] arr = new int[] {4,5,5,5,6,6,7,1,1,2};
        System.out.println(mr.search(arr, 6));
    }

    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {  // eg. 3,4,5,6,1,2
                if (target > nums[mid] || target <= nums[end]) start=mid+1;
                else end = mid;
            } else if (nums[mid] < nums[end]){  // eg. 5,6,1,2,3,4
                if (target > nums[mid] || target <= nums[end]) start=mid + 1;
                else end = mid;
            } else {
                //mid and end are equal. Reduce end by one
                end--;
            }
        }

        if (start == end && nums[start] == target) return start; //if the values match
        return -1;
    }
}
