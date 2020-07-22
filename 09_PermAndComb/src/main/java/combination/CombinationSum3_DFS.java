package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9
 * can be used and each combination should be a unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 * Example 1: Input: k = 3, n = 7 Output: [[1,2,4]]
 * Example 2: Input: k = 3, n = 9 Output: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * The first impression of this problem should be depth-first search(DFS). To solve DFS problem, recursion is a normal implementation.
 *
 * http://www.programcreek.com/2014/05/leetcode-combination-sum-iii-java/
 * https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSum3_DFS {

    public static void main(String[] args) {
        CombinationSum3_DFS a = new CombinationSum3_DFS();
        System.out.println(a.combinationSum(3,9));
    }

    public List<List<Integer>> combinationSum(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtrack(k, n, 1, new ArrayList<Integer>(), result); //Start from 1
        return result;
    }

    public void backtrack(int length, int sum, int start, ArrayList<Integer> selectedList, List<List<Integer>> resList) {
        if(sum < 0) return;
        //If we have reached the desired target number, add the selected numbers to the result list

        if(sum == 0 && selectedList.size() == length) {
            ArrayList<Integer> temp = new ArrayList<Integer>(selectedList);
            resList.add(temp);
            return;
        }

        for (int i = start; i <=9; i++) {
            selectedList.add(i); //Add the selected number to selectedList
            backtrack(length, sum - i, i+1, selectedList, resList); // i + 1 because we CANNOT reuse same elements
            selectedList.remove(selectedList.size() - 1); //Remove the number from the selectedList
        }
    }
}
