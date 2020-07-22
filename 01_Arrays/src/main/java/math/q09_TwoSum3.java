package math;

import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Design and implement a TwoSum class. It should support the following operations: add and find.
 *  add - Add the number to an internal data structure.
 *  find - Find if there exists any pair of numbers which sum is equal to the value.
 *  For example,
 *  add(1); add(3); add(5); find(4) -> true  find(7) -> false
 *
 * Links:
 * 	https://leetcode.com/problems/two-sum-iii-data-structure-design
 *  https://www.programcreek.com/2014/03/two-sum-iii-data-structure-design-java/
 */
//Since the desired class need add and get operations, HashMap is a good option for this purpose.
public class q09_TwoSum3 {
    private Map<Integer, Integer> elements = new HashMap<Integer, Integer>();

    public void add(int number) {
        if (elements.containsKey(number)) {
            elements.put(number, elements.get(number) + 1); //To track if same number occurred twice
        } else {
            elements.put(number, 1);
        }
    }

    public boolean find(int value) {
        for (Integer i : elements.keySet()) {
            int target = value - i;
            if (elements.containsKey(target)) {
                if (i == target && elements.get(target) < 2) { //If the same no occurred twice
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}
