package array;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *   Given an array and a value, remove all instances of that value in-place and return the new length.
 *   Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *   The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *   Example:   Given nums = [3,2,2,3], val = 3,
 *   Your function should return length = 2, with the first two elements of nums being 2.
 *
 * Links:
 *  https://leetcode.com/problems/remove-element/description/
 *  https://www.programcreek.com/2014/04/leetcode-remove-element-java/
 */
public class q25_RemoveDuplicates3 {
    public static void main(String[] args) {
        q25_RemoveDuplicates3 rd = new q25_RemoveDuplicates3();
        int[] A = new int[] {3,2,2,3};
        System.out.println(rd.removeDuplicates(A, 3));
    }

    public int removeDuplicates(int[] A, int num) {
        int i=0, j=0;

        while (i < A.length) {
            if (A[i] == num) {
                A[j] = A[i];
                j++;
            }
            i++;
        }
        return j;
    }
}
