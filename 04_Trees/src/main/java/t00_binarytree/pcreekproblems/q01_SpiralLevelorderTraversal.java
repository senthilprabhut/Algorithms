package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem:
 *  Given a root of binary tree, print in spiral order.
 *  e.g               1
 *              2           3
 *         4       5     6      7
 *       8   9  10    11
 * should print 1 3 2 4 5 6 7 8 9 10 11
 *
 * Links:
 *  https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *  Tushar - https://youtu.be/vjt5Y6-1KsQ
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversalInSpiralOrder.java
 *
 */
public class q01_SpiralLevelorderTraversal<T> {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6, 7,8,9,10,11};
        TreeNode<Integer> root = tree.buildTree(data);

        q01_SpiralLevelorderTraversal<Integer> lot = new q01_SpiralLevelorderTraversal<>();
        System.out.println(lot.spiralLevelOrderTraversal1(root));
        System.out.println(lot.spiralLevelOrderTraversal2(root));
        System.out.println(lot.spiralLevelOrderTraversal3(root));
    }

    /**
     * Approach:
     *  Use two stack. Put root in stack1. While stack1 is not
     *  empty take items from stack1 and put its child left,right in stack2.
     *  Then once stack1 is empty pop from stack2 and put its child right, left into stack1.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>>  spiralLevelOrderTraversal1(TreeNode<T> rootNode) {
        List<List<T>> levelorder = new ArrayList<>();
        if(rootNode == null) return levelorder;

        Deque<TreeNode<T>> stack1 = new LinkedList<>();
        Deque<TreeNode<T>> stack2 = new LinkedList<>();
        stack1.push(rootNode);

        List<T> row = new ArrayList<>();
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                rootNode = stack1.pop();
                row.add(rootNode.data);

                if (rootNode.left != null)
                    stack2.push(rootNode.left);

                if (rootNode.right != null)
                    stack2.push(rootNode.right);
            }
            if(row.size() > 0 ) levelorder.add(row); row = new ArrayList<>();

            while ((!stack2.isEmpty())) {
                rootNode = stack2.pop();
                row.add(rootNode.data);

                if (rootNode.right != null) {
                    stack1.push(rootNode.right);
                }
                if (rootNode.left != null) {
                    stack1.push(rootNode.left);
                }
            }
            if(row.size() > 0) levelorder.add(row); row = new ArrayList<>();
        }
        return levelorder;
    }

    /**
     * Approach:
     *  Use one dequeue. Technique is like above but instead of using two stack use one dequeue.
     *  Also keep count till which point you read in the dequeue.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>>  spiralLevelOrderTraversal2(TreeNode<T> rootNode) {
        List<List<T>> levelorder = new ArrayList<>();
        if(rootNode == null) return levelorder;

        Deque<TreeNode<T>> deque = new LinkedList<>();
        deque.offerFirst(rootNode);
        int levelCount=1, currentCount=0;

        List<T> row = new ArrayList<>();
        boolean top = true;
        while (!deque.isEmpty()) {
            while(levelCount != 0) {
                if(top == true) {
                    rootNode = deque.pollFirst();
                    if (rootNode.left != null) {
                        deque.offerLast(rootNode.left);
                        currentCount++;
                    }
                    if (rootNode.right != null) {
                        deque.offerLast(rootNode.right);
                        currentCount++;
                    }
                } else {
                    rootNode = deque.pollLast();
                    if (rootNode.right != null) {
                        deque.offerFirst(rootNode.right);
                        currentCount++;
                    }
                    if (rootNode.left != null) {
                        deque.offerFirst(rootNode.left);
                        currentCount++;
                    }
                }
                row.add(rootNode.data);
                levelCount--;
            }
            if(levelCount == 0) {
                top = !top; //Flip the flag
                levelCount = currentCount;
                currentCount = 0;
                levelorder.add(row);
                row = new ArrayList<>();
            }
        }
        return levelorder;
    }

    /**
     * Approach:
     *  Use one dequeue. Use a delimiter to separate between one
     *  stack growing from top and another one growing from bottom.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>>  spiralLevelOrderTraversal3(TreeNode<T> rootNode) {
        List<List<T>> levelorder = new ArrayList<>();
        if(rootNode == null) return levelorder;

        Deque<TreeNode<T>> deque = new LinkedList<>();
        deque.offerFirst(null);
        deque.offerFirst(rootNode);

        List<T> row = new ArrayList<>();
        boolean top = true;
        while (deque.size() > 1) {
            while(true) {
                if (top) {
                    rootNode = deque.pollFirst();
                    if(rootNode == null) {
                        deque.offerFirst(null);
                        break;
                    }
                    if (rootNode.left != null)
                        deque.offerLast(rootNode.left);

                    if (rootNode.right != null)
                        deque.offerLast(rootNode.right);
                } else {
                    rootNode = deque.pollLast();
                    if(rootNode == null) {
                        deque.offerLast(null);
                        break;
                    }
                    if (rootNode.right != null)
                        deque.offerFirst(rootNode.right);

                    if (rootNode.left != null)
                        deque.offerFirst(rootNode.left);
                }
                row.add(rootNode.data);
            }
            if(rootNode == null) {
                top = !top;
                levelorder.add(row);
                row = new ArrayList<>();
            }
        }
        return levelorder;
    }
}
