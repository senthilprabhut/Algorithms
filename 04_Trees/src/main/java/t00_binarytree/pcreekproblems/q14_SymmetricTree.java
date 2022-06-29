package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * Links:
 *  https://www.programcreek.com/2014/03/leetcode-symmetric-tree-java/
 */
public class q14_SymmetricTree {
    public static void main(String[] args) {
        /*
                 1
                / \
               2   2
              / \ / \
             3  4 4  3
        */
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] input1 = {1,2, 2, 3, 4, 4, 3};
        TreeNode<Integer> root1 = tree.buildTree(input1);
        TreeUtils.printTree(root1);

        q14_SymmetricTree st = new q14_SymmetricTree();
        System.out.println("Is Symmetric: " + st.isSymmetric1Rec(root1));
        System.out.println("Is Symmetric: " + st.isSymmetric2(root1));
        System.out.println("Is Symmetric: " + st.isSymmetric3(root1));
        /*
             1
            / \
           2   2
           \   \
           3    3
        */
        Integer[] input2 = {1,2, 2, null, 3, null, 3};
        TreeNode<Integer> root2 = tree.buildTree(input2);
        TreeUtils.printTree(root2);
        System.out.println("Is Symmetric: " + st.isSymmetric1Rec(root2));
        System.out.println("Is Symmetric: " + st.isSymmetric2(root2));
        System.out.println("Is Symmetric: " + st.isSymmetric3(root2));
    }

    /**
     * Approach:
     *  The key is finding the conditions that return false, such as value is not equal,
     *  only one node(left or right) has value.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h)
     */
    public boolean isSymmetric1Rec(TreeNode<Integer> rootNode) {
        return rootNode==null || isSymmetric1Rec(rootNode.left, rootNode.right);
    }
    private boolean isSymmetric1Rec(TreeNode<Integer> left, TreeNode<Integer> right) {
        if(left == null || right == null) return left == right; //if one of them is null
        if(left.data != right.data) return false; //if the data don't match

        return isSymmetric1Rec(left.left, right.right) && isSymmetric1Rec(left.right, right.left);
    }

    /*
     * Approach:
     *  One Stack - Inorder traversal of the left and right subtrees and compare the nodes
     *
     * Links:
     *  https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h)
     */
    public boolean isSymmetric2(TreeNode<Integer> rootNode) {
        if(rootNode == null) return true;
        if(rootNode.left == null || rootNode.right == null) return rootNode.left == rootNode.right;

        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        TreeNode<Integer> leftTree = rootNode.left, rightTree = rootNode.right;
        while (!stack.isEmpty() || leftTree != null) {
            if(leftTree != null) {
                if(rightTree != null) {
                    stack.push(leftTree);
                    stack.push(rightTree);

                    leftTree = leftTree.left;
                    rightTree = rightTree.right;
                } else {
                    return false; //left is not null and right is null
                }
            } else {
                if(rightTree == null) {
                    leftTree = stack.pop();
                    rightTree = stack.pop();

                    if(leftTree.data != rightTree.data) return false; //both nodes didn't match data

                    leftTree = leftTree.right;
                    rightTree = rightTree.left;
                } else {
                    return false; //left is null and right is not null
                }
            }
        }
        return true; //if the whole left & right subtree is traversed
    }

    /**
     * Approach:
     *  BFS Approach - with one queue
     *
     * Links:
     *  https://discuss.leetcode.com/topic/16889/short-and-clean-java-iterative-solution
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h)
     */
    public boolean isSymmetric3(TreeNode<Integer> rootNode) {
        if(rootNode == null) return true;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(rootNode.left);
        q.add(rootNode.right);
        while (q.size() > 1) {
            TreeNode<Integer> left = q.poll(), right = q.poll();
            if (left == null || right == null) return left == right;
            if(left.data != right.data) return false; //both nodes didn't match data

            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true; //if the whole left & right subtree is traversed
    }
}
