package t02_heap.problems;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem:
 *  Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 *  So the median is the mean of the two middle value.
 *
 * Approaches:
 *  Approach #1 Simple Sorting - Store the numbers in a resize-able container.
 *  Every time you need to output the median, sort the container and output the median.
 *      Time complexity: O(n⋅log(n))+O(1) ≃ O(n⋅log(n))
 *          Adding a number takes amortized O(1) time for a container with an efficient resizing scheme.
 *          Finding the median is primarily dependent on the sorting. This takes O(n⋅log(n)) time for a standard comparative sort.
 *      Space complexity: O(n) linear space to hold input in a container. No extra space needed since sorting can  be done in-place
 *  Approach #2 Insertion Sort - Keeping our input container always sorted - insertion sort!
 *  When a new number comes,find the correct place to insert the incoming number, using a binary search and once the
 *  position is found, we need to shift all higher elements by one space to make room for the incoming number.
 *      Time complexity: O(n) + O(log(n)) ≈ O(n). Binary Search takes O(log(n)) time to find correct insertion position.
 *          Insertion can take up to O(n) time since elements have to be shifted inside the container to make room for the new element.
 *      Space complexity: O(n)O(n)O(n) linear space to hold input in a container. No extra space other than that needed (since sorting can usually be done in-place).
 *  Approach #3 Two Heaps
 *      Time complexity: O(5*log(n)) + O(1) ≈ O(log(n)). At worst, there are three heap insertions and two heap deletions from the top.
 *          Each of these takes about O(log(n)) time. Finding the mean takes constant O(1) time since the tops of heaps are directly accessible.
 *      Space complexity: O(n) linear space to hold input in containers.
 *
 *  Approach #4 Multiset and Two Pointers - Self-balancing Binary Search Trees (like an AVL Tree) have some very interesting properties.
 *  The median always winds up in the root of the tree and/or one of its children.
 *      Time complexity: O(log(n)) + O(1) ≈ O(log(n)). Inserting a number takes O(log(n)) time for a standard multiset scheme.
 *          Finding the mean takes constant O(1) time since the median elements are directly accessible from the two pointers.
 *      Space complexity: O(n) linear space to hold input in container.
 *
 * Links:
 *  Different Approaches - https://leetcode.com/problems/find-median-from-data-stream/solution/
 *  Solution 1 - http://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream-java/
 *
 * Complexity:
 *  Time - O(log n)
 *  Space - O(n)
 */
public class MedianFromDataStream<T extends Number & Comparable> {
    private PriorityQueue<T> minHeap;
    private PriorityQueue<T> maxHeap;

    public static void main(String[] args) {
        MedianFromDataStream mfds = new MedianFromDataStream();
        mfds.addNum(1);
        mfds.addNum(2);
        System.out.println(mfds.findMedian()); //1.5
        mfds.addNum(3);
        System.out.println(mfds.findMedian()); //2
    }

    public MedianFromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Adds a number into the data structure.
    public void addNum(T data) {
        maxHeap.offer(data);
        minHeap.offer(maxHeap.poll());

        if(maxHeap.size() < minHeap.size())
            maxHeap.offer(minHeap.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(maxHeap.size() == minHeap.size())
            return ( (maxHeap.peek().doubleValue() + minHeap.peek().doubleValue()) / 2);
        else
            return maxHeap.peek().doubleValue();
    }
}
