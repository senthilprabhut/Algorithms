package datastructure;

import bits.LogBase2;

import java.util.Arrays;

/**
 * Heap
 * Heap is an almost complete binary tree. You cannot go to next level before filling the current level.
 * Root index =1, children: Left=2i, Right = 2i + 1, Parent = i/2
 * <p>
 * Links:
 * https://www.youtube.com/watch?v=EuUBxM_E03E
 * Solution 1: https://github.com/vivekanand44/codes-Youtube-videos/blob/master/heap%20sort.cpp
 * Solution 2:
 * <p>
 * Complexity:
 * Time - O(n logn) ??
 */
public class HeapSortOld {
    public static void main(String[] args) {
        HeapSortOld hs = new HeapSortOld();

        int[] binaryTree = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("Initial array is " + Arrays.toString(binaryTree));
        hs.heapSort(binaryTree);
        System.out.println("Sorted array is " + Arrays.toString(binaryTree));
        ;

    }

    public void heapSort(int[] input) {
        int totalNodes = input.length;
        while (totalNodes >= 1) {
            if (totalNodes == 1) {
                System.out.printf("%d\n", input[0]);
                System.out.println("Last node reached...");
                break;
            }
            maxHeapify(input, totalNodes);

            //check whether the array is a t02_heap or not
            boolean isHeap = checkMaxHeap(input, totalNodes);
            if (isHeap == true) {
                System.out.printf("%d, ", input[0]);
                //store the min value in the last index and maxHeapify again
                swapValues(input, 0, totalNodes - 1);
                totalNodes = totalNodes - 1;
            }
        }
    }

    private void maxHeapify(int[] input, int totalNodes) {
        boolean isHeap = false;
        while (isHeap == false) {
            //For eg, if we have total 6 element , then second_last_level will be log(6)=2.58 => integer 2
            int lastButOneLevel = LogBase2.getLogBase2(totalNodes); // go to the second-last level(level above leaf nodes)
            int lastIndexForCurrentLevel = (int) (Math.pow(2, lastButOneLevel) - 2); //Level 2 had end index of (2^2)-2 = 4 - 2 = 2

            for (int current = lastIndexForCurrentLevel; current >= 0; current--) {
                //If left or right child exists
                if (2 * current + 1 <= totalNodes - 1 || 2 * current + 2 <= totalNodes - 1) {
                    //If left child exists, its value should be greater than the current node's value. Otherwise swap values
                    if (2 * current + 1 <= totalNodes - 1 && input[current] < input[2 * current + 1]) {
                        swapValues(input, current, 2 * current + 1);
                    }

                    //If right child exists, its value should be greater than current node's value. Otherwise swap values
                    if (2 * current + 2 <= totalNodes - 1 && input[current] < input[2 * current + 2]) {
                        swapValues(input, current, 2 * current + 2);
                    }
                }
            }
            isHeap = checkMaxHeap(input, totalNodes);//check whether the array is a t02_heap or not
        }
        //Complete once correct t02_heap is generated
    }

    private boolean checkMaxHeap(int[] value, int totalNodes) {
        int lastButOneLevel = LogBase2.getLogBase2(totalNodes);
        int checkElementsUptoIndex = (int) (Math.pow(2, lastButOneLevel) - 2);

        for (int parent = checkElementsUptoIndex; parent >= 0; parent--) {
            //if current element has left child, its value should be less than that of left child
            if (2 * parent + 1 <= totalNodes - 1 && value[parent] < value[2 * parent + 1]) {
                return false;
            }
            //if current element has right child, its value should be less than that of right child
            if (2 * parent + 2 <= totalNodes - 1 && value[parent] < value[2 * parent + 2]) {
                return false;
            }
        }
        return true;
    }


    private void swapValues(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

}
