package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

/**
 * Problem:
 *  Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *  Find the total sum of all root-to-leaf numbers.
 *       1
 *      / \
 *     2   3
 *
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-sum-root-to-leaf-numbers-java/
 *
 */
public class q22_SumRootToLeafNumbers {
    public static void main(String[] args) {
        /* Constructed binary tree is
                 1
               /  \
              2    3
             / \
            4  5
         */
        q22_SumRootToLeafNumbers srtl = new q22_SumRootToLeafNumbers();

        Integer[] data = new Integer[]{1, 2, 3, 4, 5};
        BinaryTree<Integer> tree = new BinaryTree<>();
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        System.out.println("The sum is " + srtl.sumNumbers(root, 0));

    }

    /**
     * Approach:
     *  DFS recursive approach
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n)
     */
    public int sumNumbers(TreeNode<Integer> root, int sum) {
        if(root == null) return 0;

        int currentSum = sum * 10 + root.data;

        //If leaf node
        if(root.left == null && root.right == null)
            return currentSum;

        //If not leaf
        return sumNumbers(root.left, currentSum) + sumNumbers(root.right, currentSum);
    }
}
