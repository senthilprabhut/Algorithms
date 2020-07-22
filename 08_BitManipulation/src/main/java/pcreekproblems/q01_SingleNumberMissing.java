package pcreekproblems;

/**
 * Problem:
 *  Given an array of integers, every element appears three times but one element is missing
 *
 * Links:
 *  http://traceformula.blogspot.in/2015/08/single-number-ii-how-to-come-up-with.html
 */
public class q01_SingleNumberMissing {
    public static void main(String[] args) {
        q01_SingleNumberMissing sn = new q01_SingleNumberMissing();

//        int[] arr = new int[] {0,0,0, 1,1, 2, 2, 2, 3, 3, 3}; //1 occurs twice
        int[] arr = new int[] {1,1,1, 2, 2};
        System.out.println(sn.getMissingNumber(arr));
    }

    //The state transiton is 00, 01, 11
    private int getMissingNumber(int[] nums) {
        int tens=0, units=0, r=0;

        for (int i=0; i<nums.length;i++) {
            r = nums[i];
            tens = units & (tens ^ r);
            units = tens | (units ^ r);
        }
        return (tens & units);
    }
}
