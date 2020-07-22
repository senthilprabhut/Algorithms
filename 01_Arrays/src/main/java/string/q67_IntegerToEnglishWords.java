package string;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *  For example,
 *      123 -> "One Hundred Twenty Three"
 *      12345 -> "Twelve Thousand Three Hundred Forty Five"
 *      1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Links:
 *  https://leetcode.com/problems/integer-to-english-words/description/
 *  https://www.programcreek.com/2014/05/leetcode-integer-to-english-words-java/
 */
public class q67_IntegerToEnglishWords {
    public static void main(String[] args) {
        q67_IntegerToEnglishWords iew = new q67_IntegerToEnglishWords();
        System.out.println(iew.numberToWords(1270529));
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand/72854
     *
     * Complexity:
     *  Time is O(1) - The loop runs 4 times and each loop makes max of 3 recursive calls
     */
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String[] lessThan20 = new String[] {
                "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
                "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"
        };
        String[] lessThan100 = new String[] { "", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY" };
        String[] greaterThan100 = new String[] {"BILLION", "MILLION", "THOUSAND", "HUNDRED"};
        int[] radix = new int[] {1000000000,1000000,1000,100};

        StringBuilder words = new StringBuilder();
        for (int i=0; i<radix.length; i++) {
            int quotient = num / radix[i];

            if (quotient == 0) continue;  //if the number is not large enough, try dividing with smaller number

            words.append(" " + numberToWords(quotient));
            words.append(" " + greaterThan100[i]);

            num = num % radix[i];
        }

        //num is less than 100
        if (num < 20) {
            words.append(" " + lessThan20[num]);
        } else {
            //between 20 and 99
            int quotient = num / 10;
            words.append(" " + lessThan100[quotient]);

            int remainder = num %10;
            words.append(" " + lessThan20[remainder]);
        }
        return words.toString().trim();
    }
}
