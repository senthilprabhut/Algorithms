package array;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given a sorted array and a target value, return the index if the target is found.
 *  If not, return the index where it would be if it were inserted in order.
 *  You may assume no duplicates in the array.
 *  Example 1:
 *      Input: [1,3,5,6], 5
 *      Output: 2
 *  Example 2:
 *      Input: [1,3,5,6], 2
 *      Output: 1
 *
 * Links:
 *  https://leetcode.com/problems/search-insert-position/description/
 *  https://www.programcreek.com/2013/01/leetcode-search-insert-position/
 */
public class q16_SearchInsertPosition {
    public static void main(String[] args) {
        q16_SearchInsertPosition sip = new q16_SearchInsertPosition();

        int[] arr1 = new int[] {1,3,5,6};
        System.out.println(sip.searchInsert(arr1, 5));
        System.out.println(sip.searchInsert(arr1, 2));
    }

    /**
     * Approach:
     *  Binary Search
     *
     * Complexity:
     *  Time is O(log N)
     *  Space is O(1)
     */
    public int searchInsert(int[] array, int target) {
        int left = 0, right = array.length-1;

        while (left <= right) {
            int mid = (left+ right)/2;
            if (array[mid] == target) return mid;
            else if(array[mid] > target) right = mid-1; //coz the target lies left size of arr[mid]
            else  left = mid + 1;
        }
        return left;
    }
}
