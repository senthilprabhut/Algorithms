package datastructure;

import java.util.Optional;
/**
 * RED BLACK TREE
 *  Does not work for duplicates.
 *
 * Links:
 *  Tushar Roy - https://youtu.be/UaLIHuR1t8Q
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/RedBlackTree.java
 *
 * Complexity:
 *  Insert - O(logn)
 *  Delete - O(logn)
 *  Search - O(logn)
 */
public class RedBlackTree {
    public enum Color {RED, BLACK};

    public class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;
        private Color color;
        private TreeNode parent;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        RedBlackTree redBlackTree = new RedBlackTree();

        root = redBlackTree.insert(root, 10);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 15);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, -10);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 20);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 30);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 40);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 50);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, -15);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 25);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 17);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 21);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 24);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 28);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 34);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 32);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 26);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 35);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        root = redBlackTree.insert(root, 19);
//        redBlackTree.printRedBlackTree(root);
//        System.out.println("----");

        redBlackTree.printRedBlackTree(root);
    }

    private TreeNode createBlackNode(int data) {
        TreeNode n = new TreeNode();
        n.data = data;
        n.color = Color.BLACK;
        n.left = null;
        n.right = null;
        n.parent = null;
        return n;
    }

    private TreeNode createRedNode(TreeNode parent, int data) {
        TreeNode n = new TreeNode();
        n.data = data;
        n.color = Color.RED;
        n.left = null;
        n.right = null;
        n.parent = parent;
        return n;
    }

    /**
     * Main insert method of red black tree.
     */
    public TreeNode insert(TreeNode root, int data) {
        return insert(null, root, data);
    }

    private TreeNode insert(TreeNode parent, TreeNode root, int data) {
        if (root == null) {
            //if the parent is not null means tree is not empty. So create red node
            if (parent != null) {
                return createRedNode(parent, data);
            } else {
                return createBlackNode(data);
            }
        }
        //duplicate insertion is not allowed for this tree.
        if (root.data == data) {
            throw new IllegalArgumentException("Duplicate data: " + data);
        }

        //if we go on left, isLeft is true. If we go on right, isLeft is false
        boolean isLeft = false;
        if (root.data > data) {
            TreeNode left = insert(root, root.left, data);
            //if left becomes root's parent, it means a rotation has happened at lower level.
            //Return this left so that nodes at upper level can set their child correctly
            if (left == root.parent) return left;
            root.left = left;
            isLeft = true;
        } else {
            TreeNode right = insert(root, root.right, data);
            //if right becomes root's parent, then rotation happened and so return right node
            if (right == root.parent) return right;
            root.right = right;
            isLeft = false;
        }

        if (isLeft) {
            //check red-red conflict between root and its left child
            if (root.color == Color.RED && root.left.color == Color.RED) {
                Optional<TreeNode> sibling = getSibling(root);
                //If sibling is empty or BLACK colour, rotation occurs
                if (!sibling.isPresent() || sibling.get().color == Color.BLACK) {
                    //LEFT-LEFT situation - so, do one right rotate
                    if (isLeftChild(root)) rightRotate(root, true);
                    else {
                        //LEFT-RIGHT situation - do one right rotate followed by left rotate
                        rightRotate(root.left, false);
                        //when right rotation is done root becomes right child of its left
                        //child. So make root = root.parent because its left child before rotation
                        //is new root of this subtree.
                        root = root.parent;
                        leftRotate(root, true);
                    }
                } else {
                    //We have sibling color as RED. So change color of root and its sibling to Black.
                    //And then change color of their parent to red if their parent is not a root
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    //if parent's parent is not null means parent is not root.
                    if (root.parent.parent != null) root.parent.color = Color.RED;
                }
            }
        } else {
            //this is a mirror case of above
            if (root.color == Color.RED && root.right.color == Color.RED) {
                Optional<TreeNode> sibling = getSibling(root);
                if (!sibling.isPresent() || sibling.get().color == Color.BLACK) {
                    //RIGHT-RIGHT situation - so do one left rotate
                    if (!isLeftChild(root)) leftRotate(root, true);
                    else {
                        //RIGHT-LEFT situation - do a left rotate followed by a right rotate
                        leftRotate(root.right, false);
                        root = root.parent;
                        rightRotate(root, true);
                    }
                } else {
                    //Just change colour
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    if (root.parent.parent != null) root.parent.color = Color.RED;
                }
            }
        }
        return root;
    }

    /**
     * Main print method of red black tree.
     */
    public void printRedBlackTree(TreeNode root) {
        printRedBlackTree(root.right, true, "");
        System.out.println(root.data + " " + (root.color == Color.BLACK ? "B" : "R"));
        printRedBlackTree(root.left, false, "");
    }


    private void printRedBlackTree(TreeNode root, boolean isRight, String indent) {
        if(root == null) {
            return;
        }
        printRedBlackTree(root.right, true, indent + (isRight ? "        " : " |      "));
        System.out.print(indent);
        if(isRight)
            System.out.print(" /");
        else
            System.out.print(" \\");
        System.out.print("----- ");
        System.out.println(root.data + " " + (root.color == Color.BLACK ? "B" : "R"));
        printRedBlackTree(root.left, true, indent + (isRight ? " |      " : "        "));
    }

    private void rightRotate(TreeNode newRoot, boolean changeColor) {
        //newRoot's parent is involved in right rotate
        TreeNode parent = newRoot.parent, right = newRoot.right;
        ;
        newRoot.parent = parent.parent;
        if (parent.parent != null) {
            if (parent.parent.left == parent) parent.parent.left = newRoot;
            else parent.parent.right = newRoot;
        }
        newRoot.right = parent;
        parent.parent = newRoot;

        parent.left = right;
        if (right != null) right.parent = parent;

        if (changeColor) {
            newRoot.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    private void leftRotate(TreeNode newRoot, boolean changeColor) {
        TreeNode parent = newRoot.parent, left = newRoot.left;

        newRoot.parent = parent.parent;
        if (parent.parent != null) {
            if (parent.parent.left == parent) parent.parent.left = newRoot;
            else parent.parent.right = newRoot;
        }
        newRoot.left = parent;
        parent.parent = newRoot;

        parent.right = left;
        if (left != null) left.parent = parent;

        if (changeColor) {
            newRoot.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    private Optional<TreeNode> getSibling(TreeNode root) {
        TreeNode parent = root.parent;
        if (isLeftChild(root))
            return Optional.ofNullable(parent.right);
        else
            return Optional.ofNullable(parent.left);
    }

    private boolean isLeftChild(TreeNode root) {
        TreeNode parent = root.parent;
        if (parent.left == root)
            return true;
        else
            return false;
    }
}
