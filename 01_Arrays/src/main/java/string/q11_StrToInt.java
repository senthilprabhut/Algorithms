package string;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *   Implement atoi to convert a string to an integer.
 *   The following cases should be considered for this problem:
 *   1. null or empty string
 *   2. white spaces
 *   3. +/- sign
 *   4. calculate2 real value
 *   5. handle min & max
 *
 * Links:
 *  https://leetcode.com/problems/string-to-integer-atoi/description/
 *  https://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/
 */
public class q11_StrToInt {
    public static void main(String[] args) {
        q11_StrToInt sti = new q11_StrToInt();
        System.out.println(sti.convertToInt1("-11"));
    }

    /*
     * https://discuss.leetcode.com/topic/2666/my-simple-solution/15
     */
    public int convertToInt1(String str) {
        //null or empty string
        if (str==null || str.isEmpty()) return 0;

        int sign = 1, base = 0, i = 0;

        // handle white spaces
        while (str.charAt(i) == ' ')
            i++;

        //check neg or pos
        if (str.charAt(i) == '-' || str.charAt(i) == '+')
            sign = str.charAt(i++) == '-' ? -1 : 1;

        //calculate2 value
        while(i<str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = base*10 + (str.charAt(i++) - '0');
        }
        return base * sign;
    }

    /*
     * https://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/
     */
    public int convertToInt2(String str) {
        //null or empty string
        if(str == null || str.length() == 0) return 0;

        // trim white spaces
        str = str.trim();

        //check neg or pos
        int i = 0;
        boolean isNegative = false;
        if(str.charAt(i) == '-') {
            isNegative = true;
            i++;
        }
        else if (str.charAt(0) == '+') i++;

        //calculate2 value
        double result = 0;
        while(i<str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if(isNegative) result = -result;

        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;

    }
}
