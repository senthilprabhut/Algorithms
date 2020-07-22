package math;

import java.util.Arrays;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array S of n integers, find three integers in S
 *  such that the sum is closest to a given number, target. Return the sum of the three integers.
 *
 * Links:
 *  https://leetcode.com/problems/3sum-closest/description/
 *  https://discuss.leetcode.com/topic/5192/java-solution-with-o-n2-for-reference/7
 *  https://www.programcreek.com/2013/02/leetcode-3sum-closest-java/
 */
public class q10_ThreeSumClosest {
    public static void main(String[] args) {
        q10_ThreeSumClosest tsc = new q10_ThreeSumClosest();
        int[] S = new int[]{-1,2,1,-4};
        int target = 1;
        System.out.printf("Three sum closest to %d is %d\n",target, tsc.threeSumClosest(S, target));

        int[] T  = new int[]{1,4,3,7,9};
        target = 15;
        System.out.printf("Three sum closest to %d is %d\n",target, tsc.threeSumClosest(T, target));
    }

    /**
     * Approach:
     *  Similar to 3 Sum problem, use 3 pointers to point current value, next value and the last value.
     *  If the sum is less than target, it means we have to add a larger value so next value move to the next.
     *  If the sum is greater, it means we have to add a smaller value so last value move to the second last value.
     *  Keep doing this until the end. Each time compare the difference between sum and target,
     *  if it is less than minimum difference so far, then replace result with it, otherwise keep iterating.
     *
     * Complexity:
     *  Time Complexity is O(n²)
     *  Space Complexity is O(1)
     */
    public int threeSumClosest(int[] array, int target) {
        int sum = 0;
        if (array.length <= 3) {
            for(int value : array) sum += value;
            return sum;
        }

        Arrays.sort(array);
        int closestSum = Integer.MAX_VALUE;;

        for(int i=0; i<(array.length-2); i++) {
            if (i == 0 || array[i] != array[i - 1]) {
                int left = i + 1, right = array.length - 1;
                while (left < right) {
                    sum = array[i] + array[left] + array[right];
                    if (sum > target) {
                        right--;
                        //address duplicates and move closer to target sum
                        while (left < right && array[right] == array[right + 1]) right--;
                    } else if (sum < target) {
                        left++;
                        //address duplicates and move closer to target sum
                        while ((left < right && array[left] == array[left - 1])) left++;

                    } else return sum; //sum matches target
                }

                //update closest sum if needed
                if (Math.abs(target - sum) < Math.abs(target - closestSum))
                    closestSum = sum;
            }
        }
        return closestSum;
    }
}
