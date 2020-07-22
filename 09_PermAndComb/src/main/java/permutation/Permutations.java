package permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Problem:
 *  Given a collection of distinct numbers, return all possible permutations.
 *  For example, [1,2,3] have the following permutations:
 *  [
 *    [1,2,3],
 *    [1,3,2],
 *    [2,1,3],
 *    [2,3,1],
 *    [3,1,2],
 *    [3,2,1]
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/permutations
 *  Solution 1 - http://www.programcreek.com/2013/02/leetcode-permutations-java/
 *  Solution 2 - https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
 *
 * Complexity:
 *  Solution 1 -
 *  Solution 2 -
 */
public class Permutations {
    public static void main(String[] args) {
        int[] source = {1,2,3};
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();

        Permutations p = new Permutations();
        p.dfs(source,0, resultList);
        System.out.println(resultList);

        resultList = new ArrayList<List<Integer>>();
        p.dfs2(source, new ArrayList<>(), resultList);
        System.out.println(resultList);

    }

    public void dfs(int[] source, int start, List<List<Integer>> resultList) {
        if(start == source.length) {
            //Convert array to list and add to result list
            resultList.add(Arrays.stream(source).boxed().collect(Collectors.toList()));
            return;
        }

        for(int j=start; j<source.length; j++) {
            swap(source, start, j);
            dfs(source, start + 1, resultList);
            swap(source, start, j);
        }
    }

    public void dfs2(int[] input, List<Integer> selected, List<List<Integer>> results) {
        if(selected.size() == input.length) {
            results.add(new ArrayList<>(selected));
            return;
        }
        for(int i=0; i<input.length; i++) {
            if(selected.contains(input[i])) continue; //check if a no is already used in this permutation
            selected.add(input[i]);
            dfs2(input, selected, results);
            selected.remove(selected.size() - 1);
        }
    }

    private void swap(int[] source, int start, int j) {
        int temp = source[j];
        source[j] = source[start];
        source[start] = temp;
    }
}
