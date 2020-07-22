package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given numRows, getRow2 the first numRows of Pascal's triangle.
 *  For example, given numRows = 5, Return
 *  [
 *       [1],
 *      [1,1],
 *     [1,2,1],
 *    [1,3,3,1],
 *   [1,4,6,4,1]
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/pascals-triangle/description/
 *  https://www.programcreek.com/2014/03/leetcode-pascals-triangle-java/
 */
public class q44_PascalTriangle1 {
    public static void main(String[] args) {
        q44_PascalTriangle1 tr = new q44_PascalTriangle1();
        System.out.println(tr.generateViaDP(5));
    }

    /**
     * Links:
     *  https://leetcode.com/problems/pascals-triangle/discuss/38141
     */
    public List<List<Integer>> generateViaDP(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        for(int i=0; i<numRows; i++) {
            row.add(0,1); //add 1 at index 0
            for(int j=1; j<row.size()-1; j++) {  //go from 2nd element to last-1 element
                row.set(j, row.get(j) + row.get(j+1)); //Add current and next element
            }
            triangle.add(new ArrayList<>(row));
        }
        return triangle;
    }
}
