package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 *  Find all unique triplets in the array which gives the sum of zero.
 *  Note:
 *  Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 *  The solution set must not contain duplicate triplets.
 *  For example, given array S = {-1 0 1 2 -1 -4},
 *  A solution set is: (-1, 0, 1), (-1, -1, 2)
 *
 * Links:
 *  https://leetcode.com/problems/3sum-smaller
 *  https://www.programcreek.com/2012/12/leetcode-3sum/
 */
public class q09_ThreeSum {
    public static void main(String[] args) {
        q09_ThreeSum ts = new q09_ThreeSum();
        int[] input = new int[] {-1,0,1,2,-1,-4};
        System.out.println(ts.threeSum(input));
    }

    /**
     * Links:
     *  https://www.youtube.com/watch?v=-AMHUdZc9ss
     *
     * Complexity:
     *  Time - O(n²)
     *  Space - O(1)
     */
    public List<List<Integer>> threeSum(int[] input) {
        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(input);

        for(int i=0; i<input.length-2; i++) {
            if(i==0 || input[i] > input[i-1]) { //This check is to avoid selecting duplicate values
                int start = i+1;
                int end = input.length-1;

                while (start<end) {
                    if (input[i] + input[start] + input[end] == 0) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(input[i]);
                        list.add(input[start]);
                        list.add(input[end]);
                        results.add(list);

                        start++;
                        end--;

                        while(input[start] == input[start-1] && start<end) start++;
                        while (input[end] == input[end+1] && end > start) end--;
                    } else if (input[i] + input[start] + input[end] < 0) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return results;
    }
}
