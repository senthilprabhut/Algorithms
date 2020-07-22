package pcreekproblems;

import java.util.Arrays;

/**
 * Problem:
 *   Given an array of n integers where n > 1, nums, return an array output such that output[i] is
 *   equal to the product of all the elements of nums except nums[i].
 *   Solve it without division and in O(n).
 *   For example, given [1,2,3,4], return [24,12,8,6].
 *   Follow up:
 *   Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 * Links:
 *  https://leetcode.com/problems/product-of-array-except-self/description/
 */
public class q16_ProdOfArrayExceptSelf {
    public static void main(String[] args) {
        q16_ProdOfArrayExceptSelf prod = new q16_ProdOfArrayExceptSelf();

        int[] input = new int[] {1, 2, 3, 4, 5};
        System.out.println( Arrays.toString(prod.prodOfArrayExceptSelf1(input)) ); //[120, 60, 40, 30, 24]

        int[] input2 = new int[] {0, 1, 2};
        System.out.println( Arrays.toString(prod.prodOfArrayExceptSelf2(input2)) ); //[2, 0, 0]

        int[] input3 = new int[] {1, 2, 3, 4, 5};
        prod.prodOfArrayExceptSelf3(input3, 1 ,0);
        System.out.println( Arrays.toString(input3) ); //[120, 60, 40, 30, 24]
    }

    /**
     * Approach:
     *  The trick is to construct the arrays (in the case for 4 elements)
     *  {              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
     *  { a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }
     *  Both of which can be done in O(n) by starting at the left and right edges respectively.
     *
     * Links:
     *  https://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-array-of-products-of-all-other-numbers-no-div/2696005#2696005
     *  https://discuss.leetcode.com/topic/18864/simple-java-solution-in-o-n-without-extra-space
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int[] prodOfArrayExceptSelf1(int[] input) {
        int[] prod = new int[input.length];

        // Get the products below the current index - L to R
        int p=1;
        for(int i=0; i<input.length; i++) {
            prod[i] = p;
            p = p * input[i];
        }

        // Get the products above the curent index - R to L
        p=1;
        for(int j=input.length-1; j>=0; j--) {
            prod[j] = prod[j] * p;
            p = p * input[j];
        }

        return prod;
    }


    /**
     * Approach:
     *  The trick is to construct the arrays (in the case for 4 elements)
     *  {              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
     *  { a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }
     *  Both of which can be done in O(n) by starting at the left and right edges respectively.
     *  Then multiplying the two arrays element by element gives the required result
     *
     * Links:
     *  https://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-array-of-products-of-all-other-numbers-no-div/2696005#2696005
     *  https://www.programcreek.com/2014/07/leetcode-product-of-array-except-self-java/
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n) for the intermediate arrays
     */
    public int[] prodOfArrayExceptSelf2(int[] input) {
        int[] prod = new int[input.length];

        int[] prodBelow = new int[input.length];
        int p=1;
        for (int i=0; i<input.length; i++) {
            prodBelow[i] = p;
            p = p * input[i];
        }

        int[] prodAbove = new int[input.length];
        p=1;
        for(int i=input.length-1; i>=0; i--) {
            prodAbove[i] = p;
            p = p * input[i];
        }

        //calculate result
        for(int i=0; i<input.length; i++) {
            prod[i] = prodBelow[i] * prodAbove[i];
        }
        return prod;
    }


    /**
     * Approach:
     *  It does recursion first, remembering the intermediate products ,
     *  eventually forming the number product for num[N-1]; then on the way back it calculates the second part
     *  of the multiplication which is then used to modify the number array in place.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n) stack stapce
     */

    int prodOfArrayExceptSelf3(int[] a, int fwdProduct, int indx) {
        int revProduct = 1;
        if (indx < a.length) {
            revProduct = prodOfArrayExceptSelf3(a, fwdProduct*a[indx], indx+1);
            int cur = a[indx];
            a[indx] = fwdProduct * revProduct;
            revProduct *= cur;
        }
        return revProduct;
    }
}
