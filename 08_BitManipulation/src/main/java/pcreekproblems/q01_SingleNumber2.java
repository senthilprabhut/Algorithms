package pcreekproblems;

/**
 * Problem:
 *  Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 *  Note: Your algorithm should have a linear running time complexity. Could you implement it without using extra memory?
 *
 * Links:
 *  https://leetcode.com/problems/single-number-ii/description/
 *  https://www.programcreek.com/2014/03/leetcode-single-number-ii-java/
 *
 *  TO STUDY: https://discuss.leetcode.com/topic/11877/detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
 */
public class q01_SingleNumber2 {
    public static void main(String[] args) {
        q01_SingleNumber2 sn = new q01_SingleNumber2();

        int[] arr1 = new int[] {23, 166, 53, 23, 77, 53, 166, 23, 166, 53}; //77 once
//        int[] arr1 = new int[] {1,1,1,2};
        System.out.println(sn.getSingleNumber2_0(arr1));
        System.out.println(sn.getSingleNumber2_1(arr1));
        System.out.println(sn.getSingleNumber2_2(arr1));
        System.out.println(sn.getSingleNumber2_3(arr1));
        System.out.println(sn.getSingleNumber2_4(arr1));
        System.out.println(sn.getSingleNumber2_5(arr1));
    }

    /**
     * Approach 0:
     *  What we need to do is to store the number of '1's of every bit. Since each of the 32 bits follow the same rules,
     *  we just need to consider 1 bit. We know a number appears 3 times at most, so we need 2 bits to store that.
     *  Now we have 4 state, 00, 01, 10 and 11, but we only need 3 of them.
     *
     *  In this solution, 00, 01 and 10 are chosen. Let 'ones' represents the first bit, 'twos' represents the second bit.
     *  Then we need to set rules for 'ones' and 'twos' so that they act as we hopes.
     *  The complete loop is 00->01->10->00(0->1->2->3/0).
     *
     *  For 'ones', we can get 'ones = ones ^ A[i]; if (twos == 1) then ones = 0',
     *  that can be transformed to 'ones = (ones ^ A[i]) & ~twos'.
     *  Similarly, for 'twos', we can get 'twos = twos ^ A[i]; if (ones* == 1) then twos = 0' and 'twos = (twos ^ A[i]) & ~ones'.
     *  Notice that 'ones*' is the value of 'ones' after calculation, that is why twos is calculated later.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/2031/challenge-me-thx/18
     *
     * Complexity:
     *  Time Complexity is
     *  Space Complexity is
     */
    public int getSingleNumber2_0(int[] input) {
        int units=0, tens=0;

        for(int i=0; i<input.length; i++) {
            units = (units ^ input[i]) & ~tens;
            tens = (tens ^ input[i]) & ~units;
        }
        return units;
    }

    /**
     * If a number appears 5 times at most, we can write a program using the same method.
     * Now we need 3 bits and the loop is 000->001->010->011->100. The code looks like this:
     *  int singleNumber(int A[], int n) {
     *      int twos = 0xffffffff, threes = 0xffffffff, ones = 0;
     *      for(int i = 0; i < n; i++){
     *          threes = threes ^ (A[i] & twos);
     *          twos = (twos ^ A[i]) & ~ones;
     *          ones = ones ^ (A[i] & ~twos & ~threes);
     *      }
     *      return ones;
     *  }
     */

    /**
     * Approach 1:
     *  Counting the number of bit 1
     *  The general idea, is to consider all the numbers bit by bit, count the occurrence of '1' in each bit.
     *  To get the result, check if the number can be divided by 3 (mod 3 = 0), put '0' if true and '1' otherwise.
     *
     *  We only consider the right most bit of each number, and count the number of bits that equals to 1.
     *  "uniq" is the number that appears only once and "uniqBit" is the rightmost bit of "uniq"
     *  Our observation is that if no of "1 bit" is a multiple of 3 then "uniqBit" must be 0,  otherwise it is 1.
     *  We can repeat this procedure for all other bits and arrive at the unique number
     *
     * Links:
     *  http://traceformula.blogspot.in/2015/08/single-number-ii-how-to-come-up-with.html
     *
     * Complexity:
     *  Time Complexity is O(n²)
     *  Space Complexity is O(1)
     */
    public int getSingleNumber2_1(int[] input) {
        int result = 0;
        for (int i=0; i<32; i++) {
            int uniq = 0;
            int maskBit = 1 << i;

            for (int j=0; j<input.length; j++)
                if ( (input[j] & maskBit) != 0) // if it is bit 1
                    uniq++;

            if (uniq % 3 == 1) result |= maskBit;
        }
        return result;
    }

    /**
     * Approach 2:
     *  Transition table
     *  r is the corresponding bit of a number in the array. If r = 1,
     *  we have the following transitions: (00 + 1 = 01), (01 + 1 = 10), (10 + 1 = 00 -> coz when count becomes 3, we init it to 0).
     *  (The + means the transition with condition that we met bit 1).
     *  We have the following transition table, where (old_p, old_q) represents the state before we examine a number,
     *  and (p, q) represents the state after that.
     *  --------------------------------------------
     *  |old_p    |old_q    |   r    |    p     | q         |
     *  --------------------------------------------
     *  |   0     |   0     |   0    |    0     | 0         |
     *  --------------------------------------------
     *  |   0     |   1     |   0    |    0     | 1         |
     *  --------------------------------------------
     *  |   1     |   0     |   0    |    1     | 0         |
     *  --------------------------------------------
     *  |   0     |   0     |   1    |    0     | 1         |
     *  --------------------------------------------
     *  |   0     |   1     |   1    |    1     | 0         |
     *  --------------------------------------------
     *  |   1     |   0     |   1    |    0     | 0         |
     *  --------------------------------------------
     *  As you can see from the above table, the combinations of (old_p, old_q, r) in order to get p = 1 are (1, 0, 0)
     *  and (0, 1, 1). So (1, 0, 0) gives us (old_p & ~old_q & ~r), and (0, 1, 1) gives us (~old_p & old_q & r).
     *  Based on the above transition table, we can derive the formula of p and q based on old_p, old_q, and r.
     *  p = (old_p & ~old_q & ~r) | (~old_p & old_q & r)
     *  q = (~old_p & old_q& ~r) | (~old_p & ~old_q & r)
     *
     * Links:
     *  http://traceformula.blogspot.in/2015/08/single-number-ii-how-to-come-up-with.html
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getSingleNumber2_2(int[] input) {
        int p=0, q=0;

        int oldP, oldQ, r;
        for(int i=0; i<input.length; i++) {
            oldP = p; oldQ = q;
            r = input[i];

            p = (oldP & ~oldQ & ~r) | (~oldP & oldQ & r);
            q = (~oldP & oldQ & ~r) | (~oldP & ~oldQ & r);
        }
        return q;
    }

    /**
     * Approach 3:
     *  Improvement on Method 2
     *  Now, we see that in this assignment:
     *  p = (old_p & ~old_q & ~r) | (~old_p & old_q & r)
     *  we have old_q = q, and old_p is p before that assignment.
     *  So the formula become: p = (p & ~q& ~r) | (~p& q& r)
     *
     * Links:
     *  http://traceformula.blogspot.in/2015/08/single-number-ii-how-to-come-up-with.html
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getSingleNumber2_3(int[] input) {
        int p=0, q=0;

        int oldP, r;
        for(int i=0; i<input.length; i++) {
            oldP = p;
            r = input[i];

            p = (p & ~q& ~r) | (~p& q& r);
            q = (~oldP & q& ~r) | (~oldP & ~q & r);
        }
        return q;
    }

    /**
     * Approach 4:
     *  NEW TRANSITION STATES - 00, 01, 11 (no 10 used here in this transition table)
     *  In the above code, it is still ugly because we need to use old_p. Why we cannot use p to replace old_p?
     *  Let's look at the triple (old_q, r, p) in the transition table.
     *  Its values include (0, 0, 0), (1, 0, 0), (0, 0, 1), (0, 1, 0), (1, 1, 1), (0, 1, 0).
     *  The triple (0, 1, 0) is duplicated, therefore information is lost for this case.
     *  We can try different transition tables!
     *  |old_p |old_q |   r    |    p      | q        |
     *  --------------------------------------------
     *  |   0     |   0     |   0    |    0     | 0         |
     *  --------------------------------------------
     *  |   0     |   1     |   0    |    0     | 1         |
     *  --------------------------------------------
     *  |   1     |   1     |   0    |    1     | 1         |
     *  --------------------------------------------
     *  |   0     |   0     |   1    |    0     | 1         |
     *  --------------------------------------------
     *  |   0     |   1     |   1    |    1     | 1         |
     *  --------------------------------------------
     *  |   1     |   1     |   1    |    0     | 0         |
     *  --------------------------------------------
     *  It is pretty beautiful that in this table the triple (old_q, r, p) is unique, so we can use p to calculate q.
     *  And we are still able to use q to represents "elonBit" because for state 1, (p,q) = (0, 1)
     *  p = (p & q & ~ r) | (~p & q & r)
     *  q = (q & ~r & ~p) | (q & ~r & p) | (~q & r & ~p) | (q & r & p)
     *  After reducing the above equations, we have:
     *  p = q & (p ^ r)
     *  q = p | (q ^ r)
     *
     * Links:
     *  http://traceformula.blogspot.in/2015/08/single-number-ii-how-to-come-up-with.html
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getSingleNumber2_4(int[] input) {
        int p=0, q=0, r=0;
        for(int i=0; i<input.length; i++) {
            r = input[i];
            p = q & (p ^ r);
            q = p | (q ^ r);
        }
        return q;
    }

    /**
     * Approach 5:
     *  Since we know that XOR operation can be used for testing if 1 bit occurs twice, in other words,
     *  for a single bit, if 1 occurs twice, it turns to 0.
     *  Now we need a 3-time criteria for each bit, by utilizing the bit operations.
     *  This 3-time criteria needs every bit turns to 0 if  '1' occurs three times.
     *
     *  If we know on which bits '1' occurs twice, and also know on which bits '1' occurs 1-time, a simple '&' operation
     *  would result in the bit where '1' occurs three times. Then we turn these bit to zero, would do well for this problem.
     *
     *  (1). Check bits which have 1-time '1', use the XOR operation.
     *  (2). Check bits which have 2-times '1's, use current 1-time result & current number.
     *  (3). Check bits which have 3-times '1's, use '1-time' result & '2-times' result
     *  (4). To turn 3-times bits into 0:   ~(3-times result) & 1-time result
     *                                      ~(3-times result) & 2-times result
     *
     * Links:
     *  http://yucoding.blogspot.in/2014/10/single-number-ii.html
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(1)
     */
    public int getSingleNumber2_5(int[] input) {
        int t1=0, t2=0, t3=0;

        for(int i=0; i<input.length; i++) {
            t1 = t1 ^ input[i];
            t2 = t2 | ((t1^input[i]) & input[i]);
            t3 = ~(t1 & t2);

            t1 = t1 & t3;
            t2 = t2 & t3;
        }
        return t1;
    }
}
