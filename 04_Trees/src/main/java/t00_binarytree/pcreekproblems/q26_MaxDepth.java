package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

/**
 * Problem:
 *  Given a binary tree, find its maximum depth.
 *  The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-maximum-depth-of-binary-tree-java/
 */
public class q26_MaxDepth {
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
        q26_MaxDepth md = new q26_MaxDepth();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5,null, null, 6};
        TreeNode<Integer> root = tree.buildTree(data);
        System.out.println("Max depth of tree is " + md.maxDepth(root)); //4
    }

    /**
     * Approach:
     *  Similar to the Min Depth question - do DFS and
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n)
     */
    public int maxDepth(TreeNode<Integer> rootNode) {
        if(rootNode == null) return 0;

        int left = maxDepth(rootNode.left);
        int right = maxDepth(rootNode.right);
        return 1 + Math.max(left,right);
    }

}
