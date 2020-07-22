package t04_segmenttree;

/**
 * Problem:
 *  We have an array arr[0 . . . n-1]. We should be able to efficiently find the minimum value from
 *  index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.
 *
 * Approaches:
 *  Approach 1: run a loop from start to end and find minimum element in given range. This solution takes O(n) time in worst case.
 *  Approach 2: create a 2D array where an entry [i, j] stores the minimum value in range arr[i..j].
 *      Minimum of a given range can now be calculated in O(1) time, but building it takes O(n^2) time.
 *      Also, this approach needs O(n^2) extra space
 *  Approcah 3: With segment tree, preprocessing time is O(n) and time taken for range minimum query is O(Logn).
 *      The extra space required is O(n) to store the segment tree.
 *
 * Links:
 *  http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
 *  http://www.geeksforgeeks.org/lazy-propagation-in-segment-tree/
 *  Tushar - https://youtu.be/ZBHKZF5w4YU
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/SegmentTreeMinimumRangeQuery.java
 *
 * Complexity:
 *  Time Complexity to query is O(log n). To query a range minimum, we process at most two nodes at every level and number of levels is O(log n).
 */
public class MinimumRangeQuery {

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree();

        int input[] = {0,3,4,2,1,6,-1};
        SegmentTree.Operation minOperation = new SegmentTree.MinOperation();
        int segTree[] = st.createSegmentTree(input, minOperation);
        st.printTree(segTree);
        System.out.println("-------------------");

        //non lazy propagation example
        MinimumRangeQuery mrq = new MinimumRangeQuery();
        assert 0 == mrq.rangeMinimumQuery(segTree, 0, 3, input.length);
        assert 1 == mrq.rangeMinimumQuery(segTree, 1, 5, input.length);
        assert -1 == mrq.rangeMinimumQuery(segTree, 1, 6, input.length);
        System.out.println("updating at index 2 with delta 1");
        //st.updateSegmentTree(segTree, 0, input.length-1, 2, 1, 0);
        st.updateSegmentTree(segTree, 0,0, input.length-1, 2, 1, minOperation);
        st.printTree(segTree);
        System.out.println("-------------------");
        assert 2 == mrq.rangeMinimumQuery(segTree, 1, 3, input.length);
        System.out.println("updating range of  3 to 5 with delta -2");
        //st.updateSegmentTreeRange(segTree, 0, input.length-1, 3, 5, -2, 0);
        st.updateSegmentTreeRange(segTree, 0,0, input.length-1, 3, 5, -2, minOperation);
        st.printTree(segTree);
        System.out.println("-------------------");
        assert -1 == mrq.rangeMinimumQuery(segTree, 5, 6, input.length);
        assert 0 == mrq.rangeMinimumQuery(segTree, 0, 3, input.length);


        //lazy propagation example
        int input1[] = {-1,2,4,1,7,1,3,2};
        int segTree1[] = st.createSegmentTree(input1, minOperation);
        int lazy1[] = new int[segTree1.length];
        System.out.println("Lazy propagation ...");
        st.printTree(segTree1);
        System.out.println("-------------------");
        st.updateSegmentTreeRangeLazy(segTree1, 0, lazy1, 0, input1.length-1, 0, 3, 1, minOperation);
        System.out.println("updating range of  0 to 3 with delta 1");
        st.printTree(segTree1);
        st.printTree(lazy1);
        System.out.println("-------------------");
        System.out.println("updating range of  0 to 0 with delta 2");
        st.updateSegmentTreeRangeLazy(segTree1, 0, lazy1, 0, input1.length-1, 0, 0, 2, minOperation);
        st.printTree(segTree1);
        st.printTree(lazy1);
        System.out.println("-------------------");
        assert 1 == mrq.rangeMinimumQueryLazy(segTree1, lazy1, 3, 5, input1.length);
    }

    /**
     * Queries given range for minimum value.
     */
    public int rangeMinimumQuery(int[] segmentTree, int queryStart, int queryEnd, int len) {
        return rangeMinimumQuery(segmentTree, queryStart, queryEnd, 0, len - 1, 0);
    }

    private int rangeMinimumQuery(int[] segmentTreeArr, int queryStart, int queryEnd, int startIndex, int endIndex, int segTreePos) {
        //if total overlap return the value - query of [0,4] totally overlaps [2,3]
        if (queryStart <= startIndex && queryEnd >= endIndex) return segmentTreeArr[segTreePos];

        //if no overlap, return MAX value
        if (queryStart > endIndex || queryEnd < startIndex) return Integer.MAX_VALUE;

        //if partial overlap, search both ways
        int mid = (startIndex + endIndex) / 2;
        int left = rangeMinimumQuery(segmentTreeArr, queryStart, queryEnd, startIndex, mid, 2 * segTreePos + 1);
        int right = rangeMinimumQuery(segmentTreeArr, queryStart, queryEnd, mid + 1, endIndex, 2 * segTreePos + 2);
        return Math.min(left, right);
    }

    /**
     * Queries given range lazily
     */
    public int rangeMinimumQueryLazy(int segmentTree[], int lazy[], int queryStart, int queryEnd, int len) {
        return rangeMinimumQueryLazy(segmentTree, 0, lazy, queryStart, queryEnd, 0, len - 1);
    }

    private int rangeMinimumQueryLazy(int[] segmentTree, int segTreePos, int[] lazy, int queryStart, int queryEnd, int startIndex, int endIndex) {
        //make sure all propagation is done at pos. If not update tree
        //at pos and mark its children for lazy propagation.
        if(lazy[segTreePos] != 0) {
            segmentTree[segTreePos] += lazy[segTreePos];
            if (startIndex != endIndex) { //not leaf
                segmentTree[2 * segTreePos + 1] += lazy[segTreePos];
                segmentTree[2 * segTreePos + 2] += lazy[segTreePos];
            }
            lazy[segTreePos] = 0;
        }

        //if no overlap
        if(queryStart > endIndex || queryEnd < startIndex) return Integer.MAX_VALUE;

        //if complete overlap
        if(queryStart<=startIndex && queryEnd >= endIndex) return segmentTree[segTreePos];

        //if partial overlap
        int mid = (startIndex + endIndex) / 2;
        int left = rangeMinimumQuery(segmentTree, 2 * segTreePos + 1, queryStart, queryEnd, startIndex, mid);
        int right = rangeMinimumQuery(segmentTree, 2 * segTreePos + 2, queryStart, queryEnd, mid + 1, endIndex);
        return Math.min(left, right);
    }


}
