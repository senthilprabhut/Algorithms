package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *   Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *   For example, Given [100, 4, 200, 1, 3, 2],
 *   The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *   Your algorithm should run in O(n) complexity.
 *
 * Links:
 *  https://leetcode.com/problems/longest-consecutive-sequence/description/
 *  https://www.programcreek.com/2013/01/leetcode-longest-consecutive-sequence-java/
 */
public class q17_LongestConsecutiveSeq {
    public static void main(String[] args) {
        q17_LongestConsecutiveSeq lcs = new q17_LongestConsecutiveSeq();

        int[] array = new int[] {100, 4, 200, 1, 3, 2};
        System.out.println(lcs.longestConsecutive(array));
    }

    /**
     * Approach:
     *  Because it requires O(n) complexity, we can not solve the problem by sorting the array first.
     *  Sorting takes at least O(n logn) time.
     *  We can use a HashSet to add and remove elements.
     *  Elements are not ordered. The add, remove and contains methods have constant time complexity O(1).
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n) for the hash set
     */
    public int longestConsecutive(int[] num) {
        Set<Integer> set = new HashSet<>();

        for (int i=0; i<num.length; i++) {
            set.add(num[i]); //Add all the elements to the set
        }

        int longest = 0, count = 0;
        for(int i=0; i<num.length; i++) {
            int left = num[i] - 1, right = num[i] + 1;

            while (set.contains(left)) {
                count++;
                set.remove(left--);
            }

            while (set.contains(right)) {
                count++;
                set.remove(right++);
            }

            if(count > longest) longest = count;
        }
        return longest;
    }

}
