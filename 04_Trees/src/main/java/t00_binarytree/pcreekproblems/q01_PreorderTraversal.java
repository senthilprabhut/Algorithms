package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.*;

/**
 * Problem:
 *  Binary Tree Preorder Traversal
 *  The order of "preorder" is: parent -> left child -> right child
 *  Parent node is processed before its children
 *
 */
public class q01_PreorderTraversal<T> {
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

        q01_PreorderTraversal<Integer> pot = new q01_PreorderTraversal<>();
        System.out.println(pot.preorder1(root));
        System.out.println(pot.preorder2(root));
        System.out.println(pot.preorder3(root));
        System.out.println(pot.preorder4Rec(root));
        System.out.println(pot.morrisPreorder(root));
    }


    /**
     * Approach:
     *  Using stack
     *  The key is using a stack to store left and right children, and push
     *  right child first so that it is processed after the left child.
     *
     * Links:
     *  https://www.programcreek.com/2012/12/leetcode-solution-for-binary-tree-preorder-traversal-in-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> preorder1(TreeNode<T> rootNode) {
        List<T> preorder = new LinkedList<>();

        if(rootNode == null)
            return preorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(rootNode);

        while(!stack.isEmpty()) {
            TreeNode<T> current = stack.pop();
            preorder.add(current.data);

            if(current.right != null)
                stack.push(current.right);

            if(current.left != null)
                stack.push(current.left);
        }

        return preorder;
    }

    /**
     * Approach:
     *  Using One Stack and InOrder traversal like approach
     *
     * Links:
     *  Based on inorder https://www.programcreek.com/2012/12/leetcode-solution-of-binary-tree-inorder-traversal-in-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> preorder2(TreeNode<T> rootNode) {
        List<T> preorder = new LinkedList<>();

        if(rootNode == null)
            return preorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = rootNode;
        while (!stack.isEmpty() || current != null) { //End of tree, stack is empty and current is null
            if (current != null) {
                preorder.add(current.data);
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                current = current.right;
            }
        }
        return preorder;
    }

    /**
     * Approach:
     *  Using While True - Tushar code
     *
     * Links:
     *  Based on inorder https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L45
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> preorder3(TreeNode<T> rootNode) {
        List<T> preorder = new LinkedList<>();

        if(rootNode == null)
            return preorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = rootNode;
        while (true) {
            if (current != null) {
               preorder.add(current.data);
               stack.push(current);
               current = current.left;
            } else {
                if (stack.isEmpty()) break;
                current = stack.pop();
                current = current.right;
            }
        }
        return preorder;
    }

    /**
     * Approach:
     *  Recursive Approach
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> preorder4Rec(TreeNode<T> rootNode) {
        return preorder4Rec(rootNode, new ArrayList<>());
    }

    private List<T> preorder4Rec(TreeNode<T> rootNode, List<T> preorder) {
        if (rootNode == null) return preorder;

        preorder.add(rootNode.data);
        preorder4Rec(rootNode.left, preorder);
        preorder4Rec(rootNode.right, preorder);

        return preorder;
    }

    /**
     * Morris algorithm - Preorder traversal without recursion and without stack
     *
     * Approach:
     *  1...If left child is null, print the current node data. Move to right child.
     *  ….Else, Make the right child of the inorder predecessor point to the current node. Two cases arise:
     *  ………a) The right child of the inorder predecessor already points to the current node. Set right child to NULL. Move to right child of current node.
     *  ………b) The right child is NULL. Set it to current node. Print current node’s data and move to left child of current node.
     *  2...Iterate until current node is not NULL.
     *
     * Limitations:
     *  Morris traversal modifies the tree during the process.
     *  It establishes the right links while moving down the tree and resets the right links while moving up the tree.
     *  So the algorithm cannot be applied if write operations are not allowed.
     *
     * Links:
     *  Tushar - https://www.youtube.com/watch?v=wGXB9OWhPTg
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/MorrisTraversal.java
     *  http://www.geeksforgeeks.org/morris-traversal-for-preorder/
     *
     * Complexity:
     * Time complexity is O(n)
     * Space complexity is O(1)
     */
    public List<T> morrisPreorder(TreeNode<T> root) {
        List<T> preorder = new ArrayList<>();
        TreeNode<T> current = root;

        while (current != null) {
            //If left child is null, print the current node data. Move to right child.
            if (current.left == null) {
                preorder.add(current.data);
                current = current.right;
            }
            //If left child exists
            else {
                //Find the in-order predecessor - this is the max value in left subtree
                TreeNode<T> predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current)
                    predecessor=predecessor.right;

                //If the right child of inorder predecessor already points to this node
                //This path is already traversed - reset the right pointer
                if(predecessor.right == current) {
                    predecessor.right = null; //reset the right pointer
                    current = current.right;
                } else {
                    // If right child doesn't point to this node, then make right child point to this node
                    predecessor.right = current;
                    preorder.add(current.data);
                    current = current.left;
                }

            }
        }
        return preorder;
    }

}
