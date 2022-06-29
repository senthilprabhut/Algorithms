package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem:
 *  Given a binary tree, imagine yourself standing on the right side of it,
 *  return the values of the nodes you can see ordered from top to bottom.
 *
 * Links:
 *  https://www.programcreek.com/2014/04/leetcode-binary-tree-right-side-view-java/
 */
public class q16_RightSideView {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
           /
          6
        */
        q16_RightSideView rsv = new q16_RightSideView();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, null, null, 6};
        TreeNode<Integer> root = tree.buildTree(data);
        System.out.println("Right side view of tree: " + rsv.rightSideView(root)); //1, 3, 5, 6
    }

    /**
     * Approach:
     *  Do a BFS - at the end of each level, print the last element
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n) - n/2 is the no of leaf nodes and they n/2 nodes are stored in leaf level
     */
    public List<Integer> rightSideView(TreeNode<Integer> root) {
        List<Integer> result = new LinkedList<>();
        if(root == null) return result;

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root); int currentCount = 0; int levelCount = 1;
        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.poll();
            levelCount--;

            if(current.left != null) {
                queue.offer(current.left);
                currentCount++;
            }

            if(current.right != null) {
                queue.offer(current.right);
                currentCount++;
            }


            if (levelCount == 0) {
                levelCount = currentCount;
                currentCount = 0;
                //Rightmost node gets processed last
                result.add(current.data);
            }
        }
        return result;
    }
}
