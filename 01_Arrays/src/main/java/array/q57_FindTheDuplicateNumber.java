package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 *  prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *  Note:
 *      You must not modify the array (assume the array is read only).
 *      You must use only constant, O(1) extra space.
 *      Your runtime complexity should be less than O(n2).
 *      There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * Links:
 *  https://leetcode.com/problems/find-the-duplicate-number/description/
 *  https://www.programcreek.com/2015/06/leetcode-find-the-duplicate-number-java/
 */
public class q57_FindTheDuplicateNumber {
    public static void main(String[] args) {
        q57_FindTheDuplicateNumber ftdn = new q57_FindTheDuplicateNumber();
        System.out.println(ftdn.findDuplicate1(new int[] {5,2,1,3,5,7,6,4}));
        System.out.println(ftdn.findDuplicate2(new int[] {5,2,1,3,5,7,6,4}));
    }

    /**
     * Approach 1: Finding Cycle using Floyd's cycle-finding algorithm
     *  Use two pointers the fast and the slow. The fast one goes forward two steps each time, while the slow one goes only step each time.
     *  They meet in a circle and the duplicate number must be the entry point of the circle when visiting the array from nums[0].
     *  Next we just need to find the entry point.
     *  We use a point(we can use the fast one before) to visit form begining with one step each time, do the same job to slow.
     *  When fast==slow, they meet at the entry point of the circle.
     *
     *  Please note the constraint in the problem "array nums containing n + 1 integers where each integer is between 1 and n (inclusive)
     *  Since the integer range is from 1 to N+1, item 0 is guarenteed to be a “node” outside any cycle because n[x] must be larger than 0.
     *  Then the 1st fast-slow traversal is guaranteed to have the meet point at equal distance from the common starting point (0) to the cycle entry point
     *
     * Links:
     *  https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int findDuplicate1(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }

        //The "tortoise and hare" step.  We start at the end of the array and try to find an intersection point in the cycle.
        int slow=nums[0], fast=nums[nums[0]];

        //Keep advancing 'slow' by one step and 'fast' by two steps until they meet inside the loop.
        //Get the location where they meet - this is equi-distant from start
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        //Start up another pointer from the beginning of the array and march it forward until it hits the pointer inside the array.
        slow=0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        //The intersection index is the duplicate element.
        return fast;
    }

    /**
     * Approach 2: Binary Search
     *  At first the search space is numbers between 1 to n. Each time I select a number mid (which is the one in the middle)
     *  and count all the numbers equal to or less than mid. Then if the count is more than mid,
     *  the search space will be [1 mid] otherwise [mid+1 n]. I do this until search space is only one number.
     *
     *  Let’s say n=10 and I select mid=5. Then I count all the numbers in the array which are less than equal mid.
     *  If the there are more than 5 numbers that are less than 5, then by Pigeonhole Principle
     *  (https://en.wikipedia.org/wiki/Pigeonhole_principle) one of them has occurred more than once.
     *
     *  Let count be the number of elements in the range 1 .. mid, as in your solution.
     *  If count > mid, then there are more than mid elements in the range 1 .. mid and thus that range contains a duplicate.
     *  If count <= mid, then there are n+1-count elements in the range mid+1 .. n. That is, at least n+1-mid elements in a range of size n-mid. Thus this range must contain a duplicate.
     *
     * Links:
     *  https://leetcode.com/problems/find-the-duplicate-number/discuss/72844/Two-Solutions-(with-explanation):-O(nlog(n))-and-O(n)-time-O(1)-space-without-changing-the-input-array
     *
     * Complexity:
     *  Time is O(N logN)
     *  Space is O(1)
     */
    public int findDuplicate2(int[] nums) {
        int start=0, end=nums.length-1;

        //Outer loop executes O(log n) times
        while (start < end) {
            int mid = start + (end-start)/2;

            int count = 0;
            //loop executes O(n) times
            for(int num : nums) {
                if (num <= nums[mid]) {
                    count++;
                }
            }

            if (count > mid) {
                end = mid;
            } else if (count <= mid) {
                start = mid + 1;
            }
        }

        return nums[start];
    }
}
