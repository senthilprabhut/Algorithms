package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem:
 *  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that
 *  adding up all the values along the path equals the given sum.
 *
 */
public class q07_PathSum {
    public static void main(String[] args) {
        /*
         *           5
         *          / \
         *         4   8
         *        /   / \
         *       11  13  4
         *      /  \      \
         *     7    2      1
         */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q07_PathSum ps = new q07_PathSum();
        System.out.printf("Has path sum for %d: %b\n", 22, ps.hasPathSum1(root, 22));
        System.out.printf("Has path sum for %d: %b\n", 22, ps.hasPathSum2(root, 22));
    }

    /**
     * Approach:
     *  Post order traversal with sum calculated during traversal
     *
     * Links:
     *  https://discuss.leetcode.com/topic/2455/accepted-by-using-postorder-traversal
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), h is the ht of the tree
     */
    public boolean hasPathSum1(TreeNode<Integer> root, int expectedSum) {
        if(root == null) return false;

        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        stack.push(root);
        int sum = root.data; //visited root, so add the sum

        TreeNode<Integer> current = root, prev = null;
        while (!stack.isEmpty()) {
            current = stack.peek();

            if(prev == null || current == prev.left || current == prev.right) {
                if(current.left != null) {
                    stack.push(current.left);
                    sum += current.left.data;
                }
                else if(current.right != null) {
                    stack.push(current.right);
                    sum += current.right.data;
                }
            } else if (prev == current.left) {
                if(current.right != null) {
                    stack.push(current.right);
                    sum += current.right.data;
                }
            } else {
                //Leaf node will come here - so checking here
                if(sum == expectedSum && current.left == null && current.right == null)
                    return true;

                stack.pop();
                sum -= current.data;
            }
            prev = current;
        }
        return false;
    }

    /**
     * Approach:
     *  Recursive approach
     *
     * Links:
     *  https://www.programcreek.com/2013/01/leetcode-path-sum/
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), h is the ht of the tree
     */
    public boolean hasPathSum2(TreeNode<Integer> root, int expectedSum) {
        if(root == null) return false;

        if(expectedSum == root.data && root.left == null && root.right == null) return true;

        return hasPathSum2(root.left, expectedSum - root.data) || hasPathSum2(root.right, expectedSum - root.data);
    }
}
