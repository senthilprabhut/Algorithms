package t04_segmenttree;

/**
 * Problem:
 *  We have an array arr[0 . . . n-1]. We should be able to efficiently find the sum of elements from
 *  index l to r where 0 <= l <= r <= n-1
 *
 * Links:
 *  http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 *  http://www.geeksforgeeks.org/lazy-propagation-in-segment-tree/
 *
 * Complexity:
 *  Time Complexity to query is O(log n). To query a sum, we process at most four nodes at every level and number of levels is O(log n).
 */
public class SumOfRange {
    public static void main(String[] args) {
        int[] input = {1, 3, 5, 7, 9, 11};
        SegmentTree.Operation addOperation = new SegmentTree.AddOperation();
        SegmentTree  st = new SegmentTree();
        int[] segTreeArr = st.createSegmentTree(input, addOperation);

        SumOfRange sor = new SumOfRange();
        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " +
                sor.getSum(segTreeArr,1, 3, input.length));

        // Update: set arr[1] = 10 and update corresponding segment
        //st.updateValue(arr, n, 1, 10);
        // Add 10 to all nodes at indexes from 1 to 5.
        st.updateSegmentTreeRange(segTreeArr,0,0, input.length-1, 1, 5, 10, addOperation);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " +
                sor.getSum(segTreeArr, 1, 3, input.length));

        st.printTree(segTreeArr);
        System.out.println("-------------");

        int[] lazy = new int[segTreeArr.length];
        st.updateSegmentTreeRangeLazy(segTreeArr, 0, lazy, 0, input.length-1, 0, 3, 10, addOperation);
        st.printTree(segTreeArr);
        st.printTree(lazy);

        System.out.println("-------------");
        System.out.println("Updated sum of values in given range = " +
                sor.getSumLazy(segTreeArr, lazy, 1, 3, input.length));
        st.printTree(segTreeArr);
        st.printTree(lazy);
    }

    public int getSum(int[] segTreeArr, int rangeStart, int rangeEnd, int length) {
        return getSum(segTreeArr, 0, 0 , length-1, rangeStart, rangeEnd);
    }

    private int  getSum(int[] segTreeArr, int segTreePos, int startIndex, int endIndex, int rangeStart, int rangeEnd) {
        //No overlap
        if(rangeStart > endIndex || rangeEnd < startIndex) return 0;

        //Complete overlap
        if(rangeStart <= startIndex && rangeEnd >= endIndex) return segTreeArr[segTreePos];

        //Partial overlap
        int mid = (startIndex + endIndex)/2;
        int left = getSum(segTreeArr, 2*segTreePos+1, startIndex, mid, rangeStart, rangeEnd);
        int right = getSum(segTreeArr, 2*segTreePos+2, mid+1, endIndex, rangeStart, rangeEnd);
        return (left+right);
    }

    public int getSumLazy(int[] segTreeArr, int[] lazy, int rangeStart, int rangeEnd, int length) {
        return getSumLazy(segTreeArr, 0, lazy, 0 , length-1, rangeStart, rangeEnd);
    }

    private int  getSumLazy(int[] segTreeArr, int segTreePos, int[] lazy, int startIndex, int endIndex, int rangeStart, int rangeEnd) {
        //make sure all propagation is done at segTreePos. If not update tree
        //at segTreePos and mark its children for lazy propagation.
        if(lazy[segTreePos] != 0) {
            // Note that this node represents sum of elements in arr[sIndex..eIndex] and
            // all these elements must be increased by lazy[segTreePos]
            segTreeArr[segTreePos] +=  ((endIndex - startIndex + 1) * lazy[segTreePos]);
            if(startIndex != endIndex) {
                lazy[2*segTreePos+1] += lazy[segTreePos];
                lazy[2*segTreePos+2] += lazy[segTreePos];
            }
            lazy[segTreePos] = 0;
        }

        //No overlap
        if(rangeStart > endIndex || rangeEnd < startIndex) return 0;

        //Complete overlap
        if(rangeStart <= startIndex && rangeEnd >= endIndex) return segTreeArr[segTreePos];

        //Partial overlap
        int mid = (startIndex + endIndex)/2;
        int left = getSumLazy(segTreeArr, 2*segTreePos+1, lazy, startIndex, mid, rangeStart, rangeEnd);
        int right = getSumLazy(segTreeArr, 2*segTreePos+2, lazy, mid+1, endIndex, rangeStart, rangeEnd);
        return (left+right);
    }
}
