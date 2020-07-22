package pcreekproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *  Note: The solution set must not contain duplicate subsets.
 *  Example:
 *  If nums = [1,2,2], the solution is:
 *  [
 *      [2],
 *      [1],
 *      [1,2,2],
 *      [2,2],
 *      [1,2],
 *      []
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/subsets-ii/description/
 *  https://www.programcreek.com/2013/01/leetcode-subsets-ii-java/
 */
public class q05_Subsets2 {

    public static void main(String[] args) {
        int[] input = {1, 2, 2};
        q05_Subsets2 a = new q05_Subsets2();

        List<List<Integer>> result = a.subsetsWithDup1(input);
        result.forEach(System.out::println);
        System.out.println();

        result = a.subsetsWithDup2(input);
        result.forEach(System.out::println);
        System.out.println();

        result = a.subsetsWithDup3(input);
        result.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Approach 1: Iterative
     *  If we want to insert an element which is a duplicate, we can only insert it after the newly inserted elements from last step
     *
     * Links:
     *  https://leetcode.com/problems/subsets-ii/discuss/30137/Simple-iterative-solution/29221 (user: ofLucas)
     *
     * Complexity:
     *  Time: 2ⁿ since we do 2*2*2... n times, n is the no of elements in the array
     *  Space: 2ⁿ since we have 2ⁿ results
     */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        Arrays.sort(nums); //O(n log n)
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); //empty set

        for (int i = 0, startIndex=0; i < nums.length; i++) {
            int size = result.size();
            startIndex = (i > 0 && nums[i] == nums[i-1]) ? startIndex : 0;
            for (int j = startIndex; j < size; j++) {
                List<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(nums[i]);
                result.add(subset);
            }
            startIndex = size;
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
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();
        dfs(nums, 0, selected, result);
        return result;
    }

    private void dfs(int[] nums, int start, List<Integer> selected, List<List<Integer>> results) {
        results.add(new ArrayList<>(selected));

        //Base condition
        if (start == nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue; //skip duplicates
            }
            selected.add(nums[i]);
            dfs(nums, i + 1, selected, results);
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
    public List<List<Integer>> subsetsWithDup3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] isSelected = new boolean[nums.length];
        subsetsRec(nums, 0, isSelected, result);
        return result;
    }

    private void subsetsRec(int[] input, int start, boolean[] isSelected, List<List<Integer>> results) {
        //Handle duplicates
        while (start > 0 && start < input.length && input[start] == input[start - 1] && isSelected[start - 1] == false) {
            isSelected[start] = false;
            start += 1;
        }

        //Base condition
        if (start == input.length) {
            //We've crossed the last element. print selected array elements
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < input.length; i++) {
                if (isSelected[i] == true) {
                    tempList.add(input[i]);
                }
            }
            results.add(tempList);
            return;
        }

        //When we encounter a char, we first print it and go further - for cases like 100..
        isSelected[start] = true;
        subsetsRec(input, start + 1, isSelected, results);

        //In the second case, we'll NOT print it and go further - for cases like 000 ...
        isSelected[start] = false;
        subsetsRec(input, start + 1, isSelected, results);
    }
}
