package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a non-empty array of integers, return the k most frequent elements.
 *  For example,    Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *  Note:
 *      You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *      Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * Links:
 *  https://leetcode.com/problems/top-k-frequent-elements/description/
 *  https://www.programcreek.com/2014/05/leetcode-top-k-frequent-elements-java/
 *
 */
public class q59_TopKFrequentElements {
    public static void main(String[] args) {
        q59_TopKFrequentElements tfe = new q59_TopKFrequentElements();
        int[] input = {1,1,1,2,2,3};
        System.out.println(tfe.topKFrequent(input, 2));
    }

    /**
     * Approach:
     *  Bucket Sort
     *
     * Links:
     *  https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
     *
     * Complexity:
     *  Time is O(n)
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int num : nums) {
            frequencyMap.merge(num, 1, (value,one) -> value+1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);

            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> result = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0 && result.size() < k; pos--) {
            if (bucket[pos] != null) {
                result.addAll(bucket[pos]);
            }
        }
        return result;
    }
}
