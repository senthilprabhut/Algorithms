package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem:
 *  Given a binary tree, find the maximum path sum.
 *  The path may start and end at any node in the tree.
 *  The path must contain at least one node and does not need to go through the root.
 *
 * Links:
 *  https://www.programcreek.com/2013/02/leetcode-binary-tree-maximum-path-sum-java/
 */
public class q12_MaxPathSum {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               3     2
             /  \
            4    5
        */
        q12_MaxPathSum mps = new q12_MaxPathSum();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 3, 2, 4, 5};
        TreeNode<Integer> root = tree.buildTree(data);
        System.out.println("Max path sum of tree is " + mps.maxPathSum(root)); //12

    }

    /**
     * Approach:
     *  Recursively solve this problem
     *  Similar to Diameter of tree
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n) = O(h)
     */
    public int maxPathSum(TreeNode<Integer> root) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);

        maxPathSumHelper(root, stack);
        return stack.pop();
    }
    private int maxPathSumHelper(TreeNode<Integer> root, Deque<Integer> maxSumStack) {
        if(root == null) return 0;

        int leftSum = maxPathSumHelper(root.left, maxSumStack);
        int rightSum = maxPathSumHelper(root.right, maxSumStack);

        //Calculate the max and store it in stack
        int maxSum = maxSumStack.pop();
        int currentSum = leftSum + root.data + rightSum;
        maxSumStack.push(Math.max(maxSum,currentSum));

        //Add the current val to left/right max
        return root.data + Math.max(leftSum, rightSum);
    }

}
