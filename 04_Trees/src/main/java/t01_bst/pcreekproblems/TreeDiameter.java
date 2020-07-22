package t01_bst.pcreekproblems;

import t01_bst.BSTSimple;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem:
 *  The getDiameter of a tree is the number of nodes on the longest path between two leaves in the tree
 *
 * Links:
 *  Solution 1: http://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
 *  Solution 2: http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 */
public class TreeDiameter {
    public static void main(String[] args) {
        BSTSimple<Integer> bst = new BSTSimple<>(Comparator.comparingInt(a -> a));
        int[] input = {5,18, 9, 6, 12, 1, 3, 2};
        List<Integer> listOfNumbers = Arrays.stream(input).boxed().collect(Collectors.toList());

        TreeNode<Integer> root = bst.add(null, listOfNumbers);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);// Default value of getDiameter
        TreeUtils.printTree(root);

        TreeDiameter diameter = new TreeDiameter();
        diameter.heightWithDiameter(root, stack);
        System.out.println("The getDiameter of the tree is " + stack.pop());

        System.out.println("The getDiameter of the tree is " + diameter.getDiameter(root));
    }


    /**
     * Approach:
     *  As part of finding the height of the tree, calculate the diameter as well
     *
     * Complexity:
     *  Time Complexity: Since it traverses the whole tree once, O(n)
     *  Space Complexity: Recursion requires stack space of O(log₂ n)
     */
    public int heightWithDiameter(TreeNode root, Deque<Integer> stack) {
        if(root == null) return -1; //Ht of leaf node is 0

        int lHeight = heightWithDiameter(root.left, stack);
        int rHeight = heightWithDiameter(root.right, stack);

        //Calculate getDiameter and update the stack
        int lNodes = lHeight + 1, rNodes = rHeight + 1;
        int oldDiameter = stack.pop(), newDiameter = 1 + lNodes + rNodes;
        stack.push(Math.max(oldDiameter,newDiameter));

        //When returning, return ht of this level and NOT the calculated diameter
        return 1 + Math.max(lHeight,rHeight);
    }

    /**
     * Approach:
     *  Go to every depth and calculate ht at that depth
     *  Check if diameter including root is greater than diameter excluding it
     *  And return the max value
     *
     * Complexity:
     *  Time Complexity: Since it traverses the whole tree and calculates ht at every node, O(n*n) = O(n²)
     *  Space Complexity:
     */
    public int getDiameter(TreeNode root) {
        if(root == null) return 0;

        //if the getDiameter includes root - 1 is for the root and 1 is added to height to get the no of nodes
        int ht1 = 1 + (1+height(root.left)) + (1+height(root.right));

        //if the getDiameter doesn't include root
        int ht2 = Math.max(getDiameter(root.left), getDiameter(root.right));

        return Math.max(ht1,ht2);
    }

    private int height(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(height(root.left) ,height(root.right));
    }
}
