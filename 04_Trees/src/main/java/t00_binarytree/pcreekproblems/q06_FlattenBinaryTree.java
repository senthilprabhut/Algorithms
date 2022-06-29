package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem:
 *  Given a binary tree, flatten it to a linked list in-place.
 *
 * Links:
 *  https://www.programcreek.com/2013/01/leetcode-flatten-binary-tree-to-linked-list/
 *
 */
public class q06_FlattenBinaryTree<T> {
    public static void main(String[] args) {
        /* Constructed binary tree is
         *           1
         *          / \
         *         2   5
         *        / \   \
         *       3   4   6
         */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 5, 3, 4, null, 6};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q06_FlattenBinaryTree<Integer> flatten = new q06_FlattenBinaryTree<>();
        flatten.flattenTreeRec(root);
        TreeUtils.printTree(root);
    }

    /**
     * Approach:
     *  Go down through the left, when right is not null, push right to stack.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h) = O(log n), h is the ht of the tree
     */
    public void flattenTree(TreeNode<T> rootNode) {
        if(rootNode == null) return;;

        Deque<TreeNode<T>> nodeStack = new LinkedList<>();
        TreeNode<T> current = rootNode;

        while (current != null || !nodeStack.isEmpty()) {

            if(current.right != null)
                nodeStack.push(current.right);

            if(current.left != null) {
                current.right = current.left;
                current.left = null;
            } else if (!nodeStack.isEmpty()){
                current.right = nodeStack.pop();
            }
            current = current.right;
        }
    }

    /**
     * Approach:
     *  Recursion - flatten left, flatten right, then move left tree to right,
     *  move right tree to last of the left tree
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h) = O(log n), h is the ht of the tree
     */
    public void flattenTreeRec(TreeNode<T> rootNode) {
        flattenTreeHelper(rootNode);
    }

    public TreeNode<T> flattenTreeHelper(TreeNode<T> node) {
        if(node == null) return null;

        TreeNode<T> lastLeft = flattenTreeHelper(node.left);
        TreeNode<T> lastRight = flattenTreeHelper(node.right);

        if(lastLeft == null && lastRight == null) return node; //both null - leaf node
        if(lastLeft == null) return lastRight; //Rt is already the right side node and left is already null

        if (lastRight == null) { //only left tree is present
            node.right = node.left;
            node.left = null;
            return lastLeft;
        }

        //Both last left and last right are present
        lastLeft.right = node.right; //Point last of left to start of right Right
        node.right = node.left; //point current right to start of Left
        node.left = null;
        return lastRight;
    }
}
