package math;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *  You are given a target value to search. If found in the array return its index, otherwise return -1.
 *  You may assume no duplicate exists in the array.
 *
 * Links:
 *  https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *  https://www.programcreek.com/2014/06/leetcode-search-in-rotated-sorted-array-java/
 */
public class q32_SearchInRotatedSortedArr1 {
    public static void main(String[] args) {
        q32_SearchInRotatedSortedArr1 mr = new q32_SearchInRotatedSortedArr1();
        int[] arr = new int[] {4,5,6,7,1,2};
        System.out.println(mr.search(arr, 6));
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14425
     */
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {  // eg. 3,4,5,6,1,2
                if (target > nums[mid] || target <= nums[end]) start=mid+1;
                else end = mid;
            } else {  // eg. 5,6,1,2,3,4
                if (target > nums[mid] || target <= nums[end]) start=mid + 1;
                else end = mid;
            }
        }

        if (start == end && nums[start] == target) return start; //if the values match
        return -1;
    }


    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14425
     */
    public int search2(int[] nums, int target) {
        int minIdx = findMinIdx(nums);  // find the index of the smallest value using binary search.
        if (target == nums[minIdx]) return minIdx;
        int m = nums.length;
        int start = (target <= nums[m - 1]) ? minIdx : 0;
        int end = (target > nums[m - 1]) ? minIdx : m - 1;

        // The usual binary search and accounting for rotation.
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (target > nums[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    public int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end -  start) / 2;
            if (nums[mid] > nums[end]) start = mid + 1;
            else end = mid;
        }
        return start; // start==end here
    }
}
