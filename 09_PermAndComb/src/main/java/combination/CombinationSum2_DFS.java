package combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem:
 *  This problem is an extension of Combination Sum 1. The difference is one number in the array can only be used ONCE.
 *  Given a set of candidate numbers (C) (without duplicates) and a target number (T)
 *
 *  The first impression of this problem should be depth-first search(DFS). To solve DFS problem, recursion is a normal implementation.
 *
 * Links:
 *  http://www.programcreek.com/2014/04/leetcode-combination-sum-ii-java/
 *  https://leetcode.com/problems/combination-sum-ii/
 *  Solution 1 - https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
 *
 * Complexity:
 *  Solution 1 -
 */
public class CombinationSum2_DFS {

    public static void main(String[] args) {
        CombinationSum2_DFS a = new CombinationSum2_DFS();

        //The numbers can be in sorted order - to print in lexical order
        //Arrays.sort(candidates);

        //All the below amount are dollars
        int[] source = { 1, 5, 10, 20, 50, 75, 100};
        //Make 126 dollars from the above currency
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        a.backtrack(source, 126, 0, new ArrayList<Integer>(), result);
        System.out.println(result);
    }

    public void backtrack(int[] candidates, int sum, int start, ArrayList<Integer> selectedList, List<List<Integer>> resList) {
        if(sum < 0) return;
        //If we have reached the target sum=0, add the selected numbers to the result list

        if(sum == 0) {
            ArrayList<Integer> temp = new ArrayList<Integer>(selectedList);
            resList.add(temp);
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i-1]) continue; // skip duplicates for the same position

            selectedList.add(candidates[i]); //Add the selected number to selectedList
            backtrack(candidates, sum - candidates[i], i+1, selectedList, resList); // i + 1 because we CANNOT reuse same elements
            selectedList.remove(selectedList.size() - 1); //Remove the number from the selectedList
        }
    }
}
