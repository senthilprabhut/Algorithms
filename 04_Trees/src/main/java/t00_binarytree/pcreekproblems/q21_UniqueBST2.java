package t00_binarytree.pcreekproblems;

import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 *  Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *  For example, Given n = 3, your program should return all 5 unique BST's shown below.
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *   3      2     1      1   3      2
 *  /      /       \                 \
 *  2     1         2                 3
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-unique-binary-search-trees-ii-java/
 */
public class q21_UniqueBST2 {
    public static void main(String[] args) {
        q21_UniqueBST2 ub2 = new q21_UniqueBST2();
        List<TreeNode<Integer>> trees = ub2.generateTrees(3);
        for(TreeNode<Integer> root : trees){
            TreeUtils.printTree(root);
        }
    }

    /**
     * Approach:
     *  This problem can be solved by recursively forming left and right subtrees.
     *  The different combinations of left and right subtrees form the set of all unique binary search trees.
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<TreeNode<Integer>> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();

        return genTreeRec(1,n);
    }

    private List<TreeNode<Integer>> genTreeRec(int begin, int end) {
        List<TreeNode<Integer>> result = new ArrayList<>();
        if (begin > end) {
            result.add(null);
            return result;
        }

        for(int i=begin; i<=end; i++) {
            List<TreeNode<Integer>> left = genTreeRec(begin, i-1);
            List<TreeNode<Integer>> right = genTreeRec(i+1, end);
            for(TreeNode<Integer> lsubTree : left) {
                for(TreeNode<Integer> rsubTree : right) {
                    TreeNode<Integer> root = new TreeNode<>(i);
                    root.left = lsubTree;
                    root.right = rsubTree;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
