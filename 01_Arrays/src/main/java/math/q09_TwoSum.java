package math;

import java.util.HashMap;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *	You may assume that each input would have exactly one solution, and you may not use the same value twice.
 *	Example:
 *	Given nums = [2, 7, 11, 15], target = 9,
 *	Because nums[0] + nums[1] = 2 + 7 = 9,
 *	return [0, 1]
 *
 * Links:
 * 	https://leetcode.com/problems/two-sum/description/
 *  https://www.programcreek.com/2012/12/leetcode-solution-of-two-sum-in-java/
 *
 */
public class q09_TwoSum {

    /*
     * The result will contain the position (Starts from 1) instead of index
     * So the print will use pos-1 to print the value at index
     */
	public static void main(String[] args) {
	    q09_TwoSum ts = new q09_TwoSum();
		int[] arr = { 64, 57, 2, 78, 43, 73, 53, 86 };

		int[] result = ts.twoSum1(arr, 88);
        System.out.printf("%d, %d \n", arr[result[0]], arr[result[1]]);

        result = ts.twoSum2(arr, 88);
        System.out.printf("%d, %d \n", arr[result[0]], arr[result[1]]);
	}

	/*
	 * Two loops - returns the position of two numbers instead of index
	 * Time Complexity - O(n^2)
	 * Space Complexity - O(1)
	 */
	public int[] twoSum1(int[] numbers, int target) {
		int[] ret = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] == target) {
					ret[0] = i;
					ret[1] = j;
				}
			}
		}
		return ret;
	}


	/*
	 * Approach 1: Map and a loop
	 *  Map stores the remaining sum when a number is selected
	 *  The optimal solution to solve this problem is using a HashMap.
	 *  For each value of the array, (target-nums[i]) and the index are stored in the HashMap.
	 *
	 * Complexity:
     *  Time - O(n)
     *  Space - O(n)
	 */
	public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];

		HashMap<Integer, Integer> remainingSumMap = new HashMap<Integer, Integer>();
		for (int cntr = 0; cntr < numbers.length; cntr++) {
			if (remainingSumMap.containsKey(numbers[cntr])) {
				int index = remainingSumMap.get(numbers[cntr]);
				result[0] = index;
				result[1] = cntr;
				break;
			} else {
				remainingSumMap.put(target - numbers[cntr], cntr);
			}
		}
		return result;
	}

}
