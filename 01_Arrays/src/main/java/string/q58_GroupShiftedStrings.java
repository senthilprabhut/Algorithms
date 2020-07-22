package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Difficulty Level: MEDIUM (Premium)
 *
 * Problem:
 *  Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
 *  We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz".
 *  Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence, return:
 *  [
 *      ["abc","bcd","xyz"],["az","ba"],["acef"], ["a","z"]
 *  ]
 *
 * Links:
 *  https://leetcode.com/problems/group-shifted-strings
 *  https://www.programcreek.com/2014/05/leetcode-group-shifted-strings-java/
 *
 */
public class q58_GroupShiftedStrings {
    public static void main(String[] args) {
        q58_GroupShiftedStrings gss = new q58_GroupShiftedStrings();
        System.out.println(gss.groupStrings(new String[] {"abc","az","acef", "a", "ba","bcd","xyz", "z"}));
    }

    /**
     * Approach:
     *  Calculate the diff between the char and 'a' and store it as a string
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-group-shifted-strings-java/
     *
     * Complexity:
     *  Time is O(nm), n is the no of Strings and m is the avg size of each string
     *  Space is O(n)
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, ArrayList<String>> map = new HashMap<>();

        for (String str : strings) {
            char[] diffArray = new char[str.length()];

            int shiftFromA = str.charAt(0) - 'a';
            for (int i=0; i<str.length(); i++) {
                int diff = str.charAt(i) - shiftFromA;
                if (diff < 'a') diff += 26;  //Take care of rotation like a-1 = z

                diffArray[i] = (char) diff;
            }

            String mapKey = new String(diffArray);
            map.compute(mapKey, (key, value) -> {
                if (value == null) {
                    value = new ArrayList<>();
                }
                value.add(str);
                return value;
            });
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String,ArrayList<String>> entry : map.entrySet()) {
            result.add(new ArrayList<>(entry.getValue()));
        }
        return result;
    }
}
