package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 *  Given a binary tree, return all root-to-leaf paths.
 *  For example, given the following binary tree:
 *
 *     1
 *   /   \
 *  2     3
 *  \
 *   5
 *
 *  All root-to-leaf paths are:
 *  ["1->2->5", "1->3"]
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-binary-tree-paths-java/
 *  https://discuss.leetcode.com/topic/21474/accepted-java-simple-solution-in-8-lines
 */
public class q25_BinaryTreePaths {
    public static void main(String[] args) {
        /* Constructed binary tree is
                 1
               /  \
              2    3
             / \  /
            4  5 6
         */
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6};
        BinaryTree<Integer> tree = new BinaryTree<>();
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q25_BinaryTreePaths paths = new q25_BinaryTreePaths();
        System.out.println("Tree paths are " + paths.getPaths(root));
    }

    /**
     * Approach:
     *  Recursive approach
     *
     * Complexity:
     *  Time Complexity is O(n) since all nodes are traversed once
     *  Space Complexity is O(n) - There are no of paths for no of leaves and total leaves = n/2 nodes. so O(n)
     */
    public List<String> getPaths(TreeNode<Integer> root) {
        List<String> result = new ArrayList<>();
        getPaths(root, "", result);
        return result;
    }

    private void getPaths(TreeNode<Integer> root, String path, List<String> result) {
        if(root.left == null && root.right == null) result.add(path + root.data);
        if (root.left != null) getPaths(root.left, path + root.data + "->", result);
        if (root.right != null) getPaths(root.right, path + root.data + "->", result);
    }
}
