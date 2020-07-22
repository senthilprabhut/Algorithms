package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *   Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 *   For example, Given sorted array nums = [1,1,1,2,2,3],
 *   Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3
 *
 * Links:
 *  https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 *  https://www.programcreek.com/2013/01/leetcode-remove-duplicates-from-sorted-array-ii-java/
 */
public class q25_RemoveDuplicates2 {
    public static void main(String[] args) {
        q25_RemoveDuplicates2 rd = new q25_RemoveDuplicates2();
        int[] A = new int[] {1,1,1,2,2,2,3};
        System.out.println(rd.removeDuplicates(A));
    }

    public int removeDuplicates(int[] A) {
        if (A.length < 2) return A.length;

        int prev=1, curr=2;
        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev-1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }
        return prev+1;
    }
}
