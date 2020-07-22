package string;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 *  (you may want to display this pattern in a fixed font for better legibility)
 *    P   A   H   N
 *    A P L S I I G
 *    Y   I   R
 *  And then read line by line: "PAHNAPLSIIGYIR"
 *  Write the code that will take a string and make this conversion given a number of rows:
 *
 * Links:
 *  https://leetcode.com/problems/add-binary/description/
 *  https://www.programcreek.com/2014/05/leetcode-add-binary-java/
 */
public class q19_ZigzagConversion {
    public static void main(String[] args) {
        q19_ZigzagConversion zc = new q19_ZigzagConversion();
        System.out.println(zc.convert("PAYPALISHIRING", 3));
    }

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return s;
        if (numRows == 1) return s;

        StringBuilder sb = new StringBuilder();
        int step = (2 * numRows) - 2;

        for(int currRow=0; currRow<numRows; currRow++) {
            //first & last rows
            if (currRow == 0 || currRow == numRows - 1) {
                for (int j=currRow; j<s.length(); j+=step) { //First row is appended to the sb. In the end, last row is appended
                    sb.append(s.charAt(j));
                }
            } //middle rows
            else {
                int step1 = 2 * (numRows - 1 - currRow); //steps alternate - calculate them here
                int step2 = step - step1;

                int j = currRow;
                boolean flag = true;
                while (j < s.length()) {
                    sb.append(s.charAt(j));
                    if(flag) j += step1;
                    else j += step2;

                    flag = !flag;
                }
            }
        }
        return sb.toString();
    }
}
