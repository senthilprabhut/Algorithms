package array;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * Example 1   Input: [3,0,1]  Output: 2
 * Example 2   Input: [9,6,4,2,3,5,7,0,1]  Output: 8
 * Note:  Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 *
 * Links:
 * https://leetcode.com/problems/missing-number/description/
 * https://www.programcreek.com/2014/05/leetcode-missing-number-java/
 */
public class q57_MissingNumber {
    public static void main(String[] args) {
        q57_MissingNumber mn = new q57_MissingNumber();
        System.out.println(mn.missingNumber1(new int[]{3,0,1}));
        System.out.println(mn.missingNumber2(new int[]{3,0,1}));
        System.out.println(mn.missingNumber3(new int[]{3,0,1}));
        System.out.println(mn.missingNumber4(new int[]{3,0,1}));
    }

    /**
     * Approach 1:
     *  Use XOR. We know that a^b^b =a, which means two xor operations with the same number will eliminate the number and reveal the original number.
     *  Here we apply XOR operation to both the index and value of the array. In a complete array with no missing numbers,
     *  the index and value should be perfectly corresponding( nums[index] = index), so in a missing array, what left finally is the missing number
     *
     * Links:
     *  https://leetcode.com/problems/missing-number/discuss/69791/4-Line-Simple-Java-Bit-Manipulate-Solution-with-Explaination?page=3
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int missingNumber1(int[] nums) {

        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            //this will not xor against 0 but we'll get 0 if everything cancels out
            //using i+1 eliminates doing an xor with i after the loop
            xor = xor ^ (i+1) ^ nums[i];
        }
        return xor;
        //return xor ^ i;
    }

    /**
     * Approach 2:
     *  Sum everything in array and subtract from expected sum
     *  There is a problem with Integer overflow - we can use average to minimize overflow
     *
     * Links:
     *  https://leetcode.com/problems/missing-number/discuss/69791/4-Line-Simple-Java-Bit-Manipulate-Solution-with-Explaination?page=1
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int missingNumber2(int[] nums) {
        int sum=nums.length; //this is important - this is the last number in the array
        for (int i=0; i<nums.length; i++) {
            sum = sum + i - nums[i];
        }
        return sum;
    }

    /**
     * Approach 3:
     *  Using binary search
     *  Need to sort the array first which is O(NlogN)
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-missing-number-java/
     *
     * Complexity:
     *  Time is O(NlogN)
     *  Space is O(1)
     */
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        int start=0, end=nums.length;
        while (start < end) {
            int mid = start + (end-start)/2;
            if (nums[mid] == mid) { //numbers are as expected. No missing no in this range
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    /**
     * Approach 4:
     *  Using bitset
     *
     * Links:
     *  https://leetcode.com/problems/missing-number/discuss/69791/4-Line-Simple-Java-Bit-Manipulate-Solution-with-Explaination?page=1
     *
     * Complexity:
     *  Time is O()
     *  Space is O()
     */
    public int missingNumber4(int[] nums) {
        BitSet bitSet = new BitSet(nums.length+1);
        for (int i = 0; i < nums.length; i++) {
            bitSet.set(nums[i]);
        }
        return bitSet.nextClearBit(0);
    }
}
