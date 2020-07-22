package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given an index k, return the kth row of the Pascal's triangle.
 *  For example, given k = 3,  Return [1,3,3,1]
 *  Could you optimize your algorithm to use only O(k) extra space?
 *  [
 *       [1],
 *      [1,1],
 *     [1,2,1],
 *    [1,3,3,1],
 *   [1,4,6,4,1]
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/pascals-triangle-ii/description/
 *  https://www.programcreek.com/2014/04/leetcode-pascals-triangle-ii-java/
 */
public class q44_PascalTriangle2 {
    public static void main(String[] args) {
        q44_PascalTriangle2 tr = new q44_PascalTriangle2();
        System.out.println(tr.getRow2(3));  //[1,3,3,1]
    }

    /**
     * Links:
     *  https://leetcode.com/problems/pascals-triangle-ii/discuss/38420
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for(int i=1; i<=rowIndex; i++) {
            for(int j=i-1; j>=1; j--) {
                int tmp = row.get(j - 1) + row.get(j);
                row.set(j, tmp);
            }
            row.add(1);
        }
        return row;
    }
}
