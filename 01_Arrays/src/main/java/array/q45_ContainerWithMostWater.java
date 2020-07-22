package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *  Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Links:
 *  https://leetcode.com/problems/container-with-most-water/description/
 *  https://www.programcreek.com/2014/03/leetcode-container-with-most-water-java/
 *
 */
public class q45_ContainerWithMostWater {
    public static void main(String[] args) {
        q45_ContainerWithMostWater cw = new q45_ContainerWithMostWater();
        int[] heights = new int[] {1,8,6,2,5,4,8,3,7};
        System.out.println(cw.maxArea(heights));
    }

    /**
     * Approach:
     *  Initially we can assume the result is 0. Then we scan from both sides.
     *  Every time move the smaller value pointer to inner array.
     *  If leftHeight < rightHeight, move right side and find a value that is greater than leftHeight.
     *  Similarily, if leftHeight > rightHeight, move left side and find a value that is greater than rightHeight.
     *
     *  Initially we consider the area constituting the exterior most lines. Now, to maximize the area,
     *  we need to consider the area between the lines of larger lengths. If we try to move the pointer
     *  at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line.
     *  But moving the shorter line's pointer could turn out to be beneficial, as per the same argument,
     *  despite the reduction in the width. This is done since a relatively longer line obtained by moving
     *  the shorter line's pointer might overcome the reduction in area caused by the width reduction.
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;

        int max=0, left=0, right=height.length-1;
        while (left < right) {
            max = Math.max(max, (right-left) * Math.min(height[left], height[right])); //distance(rt-lt) * height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
