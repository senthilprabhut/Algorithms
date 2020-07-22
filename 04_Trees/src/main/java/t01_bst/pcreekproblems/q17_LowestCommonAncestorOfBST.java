package t01_bst.pcreekproblems;

import t01_bst.BSTSimple;
import t01_bst.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Problem:
 *  Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * Links:
 *  https://www.programcreek.com/2014/07/leetcode-lowest-common-ancestor-of-a-binary-search-tree-java/
 */
public class q17_LowestCommonAncestorOfBST {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  5
               /    \
              1     18
              \     /
              3     9
              /   /  \
             2   6   12
        */
        BSTSimple<Integer> tree = new BSTSimple<>(Comparator.comparingInt(a -> a));
        List<Integer> input = new ArrayList(){ {add(5); add(18); add(9); add(6); add(12); add(1); add(3); add(2);} };
        TreeNode<Integer> rootNode = tree.add(null, input);

        q17_LowestCommonAncestorOfBST lca = new q17_LowestCommonAncestorOfBST();
        System.out.printf("The lowest common ancestor of %d and %d is %d\n", 6, 18, lca.lowestCommonAncestor1(rootNode, 6, 18));
        System.out.printf("The lowest common ancestor of %d and %d is %d\n", 2, 12, lca.lowestCommonAncestor1(rootNode, 2, 12));
        System.out.printf("The lowest common ancestor of %d and %d is %d\n", 6, 12, lca.lowestCommonAncestor2(rootNode, 6, 12));
    }

    /**
     * Approach:
     *  This problem can be solved by using BST property, i.e., left < parent < right for each node.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n) = O(h)
     */
    public Integer lowestCommonAncestor1(TreeNode<Integer> rootNode, int first, int second) {
        if(rootNode == null) return null;

        if (rootNode.data > Math.max(first,second))
            return lowestCommonAncestor1(rootNode.left, first, second);
        else if (rootNode.data < Math.min(first,second))
            return lowestCommonAncestor1(rootNode.right, first, second);

        //rootNode.data >= first && rootNode.data <= second
        return rootNode.data;
    }

    /**
     * Approach:
     *  Iterative approach
     *  This problem can be solved by using BST property, i.e., left < parent < right for each node.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n) = O(h)
     */
    public Integer lowestCommonAncestor2(TreeNode<Integer> rootNode, int first, int second) {
        while (rootNode != null) {
            if(rootNode.data > Math.max(first,second))
                rootNode = rootNode.left;
            else if (rootNode.data < Math.min(first,second))
                rootNode = rootNode.right;
            else
                return rootNode.data; //rootNode.data >= first && rootNode.data <= second
        }
        return null; //if rootnode is null
    }
}
