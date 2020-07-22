/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 */

package t00_binarytree.pcreekproblems;

import java.util.ArrayList;
import java.util.List;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

/**
 * Problem:
 *  The key to solve this problem is converting the problem to be finding the index of the element in the result list.
 *  Then this is a typical DFS problem on trees.
 *
 * Links:
 *  https://leetcode.com/problems/find-leaves-of-binary-tree
 *  https://www.programcreek.com/2014/07/leetcode-find-leaves-of-binary-tree-java/
 *  https://discuss.leetcode.com/topic/49194/10-lines-simple-java-solution-using-recursion-with-explanation
 */
public class q31_FindLeaves {
    public static void main(String[] args) {
        q31_FindLeaves fl = new q31_FindLeaves();

        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
           /
          6
        */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5,null, null, 6};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        System.out.println(fl.findLeaves(root));
    }

    /**
     * Approach:
     *  For this question we need to take bottom-up approach.
     *  The key is to find the height of each node. Here the definition of height is:
     *  The height of a node is the number of edges from the node to the deepest leaf.
     *
     */
    public List<List<Integer>> findLeaves(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        height(root,result);
        return result;
    }

    private int height(TreeNode<Integer> root, List<List<Integer>> result) {
        if(root == null)
            return -1;

        int leftLevel = height(root.left, result);
        int rightLevel = height(root.right, result);
        int level = Math.max(leftLevel,rightLevel) + 1;

        if(result.size() == level) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(root.data);
        root.left = root.right = null;
        return level;
    }
}
