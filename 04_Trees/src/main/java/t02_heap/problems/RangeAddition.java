package t02_heap.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem:
 *  Assume you have an array of length n initialized with all 0's and are given k update operations.
 *  Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of
 *  subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 *  Return the modified array after all k operations were executed.
 *
 * Approaches:
 *  Approach 3: Lazy Segment Tree which takes O(n) time to build the tree and answers query in O(log n) time
 *      and requires O(n) space
 *  Approach 4: Matrix requires O(n^2) time to build but answers query in O(1) time. Also requires O(n^2) extra space
 *  Approach 5: scan array and add for range in update operation. This takes O(kn) time and O(1) space
 *
 * Links:
 *  https://leetcode.com/problems/range-addition
 *  http://www.programcreek.com/2014/07/leetcode-range-addition-java/
 *  http://www.geeksforgeeks.org/constant-time-range-add-operation-array/
 *
 * Complexity:
 *  n is array length, k is no of update operations
 *  Solution 1 (prefix sum array) Time - O(n)
 *  Solution 1 Space - O(n)
 *  Solution 2 (Heap) Time - O(nlogn)
 *  Solution 2 Space - O(n)
 */
public class RangeAddition {
    public static void main(String[] args) {
        int length = 5;
        int[][] updates = {
                {1, 3, 2},
                {2, 4, 3},
                {0, 2, -2}
        };
        //output is [-2, 0, 3, 5, 3]
        RangeAddition ra = new RangeAddition();
        System.out.println(Arrays.toString(ra.getModifiedArray2(length, updates)));
    }

    /*
     * The hint suggests us that we only needs to modify the first and last element of the range.
     * In fact, we need to increment the first element in the range and decreases the last element + 1
     * (if it's within the length) by inc.
     * Why does this work? When we sum up the array, the increment is passed along to the subsequent elements until the last element.
     * When we decrement the end + 1 index, we offset the increment so no increment is passed along to the next element.
     */
    public int[] getModifiedArray1(int length, int[][] updates) {
        //Validations
        if (length < 0 || updates == null || updates.length == 0) return null;
        if (updates[0] == null || updates[0].length != 3) return null;

        int[] result = new int[length];
        for (int i = 0; i < updates.length; i++) {
            int startIndex = updates[i][0], endIndex = updates[i][1], value = updates[i][2];
            result[startIndex] += value;
            if (endIndex < length - 1)
                result[endIndex + 1] -= value; //subtract from end+1 index
        }

        int sum = 0;
        for (int j = 0; j < result.length; j++) {
            sum += result[j];
            result[j] = sum;
        }
        return result;
    }

    /*
     * Uses t02_heap
     */
    public int[] getModifiedArray2(int length, int[][] updates) {
        int result[] = new int[length];
        if (updates == null || updates.length == 0)
            return result;

        // Sort updates array by starting index
        Arrays.sort(updates, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        // Create a t02_heap sorted by ending index - Integer here is the individual update array index
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return updates[a][1] - updates[b][1];
            }
        });

        int sum = 0, updArrIndex = 0;
        //Sum is calculated for each index of results and stored in results
        for (int i = 0; i < length; i++) {
            //add value to sum when starting index is reached. updArray[x][0] is startIndex
            while (updArrIndex < updates.length && updates[updArrIndex][0] <= i) {
                sum = sum + updates[updArrIndex][2];
                queue.offer(updArrIndex); //Add this to t02_heap after processing startIndex index
                updArrIndex++;
            }

            //substract value from sum when ending index is reached
            //When i > endIndex, we have crossed the endIndex and it is safe to subract the value from sum
            while (!queue.isEmpty() && i > updates[queue.peek()][1]) {
                int top = queue.poll();
                sum -= updates[top][2];
            }
            result[i] = sum;
        }
        return result;
    }
}
