package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Invert a binary tree.
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *  to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * Links:
 *  https://leetcode.com/problems/invert-binary-tree/description/
 *  https://www.programcreek.com/2014/06/leetcode-invert-binary-tree-java/
 */
public class q02_InvertBinaryTree<T> {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  4
                /   \
               2     7
             /  \   /  \
            1    3 6    9
        */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6, 7};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q02_InvertBinaryTree<Integer> ibt = new q02_InvertBinaryTree<>();
        TreeUtils.printTree(ibt.invertTree3(root));
    }

    /**
     * Approach:
     *  recursive DFS - it is bound to the application stack, which means that it's no so much scalable
     *  (you can find the problem size that will overflow the stack and crash your application),
     *  so more robust solution would be to use stack data structure
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public TreeNode<T> invertTree1(TreeNode<T> rootNode) {
        if(rootNode == null) return null;

        TreeNode<T> left = rootNode.left;
        rootNode.left = invertTree1(rootNode.right);
        rootNode.right = invertTree1(left);
        return rootNode;
    }


    /**
     * Approach:
     *  Iterative DFS with stack data structure
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public TreeNode<T> invertTree2(TreeNode<T> rootNode) {
        Deque<TreeNode<T>> stack = new LinkedList<>();

        TreeNode<T>current = rootNode;
        stack.push(current);
        while (! stack.isEmpty()) {

            if(current != null) {
                //Swap Nodes
                TreeNode<T> left = current.left;
                current.left = current.right;
                current.right = left;

                stack.push(current);
                current = current.left;
            }
            else {
                current = stack.pop();
                current = current.right;
            }
        }
        return rootNode;
    }

    /**
     * Approach:
     *  Iterative BFS with queue data structure
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public TreeNode<T> invertTree3(TreeNode<T> rootNode) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(rootNode);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();

            //Swap Nodes
            TreeNode<T> left = current.left;
            current.left = current.right;
            current.right = left;

            if (current.left != null) queue.offer(current.left);
            if  (current.right != null) queue.offer(current.right);
        }
        return rootNode;
    }
}
