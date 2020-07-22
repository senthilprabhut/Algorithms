package string;

/**
 * Difficulty Level: MEDIUM (LOCKED)
 *
 * Problem:
 *  Given two strings S and T, determine if they are both one edit distance apart.
 *
 * Links:
 *  https://leetcode.com/problems/one-edit-distance
 *  https://www.programcreek.com/2014/05/leetcode-one-edit-distance-java/
 *
 */
public class q64_OneEditDistance {
    public static void main(String[] args) {

    }

    /**
     * Approach:
     *  Modify:  abcde    abXde (length diff = 0)
     *  Insertion:  abcde    abXcde  (length diff = 1)
     *  Append:  abcde    abcdeX    (length diff = 1)
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-one-edit-distance-java/
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();

        if ( Math.abs(m-n) > 1) return false; //Edit distance is > 1

        if (m > n) return isOneEditDistance(t,s);

        int index=0, lengthDiff=n-m;

        while (index < m && s.charAt(index) == t.charAt(index)) {
            index++;
        }

        //Incase of abcde, abcdeX (Append), this above loop breaks before X. Diff is 1 in this case
        if (index == m) {
            return lengthDiff > 0;
        }

        //Incase of abcde, abXde (Modify) - this above loop breaks after ab. Diff is 0 in this case
        if (lengthDiff == 0) index++;

        //Incase of abcde, abXcde (Delete), we break after "ab". then start comparing "c" to the char after "X" (index + diff)
        while (index < m && s.charAt(index) == t.charAt(index+lengthDiff)) {
            index++;
        }
        return index == m;
    }
}
