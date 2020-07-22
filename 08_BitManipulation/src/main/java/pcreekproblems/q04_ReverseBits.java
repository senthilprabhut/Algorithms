package pcreekproblems;

/**
 * Problem:
 *  Reverse bits of a given 32 bits unsigned integer.
 *
 * Links:
 *  https://leetcode.com/problems/reverse-bits/description/
 *  https://www.programcreek.com/2014/03/leetcode-reverse-bits-java/
 */
public class q04_ReverseBits {
    public static void main(String[] args) {
        q04_ReverseBits rb = new q04_ReverseBits();
        System.out.println(rb.reverseBits1(11));
        System.out.println(rb.reverseBits2(11));
    }

    public int reverseBits1(int n) {
        int result = 0;
        for (int i=0; i<32; i++, n>>=1) {
            result <<= 1; //left shift value of m by 1 bit every loop
            result |= (n & 1);
        }
        return result;
    }

    public int reverseBits2(int n) {
        int result = 0;
        for(int i=0; i<32; i++) {
            result += (n & 1);
            if (i < 31) // CATCH: for last digit, don't shift!
                result <<= 1;
            n >>>= 1;   // CATCH: must do unsigned shift
        }
        return result;
    }
}
