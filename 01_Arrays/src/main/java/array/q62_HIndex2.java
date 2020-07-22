package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 *
 * Links:
 *  https://leetcode.com/problems/h-index-ii/description/
 *  https://www.programcreek.com/2014/05/leetcode-h-index-ii-java/
 */
public class q62_HIndex2 {
    public static void main(String[] args) {
        q62_HIndex2 hi1 = new q62_HIndex2();
        System.out.println(hi1.hIndex(new int[]{0, 1, 3, 5, 6}));
    }

    /**
     * Approach: Binary Search
     *
     * Links:
     *  https://leetcode.com/problems/h-index-ii/discuss/71063/Standard-binary-search
     *
     * Complexity:
     *  Time is O(log n)
     *  Space is O(1)
     */
    public int hIndex(int[] citations) {
        int len = citations.length, left=0, right= len-1,  mid;

        while (left <= right) {
            mid = left + (right - left)/2;
            if (citations[mid] == len-mid) return citations[mid];
            else if (citations[mid] > len-mid) right = mid - 1;
            else left = mid + 1;
        }

        return len - left;
    }
}
