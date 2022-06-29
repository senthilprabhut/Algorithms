package t01_bst.pcreekproblems;


import datastructure.BSTSimple;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.*;

/**
 * Problem:
 *  Given a binary search tree, write a function kthSmallest1 to find the kth smallest element in it.
 *
 * Links:
 *  https://www.programcreek.com/2014/07/leetcode-kth-smallest-element-in-a-bst-java/
 */
public class q03_kthsmallestelement<T> {
    public static void main(String[] args) {
        q03_kthsmallestelement kse = new q03_kthsmallestelement();

        /* Constructed binary tree is
                  5
               /    \
              1     18
              \     /
              3     9
              /   /  \
             2   6   12
        */
        BSTSimple<Integer> bst = new BSTSimple<>(Comparator.comparingInt(a -> a));
        List<Integer> input = new ArrayList<Integer>(){ {add(5); add(18); add(9); add(6); add(12); add(1); add(3); add(2);} };
        TreeNode<Integer> root = bst.add(null, input);
        TreeUtils.printTree(root);

        System.out.printf("\n%dth smallest number in the tree is %d\n", 4, kse.kthSmallest1(root, 4));
        System.out.printf("\n%dth smallest number in the tree is %d\n", 9, kse.kthSmallest2(root, 9));
    }

    /**
     * Approach:
     *  DFS search and decrement the k value each tie a node is found
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n) for the stack
     */
    public T kthSmallest1(TreeNode<T> root, int k) {
        int copy = k;
        Deque<TreeNode<T>> stack = new ArrayDeque<>();

        TreeNode<T> current = root;
        while(!stack.isEmpty() || current !=null) { //both conditions are true once tree is processed
            if(current != null) {
                stack.push(current);
                current = current.left;
            }
            else {
                current = stack.pop();
                k--;
                if(k == 0) break; //If we reached the desired node
                current = current.right;
            }
        }
        if(k > 0) throw new RuntimeException(copy + "th smallest number is not found");
        return current.data;
    }

    /**
     * Approach:
     *  Same approach - another way of doing a DFS search
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n) for the stack
     */
    public T kthSmallest2(TreeNode<T> root, int k) {
        int copy = k;
        Deque<TreeNode<T>> stack = new ArrayDeque<>();

        TreeNode<T> current = root;
        while(current != null) {
            stack.push(current);
            current = current.left;
        }

        while (!stack.isEmpty()) {
            current = stack.pop();
            k--;
            if (k == 0) break;

            current = current.right;
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
        }
        if(k > 0) throw new RuntimeException(copy + "th smallest number is not found");
        return current.data;
    }
}
