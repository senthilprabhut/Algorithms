package math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 * Given a list of numbers and a target number, write a program to determine whether the target number can be
 * calculated by applying "+-*\/" operations to the number list? You can assume () is automatically added when necessary.
 * For example, given {1,2,3,4} and 21, return true. Because (1+2)*(3+4)=21
 *
 * Links:
 * https://www.programcreek.com/2016/04/get-target-using-number-list-and-arithmetic-operations-google/
 */
public class q54_GetTargetNoUsingNumberList {
    public static void main(String[] args) {
        q54_GetTargetNoUsingNumberList gt = new q54_GetTargetNoUsingNumberList();
        System.out.println(gt.isReachable(Arrays.asList(1,2,3,4), 21));
        System.out.println(gt.isReachable(Arrays.asList(1,2,3,4), 19));
    }

    /**
     * Approach:
     *  This is a partition problem which can be solved by using depth first search.
     *
     * Explanation:
     *  http://codinghelmet.com/?path=exercises/expression-from-numbers
     *
     * Links:
     *  https://www.programcreek.com/2016/04/get-target-using-number-list-and-arithmetic-operations-google/
     *
     * Complexity:
     *
     */
    public boolean isReachable(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return false;
        }

        int start=0, end=list.size()-1;
        List<Integer> results = getResults(list, start, end, target);

        for (int result : results) {
            if (result == target) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getResults(List<Integer> list, int start, int end, int target) {
        List<Integer> result = new LinkedList<>();

        if (start > end) {
            return result;
        } else if (start == end) {
            result.add(list.get(start)); //Add the only element available
        }

        for (int i = start; i < end; i++) {
            List<Integer> result1 = getResults(list, start, i, target);
            List<Integer> result2 = getResults(list, i+1, end, target);

            for (int x : result1) {
                for (int y : result2) {
                    result.add(x+y);
                    result.add(x-y);
                    result.add(x*y);
                    if (y != 0) {
                        result.add(x/y);
                    }
                }
            }
        }
        return result;
    }
}
