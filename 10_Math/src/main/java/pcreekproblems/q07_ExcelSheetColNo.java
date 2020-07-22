package pcreekproblems;

/**
 * Problem:
 *  Given a column title as appear in an Excel sheet, return its corresponding column number. For example:
 *  A -> 1
 *  B -> 2
 *  C -> 3
 *  ...
 *  Z -> 26
 *  AA -> 27
 *  AB -> 28
 *  ...
 *  AAA -> 703
 *
 * Links:
 *  https://www.programcreek.com/2014/03/leetcode-excel-sheet-column-number-java/
 *
 */
public class q07_ExcelSheetColNo {
    public static void main(String[] args) {
        q07_ExcelSheetColNo ecn = new q07_ExcelSheetColNo();
        System.out.printf("The integer representataion of %s is %d\n", "AA", ecn.titleToNumber("AA"));  //27
        System.out.printf("The integer representataion of %s is %d\n", "AAA", ecn.titleToNumber("AAA"));  //703
    }

    public int titleToNumber(String s) {
        int result = 0, pow=0;

        for(int i=s.length()-1; i >=0; i--) {
            char curr = s.charAt(i);
            result =  result + (curr - 'A' + 1) * (int)(Math.pow(26,pow));
            pow++;
        }
        return result;
    }
}
