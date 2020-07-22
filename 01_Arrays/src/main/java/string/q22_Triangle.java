package string;

import java.util.ArrayList;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below
 *   For example, given the following triangle
 *   [
 *       [2],
 *      [3,4],
 *     [6,5,7],
 *    [4,1,8,3]
 *  ]
 *  The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Links:
 *  https://leetcode.com/problems/triangle/description/
 *  https://www.programcreek.com/2013/01/leetcode-triangle-java/
 */
public class q22_Triangle {
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<Integer>() {{add(2);}};
        ArrayList<Integer> l2 = new ArrayList<Integer>() {{add(3);add(4);}};
        ArrayList<Integer> l3 = new ArrayList<Integer>() {{add(6);add(5); add(7);}};
        ArrayList<Integer> l4 = new ArrayList<Integer>() {{add(4);add(1); add(8); add(3);}};
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(){ {add(l1); add(l2); add(l3); add(l4);} };

        q22_Triangle tr = new q22_Triangle();
        System.out.println(tr.minimumTotal(list));
    }

    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int[] total = new int[triangle.size()];
        int last = triangle.size() - 1;

        for(int i=0; i<triangle.get(last).size(); i++) {
            total[i] = triangle.get(last).get(i);
        }

        // iterate from last second row
        for(int i=triangle.size()-2; i>=0; i--) {
            for(int j=0; j<triangle.get(i).size(); j++) {
                total[j] = Math.min(total[j], total[j+1]) + triangle.get(i).get(j);
            }
        }
        return total[0];
    }
}

