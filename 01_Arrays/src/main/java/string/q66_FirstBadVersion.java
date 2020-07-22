package string;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  You are a product manager and currently leading a team to develop a new product.
 *  Unfortunately, the latest version of your product fails the quality check.
 *  Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *  Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *  You are given an API bool isBadVersion(version) which will return whether version is bad.
 *  Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Links:
 *  https://leetcode.com/problems/first-bad-version/description/
 *  https://www.programcreek.com/2014/05/leetcode-first-bad-version-java/
 */
public class q66_FirstBadVersion {
    public static void main(String[] args) {

    }

    /**
     * Approach:
     *  Binary Search
     *
     * Links:
     *  https://leetcode.com/problems/first-bad-version/discuss/71296/O(lgN)-simple-Java-solution
     *
     * Complexity:
     *  Time is O(log n)
     *
     */
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (!isBadVersion(mid)) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }
}
