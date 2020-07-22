package t00_binarytree.pcreekproblems;


import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Given a binary tree, find its minimum depth.
 *  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Links:
 *  https://www.programcreek.com/2013/02/leetcode-minimum-depth-of-binary-tree-java/
 *  http://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/
 */
public class q11_MinDepth {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
        */
        q11_MinDepth md = new q11_MinDepth();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5};
        TreeNode<Integer> root = tree.buildTree(data);
        System.out.println("Min depth of tree is " + md.minDepth1(root)); //2
        System.out.println("Min depth of tree is " + md.minDepth2(root)); //2
    }

    /**
     * Approach:
     *  Do Level Order Traversal. While doing traversal, returns depth of the first encountered leaf node.
     *  The 2nd method may end up with complete traversal of Binary Tree even when the topmost leaf is close to root.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n)
     */
    public int minDepth1(TreeNode<Integer> rootNode) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(rootNode);

        int levelCount = 1, currentCount = 1, depth = 1;
        while (!queue.isEmpty()) {
            rootNode = queue.poll();
            levelCount--;

            if(rootNode.left == null || rootNode.right == null)
                return depth;

            if(rootNode.left != null) {
                queue.offer(rootNode.left);
                currentCount++;
            }
            if(rootNode.right != null) {
                queue.offer(rootNode.right);
                currentCount++;
            }

            if(levelCount==0) {
                levelCount=currentCount;
                currentCount=0;
                depth++;
            }
        }
        return 0;
    }

    /**
     * Approach:
     *  The idea is to traverse the given Binary Tree. For every node, check if it is a leaf node.
     *  If yes, then return 1.
     *  Else recur for both left and right subtrees and then take the minimum of two heights.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n)
     */
    public int minDepth2(TreeNode<Integer> rootNode) {
        if(rootNode == null) return 0;

        // Base case : Leaf Node. This accounts for depth = 1.
        if(rootNode.left == null && rootNode.right == null) return 1;

        return Math.min(minDepth2(rootNode.left), minDepth2(rootNode.right)) + 1;
    }
}
