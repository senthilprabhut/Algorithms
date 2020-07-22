package t04_segmenttree;

import bits.NextPowerOf2;

/**
 * Segment Tree:
 *  A segment tree is a tree data structure for storing intervals, or segments. It allows
 *  for faster querying (e.g sum or min) in these intervals. Lazy propagation is useful
 *  when there are high number of updates in the input array.
 *
 *  All levels of the constructed segment tree will be completely filled except the last level.
 *  Also, the tree will be a Full Binary Tree because we always divide segments in two halves at every level.
 *  Since the constructed tree is always full binary tree with n leaves, there will be n-1 internal nodes.
 *  So total number of nodes will be 2*n – 1.
 *
 * Links:
 *  http://www.geeksforgeeks.org/lazy-propagation-in-segment-tree/
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/SegmentTree.java
 *
 * Complexity:
 *  Time complexity to create segment tree is O(n). There are total 2n-1 nodes, and value of every node is calculated only once in tree construction.
 *  Space complexity to create segment tree is O(n) since new array will be at max 4n size
 *  Time complexity to update in segment tree is O(log n). To update a leaf value, we process one node at every level and number of levels is O(log n)
 */
public class SegmentTree {
    /**
     * Creates a new segment tree based off input array.
     */
    public int[] createSegmentTree(int[] inputArr, Operation operation) {
        //if n is the no of leaf nodes, then tree will have n-1 non-leaf nodes -> so 2n -1 total nodes in tree
        int nextPowOfTwo = NextPowerOf2.nextPowerOf2(inputArr.length); //Making leaf node count to power of 2
        int[] segTreeArr = new int[2 * nextPowOfTwo - 1];
        for (int i = 0; i < segTreeArr.length; i++) {
            segTreeArr[i] = Integer.MAX_VALUE;
        }

        constructSegTree(segTreeArr, 0, inputArr, 0, inputArr.length - 1, operation);
        return segTreeArr;
    }

    private void constructSegTree(int[] segTreeArr, int segTreePos, int[] inputArr, int startIndex, int endIndex, Operation operation) {
        //if leaf node
        if (startIndex == endIndex) {
            segTreeArr[segTreePos] = inputArr[startIndex];
            return;
        }

        int mid = (startIndex + endIndex) / 2;
        constructSegTree(segTreeArr, 2 * segTreePos + 1, inputArr, startIndex, mid, operation);
        constructSegTree(segTreeArr, 2 * segTreePos + 2, inputArr, mid + 1, endIndex, operation);
        segTreeArr[segTreePos] = operation.perform(segTreeArr[2 * segTreePos + 1], segTreeArr[2 * segTreePos + 2]);
    }

    public void updateSegmentTree(int[] segTreeArr, int segTreePos, int startIndex, int endIndex, int updIndex, int delta, Operation operation) {
        //Out of range
        if (updIndex < startIndex || updIndex > endIndex)
            return;

        //Leaf node
        if (startIndex == endIndex) {
            segTreeArr[segTreePos] += delta;
            return;
        }

        //If not a leaf node, recur for children
        int mid = (startIndex + endIndex) / 2;
        updateSegmentTree(segTreeArr, 2 * segTreePos + 1, startIndex, mid, updIndex, delta, operation);
        updateSegmentTree(segTreeArr, 2 * segTreePos + 2, mid + 1, endIndex, updIndex, delta, operation);
        segTreeArr[segTreePos] = operation.perform(segTreeArr[2 * segTreePos + 1], segTreeArr[2 * segTreePos + 2]);
    }

    public void updateSegmentTreeRange(int[] segTreeArr, int segTreePos, int startIndex, int endIndex, int updStart, int updEnd, int delta, Operation operation) {
        //Out of range
        if (updStart > endIndex || updEnd < startIndex) return;

        //Leaf node
        if (startIndex == endIndex) {
            segTreeArr[segTreePos] += delta;
            return;
        }

        //If not a leaf node, recur for children
        int mid = (startIndex + endIndex) / 2;
        updateSegmentTreeRange(segTreeArr, 2 * segTreePos + 1, startIndex, mid, updStart, updEnd, delta, operation);
        updateSegmentTreeRange(segTreeArr, 2 * segTreePos + 2, mid+1, endIndex, updStart, updEnd, delta, operation);
        segTreeArr[segTreePos] = operation.perform(segTreeArr[2 * segTreePos + 1], segTreeArr[2 * segTreePos + 2]);
    }

    public void updateSegmentTreeRangeLazy(int[] segTreeArr, int segTreePos, int[] lazy, int startIndex, int endIndex, int updStart, int updEnd, int delta, Operation operation) {
        //Make sure all propagation is done at segTreePos.
        //If not update tree at segTreePos and mark its children for lazy propagation.
        if (lazy[segTreePos] != 0) {
            segTreeArr[segTreePos] += (operation.getLazyMultiplier(startIndex, endIndex) * lazy[segTreePos]);
            if (startIndex != endIndex) { //if not leaf
                lazy[2 * segTreePos + 1] += lazy[segTreePos];
                lazy[2 * segTreePos + 2] += lazy[segTreePos];
            }
            lazy[segTreePos] = 0;
        }

        //if no overlap, return
        if (updStart > endIndex || updEnd < startIndex) return;

        //if complete overlap, update value here and update lazy too
        if (updStart <= startIndex && updEnd >= endIndex) {
            segTreeArr[segTreePos] += (operation.getLazyMultiplier(startIndex, endIndex) * delta);
            if (startIndex != endIndex) { //If not leaf
                lazy[2 * segTreePos + 1] += delta;
                lazy[2 * segTreePos + 2] += delta;
            }
            return;
        }

        //if partial overlap, recur for children
        int mid = (startIndex + endIndex) / 2;
        updateSegmentTreeRangeLazy(segTreeArr, 2 * segTreePos + 1, lazy, startIndex, mid, updStart, updEnd, delta, operation);
        updateSegmentTreeRangeLazy(segTreeArr, 2 * segTreePos + 2, lazy, mid + 1, endIndex, updStart, updEnd, delta, operation);
        segTreeArr[segTreePos] = operation.perform(segTreeArr[2 * segTreePos + 1], segTreeArr[2 * segTreePos + 2]);
    }

    public void printTree(int[] segmentTree) {
        if(null == segmentTree || segmentTree.length ==0) System.out.println("No tree exists to print");

        printTree(segmentTree, 2, true, "");
        System.out.println(segmentTree[0]);
        printTree(segmentTree, 1,false, "");
    }

    private void printTree(int[] segmentTree, int segTreeIndex, boolean isRight, String indent) {
        if(segTreeIndex >= segmentTree.length || segmentTree[segTreeIndex] == Integer.MAX_VALUE) return;

        printTree(segmentTree, 2*segTreeIndex+2, true, indent + (isRight ? "        " : " |      ") );
        System.out.print(indent);
        if(isRight) System.out.print(" /");
        else System.out.print(" \\");
        System.out.print("-----  ");
        System.out.println(segmentTree[segTreeIndex]);
        printTree(segmentTree, 2*segTreeIndex+1, false, indent + (isRight ? " |      " : "        "));
    }

    static interface Operation {
        public int perform(int a, int b);

        public int getLazyMultiplier(int startIndex, int endIndex);
    }

    static class AddOperation implements Operation {
        @Override
        public int perform(int a, int b) {
            return a + b;
        }

        @Override
        public int getLazyMultiplier(int startIndex, int endIndex) {
            return (endIndex - startIndex + 1);
        }
    }

    static class MinOperation implements Operation {
        @Override
        public int perform(int a, int b) {
            return Math.min(a, b);
        }

        @Override
        public int getLazyMultiplier(int startIndex, int endIndex) {
            return 1;
        }
    }
}


