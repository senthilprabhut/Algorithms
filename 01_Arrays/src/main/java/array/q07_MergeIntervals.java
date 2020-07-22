package array;

import java.util.LinkedList;
import java.util.List;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *   Given a collection of intervals, merge all overlapping intervals.
 *   For example,
 *   Given [1,3],[2,6],[8,10],[15,18],
 *   return [1,6],[8,10],[15,18].
 *
 * Links:
 *  https://leetcode.com/problems/merge-intervals/description/
 *  https://www.programcreek.com/2012/12/leetcode-merge-intervals/
 *  https://discuss.leetcode.com/topic/4319/a-simple-java-solution
 */
public class q07_MergeIntervals {
    public static void main(String[] args) {
        q07_MergeIntervals mi = new q07_MergeIntervals();
        List<Interval> list = new LinkedList<>();
        list.add(new Interval(1,3));
        list.add(new Interval(2,6));
        list.add(new Interval(8,10));
        list.add(new Interval(15,18));
        System.out.println(mi.merge(list));
    }

    /**
     * Approach 1:
     *  The idea is to sort the intervals by their starting points.
     *  Then, we take the first interval and compare its end with the next intervals starts.
     *  As long as they overlap, we update the end to be the max end of the overlapping intervals.
     *  Once we find a non overlapping interval, we can add the previous "extended" interval and start over.
     *
     * Complexity:
     *  Time - Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the resulting algorithm takes O(n log(n))
     *  Space - O(1). No extra space other than to store the results which in worst case is O(n)
     */
    public List<Interval> merge(List<Interval> intervals) {
        //Input Validation
        if (intervals.size() <= 1)
            return intervals;

        intervals.sort((Interval i1, Interval i2) -> {
            if (i1.start != i2.start) {
                return Integer.compare(i1.start, i2.start);
            } else
                return Integer.compare(i1.end, i2.end);
        }); //Sort the collections based on start interval

        List<Interval> result = new LinkedList<>();
        Interval pre = intervals.get(0);
        for (Interval curr : intervals) {
            if(curr.start > pre.end) {
                result.add(pre);
                pre = curr;
            } else {
                Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
                pre = merged;
            }
        }
        // Add the last interval
        result.add(pre);

        return result;
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "[" + start +"," + end + "]";
        }
    }

}
