package t02_heap.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Problem:
 *  Given K sorted arrays, merge them into a single sorted array
 *
 * Approaches:
 *  Given k arrays and n is the total elements across all arrays
 *  1. Combine all the arrays into one array and sort it -  kn [copy time] + kn * log(kn) [sorting time] = kn * log(kn)
 *  2. Compare smallest elements in each arrayand copy them to new array. Have an array[k] to track the copied elements' index.
 *     Total k*n elements and k comparisons each time b4 inserting into new array  = kn * k
 *  3. Priority Queues (Heap) - kn elements and log k time to insert each element in t02_heap with t02_heap size k = kn * log k
 *
 * Links:
 *  http://www.geeksforgeeks.org/merge-k-sorted-arrays/
 *  Solution - http://www.programcreek.com/2014/05/merge-k-sorted-arrays-in-java/
 *
 * Complexity:
 *  Time - Height of the tree is log k, where k is the no of arrays. There is a node in t02_heap for each array
 *  Once we remove the min element, we insert another element from the same array - insertion takes O(log n)
 *  Same thing is repeated for all the k*n elements - so total complexity is kn * log(k)
 *
 *  Space - O(kn) which is the size of result array
 */
public class KSortedArrays {
    private class ArrayContainer implements Comparable<ArrayContainer> {
        int[] array;
        int index;

        public ArrayContainer(int[] array, int index) {
            this.array = array;
            this.index = index;
        }

        public int compareTo(ArrayContainer next) {
            return this.array[index] - next.array[next.index];
        }
    }

    public int[] mergeKSortedArray(int[][] arr) {
        //PriorityQueue is t02_heap in Java
        PriorityQueue<ArrayContainer> queue = new PriorityQueue<>();
        int total = 0;

        for(int i=0; i<arr.length; i++) {
            queue.add(new ArrayContainer(arr[i], 0));
            total += arr[i].length;
        }

        int[] result = new int[total];
        int resultIndex = 0;

        //while t02_heap is not empty
        while (!queue.isEmpty()) {
            ArrayContainer ac = queue.poll();
            result[resultIndex++] = ac.array[ac.index];
            if(ac.index < ac.array.length-1) {
                queue.add(new ArrayContainer(ac.array, ac.index+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] arr3 = { 0, 9, 10, 11 };

        KSortedArrays ksa = new KSortedArrays();
        int[] result = ksa.mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
        System.out.println(Arrays.toString(result));
    }
}
