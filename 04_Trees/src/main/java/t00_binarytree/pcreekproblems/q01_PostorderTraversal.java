package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;

import java.util.*;

/**
 * Problem:
 *  Binary Tree Postorder Traversal
 *  The order of "postorder" is:  left child -> right child -> parent
 *  Parent node is processed after its children
 */
public class q01_PostorderTraversal<T> {
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

        q01_PostorderTraversal<Integer> pot = new q01_PostorderTraversal();
        System.out.println(pot.postorder1(root));
        System.out.println(pot.postorder2(root));
        System.out.println(pot.postorder3(root));
        System.out.println(pot.postorder4(root));

    }

    /**
     * Approach:
     *  Two Stack Approach
     *  This approach takes up more space compared to the solution using one stack
     *
     * Links:
     *  http://articles.leetcode.com/binary-tree-post-order-traversal/
     *  Tushar - https://youtu.be/qT65HltK2uE
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L79
     *  http://www.geeksforgeeks.org/iterative-postorder-traversal/
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<T> postorder1(TreeNode<T> root) {
        List<T> postorder = new ArrayList<>();
        if (root == null)
            return postorder;

        Deque<TreeNode<T>> stack1 = new ArrayDeque<>();
        Deque<TreeNode<T>> stack2 = new ArrayDeque<>();
        TreeNode<T> current = root;
        stack1.push(root);

        while (!stack1.isEmpty()) {
            current = stack1.pop();
            stack2.push(current);

            if (current.left != null)
                stack1.push(current.left);

            if (current.right != null)
                stack1.push(current.right);
        }

        //Reverse the order again by popping out of stack 2
        while (!stack2.isEmpty())
            postorder.add(stack2.pop().data);

        return postorder;
    }

    /**
     * Approach:
     *  One Stack Approach - Leetcode
     *  We use a prev variable to keep track of the previously-traversed node.
     *  If prev is curr‘s left child, we are traversing up the tree from the left. We look at curr‘s right child.
     *  If it is available, then traverse down the right child (ie, push right child to the stack), otherwise print curr‘s value and pop it off the stack.
     *  If it has a right child, then change root so that the right child is processed before.
     *
     * Links:
     *  http://articles.leetcode.com/binary-tree-post-order-traversal/
     */
    public List<T> postorder2(TreeNode<T> rootNode) {
        List<T> postorder = new ArrayList<>();
        if (rootNode == null)   // Check for empty tree
            return postorder;

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.push(rootNode);

        TreeNode<T> current = rootNode, prev = null;
        while (!stack.isEmpty()) {
            current = stack.peek();

            //We are traversing down the tree - if left is available, push it to stack and traverse it
            //If not available, look at curr's right child. If both left and right children are not available,
            //print the current node and pop it off the stack
            if(prev == null || prev.left == current || prev.right == current) {
                if(current.left != null)
                    stack.push(current.left);
                else if(current.right != null)
                    stack.push(current.right);
//                else {
//                    postorder.add(current.data);
//                    stack.pop()
//                }
            } // we are traversing up the tree from the left
            else if (prev == current.left) {
                if(current.right != null)
                    stack.push(current.right);
//                else {
//                    postorder.add(current.data);
//                    stack.pop();
//                }
            } // we are traversing up the tree from the right
//            else if (prev == current.right) {
//                    postorder.add(current.data);
//                    stack.pop();
//            }
            else {
                //current==prev or current.right == prev
                postorder.add(current.data);
                stack.pop();
            }
            prev = current; // record previously traversed node
        }
        return postorder;
    }

    /**
     * Approach:
     *  One Stack Approach - Tushar
     *
     * Links:
     *  Tushar - https://youtu.be/xLQKdq0Ffjg
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L98
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> postorder3(TreeNode<T> root) {
        List<T> postorder = new ArrayList<>();
        if (root == null)   // Check for empty tree
            return postorder;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = root;
        while (!stack.isEmpty() || current != null) {

            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode<T> temp = stack.peek().right;
                if(temp == null) {
                    //If leaf node, both left and right are null
                    temp = stack.pop();
                    postorder.add(temp.data);

                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        postorder.add(temp.data);
                    }
                } else {
                    current = temp;
                }
            }
        }
        return postorder;
    }

    /**
     * Approach:
     *  Recursive Approach
     *
     * Links:
     *  Tushar - https://youtu.be/qT65HltK2uE
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L36
     *  http://www.geeksforgeeks.org/iterative-postorder-traversal/
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<T> postorder4(TreeNode<T> root) {
        return postorder4Rec(root, new ArrayList<>());
    }

    private List<T> postorder4Rec(TreeNode<T> root, List<T> postorder) {
        if (root == null) return postorder;
        postorder4Rec(root.left, postorder);
        postorder4Rec(root.right, postorder);
        postorder.add(root.data);
        return postorder;
    }

    /**
     * Approach:
     *  Morris Approach
     *
     * Links:
     *  https://stackoverflow.com/questions/36384599/can-we-use-morris-traversal-for-postorder
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
}
