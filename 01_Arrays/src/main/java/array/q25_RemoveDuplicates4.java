package array;

import java.util.Arrays;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *    Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
 *    the non-zero elements.
 *    For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *    Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.
 *
 * Links:
 *  https://leetcode.com/problems/move-zeroes/description/
 *  https://www.programcreek.com/2014/05/leetcode-move-zeroes-java/
 */
public class q25_RemoveDuplicates4 {
    public static void main(String[] args) {
        q25_RemoveDuplicates4 rd = new q25_RemoveDuplicates4();
        int[] A = new int[] {0,1,0,3,12};
        System.out.println(rd.moveZeroes(A));
        System.out.println(Arrays.toString(A));
    }

    public int moveZeroes(int[] A) {
        int i=0, zeroIndex=0;

        while (i < A.length) {
            if (A[i] != 0) {
                A[zeroIndex] = A[i];
                A[i] = 0;
                zeroIndex++;
            }
            i++;
        }
        return zeroIndex;
    }
}
