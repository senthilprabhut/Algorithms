package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *  You may assume that the intervals were initially sorted according to their start times.
 *  Example 1:
 *  Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *  Example 2:
 *  Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *  This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 * Links:
 *  https://leetcode.com/problems/insert-interval/description/
 *  https://www.programcreek.com/2012/12/leetcode-insert-interval/
 */
public class q07_InsertIntervals {
    public static void main(String[] args) {
        q07_InsertIntervals ii = new q07_InsertIntervals();
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1,2));
        input.add(new Interval(3,5));
        input.add(new Interval(6,7));
        input.add(new Interval(8,10));
        input.add(new Interval(12,16));

        Interval newInterval = new Interval(4,9);
        System.out.println(ii.insertAndMerge1(input, newInterval));
    }


    /**
     * Approach 1:
     *
     * Link:
     *  https://discuss.leetcode.com/topic/12691/short-java-code
     */
    public List<Interval> insertAndMerge1(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        if (intervals.size() == 0) return result;

        for (Interval i : intervals) {
            if(newInterval == null || i.end < newInterval.start) {
                result.add(i);
            } else if (i.start > newInterval.end) {
                result.add(newInterval); //since the list is sorted interval, if start > newInterval.end, then we can add newInterval
                result.add(i);
                newInterval = null;
            } else {
                newInterval.start = Math.min(i.start, newInterval.start);
                newInterval.end = Math.max(i.end, newInterval.end);
            }
        }

        if (newInterval != null)
            result.add(newInterval);

        return result;
    }

    /**
     * Approach 2:
     *  NOT Needed to learn - Verbose way of doing the above solution
     * Links:
     *  https://discuss.leetcode.com/topic/7808/short-and-straight-forward-java-solution
     */
    public List<Interval> insertAndMerge2(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        if (intervals.size() == 0) return result;

        //Add all the intervals ending before newInterval starts
        int i = 0;
        while (i<intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            i++;
        }

        //Merge all overlapping intervals to one considering newInterval
        while(i<intervals.size() && intervals.get(i).start < newInterval.end) {
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
                    Math.max(intervals.get(i).end, newInterval.end));
            i++;
        }
        //Add the union of intervals we got
        result.add(newInterval);

        //Add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++));
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
