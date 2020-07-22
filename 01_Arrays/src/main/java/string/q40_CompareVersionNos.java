package string;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Compare two version numbers version1 and version2.
 *  If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 *  You may assume that the version strings are non-empty and contain only digits and the . character.
 *  The . character does not represent a decimal point and is used to separate number sequences.
 *  For instance, 2.5 is not "two and a half" or "half way to version three",
 *  it is the fifth second-level revision of the second first-level revision.
 *  Here is an example of version numbers ordering: 0.1 < 1.1 < 1.2 < 13.37
 *
 * Links:
 *  https://leetcode.com/problems/compare-version-numbers/
 *  https://www.programcreek.com/2014/03/leetcode-compare-version-numbers-java/
 *  https://leetcode.com/problems/compare-version-numbers/discuss/50774
 */
public class q40_CompareVersionNos {
    public static void main(String[] args) {
        q40_CompareVersionNos sp = new q40_CompareVersionNos();
        System.out.println(sp.compareVersion("0.1", "1.1"));
        System.out.println(sp.compareVersion("1", "1.0"));
        System.out.println(sp.compareVersion("1.22", "1.115"));
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");  //“\.” means a dot in regexp, and “.” means any character in regexp
        String[] v2 = version2.split("\\.");

        int length = Math.max(v1.length, v2.length);
        for(int i=0; i<length; i++) {
            Integer i1 = (i<v1.length) ? Integer.parseInt(v1[i]) : 0;
            Integer i2 = (i<v2.length) ? Integer.parseInt(v2[i]) : 0;
            int compare = i1.compareTo(i2);
            if (compare != 0) return compare;
        }
        return 0;
    }
}
