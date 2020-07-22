package pcreekprobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import utils.DPUtils;
import utils.TimeWatch;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a string s, partition2 s such that every substring of the partition2 is a palindrome.
 *  Return all possible palindrome partitioning of s.
 *  For example, given s = "aab",   Return  [[a, a, b], [aa, b]]
 *
 * Notes:
 *  For this problem, DP is slower than DFS because for input like "aaaaa", there are  2ⁿ ways to cut the word
 *
 * Links:
 *  https://leetcode.com/problems/palindrome-partitioning/description/
 *  https://www.programcreek.com/2013/03/leetcode-palindrome-partitioning-java/
 */
public class q05_PalindromePartitioning1 {
    public static void main(String[] args) {
        q05_PalindromePartitioning1 pp = new q05_PalindromePartitioning1();

        //856 micros execution time
        TimeWatch watch = TimeWatch.start();
        System.out.println(pp.partition2("aaaa"));
        System.out.println(pp.partition2("aab"));
        System.out.println(watch.time(TimeUnit.MICROSECONDS));

        //Approach 2 - 55096 ms execution time
    }

    /**
     * Approach 1: DFS
     *
     * Links:
     *  https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java:-Backtracking-solution.
     *
     * Explantion:
     *  https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java:-Backtracking-solution.
     *
     * Complexity:
     *  Time complexity: O(n*(2^n)). For a string with length n, there will be (n - 1) intervals between chars.
     *      For every interval, we can cut it or not cut it, so there will be 2^(n - 1) ways to partition the string.
     *      For every partition way, we need to check if it is palindrome, which is O(n). So the time complexity is O(n*(2^n))
     *
     */
    public List<List<String>> partition2(String s) {
        List<List<String>> resultList = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return resultList;
        }

        List<String> partitions = new ArrayList<>();//track each possible partition
        Map<String,List< List<String>>> map = new HashMap<>();
        backtrack2(s, 0, partitions, resultList);

        return resultList;
    }

    public void backtrack2(String s, int start, List<String> partitions, List<List<String>> resultList) {
        //Base condition
        if (partitions.size() > 0 && start >= s.length()) {
            List<String> temp = new ArrayList<>(partitions); //make a copy of the list
            resultList.add(temp);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            //if palindrome, check if it can be further partitioned
            if (DPUtils.isPalindrome(s, start, i)) {
                partitions.add(s.substring(start, i + 1)); //i+1 specified instead of i since the endIndex is exclusive
                backtrack2(s, i + 1, partitions, resultList); //Backtrack for the remaining characters
                partitions.remove(partitions.size() - 1);
            }
        }
    }

    /**
     * Approach 2: DFS with Memoization
     *
     * Links:
     *  https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java:-Backtracking-solution comment: KnightRider
     *
     * Explantion:
     *  https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java:-Backtracking-solution
     *
     * Complexity:
     *  Time complexity: O(n*(2^n)). For a string with length n, there will be (n - 1) intervals between chars.
     *  For every interval, we can cut it or not cut it, so there will be 2^(n - 1) ways to partition the string.
     *  For every partition way, we need to check if it is palindrome, which is O(n). So the time complexity is O(n*(2^n))
     */
//    public List<List<String>> partition3(String s) {
//        List<List<String>> resultList = new ArrayList<>();
//        if (s == null || s.length() == 0) {
//            return resultList;
//        }
//
//        resultList = backtrack3(s, 0, new HashMap<>());
//        return resultList;
//    }
//
//    public List<List<String>> backtrack3(String s, int start, Map<String,List<List<String>>> memoizationMap) {
//        String key = s.substring(start);
//        if (memoizationMap.containsKey(key)) { //if  the key has a result, return it
//            return memoizationMap.get(key);
//        }
//
//        List<List<String>> result = new ArrayList<>();
//
//        //Base condition
//        if (start == s.length()) {
//            result.add(new ArrayList<>());
//            memoizationMap.put(key, result); //The string will be "" - add empty arraylist for this key
//            return result;
//        }
//
//        for(int i=start; i<s.length(); i++) {
//            if (DPUtils.isPalindrome(s, start, i)) {
//                String palSubstring = s.substring(start, i+1);
//                List<List<String>> response = backtrack3(s, i+1, memoizationMap);
//
//                response.stream().forEach(list -> {
//                    List<String> temp = new LinkedList<>(list);
//                    temp.add(0, palSubstring);
//                    result.add(temp);
//                });
//            }
//        }
//        memoizationMap.put(key,result); //Since the key doesn't have any value in map, store the new result
//        return result;
//    }

}
