package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem:
 *  Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * Links:
 *  https://www.programcreek.com/2014/07/leetcode-lowest-common-ancestor-of-a-binary-tree-java/
 */
public class q18_LowestCommonAncestor {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               3     2
             /  \
            4    5
        */
        q18_LowestCommonAncestor lca = new q18_LowestCommonAncestor();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[]{1, 3, 2, 4, 5};
        TreeNode<Integer> root = tree.buildTree(data);

        System.out.printf("The lowest common ancestor of %d and %d is %d\n", 3, 2, lca.lowestCommonAncestor2(root,3,2)); //1
        System.out.printf("The lowest common ancestor of %d and %d is %d\n", 4, 5, lca.lowestCommonAncestor2(root,4,5)); //3
        System.out.printf("The lowest common ancestor of %d and %d is %d\n", 4, 2, lca.lowestCommonAncestor2(root,4,2)); //1
    }

    /**
     * Approach:
     *  Recursive approach
     *
     * Complexity:
     *  Time Complexity is f(n)=2*f(n-1)=2*2*f(n-2)=2^(logn), so time=O(n).
     *  Space Complexity is O(log n) = O(h)
     */
    public Integer lowestCommonAncestor1(TreeNode<Integer> root, Integer left, Integer right) {
        if(root == null) return null;

        if(root.data == left || root.data == right) return root.data;

        Integer leftVal = lowestCommonAncestor1(root.left, left, right);
        Integer rightVal = lowestCommonAncestor1(root.right, left, right);

        if(rightVal != null && leftVal != null)
            return root.data;
        else if (rightVal == null && leftVal == null)
            return null;

        return (leftVal == null ? rightVal : leftVal);
    }

    /**
     * Approach:
     *  Iterative approach - use post-order traversal
     *
     * Complexity:
     *  Time Complexity is O(n).
     *  Space Complexity is O(n)
     */
    public Integer lowestCommonAncestor2(TreeNode<Integer> root, Integer left, Integer right) {
        if(root == null) return null;

        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        stack.push(root);

        TreeNode<Integer> current = null, prev = null;
        Integer leftVal = null, rightVal = null, nodeVal = null;
        while (!stack.isEmpty()) {
            current = stack.peek();

            if(prev == null || current == prev.left || current == prev.right) {
                if(current.left != null)
                    stack.push(current.left);
                else if(current.right != null)
                    stack.push(current.right);
            } else if (current.left == prev) { //On way up from left sub-tree
                leftVal = nodeVal;
                nodeVal = null;

                if(current.right != null)
                    stack.push(current.right);
            } else {

                //if node matches left or right values
                if(current == prev && (current.data == left || current.data == right))
                    nodeVal = current.data; //set the node value
                else if (current.right == prev) { //On way up from right sub-tree
                    rightVal = nodeVal;
                    nodeVal = null;

                    if(leftVal == null && rightVal == null) nodeVal = null;
                    else if (leftVal != null && rightVal != null) nodeVal = current.data; //return parent for both non-null values
                    else nodeVal = (leftVal == null ? rightVal : leftVal);
                }
                stack.pop();
            }

            prev = current;
        }
        return nodeVal;
    }
}
