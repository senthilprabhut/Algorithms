package string;

import java.util.Arrays;

/**
 * Problem:
 *  Rotate an array of n elements to the right by k steps.
 *  For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]
 *
 * Links:
 *  https://leetcode.com/problems/rotate-array/description/
 *  https://www.programcreek.com/2015/03/rotate-array-in-java/
 */
public class q01_RotateArray {
    public static void main(String[] args) {
        q01_RotateArray ra = new q01_RotateArray();
        int[] input = new int[]{1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(ra.rotate1(input, 3) ));
        System.out.println(Arrays.toString(ra.rotate2(input, 4) ));

        int[] input2 = new int[]{1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(ra.rotate3(input2, 3) ));
    }

    /**
     * Approach 1:
     *  In a straightforward way, we can create a new array and then copy elements to the new array.
     *  Then change the original array by using System.arraycopy().
     *
     * Complexity:
     *  Time - O(n)
     *  Space - O(n)
     */
    public int[] rotate1(int[] nums, int k) {
        if(k > nums.length)
            k=k%nums.length;

        int[] result = new int[nums.length];

        for(int i=0; i<k; i++) {
            result[i] = nums[nums.length-k+i];
        }

        int j=0;
        for(int i=k; i<nums.length; i++){
            result[i] = nums[j];
            j++;
        }
        return result;
    }

    /**
     * Approach 2:
     *  Bubble Rotate - take the last item and move it to the front
     *  repeat this k times
     *
     * Complexity:
     *  Time - O(n * k)
     *  Space - O(1) In place replacement
     */
    public int[] rotate2(int[] nums, int k) {
        if(k > nums.length)
            k=k%nums.length;

        for(int i=0; i<k; i++) {
            for(int j=nums.length-1; j>0; j--) {
                int temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
            }
        }
        return nums;
    }

    /**
     * Approach 3:
     *  1. Divide the array two parts: 1,2,3,4 and 5, 6
     *  2. Reverse first part: 4,3,2,1,5,6
     *  3. Reverse second part: 4,3,2,1,6,5
     *  4. Reverse the whole array: 5,6,1,2,3,4
     *
     * Complexity:
     *  Time - O(n)
     *  Space - O(1) In place replacement
     */
    public int[] rotate3(int[] nums, int k) {
        if(k > nums.length)
            k=k%nums.length;

        //length of first part
        int a = nums.length - k;
        reverse(nums, 0, a-1);
        reverse(nums, a, nums.length-1);
        reverse(nums, 0, nums.length-1);
        return nums;
    }

    public void reverse(int[] input, int left, int right) {
        while(left < right) {
            int temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            left++;
            right--;
        }
    }
}
