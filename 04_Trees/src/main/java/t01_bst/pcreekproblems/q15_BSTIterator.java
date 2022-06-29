package t01_bst.pcreekproblems;

import datastructure.BSTSimple;
import datastructure.TreeNode;

import java.util.*;

/**
 * Problem:
 *  Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *  Calling next() will return the next smallest number in the BST.
 *  Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 * Links:
 *  https://www.programcreek.com/2014/04/leetcode-binary-search-tree-iterator-java/
 */
public class q15_BSTIterator<T> implements Iterator<T> {
    private Deque<TreeNode<T>> stack = new LinkedList<>();

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
        List<Integer> input = new ArrayList(){ {add(5); add(18); add(9); add(6); add(12); add(1); add(3); add(2);} };
        TreeNode<Integer> root = tree.add(null, input);

        q15_BSTIterator<Integer> it = new q15_BSTIterator<>(root);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public q15_BSTIterator(TreeNode<T> rootNode) {
        while (rootNode != null) {
            stack.push(rootNode);
            rootNode = rootNode.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public T next() {
        TreeNode<T> temp = stack.pop();
        T result = temp.data;
        if(temp.right != null) {
            temp = temp.right;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
        }
        return result;
    }
}
