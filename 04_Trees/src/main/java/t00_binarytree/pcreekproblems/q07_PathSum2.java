package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem:
 *  Given a binary tree and a sum, find all root-to-leaf paths
 *  where each path's sum equals the given sum.
 */
public class q07_PathSum2 {
    public static void main(String[] args) {
        /*
         *           5
         *          / \
         *         4   8
         *        /   / \
         *       11  13  4
         *      /  \    / \
         *     7    2  5   1
         */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q07_PathSum2 ps = new q07_PathSum2();
        System.out.printf("Path for sum %d is %s\n", 22, ps.getPathSum1(root, 22));
        System.out.printf("Path for sum %d is %s\n", 22, ps.getPathSum2Rec(root, 22));
    }

    /**
     * Approach:
     *  Recursion based approach
     *
     * Complexity:
     *  Time Complexity is O(n) + O(p*h) ,p is the number of root to leaf paths and h is the ht of tree
     *      In perfect binary tree, p = 2^h = n/2 and h = log n
     *      = O(n) + O( n/2 * log n) = O(n log n)
     *  Space Complexity is O(p*h) = O(n log n) in worst case
     */
    public List<List<Integer>> getPathSum1(TreeNode<Integer> root, int expectedSum) {
        List<List<Integer>> paths = new ArrayList<>();
        if(root == null) return paths;

        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        stack.push(root);
        int sum = root.data;

        TreeNode<Integer> current = root, prev = null;
        while (!stack.isEmpty()) {
            current = stack.peek();

            if(prev == null || current == prev.left || current == prev.right) {
                if(current.left != null) {
                    stack.push(current.left);
                    sum += current.left.data;
                } else if (current.right != null) {
                    stack.push(current.right);
                    sum += current.right.data;
                }
            } else if (prev == current.left) {
                if (current.right != null) {
                    stack.push(current.right);
                    sum += current.right.data;
                }
            } else {
                //leaf nodes come here
                if(sum == expectedSum && current.left == null && current.right == null) {
                    paths.add(stack.stream().map(t -> t.data).collect(Collectors.toList()));
                }

                stack.pop();
                sum -= current.data;
            }

            prev = current;
        }
        return paths;
    }

    /**
     * Approach:
     *  Recursion based approach
     *
     * Complexity:
     *  Time Complexity is O(n) + O(p*h) ,p is the number of root to leaf paths and h is the ht of tree
     *      In perfect binary tree, p = 2^h = n/2 and h = log n
     *      = O(n) + O( n/2 * log n) = O(n log n)
     *  Space Complexity is O(p*h) = O(n log n) in worst case
     */
    public List<List<Integer>> getPathSum2Rec(TreeNode<Integer> root, int expectedSum) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> tempList = new ArrayList<>(); //Removal is O(1) while LinkedList is O(n)
        getPathSum2Rec(root, expectedSum, tempList, result);
        return result;
    }

    private void getPathSum2Rec(TreeNode<Integer> root, int expectedSum, List<Integer> currentResult,
                                               List<List<Integer>> result) {
        if(root == null) return;

        currentResult.add(root.data);

        if(root.data == expectedSum && root.left == null && root.right == null) {
            result.add(new ArrayList(currentResult)); //O(p * h) h will be size of array list and it might be added p times in worst case
        } else {
            //O(n) since it visits each of the tree node
            getPathSum2Rec(root.left, expectedSum - root.data, currentResult, result);
            getPathSum2Rec(root.right, expectedSum - root.data, currentResult, result);
        }
        currentResult.remove(currentResult.size()-1);
    }
}
