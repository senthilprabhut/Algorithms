package pcreekproblems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *  For example, given the following matrix:
 *  1 0 1 0 0
 *  1 0 1 1 1
 *  1 1 1 1 1
 *  1 0 0 1 0
 *  Return 6
 *
 * Links:
 *  https://leetcode.com/problems/maximal-rectangle/description/
 *  https://www.programcreek.com/2014/05/leetcode-maximal-rectangle-java/
 *  Tushar - https://www.youtube.com/watch?v=ZmnqCZp9bBs&t=706s
 */
public class q10_MaximalRectangle {
    public static void main(String[] args) {
        q10_MaximalRectangle mr = new q10_MaximalRectangle();
        char[][] input = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(mr.maximalRectangle(input));
    }

    /**
     * Approach:
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-maximal-rectangle-java/
     *
     * Complexity:
     *  Time is O(n²)
     *  Space is O(n²)
     */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] height = new int[rows+1][cols];
        int maxArea = 0;

        for (int i=1; i<=rows; i++) {
            for (int j=0; j<cols; j++) {
                if (matrix[i-1][j] == '0') {
                    height[i][j] = 0;
                } else {
                    height[i][j] = 1 + height[i-1][j];
                }
            }
        }


        for (int i = 1; i < height.length; i++) {
            int area = maxHistogramArea(height[i]);
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    private int maxHistogramArea(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = Integer.MIN_VALUE;

        for (int i=0; i<height.length; i++) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
            } else {
                //if a smaller no is found, calculate area
                int top = stack.pop(), area=0;
                if (stack.isEmpty()) {
                    area = top * i;
                } else {
                    area = top * (i-stack.peek()-1);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}
