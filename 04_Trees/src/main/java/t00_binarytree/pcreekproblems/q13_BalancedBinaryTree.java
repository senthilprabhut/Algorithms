package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

/**
 * Problem:
 *  Given a binary tree, determine if it is height-balanced.
 *  a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Links:
 *  https://www.programcreek.com/2013/02/leetcode-balanced-binary-tree-java/
 */

public class q13_BalancedBinaryTree {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
           /
          6
        */
        q13_BalancedBinaryTree bbt = new q13_BalancedBinaryTree();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5,null, null, 6};
        TreeNode<Integer> root = tree.buildTree(data);
        System.out.println("Is balanced tree: " + bbt.isBalanced(root)); //false

        Integer[] data2 = new Integer[] {1, 2, 3, 4, 5,6};
        TreeNode<Integer> root2 = tree.buildTree(data2);
        System.out.println("Is balanced tree: " + bbt.isBalanced(root2)); //true
    }

    public boolean isBalanced(TreeNode<Integer> root) {
        if(root == null) return true;

        if( height(root) == Integer.MIN_VALUE ) return false; //If the diff is > 1

        return true;
    }

    private int height(TreeNode<Integer> root) {
        if(root == null) return -1;

        int left = height(root.left);
        int right = height(root.right);

        if(left == Integer.MIN_VALUE || right == Integer.MIN_VALUE || Math.abs(left - right) > 1)
            return Integer.MIN_VALUE;

        return 1 + Math.max(left, right); //Leaf has height 0
    }
}
