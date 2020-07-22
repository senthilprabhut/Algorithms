package pcreekprobs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an unsorted array of integers, find the length of longest increasing subsequence.
 *  For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is [2, 3, 7, 101].
 *  Therefore the length is 4.
 *
 * Links:
 *  https://leetcode.com/problems/longest-increasing-subsequence/description/
 *  https://www.programcreek.com/2014/04/leetcode-longest-increasing-subsequence-java/
 *  http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 *
 */
public class q15_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        q15_LongestIncreasingSubsequence lis = new q15_LongestIncreasingSubsequence();
        int[] arr1 = {3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10};
        System.out.println(lis.lengthOfLIS1(arr1));
        int[] arr2 = {3, 4, -1, 0, 6, 2, 3};
        System.out.println(lis.lengthOfLIS1(arr2));

        System.out.println(lis.lengthOfLIS2(arr1));
        System.out.println(lis.lengthOfLIS2(arr2));
    }

    /**
     * Approach 1: Binary Search
     *  Maintain a Temporary array which stores the min of the last value of an increasing subsequence of particular length
     *  Eg. TempArr[1] will store the index of the smallest element of subsequence length 1.
     *  If subsequece is of size 2, then the index of the last element in the subsequence is stored
     *
     *  The result array will store the indices of numbers which are part of the longest increasing subsequence
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=S9oUiVYEq7E
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/array/LongestIncreasingSubSequenceOlogNMethod.java
     *
     * Complexity:
     *  Time complexity is O(n log n)
     *  Space complexity is O(n)
     */
    public int lengthOfLIS1(int[] nums) {
        int[] temp = new int[nums.length];
        int[] result = new int[nums.length];

        //We set temp[0] = 0 and initialize result array with -1
        Arrays.fill(result, -1);

        int lengthIdx = 0; //Length tracks the longest increasing subsequence
        for (int i=1; i<nums.length; i++) {
            if (nums[i] > nums[temp[lengthIdx]]) {
                temp[++lengthIdx] = i; //placed index "i" in temp[++length]
                result[i] = temp[lengthIdx-1]; //we came to i from temp[length-1]
            } else {
                //Binary search on temp array and replace the number equal to or the upper bound of nums[i] with itself
                int start=0, end=lengthIdx;
                while (start != end) {
                    int mid = start + (end-start)/2;
                    if (nums[i] > nums[temp[mid]]) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }
                temp[start] = i; //placed index "i" in the correct location in temp array
                result[i] = (start == 0 ? -1 : temp[start-1]); //we came to i from temp[start-1]
            }
        }

        printSequence(nums, result, temp[lengthIdx] );
        return lengthIdx + 1;
    }

    private void printSequence(int[] nums, int[] result, int currIdx) {
        Deque<Integer> stack = new LinkedList<>();
        while (currIdx != -1) {
            stack.push(nums[currIdx]);
            currIdx = result[currIdx];
        }
        System.out.println(stack.toString());
    }

    /**
     * Approach 2:  DP
     *  Formula is if(arr[j] < arr[i]) { T[i] = max(T[i], T[j] + 1 }
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=CE2b_-XfVDk&t=14s
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
     *
     * Complexity:
     *  Time complexity is O(n²)
     *  Space complexity is O(n)
     */
    public int lengthOfLIS2(int[] nums) {

        int[] dp = new int[nums.length];

        //There is a LIS of min size 1 at each location
        Arrays.fill(dp ,1);

        for (int i=1; i<nums.length; i++) {
            for (int j=0; j <i; j++) {
                if (nums[j] < nums[i]) {
                    //If nums[i] is greater, its longest subsequence will be ATLEAST 1 more than the longest subsequence in nums[j]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length-1];
    }
}
