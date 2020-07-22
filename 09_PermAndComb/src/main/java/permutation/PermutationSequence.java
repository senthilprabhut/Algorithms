package permutation;


import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 *  The set [1,2,3,…,n] contains a total of n! unique permutations.
 *  By listing and labeling all of the permutations in order,
 *  We get the following sequence (ie, for n = 3):
 *  "123"
 *  "132"
 *  "213"
 *  "231"
 *  "312"
 *  "321"
 *
 *  Given n and k, return the kth permutation sequence.
 *
 *  Note: Given n will be between 1 and 9 inclusive.
 *
 * Links:
 *  https://leetcode.com/problems/permutation-sequence
 *  http://www.programcreek.com/2013/02/leetcode-permutation-sequence-java/
 *  Solution 1 - Not the best. Variant of http://www.programcreek.com/2013/02/leetcode-permutations-java/
 *  Solution 2 - https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n
 *
 * Complexity:
 *  Solution 1 -
 *  Solution 2 -
 *
 */
public class PermutationSequence {
    public static void main(String[] args) {
        PermutationSequence ps = new PermutationSequence();
        int n=3, k=5; //should print 312 from the above sequence


        //Bad way - find all permutations and get nth number from it
        List<List<Integer>> results = new ArrayList<>();
        int[] input = new int[n];
        for(int i=1; i<=3; i++)
            input[i-1] = i;
        ps.dfs(input,k, new ArrayList<>(), results); //The set starts from 1
        System.out.println(results.get(results.size()-1));

        //Right way
        List<Integer> result = ps.getPermutation(n,k);
        System.out.println(result);
    }

    private void dfs(int[] input, int k, List<Integer> selected, List<List<Integer>> results) {
        if(selected.size() == input.length) {
            results.add(new ArrayList<>(selected));
            return;
        }
        for(int i=0; i<input.length; i++) {
            if(selected.contains(input[i])) continue; //check if a no is already used in this permutation
            selected.add(input[i]);
            dfs(input, k, selected, results);
            selected.remove(selected.size() - 1);

            if(results.size()==k) break; //we've reached the permutation that we'd like to print
        }
    }

    /**
     * Algorithm:
     *  say n = 4, you have {1, 2, 3, 4}. We also know the number of permutations of n numbers is n! (4 numbers = 4! = 24)
     *
     *  If you were to list out all the permutations for above list, you have 1 + (permutations of 2, 3, 4),
     *  2 + (permutations of 1, 3, 4), 3 + (permutations of 1, 2, 4), 4 + (permutations of 1, 2, 3)
     *  Each of ones in above list with permutations of 3 numbers will have 6 (3! = 6) possible permutations.
     *  So there would be a total of 24 permutations (1*3! + 1*3! + 1*3! + 1*3! = 24)
     *  So if you were to look for the (k = 14) 14th permutation, it would be in the 3 + (permutations of 1, 2, 4) subset
     *
     *  To programmatically get that, you take k = 13 (subtract 1 because of things always starting at 0) and
     *  divide that by the 6 we got from the factorial, which would give you the index of the number you want
     *  In the array {1, 2, 3, 4}, n=4 and with k=13,  k/(n-1)! = 13/(4-1)! = 13/3! = 13/6 = 2.
     *  The array {1, 2, 3, 4} has a value of 3 at index 2. So the first number is a 3 (for the 14th permutation).
     *
     *  Now, the problem repeats with less numbers. The permutations of {1, 2, 4} would be:
     *  1 + (permutations of 2, 4), 2 + (permutations of 1, 4), 4 + (permutations of 1, 2)
     *  And k is not 13 any more since we eliminated 12 4-number permutations starting with 1 and 2
     *  (1 + permutations of (2, 3, 4) &  2 + permutations of (1, 3, 4) which is 1*3! + 1*3! = 12 numbers).
     *  And 1*3 & 1*3 are 2 sets eliminated which is the same as the index we got previously.
     *  So you subtract 12 from k.. which gives you 1.
     *
     *  Programmatically that would be k = k - [(index from previous) * (n-1)!] = k - 2*(n-1)! = 13 - 2*(3)! = 1
     *  The index to get number from is k / (n - 2)! = 1 / (4-2)! = 1 / 2! = 0.
     *  From sub-array {1, 2, 4}, index 0 is 1 which is our second number of the 14th permutation
     *
     *  Now, the problem repeats with less numbers. The permutations of {2, 4} would be 2 + (Perm 4), 4 + (Perm 2)
     *  New k = k - [(index from prev) * (n-2)!] = 1 - [0 * 2!] = 1 - 0 = 1
     *  New index = k / (n-3)! = 1 / (4-3)! = 1/1 = 1
     *  From sub-array {2,4}, index 1 is 4 which is our 3rd number of the 14th permutation
     *
     *  The last permutation set is {2}
     *  k = k - [(index from pervious) * (n - 3)!] = k - [1 * (4 - 3)!] = 1 - 1 = 0;
     *  Third number's index = k / (n - 4)! = 0 / (4-4)! = 0/ 1 = 0.
     *  From sub-array {2}, index 0 has 2. Fourth number is 2 giving us 3142
     */
    public List<Integer> getPermutation(int n, int k) {
        List<Integer> permutation = new ArrayList<>();

        // create an array of factorial lookup
        int[] factorial = new int[n+1];
        factorial[0] = 1; //0! = 1
        for(int i=1; i<=n; i++) {
            factorial[i] = i * factorial[i-1]; //n! = n * (n-1)!
        }

        //k is the number to find. Reduce it by 1 to get the index
        k--;

        // create a list of numbers - from 1 to n
        List<Integer> numbers = new ArrayList<>();
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }

        for(int j=1; j<=n; j++) {
            int index = k/factorial[n-j];
            permutation.add(numbers.get(index));
            numbers.remove(index);
            k = k - (index * factorial[n-j]);
        }

        return permutation;
    }

    private void swap(int[] input, int i, int start) {
        int temp = input[i];
        input[i] = input[start];
        input[start] = temp;

    }

}
