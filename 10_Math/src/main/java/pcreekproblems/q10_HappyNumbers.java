package pcreekproblems;

import java.util.HashSet;

/**
 * Problem:
 *  Write an algorithm to determine if a number is "happy".
 *  A happy number is a number defined by the following process: Starting with any positive integer, replace the number
 *  by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
 *  or it loops endlessly in a cycle which does not include 1.
 *  Those numbers for which this process ends in 1 are happy numbers.
 *
 *  Example: 19 is a happy number
 *  12 + 92 = 82
 *  82 + 22 = 68
 *  62 + 82 = 100
 *  12 + 02 + 02 = 1
 *
 * Links:
 *  https://leetcode.com/problems/happy-number/description/
 *
 */
public class q10_HappyNumbers {
    public static void main(String[] args) {
        q10_HappyNumbers hn = new q10_HappyNumbers();
        System.out.printf("Is happy number %d: %b%n", 19, hn.isHappy1(19));
        System.out.printf("Is happy number %d: %b%n", 19, hn.isHappy2(19));

        System.out.println();
        System.out.printf("Is happy number %d: %b%n", 21, hn.isHappy1(21));
        System.out.printf("Is happy number %d: %b%n", 21, hn.isHappy2(21));
    }

    /**
     * Approach:
     *  To show that a non-happy number will definitely generate a loop, we only need to show that for any non-happy number,
     *  all outcomes during the process are bounded by some large but finite integer N. If all outcomes can only be in a finite set (2,N],
     *  and since there are infinitely many outcomes for a non-happy number, there has to be at least one duplicate, meaning a loop!
     *
     * Links:
     *  https://www.programcreek.com/2014/04/leetcode-happy-number-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public boolean isHappy1(int n) {
        HashSet<Integer> set = new HashSet<Integer>();

        while(! set.contains(n)) {
            set.add(n);

            //calculate new number which is sum of squares of each digit
            int sum=0, temp=0;
            while(n > 0) {
                temp = n%10;
                sum += temp * temp;
                n /= 10;
            }
            n = sum;

            if(n == 1) return true;
        }
        return false;
    }

    /**
     * Approach:
     *  we can simply adapt the Floyd Cycle detection algorithm.
     *  I believe that many people have seen this in the Linked List Cycle detection problem.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/12587/my-solution-in-c-o-1-space-and-no-magic-math-property-involved
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity is O(1)
     */
    public boolean isHappy2(int n) {
        int slow,fast;
        slow = fast = n;

        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while (slow != fast);

        return slow == 1;
    }

    private int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n > 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

}
