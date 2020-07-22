package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 *  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n
 *
 *  For example,
 *  If n = 4 and k = 2, a solution is:
 *  [
 *    [2,4],
 *    [3,4],
 *    [2,3],
 *    [1,2],
 *    [1,3],
 *    [1,4],
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/combinations
 *  http://www.programcreek.com/2014/03/leetcode-combinations-java/ - Solution 1
 *
 * Complexity:
 *  Time complexity is O(2^n) and Space complexity is O(n)
 */
public class Combinations {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        Combinations a = new Combinations();

        System.out.println(a.combinations(n, k));

    }

    private List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(n<=0 || k==0 || n<k)
            return result;

        dfs1(n,k,1, new ArrayList<Integer>(), result); // start is 1 because question says numbers begin from 1
        return result;

    }

    public void dfs1(int n, int subsetLength, int start, List<Integer> selectedList, List<List<Integer>> resultList) {
        if(selectedList.size() == subsetLength) {
            resultList.add(new ArrayList<>(selectedList));
            return;
        }

        if(start > n) return;

        for(int currentNumber=start; currentNumber<=n; currentNumber++) {
            selectedList.add(currentNumber);
            dfs1(n,subsetLength,currentNumber+1,selectedList,resultList);
            selectedList.remove(selectedList.size()-1);
        }
    }

}



