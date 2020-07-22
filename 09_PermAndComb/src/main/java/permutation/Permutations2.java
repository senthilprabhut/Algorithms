package permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Problem:
 *   Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *   For example,
 *   [1,1,2] have the following unique permutations:
 *  [
 *    [1,1,2],
 *    [1,2,1],
 *    [2,1,1],
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/permutations-ii
 *  Solution 1: http://www.programcreek.com/2013/02/leetcode-permutations-java/
 *  Solution 2 - https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
 *
 * Complexity:
 *
 */
public class Permutations2 {
    public static void main(String[] args) {
        int[] input = {1,1,2};
        Permutations2 p = new Permutations2();

        //Sort the array - helps to weed out duplicates during permutation
        Arrays.sort(input);
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        p.dfs(input,0, results);
        System.out.println(results);

        results = new ArrayList<>();
        p.dfs2(input, new ArrayList<>(), results, new boolean[input.length]);
        System.out.println(results);
    }

    public void dfs(int[] input, int start, List<List<Integer>> resultList) {
        if(start == input.length) {
            //Convert array to list and add to result list
            resultList.add(Arrays.stream(input).boxed().collect(Collectors.toList()));
            return;
        }

        for(int j=start; j<input.length; j++) {
            if(j > start && input[j] == input[j-1]) continue; //skip choosing the same number for the same position

            swap(input, start, j);
            dfs(input, start + 1, resultList);
            swap(input, start, j);
        }
    }

    public void dfs2(int[] input, List<Integer> selected, List<List<Integer>> results, boolean[] isSelected) {
        if(selected.size() == input.length) {
            results.add(new ArrayList<>(selected));
            return;
        }

        for(int i=0; i<input.length; i++) {
            if(isSelected[i] == true || i>0 && input[i] == input[i-1] && isSelected[i-1] == false ) continue;

            isSelected[i] = true;
            selected.add(input[i]);
            dfs2(input,selected,results, isSelected);
            isSelected[i] = false;
            selected.remove(selected.size()-1);
        }
    }

    private void swap(int[] source, int start, int j) {
        int temp = source[j];
        source[j] = source[start];
        source[start] = temp;
    }
}
