package array;

import java.util.Arrays;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given a sorted array, remove the duplicates in place such that each value appear only once and return the new length.
 *  Do not allocate extra space for another array, you must do this in place with constant memory.
 *  For example, given input array A = [1,1,2], your function should return length = 2, and A is now [1,2].
 *
 * Links:
 *  https://leetcode.com/problems/triangle/description/
 *  https://www.programcreek.com/2013/01/leetcode-triangle-java/
 */
public class q25_RemoveDuplicates1 {
    public static void main(String[] args) {
        q25_RemoveDuplicates1 rd = new q25_RemoveDuplicates1();
        int[] arr = new int[] {1,1,2};
        System.out.println("New length: " + rd.removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

    public int removeDuplicates(int[] A) {
        if (A.length < 2) return A.length;

        int curr=1, insert=0;
        while(curr < A.length) {
            if(A[insert] == A[curr]) {
                curr++;
            } else {
                //unequal chars
                insert++; //insert at the next available position
                A[insert] = A[curr];
                curr++;
            }
        }

        int newLength = insert + 1; //convert index to length

        //0 the empty array elements
        while (insert+1 < A.length) {
            insert++;
            A[insert] = 0;
        }
        return newLength;
    }
}
