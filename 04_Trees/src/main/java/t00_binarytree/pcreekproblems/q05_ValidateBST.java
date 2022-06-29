package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Links:
 *  https://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/
 */
public class q05_ValidateBST {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
        */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q05_ValidateBST vb = new q05_ValidateBST();
        System.out.println("Is valid BST: " + vb.isValidBSTRec(root));
        System.out.println("Is valid BST: " + vb.isValidBSTRec2(root));
        System.out.println("Is valid BST: " + vb.isValidBST3(root));



        /* Constructed binary tree is
                  4
                /   \
               2     5
             /  \
            1    3
        */
        Integer[] data2 = new Integer[] {4, 2, 5, 1, 3};
        TreeNode<Integer> root2 = tree.buildTree(data2);
        TreeUtils.printTree(root2);
        System.out.println("Is valid BST: " + vb.isValidBSTRec(root2));
        System.out.println("Is valid BST: " + vb.isValidBSTRec2(root2));
        System.out.println("Is valid BST: " + vb.isValidBST3(root2));
    }

    /**
     * Approach:
     *  Recursive - All values on the left sub tree must be less than root, and all values on the right sub tree
     *  must be greater than root. So we just check the boundaries for each node.
     *
     *   *If the violation occurs close to the root but on the right subtree, the method still cost O(n)*
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), h is the ht of the stack
     */
    public boolean isValidBSTRec(TreeNode<Integer> rootNode) {
        return isValidHelper(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidHelper(TreeNode<Integer> rootNode, Integer min, Integer max) {
        if(rootNode == null) return true;

        if(rootNode.data < min || rootNode.data > max) return false; //Out of range

        return isValidHelper(rootNode.left, min, rootNode.data) &&
                isValidHelper(rootNode.right, rootNode.data, max);
    }

    /**
     * Approach:
     *  Recursive - The second solution below can handle violations close to root node faster.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), h is the ht of the stack
     */
    public boolean isValidBSTRec2(TreeNode<Integer> rootNode) {
        if(rootNode == null) return true;
        return isValidHelper2(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidHelper2(TreeNode<Integer> rootNode, Integer min, Integer max) {
        if(rootNode.data < min || rootNode.data > max) return false;

        if(rootNode.left != null && isValidHelper2(rootNode.left,min, rootNode.data) == false)
            return false;

        if(rootNode.right != null && isValidHelper2(rootNode.right, rootNode.data, max) == false)
            return false;

        return true;
    }

    /**
     * Approach:
     *  Iterative
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), h is the ht of the stack
     */
    public boolean isValidBST3(TreeNode<Integer> rootNode) {
        Queue<TreeNode<Integer>> nodeQueue = new LinkedList<>();
        Queue<Integer[]> minMaxQueue = new LinkedList<>();

        nodeQueue.add(rootNode);
        minMaxQueue.add(new Integer[] {Integer.MIN_VALUE, Integer.MAX_VALUE});

        while (!nodeQueue.isEmpty()) {
            rootNode = nodeQueue.poll();
            Integer[] minMax = minMaxQueue.poll();

            if(rootNode.data < minMax[0] || rootNode.data > minMax[1]) return false;

            if(rootNode.left != null) {
                nodeQueue.add(rootNode.left);
                minMaxQueue.add(new Integer[]{minMax[0], rootNode.data});
            }

            if(rootNode.right != null) {
                nodeQueue.add(rootNode.right);
                minMaxQueue.add(new Integer[]{rootNode.data, minMax[1]});
            }
        }
        return true;
    }
}
