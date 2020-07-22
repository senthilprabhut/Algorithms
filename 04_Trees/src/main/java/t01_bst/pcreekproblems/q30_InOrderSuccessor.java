package t01_bst.pcreekproblems;

import t01_bst.BSTSimple;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem:
 *  Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * Links:
 *  Solution 1 - Interview Candidate
 *  Solution 2 - https://www.programcreek.com/2014/05/leetcode-inorder-successor-in-bst-java/
 *  Solution 3 - http://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
 *
 *  Parent Pointer (not implemented) - http://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
 */
public class q30_InOrderSuccessor<T> {
    private Comparator<T> comparator;

    public q30_InOrderSuccessor(Comparator<T> comparator) {
        this.comparator = comparator;
    }

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
        int[] input = {5,18, 9, 6, 12, 1, 3, 2};
        List<Integer> listOfNumbers = Arrays.stream(input).boxed().collect(Collectors.toList());
        TreeNode<Integer> root = tree.add(null, listOfNumbers);
        TreeUtils.printTree(root);
        System.out.println();

        q30_InOrderSuccessor<Integer> ios = new q30_InOrderSuccessor<>(Comparator.comparingInt(a -> a));
        System.out.printf("Inorder successor of %d is %d \n", 6, ios.inorderSuccessor1(root, 6));
        System.out.printf("Inorder successor of %d is %d \n", 3, ios.inorderSuccessor3(root, tree.search(root, 3)));
        System.out.printf("Inorder successor of %d is %d \n", 18, ios.inorderSuccessor2(root, 18));
    }

    /*
     * Approach:
     *  Use search like technique to find the node matching data
     *  Then find the inorder successor in the matching node's right
     *
     * Complexity:
     *  Time Complexity: The code traverses the height of the tree to find the matching node and another height
     *      traversal to find the inorder successor - so O(2h) = O(h) = O(log n)
     *  Space Complexity: The code uses recursion to store the state and it reaches "h" level via recursion.
     *      So space required is O(h) = O(log n)
     */
    public T inorderSuccessor1(TreeNode<T> root, T data) {
        if(root == null) return null;

        //if data is greater than root or equals the root, get successor from right
        if(comparator.compare(data, root.data) >= 0)
            return inorderSuccessor1(root.right, data);
        else {
            T left = inorderSuccessor1(root.left, data);
            if (left == null)
                return root.data;
            return left;
        }
    }

    /**
     * Approach:
     *  First compare the data and find the node which matches data
     *  If it a left node, we keep changing the "successor" value
     *  If we go right, nothing changes
     *
     * Complexity:
     *  Time Complexity is O(log n) = O(h), n is the no of nodes and h is the height
     *  Space Complexity is O(1)
     */
    public T inorderSuccessor2(TreeNode<T> root, T data) {
        if (root == null) return null;

        TreeNode<T> node = root, successor = null;
        while(node != null && comparator.compare(data, node.data) !=0 ) {
            if(comparator.compare(data, node.data) <0) {
                successor = node;  //successor changes only when left traversal happens, remains the same in right sub-tree
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if(node == null) return null;

        //If right node is null, return successor
        if(node.right == null) return (successor != null) ? successor.data : null;

        //If right sub-tree exists, find the min value in the right sub-tree
        node = node.right;
        while(node.left != null) node = node.left;
        return node.data;
    }

    /**
     * Approach:
     *
     *
     * Complexity:
     *  Time Complexity is O(log n)
     *  Space Complexity is O(1)
     */
    public T inorderSuccessor3(TreeNode<T> root, TreeNode<T> parent) {
        //If parent's right is not null, then return minValue from its right sub-tree
        TreeNode<T> node = parent;
        if(node.right != null) {
            node = node.right;
            while(node.left != null) node = node.left;
            return node.data;
        }

        //Else, search from root on its left side
        TreeNode<T> successor = null;
        node = root;
        while(node != null) {
            if (comparator.compare(parent.data, node.data) < 0) {
                successor = node;
                node = node.left;
            } else if (comparator.compare(parent.data, node.data) > 0) {
                node = node.right;
            } else {
                break; //if parent node is found
            }
        }
        return (successor != null) ? successor.data : null;
    }
}
