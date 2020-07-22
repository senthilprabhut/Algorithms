package math;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Find the kth largest value in an unsorted array.
 *  Note that it is the kth largest value in the sorted order, not the kth distinct value.
 *  For example,
 *  Given [3,2,1,5,6,4] and k = 2, return 5.
 *  Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * Links:
 *  https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *  https://www.programcreek.com/2014/05/leetcode-kth-largest-element-in-an-array-java/
 *  https://discuss.leetcode.com/topic/14597/solution-explained
 */
public class q05_KthLargestElementInArray {
    public static void main(String[] args) {
        q05_KthLargestElementInArray kle = new q05_KthLargestElementInArray();
        int[] input = {8,3,2,1,5,6,4};
        System.out.println(kle.findKthLargest1(input, 3));
        System.out.println(kle.findKthLargest2(input, 3));
        System.out.println(kle.findKthLargest3(input, 3));
    }

    /**
     * Approach 1:
     *  Sort array and return the kth value
     *
     * Complexity:
     *  Time: O(N lg N)
     *  Space: O(1)
     */
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * Approach 2:
     *  Other possibility is to use a min oriented priority queue that will store the K-th largest values.
     *  The algorithm iterates over the whole input and maintains the size of priority queue.
     *
     * Complexity:
     *  Time - O(N lg K) running time
     *  Space - O(K) memory
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int num : nums) {
            queue.add(num);

            if (queue.size() > k)
                queue.poll(); //Keep maintaining the size of Heap to match K
        }

        return queue.peek();
    }

    /**
     * Approach 3:
     *  The smart approach for this problem is to use the selection algorithm (based on the partion method -
     *  the same one as used in quicksort). how can we improve the above solution and make it O(N) guaranteed?
     *  The answer is quite simple, we can randomize the input, so that even when the worst case input would be provided
     *  the algorithm wouldn't be affected. So all what it is needed to be done is to shuffle the input.
     *
     * Complexity:
     *  Time: Avg case is O(n). Worst case is P(n²)
     *  Space: O(1)
     */
    public int findKthLargest3(int[] nums, int k) {
        if (k<1 || nums == null) return 0;
        //nums.length-k+1 converts Kth largest query to Nth value query in sorted array
        return getKth(nums.length-k+1, nums, 0, nums.length-1);
    }

    private int getKth(int k, int[] nums, int start, int end) {
        int pivot = nums[end];
        int left=start, right=end;

        //partition based on pivot
        while (true) {
            //All the elements on left of pivot is less than pivot and all the ones on right are greater than pivot
            while (nums[left]<pivot && left<right) left++; //move left ptr forward until the one which is > pivot
            while (nums[right]>=pivot && right>left) right--; //move right ptr left until the one which is < pivot
            if (left == right) break;
            swap(nums, left, right);
        }
        swap(nums, left, end); //Swap the pivot value with the value on left pointer

        if(k == (left+1) ) return pivot; //We use left+1 to convert index to count and compare it with  k
        else if (k < (left+1)) return getKth(k, nums, start, left-1);
        else return getKth(k, nums, left+1, end);
    }

    public void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
}
