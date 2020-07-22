package pcreekproblems;

import java.util.ArrayList;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a set of distinct integers, nums, return all possible subsets
 *  Note: The solution set must not contain duplicate subsets
 *  Example:
 *  If nums = [1,2,3], a solution is:
 *  [
 *      [3],
 *      [1],
 *      [2],
 *      [1,2,3],
 *      [1,3],
 *      [2,3],
 *      [1,2],
 *      []
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/subsets/description/
 *  https://www.programcreek.com/2013/01/leetcode-subsets-java/
 */
public class q04_Subsets {

    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        q04_Subsets a = new q04_Subsets();

        List<List<Integer>> results = a.subsets1(input);
        results.forEach(System.out::println);
        System.out.println();

        results = a.subsets2(input);
        results.forEach(System.out::println);
        System.out.println();

        results = a.subsets3(input);
        results.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Approach 1: Iterative
     *  While iterating through all numbers, for each new number, we can either pick it or not pick it
     *  If pick, just add the current number to every existing subset
     *  If not picked, just leave all existing subsets1 as they are
     *  We just combine both into our result
     *
     * Links:
     *  https://leetcode.com/problems/subsets/discuss/122645
     *
     * Complexity:
     *  Time: 2ⁿ since we do 2*2*2... n times, n is the no of elements in the array
     *  Space: 2ⁿ since we have 2ⁿ results
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i)); //O(1) - uses System.arraycopy
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }

    /**
     * Approach 2: Recursive - DFS
     *  Prints the items in lexical order
     *
     * Links:
     *   https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
     *
     * Complexity:
     *  Time: 2ⁿ since we do 2*2*2... n times, n is the no of elements in the array
     *  Space: 2ⁿ since we have 2ⁿ results
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();
        dfs(nums, 0, selected, result);
        return result;
    }

    private void dfs(int[] input, int start, List<Integer> selected, List<List<Integer>> results) {
        results.add(new ArrayList<>(selected));

        //Base condition
        if (start == input.length) {
            return;
        }

        for (int i = start; i < input.length; i++) {
            selected.add(input[i]);
            dfs(input, i + 1, selected, results);
            selected.remove(selected.size() - 1);
        }
    }

    /**
     * Approach 3: Recursive
     *
     * Links:
     *  https://www.youtube.com/watch?v=p8SDPaX1wgw
     *
     * Complexity:
     *  Time: 2ⁿ since we do 2*2*2... n times, n is the no of elements in the array
     *  Space: 2ⁿ since we have 2ⁿ results
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] isSelected = new boolean[nums.length];
        subsetsRec(nums, 0, isSelected, result);
        return result;
    }

    private void subsetsRec(int[] nums, int start, boolean[] isSelected, List<List<Integer>> result) {
        //Base condition
        if (start == nums.length) {
            //We've crossed the last element. Add selected array elements to result
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (isSelected[i] == true) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
            return;
        }

        //When we encounter a char, we first not print it and go further - for cases like 000..
        isSelected[start] = false;
        subsetsRec(nums, start + 1, isSelected, result);

        //In the second case, we'll print it and go further - for cases like 100 ...
        isSelected[start] = true;
        subsetsRec(nums, start + 1, isSelected, result);
    }
}
