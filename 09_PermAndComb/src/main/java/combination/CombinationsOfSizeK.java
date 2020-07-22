package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 *  Print all possible combinations of r elements in a given array of size n
 *
 * For example,
 * If input array is {1, 2, 3, 4} and r is 2, a solution is:
 * [
 *   [1,2],
 *   [1,3],
 *   [1,4],
 *   [2,3],
 *   [2,4],
 *   [3,4]
 * ]
 *
 * Links:
 *  Solution 1 - http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 *  Solution 2 - http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 *
 * Complexity:
 *  Time complexity is O(2^n) and Space complexity is O(n)
 */
public class CombinationsOfSizeK {
    private int[] source;

    public CombinationsOfSizeK(int[] source) {
        this.source = source;
    }

    public static void main(String[] args) {
        int[] source = { 1, 2, 3, 4};
        CombinationsOfSizeK a = new CombinationsOfSizeK(source);
        boolean[] isSelected = new boolean[source.length];

        List<List<Integer>> results = new ArrayList<>();
        a.dfs1(source, 2, 0, new ArrayList<>(), results);
        results.forEach(System.out::println);
        System.out.println();

        a.dfs2(source,2, 0, 0, isSelected);
    }

    /**
     * Method 1 (Fix Elements and Recur)
     * Print elements in lexical order
     */
    public void dfs1(int[] input, int subsetLength, int start, List<Integer> selectedList, List<List<Integer>> results) {
        if(selectedList.size() == subsetLength) {
            results.add(new ArrayList<>(selectedList));
            return;
        }

        for(int i=start; i<input.length; i++) {
            if(i>start && input[i] == input[i-1]) continue; //This is done to skip duplicates
            selectedList.add(input[i]);
            dfs1(input, subsetLength, i+1, selectedList, results);
            selectedList.remove(selectedList.size()-1);
        }
    }

    /*
     *  Method 2 (Include and Exclude every element)
     *  This method is mainly based on Pascal’s Identity, i.e. ncr = n-1cr + n-1cr-1
     *  The idea here is similar to Subset Sum Problem.
     *  Prints elements in lexical order - isSelected is true and then isSelected is false
     */
    public void dfs2(int[] input, int subsetLength, int start, int currLen, boolean[] isSelected) {
        //base condition -  Current combination is ready to be printed
        if(currLen == subsetLength) {
            printArray(input,isSelected);
            return;
        }

        //Below condition handles duplicate values in the array - don't select the same no twice for the same index
        while(start >0 && start<input.length && isSelected[start-1]==false && input[start-1] == input[start]) {
            isSelected[start] = false;
            start += 1;
        }

        // When no more elements are there to put in isSelected
        if (start == input.length) {
            return;
        }

        // For every index we have two options,
        // 1. Either we select it, means put true in used[] and make currLen+1
        // 2. OR we dont select it, means put false in used[] and don't increase currLen

        //Select the number at index
        isSelected[start] = true;
        dfs2(input, subsetLength, start+1, currLen+1, isSelected);

        //We don't select number at index
        isSelected[start] = false;
        dfs2(input, subsetLength, start+1, currLen, isSelected);
    }

    public void printArray(int[] source, boolean[] isSelected) {
        System.out.print("{");
        for (int i = 0; i < source.length; i++) {
            if (isSelected[i] == true) {
                System.out.print(source[i] + "");
            }
        }
        System.out.print("}");
        System.out.println();
    }

}



