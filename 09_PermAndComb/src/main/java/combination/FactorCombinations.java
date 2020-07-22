package combination;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem:
 *  Write a function that takes an integer n and return all possible combinations of its factors.
 *  Note:
 *      You may assume that n is always positive.
 *      Factors should be greater than 1 and less than n.
 *   8 = 2 x 2 x 2
 *     = 2 x 4
 *
 * Links:
 *  https://leetcode.com/problems/factor-combinations
 *  Solution 1 - http://www.programcreek.com/2014/07/leetcode-factor-combinations-java/
 *
 * Complexity:
 *
 */
public class FactorCombinations {
    public static void main(String[] args) {
        int number = 12;
        FactorCombinations fc = new FactorCombinations();

        //1 and the number are factors for any number
        List<List<Integer>> results = new ArrayList<>();
        results.add(Stream.of(1,number).collect(Collectors.toList()));

        fc.dfs(number, 2,1, new ArrayList<>(), results);
        results.forEach(System.out::println);
    }

    public void dfs(int targetNumber, int start, int product, List<Integer> selected, List<List<Integer>> results) {
        System.out.println(product);
        if(product==targetNumber) {
            results.add(new ArrayList<>(selected));
            return;
        }

        //The following would never get reached because of the check in for-loop
        if(product>targetNumber || start>targetNumber) {
            return;
        }

        for(int i=start; i<=targetNumber; i++) {
            if(i*product > targetNumber) break; //No point looping on numbers after this index

            if(targetNumber%i == 0) {
                selected.add(i);
                dfs(targetNumber, i, product*i, selected,results);
                selected.remove(selected.size()-1);
            }
        }

    }
}
