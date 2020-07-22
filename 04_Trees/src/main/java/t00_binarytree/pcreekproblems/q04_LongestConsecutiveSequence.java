package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem:
 *  Given a Binary Tree find the length of the longest path which comprises of nodes with consecutive values
 *  in increasing order. Every node is considered as a path of length 1.
 *
 *      1
 *    /   \
 *   2     5
 *  / \   / \
 * 0   3 8   6
 *      \    /
 *       4  7
 * In the above tree, longest consecutive path(LCP) is 1,2,3 and Length is 3
 *
 * Links:
 *  http://www.geeksforgeeks.org/longest-consecutive-sequence-binary-tree/
 *  https://discuss.leetcode.com/topic/28269/two-simple-iterative-solutions-bfs-and-dfs
 *  https://www.programcreek.com/2014/04/leetcode-binary-tree-longest-consecutive-sequence-java/
 *
 */
public class q04_LongestConsecutiveSequence {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        java.lang.Integer[] data = new java.lang.Integer[] {1, 2, 5, 0, 3, 8, 6, null, null, null, 4, null, null, 7};
        TreeNode<java.lang.Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q04_LongestConsecutiveSequence lcs = new q04_LongestConsecutiveSequence();
        System.out.println();
        System.out.println(lcs.LongestSequenceBFS(root));
        System.out.println(lcs.LongestSequenceDFS(root));
        System.out.println(lcs.LongestSequenceRecursion(root));
    }

    /**
     * Approach:
     *  BFS using Queue
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int LongestSequenceBFS(TreeNode<Integer> rootNode) {
        if (rootNode == null) return 0;

        Deque<TreeNode<Integer>> nodeQueue = new LinkedList<>();
        Deque<Integer> lengthQueue = new LinkedList<>();

        nodeQueue.add(rootNode);
        lengthQueue.add(1);
        int longest = 1;

        while(! nodeQueue.isEmpty()) {
            TreeNode<Integer> head = nodeQueue.poll();
            int currLength = lengthQueue.poll();
            longest = Math.max(longest, currLength);

            //query left child
            if(head.left != null) {
                nodeQueue.push(head.left);
                if(head.data == (head.left.data - 1))
                    lengthQueue.push(currLength+1);
                else
                    lengthQueue.push(1);
            }

            //query right child
            if(head.right != null) {
                nodeQueue.push(head.right);
                if(head.data == (head.right.data - 1))
                    lengthQueue.push(currLength+1);
                else
                    lengthQueue.push(1);
            }
        }

        return longest;
    }

    /**
     * Approach:
     *  DFS using Stack
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int LongestSequenceDFS(TreeNode<Integer> rootNode) {
        Deque<TreeNode<Integer>> nodeStack = new LinkedList<>();
        Deque<Integer> lengthStack = new LinkedList<>();

        TreeNode<Integer> current = rootNode, parent = null;
        int longest = 0, currLength = 0;

        //Do a pre-order traversal
        while (! nodeStack.isEmpty() || current != null) {
            if(current != null) {
                currLength = (parent == null || current.data != parent.data+1) ? 1 : currLength+1;
                longest = Math.max(longest, currLength);

                nodeStack.push(current);
                lengthStack.push(currLength);

                parent = current;
                current = current.left;
            } else {
                current = nodeStack.pop();
                currLength = lengthStack.pop();

                parent = current;
                current = current.right;
            }
        }
        return longest;
    }

    /**
     * Approach:
     *  DFS using Recursion
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int LongestSequenceRecursion(TreeNode<Integer> rootNode) {
        int[] longest = new int[] {0};
        longestSeqHelper(null, rootNode, 0, longest);
        return longest[0];
    }

    private void longestSeqHelper(TreeNode<Integer> parent, TreeNode<Integer> current, int currLength, int[] longest) {
        if(current == null) return;

        currLength =  (parent == null || current.data != parent.data+1) ? 1 : currLength + 1;
        longest[0] = Math.max(longest[0], currLength);

        longestSeqHelper(current, current.left, currLength, longest);
        longestSeqHelper(current, current.right, currLength, longest);
    }
}
