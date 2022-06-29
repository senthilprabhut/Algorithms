package datastructure;

import java.util.Arrays;

/**
 * Heap
 *  Heap is an almost complete binary tree. You cannot go to next level before filling the current level.
 *  Assuming Root index =1, children: Left=2i, Right = 2i + 1, Parent = i/2
 *  1 to n/2 are non-eaf nodes, n/2 + 1 to n are leaf nodes
 *      Leaf nodes are t02_heap by default
 *  No of nodes of heightWithDiameter h = n / (2 pow h+1).
 *      if no of nodes=15 and heightWithDiameter=0, no of max nodes of heightWithDiameter = 15/ 2 pow 1 = 7.5 ~= 8
 *
 * Links:
 *  Max-Heapify Algo - https://www.youtube.com/watch?v=2fA1FdxNqiE
 *  Build Heap - https://www.youtube.com/watch?v=HI97KDV23Ig
 *  Extract Max Value - https://www.youtube.com/watch?v=j5ij59EjPh0
 *
 * Complexity:
 * Time
 * ----
 *  Max-Heapify Time:
 *      n is the no of nodes in the tree and h is the heightWithDiameter of the tree
 *      O(log n) = O(h) - Worst case is the root node has to go to leaf. To go one level down, 2 comparisons are required
 *      and a total of 2 * log n comparisons are required till value reaches the leaf.
 *      O(1) - Best case if the tree is already t02_heap and we find it by comparing with left and right.
 *      So 2 comparisons which is O(1)
 *  Build Max Heap Time:
 *      At each heightWithDiameter h, there are [n/ 2^(h+1)] nodes and max heapify is applied for each of them which has complexity O(log n) = O(h)
 *      So in total for the tree, for each heightWithDiameter
 *                log n                         log n                           log n              infinity
 *      Complexity=   ⎲ [n/ 2^(h+1)] * O(h) =   ⎲[n/(2^h * 2^1)]* (C*h) = Cn/2 ⎲h/2^h   <= Cn/2 ⎲h* (1/2)^h
 *                  0 ⎳                        0⎳                             0⎳               0⎳
 *      The above is a harmonic progression and according to Cormen Algo book Appendix A.8, For |x|<1 , k*x^k = x/(1-x)^2
 *      With x = 1/2, above integration evaluates to Cn/2 * [2 / (1-2)^2] = Cn/2 * (2/1) = Cn/2 * 2 = n
 *      Complexity = O(n)
 *
 *  Get Max Value Time:
 *      O(log n) - Part until maxHeapify is constant time and maxHeapify takes o(log n). Total is O(1) + O(log n) = O(log n)
 *
 *  increaseKeyValue Time:
 *      O(log n) - The leaf node compares itself with its parent all the way to the root in worst case. Hence ht of tree is the complexity
 *
 *  Heap Sort Time:
 *      O(n log n) - The time for buildMaxHeap is n + time for calling get max n times is n*log n and so total time is n+nlog n = O(n log n)
 *
 *
 *  Space
 *  -----
 *  Max-Heapify Space:
 *      O(log n) - Since we didn't use extra space and just used constants, the only thing taking space is recursive call
 *      In worst case we are making Log n recursive call, so space complexity is O(log n)
 *
 *  Build Max Heap Space:
 *      O(log n) - Space complexity of BuildMaxHeap is the maximum space taken by any of the Max-Heapify calls and it is Maximum for the root node
 *
 *  Get Max Value Space:
 *      O(log n) - Part until maxHeapify has constant space and maxHeapify takes O(log n) space because of recursion
 *
 *  Heap Sort Space:
 *      O(log n) - Space for build max t02_heap is O(log n) + space for Max Heapify is O(log n)
 */
public class BinaryMaxHeap {

    public static void main(String[] args) {
        BinaryMaxHeap heap = new BinaryMaxHeap();

        int[] binaryTree = {1, 2, 3, 9, 8, 7, 6, 5, 4};
        System.out.println("Initial array is " + Arrays.toString(binaryTree));
        heap.heapSort(binaryTree);
        System.out.println("Sorted array is " + Arrays.toString(binaryTree));

        heap.increaseKeyValue(binaryTree, 4, 12);
    }

    public void printTree(int[] input) {
        int left = (2 * 0) + 1;
        int right = (2 * 0) + 2;
        printTree(input, right, "", true);
        System.out.println(input[0]);
        printTree(input, left, "", false);
    }

    private void printTree(int[] input, int rootIndex, String indent, boolean isRight) {
        if(rootIndex >= input.length) return;

        int left = (2 * rootIndex) + 1;
        int right = (2 * rootIndex) + 2;
        printTree(input, right, indent + (isRight ?  "        " : " |      "), true);
        System.out.print(indent);
        if(isRight)
            System.out.print(" /------");
        else
            System.out.print(" \\------");
        System.out.println(input[rootIndex]);
        printTree(input, left, indent + (isRight ?  " |      " : "        "), false);
    }

    public void heapSort(int[] input) {
        //O(n) for building the maxHeap
        buildMaxHeap(input);

        //O(n * log n) for calling the getMaxValue n times
        for(int i=input.length; i>=1; i--) {
            input[i-1] = getMaxValue(input, i);
        }
    }

    public void buildMaxHeap(int[] input) {
        int n = input.length;
        for(int i=(n/2 - 1); i>=0; i--) {
            maxHeapfy(input, i, n);
        }
    }

    public int getMaxValue(int[] input, int totalLength) {
        if(input.length == 0 || totalLength == 0) throw new RuntimeException("Heap underflow");
        int max = input[0];
        swap(input, 0, totalLength-1);
        maxHeapfy(input, 0, totalLength-1);
        return max;
    }


    public void increaseKeyValue(int[] input, int keyIndex, int newValue) {
        if(newValue < input[keyIndex]) return;

        input[keyIndex] = newValue;

        int parentIndex = (keyIndex-1)/2;
        while(parentIndex >=0 && input[parentIndex] < input[keyIndex]) {
            swap(input,parentIndex, keyIndex);
            keyIndex = parentIndex;
            parentIndex = (keyIndex-1)/2;
        }
    }

    //Time complexity: O(log n), Space Complexity: O(log n)
    public void decreaseKeyValue(int[] input, int keyIndex, int newValue) {
        if(newValue > input[keyIndex]) return;
        input[keyIndex] = newValue;
        maxHeapfy(input, keyIndex, input.length);
    }

    public void maxHeapfy(int[] input, int nodeIndex, int totalLength) {
        int leftChild = 2 * nodeIndex  + 1;
        int rightChild = 2 * nodeIndex + 2;

        int largest = nodeIndex;
        if(leftChild < totalLength && input[leftChild] > input[nodeIndex]) largest=leftChild;
        if(rightChild < totalLength && input[rightChild] > input[largest]) largest=rightChild;

        if(largest != nodeIndex) {
            swap(input, nodeIndex, largest);
            maxHeapfy(input, largest, totalLength);
        }
    }

    private void swap(int[] input, int parent, int child) {
        int temp = input[parent];
        input[parent] = input[child];
        input[child] = temp;
    }
}
