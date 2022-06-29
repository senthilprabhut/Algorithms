package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.*;

/**
 * Problem:
 *  Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 *  For example, given binary tree {3,9,20,#,#,15,7},
 *  return its level order traversal as [[15,7], [9,20],[3]]
 */
public class q01_ReverseLevelorderTraversal<T> {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \   /  \
            4    5 6    7
        */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6, 7};
        TreeNode<Integer> root = tree.buildTree(data);

        q01_ReverseLevelorderTraversal<Integer> lot = new q01_ReverseLevelorderTraversal();
        System.out.println(lot.reverseLevelOrderTraversal1(root));
        System.out.println(lot.reverseLevelOrderTraversal2(root));
    }

    /**
     * Approach:
     *  Maintain a stack and queue. Do regular level order traversal but put right first in the queue.
     *  Instead of printing, put the result in stack. Finally print contents of the stack.
     *
     * Links:
     *  Tushar - https://youtu.be/D2bIbWGgvzI
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LevelOrderTraversalInReverse.java
     *  http://www.geeksforgeeks.org/reverse-level-order-traversal/
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<T> reverseLevelOrderTraversal1(TreeNode<T> rootNode) {
        List<T> revLevelOrder = new ArrayList<>();
        if(rootNode == null)
            return revLevelOrder;


        Deque<T> stack = new ArrayDeque<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(rootNode);

        while (! queue.isEmpty()) {
            rootNode = queue.poll();

            if(rootNode.right != null){
                queue.offer(rootNode.right);
            }
            if(rootNode.left != null){
                queue.offer(rootNode.left);
            }
            stack.push(rootNode.data);
        }

        while (!stack.isEmpty())
            revLevelOrder.add(stack.pop());
        return revLevelOrder;
    }

    /**
     * Approach:
     *  Do regular level order traversal and then reverse the list in the end
     *
     * Links:
     *  https://www.programcreek.com/2014/04/leetcode-binary-tree-level-order-traversal-ii-java/
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>> reverseLevelOrderTraversal2(TreeNode<T> rootNode) {
        List<List<T>> revLevelOrderTraversal = new ArrayList<>();
        if(rootNode == null) return revLevelOrderTraversal;

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(rootNode);
        int levelCount = 1, currentCount = 0;

        List<T> row = new ArrayList<>();
        while (! queue.isEmpty()) {
            rootNode = queue.poll();
            row.add(rootNode.data);
            levelCount--;

            if(rootNode.left != null)
                queue.add(rootNode.left); currentCount++;

            if(rootNode.right != null)
                queue.add(rootNode.right); currentCount++;

            if(levelCount == 0) {
                levelCount = currentCount;
                currentCount = 0;
                revLevelOrderTraversal.add(row);
                row = new ArrayList<>();
            }
        }

        Collections.reverse(revLevelOrderTraversal);
        return revLevelOrderTraversal;
    }
}
