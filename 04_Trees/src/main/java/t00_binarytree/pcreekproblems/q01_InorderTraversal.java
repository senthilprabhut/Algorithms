package t00_binarytree.pcreekproblems;


import datastructure.BinaryTree;
import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Problem:
 *  Binary Tree Inorder Traversal
 *  The order of "inorder" is: left child -> parent -> right child
 */
public class q01_InorderTraversal<T> {
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

        q01_InorderTraversal iot = new q01_InorderTraversal();
        System.out.println(iot.inorder1(root));
        System.out.println(iot.inorder2(root));
        System.out.println(iot.inorder3(root));
        System.out.println(iot.inorder4(root));
        System.out.println(iot.inorder5(root));
    }

    /**
     * Approach:
     *  Using Stack
     *
     * Links:
     *  https://www.programcreek.com/2012/12/leetcode-solution-of-binary-tree-inorder-traversal-in-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> inorder1(TreeNode<T> rootNode) {
        List<T> inorder = new ArrayList<>();
        if(rootNode == null) return inorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = rootNode;
        while( !stack.isEmpty() || current != null ) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                inorder.add(current.data);
                current = current.right;
            }
        }
        return inorder;
    }

    /**
     * Approach:
     *  Using While True
     *
     * Links:
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L45
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> inorder2(TreeNode<T> rootNode) {
        List<T> inorder = new ArrayList<>();
        if(rootNode == null) return inorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = rootNode;
        while (true) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                if (stack.isEmpty()) break;

                current = stack.pop();
                inorder.add(current.data);
                current = current.right;
            }
        }
        return inorder;
    }

    /**
     * Approach:
     *  Using Two While Loops
     *
     * Links:
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L45
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> inorder3(TreeNode<T> rootNode) {
        List<T> inorder = new ArrayList<>();
        if(rootNode == null) return inorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = rootNode;
        while(current != null) {
            stack.push(current);
            current = current.left;
        }

        while (! stack.isEmpty()) {
            current = stack.pop();
            inorder.add(current.data);

            if(current.right != null) {
                current = current.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
        }
        return inorder;
    }

    /**
     * Approach:
     *  Recursive method
     *
     * Links:
     *  https://www.programcreek.com/2012/12/leetcode-solution-of-binary-tree-inorder-traversal-in-java/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> inorder4(TreeNode<T> rootNode) {
        return inorder4Rec(rootNode, new ArrayList<>());
    }

    private List<T> inorder4Rec(TreeNode<T> rootNode, List<T> inorder) {
        if (rootNode == null) return inorder;
        inorder4Rec(rootNode.left, inorder);
        inorder.add(rootNode.data);
        inorder4Rec(rootNode.right, inorder);
        return inorder;
    }

    /**
     * Approach:
     *  Morris algorithm - Inorder traversal without recursion and without stack
     *
     * Limitations:
     *  Morris traversal modifies the tree during the process. It establishes the right links while moving down
     *  the tree and resets the right links while moving up the tree. So the algorithm cannot be applied if
     *  write operations are not allowed.
     *
     * Links:
     *  http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/MorrisTraversal.java
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> inorder5(TreeNode<T> rootNode) {
        List<T> inorder = new ArrayList<>();
        TreeNode<T> current = rootNode;

        while (current != null) {
            if(current.left == null) {
                inorder.add(current.data);
                current = current.right;
            } else {
                //Get in-order predecessor
                TreeNode<T> predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                //This path has already been traversed
                if (predecessor.right == current) {
                    predecessor.right = null;
                    inorder.add(current.data);
                    current = current.right;
                } else {
                    /* Make current as right child of its inorder predecessor */
                    predecessor.right = current;
                    current = current.left;
                }
            }
        }

        return inorder;
    }
}
