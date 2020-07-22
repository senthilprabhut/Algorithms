package array;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *   Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *   Note: You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
 *   elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 *
 * Links:
 *  https://leetcode.com/problems/merge-sorted-array/description/
 *  https://www.programcreek.com/2012/12/leetcode-merge-sorted-array-java/
 */
public class q12_MergeSortedArr {
    public static void main(String[] args) {

    }

    public void merge(int[] a, int lenA, int[] b, int lenB) {
        while (lenA > 0 && lenB>0) {
            if(a[lenA-1] > b[lenB-1]) {
                a[lenA+lenB-1] = a[lenA-1];
                lenA--;
            } else {
                a[lenA+lenB-1] = b[lenB-1];
                lenB--;
            }
        }

        while (lenB>0) {
            a[lenA+lenB-1] = b[lenB-1];
            lenB--;
        }
    }


}
