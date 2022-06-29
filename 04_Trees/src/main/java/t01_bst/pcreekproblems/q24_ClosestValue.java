package t01_bst.pcreekproblems;

import datastructure.BSTSimple;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem:
 *  Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-closest-binary-search-tree-value-java/
 *  https://discuss.leetcode.com/topic/22590/4-7-lines-recursive-iterative-ruby-c-java-python/2
 */
public class q24_ClosestValue {
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
        int[] input = {5,18, 9, 6, 12, 1, 3, 2};
        List<Integer> listOfNumbers = Arrays.stream(input).boxed().collect(Collectors.toList());
        TreeNode<Integer> root = tree.add(null, listOfNumbers);
        TreeUtils.printTree(root);
        System.out.println();

        q24_ClosestValue cv = new q24_ClosestValue();
        System.out.println("Closest value of 5.8 is " + cv.closestValue1(root, 5.8));
        System.out.println("Closest value of 2.6 is " + cv.closestValue2(root, 2.6));
    }

    /**
     * Approach:
     *  Closest is either the root's value (a) or the closest in the appropriate subtree (b)
     *
     * Complexity:
     *  Time Complexity is O(log n)
     *  Space Complexity is O(log n)
     */
    public int closestValue1(TreeNode<Integer> rootNode, double target) {
        double rootMinValue =  Math.abs(rootNode.data - target);

        TreeNode<Integer> child = (target < rootNode.data) ? rootNode.left : rootNode.right;
        if(child == null) return rootNode.data;

        int childNodeData = closestValue1(child, target);
        double childMinValue = Math.abs(childNodeData - target);

        return (rootMinValue < childMinValue) ? rootNode.data : childNodeData;
    }


    /**
     * Approach:
     *  Iterative approach
     *
     * Complexity:
     *  Time Complexity is O(log n)
     *  Space Complexity is O(1)
     */
    public int closestValue2(TreeNode<Integer> rootNode, double target) {
        int closest = rootNode.data;

        while (rootNode != null) {
            closest = ( Math.abs(closest-target) < Math.abs(rootNode.data-target))
                    ? closest : rootNode.data;
            rootNode = (target < rootNode.data) ? rootNode.left : rootNode.right;
        }
        return closest;
    }
}
