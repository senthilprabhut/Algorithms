package math;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *  Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 *  Example 1:
 *  nums1 = [1, 3]
 *  nums2 = [2]
 *  The median is 2.0
 *
 *  Example 2:
 *  nums1 = [1, 2]
 *  nums2 = [3, 4]
 *  The median is (2 + 3)/2 = 2.5
 *
 * Links:
 *  https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *  https://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/
 *  TUSHAR - https://www.youtube.com/watch?v=LPFhl65R7ww
 */
public class q05_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        q05_MedianOfTwoSortedArrays mosa = new q05_MedianOfTwoSortedArrays();
        int[] nums1 = {1,3}, nums2 = {2};
        System.out.println(mosa.medianOfTwoDiffLenArrays(nums1,nums2));

        int[] nums3 = {1,2}, nums4 = {3,4};
        System.out.println(mosa.medianOfTwoDiffLenArrays(nums3,nums4));
    }

    public double medianOfTwoDiffLenArrays(int[] array1, int[] array2) {

        //Make sure that smaller array is first and larger array is 2nd
        if (array1.length > array2.length) {
            int[] temp = array1;
            array1 = array2;
            array2 = temp;
        }

        //Initialize the variables
        int x = array1.length, y = array2.length;
        int low=0, high=x;

        while(low <= high) {
            int partitionX = (low+high)/2;
            int partitionY = (x+y+1)/2 - partitionX; //To get equal elements in both partitions

            //if partitionX is 0, it means that there is nothing on the left side. Use -INF for maxLeftX
            //if partitionX is the length of array, then there is nothing on the right side. Use +INF for minRightX
            int maxLeftX = (partitionX==0) ? Integer.MIN_VALUE : array1[partitionX-1];
            int minRightX = (partitionX==x) ? Integer.MAX_VALUE : array1[partitionX];

            int maxLeftY = (partitionY==0) ? Integer.MIN_VALUE : array2[partitionY-1];
            int minRightY = (partitionY==y) ? Integer.MAX_VALUE : array2[partitionY];

            if (maxLeftX < minRightY && maxLeftY < minRightX) {
                //We have partitioned the array at the correct place
                //Now get the max of left value and min of right value to get median in case of even length (combined arr size)
                //Or get max of left for odd length combined arr size
                if ((x+y)%2 == 0) {
                   return (double)(Math.max(maxLeftX,maxLeftY) + Math.min(minRightX,minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX,maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //We are too far on the right side for partitionX. Go to left side
                high = partitionX-1;
            } else { //We are too far on the left side for partitionX. Go to right side
                low = partitionX+1;
            }
        }

        //We come here only if the input arrays are not sorted
        throw new IllegalArgumentException();
    }
}
