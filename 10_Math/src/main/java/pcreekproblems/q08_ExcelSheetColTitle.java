package pcreekproblems;

/**
 * Problem:
 *  Given a positive integer, return its corresponding column title as appear in an Excel sheet. For example:
 *          1 -> A
 *          2 -> B
 *          3 -> C
 *          ...
 *          26 -> Z
 *          27 -> AA
 *          28 -> AB
 *
 * Links:
 *  https://www.programcreek.com/2014/03/leetcode-excel-sheet-column-title-java/
 */
public class q08_ExcelSheetColTitle {
    public static void main(String[] args) {
        q08_ExcelSheetColTitle ect = new q08_ExcelSheetColTitle();
        System.out.printf("The char representation for %d is %s\n", 27, ect.convertToTitle(27)); //AA
        System.out.printf("The char representation for %d is %s\n", 235, ect.convertToTitle(235)); //AI
        System.out.printf("The char representation for %d is %s\n", 703, ect.convertToTitle(703)); //AAA
    }

    /**
     * Approach:
     *  This problem is the reverse version of Excel Sheet Column Number.
     *  The key is n--. The minimum in 26-bit number is mapped to 1, not 0.
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public String convertToTitle(int n) {
        if(n <= 0){
            throw new IllegalArgumentException("Input is not valid!");
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % 26;
            int offset = remainder - 1; //Subtract 1 to convert number to index
            sb.append( (char) ('A' + offset));
            n = n / 26;
        }
        return sb.toString();
    }
}
