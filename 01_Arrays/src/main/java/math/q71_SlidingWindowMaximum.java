package math;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
 *  very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *  For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *  Window position                Max
 *  ---------------               -----
 *  [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7        3
 *  1  3 [-1  -3  5] 3  6  7        5
 *  1  3  -1 [-3  5  3] 6  7        5
 *  1  3  -1  -3 [5  3  6] 7        6
 *  1  3  -1  -3  5 [3  6  7]       7
 *  Therefore, return the max sliding window as [3,3,5,5,6,7].
 *  Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Links:
 *  https://leetcode.com/problems/sliding-window-maximum/description/
 *  https://www.programcreek.com/2014/05/leetcode-sliding-window-maximum-java/
 */
public class q71_SlidingWindowMaximum {
    public static void main(String[] args) {
        q71_SlidingWindowMaximum swm = new q71_SlidingWindowMaximum();
        int[] arr = new int[] {1,3,-1,-3,-5,3,6,7};
        System.out.println(Arrays.toString(swm.maxSlidingWindow(arr, 3)));
    }

    /**
     * Approach:
     *  We scan the array from 0 to n-1, keep “promising” elements in the deque.
     *  The algorithm is amortized O(n) as each element is put and polled once.
     *  At each i, we keep “promising” elements, which are potentially max number in
     *  window [i-(k-1),i] or any subsequent window. This means
     *      1. If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head,
     *      as we are using a deque and elements are ordered as the sequence in the array
     *      2. Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i]
     *      from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the “max”
     *      in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.
     *      3. As a result elements in the deque are ordered in both sequence in array and their value.
     *      At each step the head of the deque is the max element in [i-(k-1),i]
     *
     * Links:
     *  https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation - comment EdickCoding
     *
     * Complexity:
     *  Time is O(N)
     *  Space is O(1) // Constant space for queue
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return nums; //Return empty array

        int[] result = new int[n-k+1];
        Deque<Integer> queue = new ArrayDeque<>(k); //Store only index
        for (int i = 0; i < n; i++) {
            //Remove numbers out of range k
            if (!queue.isEmpty() && queue.peek() < (i-k+1)) {   //i-k+1 is the starting index
                queue.poll();
            }

            //Remove smaller numbers in k range as they are useless
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }

            //q contains index... result contains content
            queue.offer(i);
            if (i-k+1 >= 0) {
                result[i-k+1] = nums[queue.peek()];
            }
        }
        return result;
    }
}
