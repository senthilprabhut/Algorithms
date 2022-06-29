package t01_bst.pcreekproblems;

import datastructure.TreeNode;
import utils.TreeUtils;

/**
 * Problem:
 *  Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * Links:
 *  https://www.programcreek.com/2013/01/leetcode-convert-sorted-array-to-binary-search-tree-java/
 */
public class q09_SortedArrayToBST {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 5, 6, 9 ,12, 18};

        q09_SortedArrayToBST atb = new q09_SortedArrayToBST();
        TreeUtils.printTree(atb.sortedArrayToBST(input));
        TreeUtils.printTree(atb.sortedArrayToBST2(input));
    }

    /**
     * Approach:
     *  A typical DFS problem using recursion
     *  Pre-order like approach - build root and then build left tree and right tree
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is  O(h) = O(log n)
     */
    public TreeNode<Integer> sortedArrayToBST(int[] array) {
        return sortedArrayToBST(array, 0, array.length-1);
    }

    private TreeNode<Integer> sortedArrayToBST(int[] array, int start, int end) {
        if(start > end) return null;

        int mid = (start + end) / 2;
        TreeNode<Integer> root = new TreeNode<>(array[mid]);
        root.left = sortedArrayToBST(array, start, mid-1);
        root.right = sortedArrayToBST(array, mid+1, end);
        return root;
    }

    /**
     * Approach:
     *  In-order like approach - build left, root and then right tree
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is  O(h) = O(log n)
     */
    public TreeNode<Integer> sortedArrayToBST2(int[] array) {
        int[] index = new int[] {0}; //using array since index has to be transferred across recursive calls
        return sortedArrayToBST2(array, index, 0, array.length-1);
    }

    private TreeNode<Integer> sortedArrayToBST2(int[] array, int[] index, int start, int end) {
        if(start > end) return null;

        int mid = (start + end) / 2;
        //Build left subtree
        index[0]++;
        TreeNode<Integer> left = sortedArrayToBST2(array, index, start, mid-1);

        TreeNode<Integer> root = new TreeNode<>(array[mid]);

        //Build right subtree
        index[0]++;
        TreeNode<Integer> right = sortedArrayToBST2(array, index, mid+1, end);

        root.left = left;
        root.right = right;
        return root;
    }
}
