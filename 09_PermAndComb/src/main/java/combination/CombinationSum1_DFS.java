package combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem:
 *  Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 *  find all unique combinations in C where the candidate numbers sums to T.
 *  The same repeated number may be chosen from C unlimited number of times.
 *  #### Same as CombinationSum4 solved by DP and Coin Change Problems ####
 *
 *  Note:
 *   All numbers (including target) will be positive integers.
 *   The solution set must not contain duplicate combinations.
 *   Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 *   The combinations themselves must be sorted in ascending order.
 *   CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR … (a1 = b1 AND a2 = b2 AND … ai = bi AND ai+1 > bi+1)
 *
 * The first impression of this problem should be depth-first search(DFS). To solve DFS problem, recursion is a normal implementation.
 *
 * Links:
 *  https://leetcode.com/problems/combination-sum/
 *  http://www.programcreek.com/2014/02/leetcode-combination-sum-java/
 *  Solution 1 - https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
 *
 * Complexity:
 *  Solution 1 -
 */
public class CombinationSum1_DFS {

    public static void main(String[] args) {
        CombinationSum1_DFS a = new CombinationSum1_DFS();

        int[] input1 = { 3, 1, 2};
        //Arrays.sort(input);   //Sort to print the combinations in lexical order
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        a.backtrack(input1, 6, 0, new ArrayList<Integer>(), result);
        System.out.println(result);

        int[] input2 = {3,2};
        result.clear();
        a.backtrack(input2, 7, 0, new ArrayList<Integer>(), result);
        System.out.println(result);
    }


    public void backtrack(int[] candidates, int sum, int start, ArrayList<Integer> selectedList, List<List<Integer>> resList) {
        if(sum < 0) return;

        //If we have reached target sum=0, add the selected numbers to the result list
        if(sum == 0) {
            ArrayList<Integer> temp = new ArrayList<Integer>(selectedList);
            resList.add(temp);
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            //Below condition is not required if we have condition if (sum < 0)
            //if (sum < candidates[i]) //because we have sorted array, if the target required is less than the current no, we can return
            //    return;

            selectedList.add(candidates[i]); //Add the selected number to selectedList
            backtrack(candidates, sum - candidates[i], i, selectedList, resList); // not i + 1 because we can reuse same elements
            selectedList.remove(selectedList.size() - 1); //Remove the number from the selectedList
        }
    }
}
