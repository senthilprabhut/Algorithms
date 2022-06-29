package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Two binary trees are considered the same if they have identical structure and nodes have the same value.
 *
 * Links:
 *  https://www.programcreek.com/2012/12/check-if-two-trees-are-same-or-not/
 *  https://youtu.be/ySDDslG8wws
 */
public class q28_SameBinaryTree<T> {
    private Comparator<T> comparator;

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] input1 = {5,18, 9, 6, 12, 1, 3, 2};
        TreeNode<Integer> root1 = tree.buildTree(input1);
        TreeUtils.printTree(root1);

        Integer[] input2 = {5,18, 9, 6, 12, 1, 3};
        TreeNode<Integer> root2 = tree.buildTree(input2);
        TreeUtils.printTree(root2);

        q28_SameBinaryTree<Integer> sbt = new q28_SameBinaryTree<>(Comparator.comparingInt(a -> a));
        System.out.println("Is Same: " + sbt.isSame1Rec(root1,root2));
        System.out.println("Is Same: " + sbt.isSame2(root1,root2));
        System.out.println("Is Same: " + sbt.isSame3(root1,root2));

        Integer[] input3 = {5,18, 9, 6, 12, 1, 3, 2};
        TreeNode<Integer> root3 = tree.buildTree(input3);
        TreeUtils.printTree(root3);
        System.out.println("Is Same: " + sbt.isSame1Rec(root1,root3));
        System.out.println("Is Same: " + sbt.isSame2(root1,root3));
        System.out.println("Is Same: " + sbt.isSame3(root1,root3));
    }

    public q28_SameBinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Approach:
     *  Recursive approach
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), ht is ht of the tree
     */
    public boolean isSame1Rec(TreeNode<T> first, TreeNode<T> second) {
        if(first == null || second == null) return first == second; //return true if both are null

        return (comparator.compare(first.data,second.data) == 0) && isSame1Rec(first.left,second.left) &&
                isSame1Rec(first.right, second.right);
    }

    /**
     * Approach:
     *  DFS approach
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), ht is ht of the tree
     */
    public boolean isSame2(TreeNode<T> first, TreeNode<T> second) {
        if(first == null || second == null) return first == second; //return true if both are null

        Deque<TreeNode<T>> stack = new LinkedList<>();
        while(!stack.isEmpty() || first != null) {
            if(first != null) {
                if(second != null) {
                    stack.push(first);
                    stack.push(second);

                    first = first.left;
                    second = second.left;
                } else {
                    return false; //first is not null and second is null
                }
            } else {
                if(second == null) {
                    first = stack.pop();
                    second = stack.pop();

                    first = first.right;
                    second = second.right;
                } else {
                    return false;//first is null and second is not null
                }

            }
        }
        return true;
    }

    /**
     * Approach:
     *  BFS approach
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(h), ht is ht of the tree
     */
    public boolean isSame3(TreeNode<T> first, TreeNode<T> second) {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.offer(first);
        queue.offer(second);

        while (!queue.isEmpty()) {
            first = queue.poll();
            second = queue.poll();

            if(first == null || second == null) return first == second;

            if(comparator.compare(first.data,second.data) != 0) return false;

            queue.offer(first.left);
            queue.offer(second.left);

            queue.offer(first.right);
            queue.offer(second.right);
        }
        return true;
    }
}
