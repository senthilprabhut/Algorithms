package pcreekproblems;

/**
 * Problem:
 *  Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 *  Note:
 *      The length of both num1 and num2 is < 110.
 *      Both num1 and num2 contains only digits 0-9.
 *      Both num1 and num2 does not contain any leading zero.
 *      You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Links:
 *  https://leetcode.com/problems/multiply-strings/description/
 *  https://www.programcreek.com/2014/05/leetcode-multiply-strings-java/
 *
 */
public class q14_MultiplyStrings {
    public static void main(String[] args) {
        q14_MultiplyStrings ms = new q14_MultiplyStrings();
        System.out.printf("Multiplication of %d with %d is %s%n", 25, 34, ms.multiply1("25","34"));
        System.out.printf("Multiplication of %d with %d is %s%n", 25, 34, ms.multiply2("25","34"));
        System.out.printf("Multiplication of %d with %d is %s%n", 125, 234, ms.multiply3("125","234"));
    }

    /**
     * Approach:
     *  Start from right to left, perform multiplication on every pair of digits, and add them together.
     *  Let's draw the process! From the following draft, we can immediately conclude:
     *  `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
     *
     * Links:
     *  https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation/2
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public String multiply1(String num1, String num2) {
        int[] sum = new int[num1.length() + num2.length()];

        for(int i=num1.length()-1; i>=0; i--) {
            for (int j=num2.length() - 1; j>= 0; j--) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int pos1 = i+j, pos2 = i+j+1;

                int add = sum[pos2] + mul; //Add with old value
                sum[pos2] = add%10; //Set the remainder to 2nd place
                sum[pos1] += add/10; //Add the carry over to the 1st place. Even if it goes beyond 10, it'll be taken care of in the next iteration
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int s : sum) if(!(sb.length()==0 && s==0)) sb.append(s);

        return sb.length()==0 ? "0" : sb.toString();
    }

    /**
     * Approach:
     *  A minor modification to the above approach - we use carry here
     *  We use two nested for loops, working backward from the end of each input number.
     *  We pre-allocate our result and accumulate our partial result in there.
     *  One special case to note is when our carry requires us to write to our sum string outside of our for loop.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/9449/brief-c-solution-using-only-strings-and-without-reversal
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public String multiply2(String num1, String num2) {
        int[] sum = new int[num1.length() + num2.length()];

        for(int i=num1.length()-1; i>=0; i--) {
            int carry=0;
            for(int j=num2.length()-1;j>=0; j--) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int add = sum[i+j+1] + mul + carry;

                sum[i+j+1] = add%10;
                carry = add/10;
            }
            sum[i] += carry;
        }

        StringBuilder sb = new StringBuilder();
        for (int s : sum) if(!(sb.length()==0 && s==0)) sb.append(s);

        return sb.length()==0 ? "0" : sb.toString();
    }
    /**
     * Approach:
     *  The key to solve this problem is multiplying each digit of the numbers at the corresponding positions
     *  and get the sum values at each position.
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-multiply-strings-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public String multiply3(String num1, String num2) {
        int[] sum = new int[num1.length() + num2.length()];

        for(int i=num1.length()-1; i>=0; i--) {
            for(int j=num2.length()-1; j>=0; j--) {
                sum[i+j+1] += (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
            }
        }

        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for(int cntr=sum.length-1; cntr>=0; cntr--) {
            sum[cntr] += carry;
            carry = sum[cntr] / 10;
            sum[cntr] = sum[cntr] % 10;
            sb.insert(0,sum[cntr]);
        }

        //Now there may be a couple of entries with 0 in the front. ignore them when building string
        while(sb.charAt(0) == '0' && sb.length()> 1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
