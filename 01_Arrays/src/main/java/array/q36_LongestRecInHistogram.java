package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 *  find the area of largest rectangle in the histogram.
 *  a histogram where width of each bar is 1 and given height = [2,1,5,6,2,3], The largest rectangle has area = 10 unit
 *
 * Links:
 *  https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 *  https://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
 */
public class q36_LongestRecInHistogram {
    public static void main(String[] args) {
        q36_LongestRecInHistogram lr = new q36_LongestRecInHistogram();
        int[] arr = new int[] {2,1,5,6,2,3};
        System.out.println(lr.largestRectangleArea(arr));
    }

    /**
     * Approach:
     *  The key to solve this problem is to maintain a stack to store bars' indexes. The stack only keeps the increasing bars.
     *  Do push all heights including 0 height.
     *  i - 1 - s.peek() uses the starting index where height[s.peek() + 1] >= height[top], because the index on
     *      top of the stack right now is the first index left of top with height smaller than top’s height.
     *
     * Explanation:
     *  https://www.youtube.com/watch?v=VNbkzsnllsU
     *  http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
     *
     * Link:
     *  https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28900/O(n)-stack-based-JAVA-solution
     *  https://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        int top=0, currIndex=0, maxHeight=Integer.MIN_VALUE;

        while (currIndex<heights.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || heights[currIndex] >= heights[stack.peek()]) {
                stack.push(currIndex);
                currIndex++;
            } else {
                //when the current height is less than the previous one, calculate max value and check against current Max

                //Get the prev larger height
                top = stack.pop();

                //If the stack is empty, this is the lowest ht encountered - so width is the current index
                //If the stack is not empty, then width is curr ht index (inclusive) - prev smaller ht index (exclusive)
                int width = (stack.isEmpty()) ? currIndex : (currIndex - stack.peek() - 1);
                maxHeight = Math.max(heights[top] * width, maxHeight);
            }
        }

        //if currIndex = heights.length, there might be
        while (!stack.isEmpty()) {
            top = stack.pop();
            int width = (stack.isEmpty()) ? currIndex : (currIndex - stack.peek() - 1);
            maxHeight = Math.max(heights[top] * width, maxHeight);
        }
        return maxHeight;
    }

}
