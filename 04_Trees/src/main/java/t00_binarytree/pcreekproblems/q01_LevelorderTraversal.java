package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem:
 *  Given a binary tree, return the level order traversal of its nodes' values.
 *  (ie, from left to right, level by level).
 *
 */
public class q01_LevelorderTraversal<T> {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
        */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5};
        TreeNode<Integer> root = tree.buildTree(data);

        q01_LevelorderTraversal<Integer> lot = new q01_LevelorderTraversal<>();
        System.out.println(lot.levelorder1(root));
        System.out.println(lot.levelorder2(root));
        System.out.println(lot.levelorder3(root));
        System.out.println(lot.levelorder4(root));
    }

    /**
     * Approach:
     *  Using one queue - simple level order traversal
     *
     * Links:
     *  Tushar - https://youtu.be/9PHkM0Jri_4
     *  http://www.geeksforgeeks.org/level-order-tree-traversal/
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LevelOrderTraversal.java
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<T> levelorder1(TreeNode<T> rootNode) {
        List<T> levelorder = new ArrayList<>();
        if(rootNode == null)
            return levelorder;

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            levelorder.add(current.data);

            if(current.left != null)
                queue.add(current.left);

            if(current.right != null)
                queue.add(current.right);
        }
        return levelorder;
    }

    /**
     * Approach:
     *  Level by Level - Using two queues to differentiate the levels
     *
     *
     * Links:
     *  Tushar - https://youtu.be/7uG0gLDbhsI
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversalLevelByLevel.java
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>> levelorder2(TreeNode<T> rootNode) {
        List<List<T>> levelorder = new ArrayList<>();
        if (rootNode == null) {
            return levelorder;
        }

        Queue<TreeNode<T>> queue1 = new LinkedList<>();
        Queue<TreeNode<T>> queue2 = new LinkedList<>();
        List<T> row = new ArrayList<>();

        queue1.add(rootNode);

        while (!queue1.isEmpty()) {
            TreeNode<T> current = queue1.poll();
            row.add(current.data);

            if(current.left != null)
                queue2.add(current.left);

            if(current.right != null)
                queue2.add(current.right);

            if (queue1.isEmpty()) {
                levelorder.add(row);
                row = new ArrayList<>();
                //Swap queues so that the queue is read from non-empty queue
                Queue<TreeNode<T>> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }
        }
        return levelorder;
    }

    /**
     * Approach:
     *  Level by Level - Use one queue and delimiter to print level by level
     *
     * Links:
     *  Tushar - https://youtu.be/7uG0gLDbhsI
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversalLevelByLevel.java
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>> levelorder3(TreeNode<T> rootNode) {
        List<List<T>> levelorder = new ArrayList<>();
        if (rootNode == null) {
            return levelorder;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(rootNode);
        queue.offer(null);

        List<T> row = new ArrayList<>();
        while (! queue.isEmpty()) {
            rootNode = queue.poll();

            if(rootNode != null) {
                row.add(rootNode.data);

                if (rootNode.left != null) {
                    queue.offer(rootNode.left);
                }
                if (rootNode.right != null) {
                    queue.offer(rootNode.right);
                }
            } else {
                levelorder.add(row);
                if (!queue.isEmpty()) {
                    row = new ArrayList<>();
                    queue.offer(null);
                }
            }
        }
        return levelorder;
    }

    /**
     * Approach:
     *  Level by Level - Use one queue and count to print level by level
     *
     * Links:
     *  Tushar - https://youtu.be/7uG0gLDbhsI
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversalLevelByLevel.java
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>> levelorder4(TreeNode<T> rootNode) {
        List<List<T>> levelorder = new ArrayList<>();
        if (rootNode == null) {
            return levelorder;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(rootNode);
        List<T> row = new ArrayList<>();
        int levelCount = 1, currentCount = 0;


        while (!queue.isEmpty()) {
            rootNode = queue.poll();
            row.add(rootNode.data);
            levelCount--;

            if (rootNode.left != null) {
                queue.add(rootNode.left);
                currentCount++;
            }

            if (rootNode.right != null) {
                queue.add(rootNode.right);
                currentCount++;
            }

            if(levelCount == 0) {
                levelCount=currentCount;
                currentCount=0;
                levelorder.add(row);
                row = new ArrayList<>();
            }
        }
        return levelorder;
    }
}
