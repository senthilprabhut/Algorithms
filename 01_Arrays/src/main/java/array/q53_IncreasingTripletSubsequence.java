package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *  Formally the function should:
 *      Return true if there exists i, j, k
 *      such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 *  Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *  Examples:   Given [1, 2, 3, 4, 5],  return true.
 *              Given [5, 4, 3, 2, 1],  return false.
 *
 * Links:
 *  https://leetcode.com/problems/increasing-triplet-subsequence/description/
 *  https://www.programcreek.com/2015/02/leetcode-increasing-triplet-subsequence-java/
 */
public class q53_IncreasingTripletSubsequence {
    public static void main(String[] args) {
        q53_IncreasingTripletSubsequence it = new q53_IncreasingTripletSubsequence();
        System.out.println(it.increasingTriplet(new int[]{1, 3, 0, 5}));  //true
        System.out.println(it.increasingTriplet(new int[]{1, 2, 3, 4, 5}));  //true
        System.out.println(it.increasingTriplet(new int[]{5, 4, 3, 2, 1}));  //false
    }

    /**
     * Approach:
     *  Starting from left to right, the numbers could lie in range [-----] for any small<big<thirdvalue
     *  -----small< -----big< -----thirdvalue
     *  a) if currentelement is less than small : update small to currentelement now the range for big can expand between new small and big
     *  b) if currentelement is between small and bigand less than current big : update big to currentelement now the range for thirdvalue can be any number greater than big
     *  c) if currentelement is greater than big: we found 3 such values return true
     *
     * Links:
     *  https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments.
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n < small) {
                small = n; // update small if n is smaller than both
            } else if (n < big) {
                big = n; // update big only if greater than small but smaller than big
            } else {
                return true; // return if you find a number bigger than both
            }
        }
        return false;
    }
}
