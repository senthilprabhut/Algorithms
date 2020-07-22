package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given n non-negative integers representing an elevation map where the width of each bar is 1,
 *  compute how much water it is able to trap after raining.
 *  For example,
 *      Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6
 *
 * Links:
 *  https://leetcode.com/problems/trapping-rain-water/description/
 *  https://leetcode.com/problems/trapping-rain-water/solution/
 *  https://www.youtube.com/watch?v=UzeL2GcLx3Y
 */
public class q45_TrappingRainWaterInBarGraph {
    public static void main(String[] args) {
        int[] input = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};

        q45_TrappingRainWaterInBarGraph wibg = new q45_TrappingRainWaterInBarGraph();

        System.out.println(wibg.totalWater1(input));
        System.out.println(wibg.totalWater2(input));
        System.out.println(wibg.totalWater3(input));
        System.out.println(wibg.totalWater4(input));
    }

    /**
     * Approach 1:
     *  Use two pointers - From the figure in dynamic programming approach, notice that
     *  as long as (right_max[i] > left_max[i]) (from element 0 to 6), the water trapped depends upon the left_max,
     *  and similar is the case when (left_max[i] > right_max[i]) (from element 8 to 11).
     *  So, we can say that if there is a larger bar at one end(say right), we are assured that the water trapped
     *  would be dependant on height of bar in current direction(from left to right).
     *
     *  As soon as we find the bar at other end(right) is smaller, we start iterating in opposite direction(from right to left).
     *  We must maintain left_max and right_max during the iteration,
     *  but now we can do it in one iteration using 2 pointers, switching between the two.
     *
     *  Initialize left pointer to 0 and right pointer to size-1
     *  While left<right, do:
     *      If height[left] is smaller than height[right]
     *          If height[left]>=left_max , update left_max
     *          Else add left_max−height[left] to ans
     *          Add 1 to left
     *      Else
     *          If height[right]>=right_max, update right_max
     *          Else add right_max−height[right] to ans
     *          Subtract 1 from right
     *
     * Complexity:
     *  Time Complexity is O(n). Single iteration of O(n)
     *  Space Complexity is O(1). Only constant space required for left\text{left}left, right\text{right}right, left_max and right_max.
     */
    public int totalWater1(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water=0;

        while(left < right) {
            if(height[left] < height[right]) { //Water trapped depends on left max
                if (height[left] >= leftMax)
                    leftMax = height[left];
                else
                    water += leftMax - height[left];

                ++left;
            } else {
                if(height[right] >= rightMax) //Water trapped depends on right max
                    rightMax = height[right];
                else
                    water += rightMax - height[right];

                --right;
            }
        }
        return water;
    }

    /**
     * Approach 2:
     *  Use stack - Instead of storing the largest bar upto an index as in Approach #3, we can use stack to
     *  keep track of the bars that are bounded by longer bars and hence, may store water.
     *  Using the stack, we can do the calculations in only one iteration.
     *
     *  While stack is not empty and height[current]>height[st.top()]
     *      It means that the stack element can be popped. Pop the top element as top
     *      Find the distance between the current element and the element at top of stack, which is to be filled.
     *      distance = current− st.top() − 1
     *      Find the bounded height bounded_height=min(height[current],height[st.top()])−height[top]
     *      Add resulting trapped water to answer ans+=distance∗bounded_height
     *  Push current index to top of the stack
     *  Move current to the next position
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public int totalWater2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(0);
        int water=0;
        for(int current=1; current< height.length; current++) {
            if(height[current] <= height[stack.peek()]) {
                stack.push(current);
            } else {
                while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                    int prevHt = height[stack.pop()]; //pop the top element
                    if(stack.isEmpty()) break;

                    int distance = current - stack.peek() - 1; //-1 is to exclude the 1st index which is left boundry
                    int boundedHt = Math.min(height[current], height[stack.peek()]) - prevHt;
                    water += boundedHt * distance;
                }
                stack.push(current);
            }
        }
        return water;
    }

    /**
     * Approach 3:
     *  Dynamic Programming - Pre-compute highest bar on left and right of every bar in O(n) time.
     *  Then use these pre-computed values to find the amount of water in every array element.
     *
     *  Find maximum height of bar from the left end upto an index i in the array left_max
     *  Find maximum height of bar from the right end upto an index i in the array right_max
     *  Iterate over the height\text{height}height array and update ans:
     *  Add min(max_left[i],max_right[i])−height[i] to ans
     *
     * Complexity:
     *  Time Complexity is O(n). We store the maximum heights upto a point using 2 iterations of O(n) each.
     *  We finally update ans using the stored values in O(n).
     *  Space Complexity is O(n) extra space. Additional O(n) space for left_max and right_max arrays than in Approach #4.
     */
    public int totalWater3(int[] height) {
        if(height == null) return 0;

        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        for(int i=1; i<height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }

        int[] rightMax = new int[height.length];
        rightMax[height.length-1] = height[height.length-1];
        for(int j=height.length-2; j>=0; j--) {
            rightMax[j] = Math.max(height[j],rightMax[j+1]);
        }

        int water = 0;
        //Leave the first and last elements
        for(int index=1; index<height.length-1; index++) {
            water += Math.min(leftMax[index], rightMax[index]) - height[index]; //Water at that pos is  lower of LeftMax,RightMax - ht at index
        }
        return water;
    }


    /**
     * Approach 4:
     *  Brute Force - A Simple Solution is to traverse every array element and find the highest bars on left and right sides.
     *  Initialize ans=0
     *  Iterate the array from left to right:
     *  Initialize max_left=0 and max_right=0
     *  Iterate from the current element to the beginning of array updating: max_left=max(max_left,height[j])
     *  Iterate from the current element to the end of array updating: max_right=max(max_right,height[j])
     *  Add min(max_left,max_right)−height[i] to ans
     *
     * Complexity:
     *  Time complexity of this solution is O(n²). For each element of array, we iterate the left and right parts.
     *  Space Complexity is O(1)
     */
    public int totalWater4(int[] height) {
        if(height == null) return 0;

        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }
}
