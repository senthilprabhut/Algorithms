package t01_bst;

import java.util.*;
import java.util.stream.Collectors;

/**
 * BINARY SEARCH TREE
 *  The worst case scenario of binary search tree is when all the values are children of prev values
 *
 * Links:
 *  Solution - https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
 *  Delete - https://youtu.be/gcULXE7ViZw
 *  Delete Solution - https://gist.github.com/mycodeschool/9465a188248b624afdbf
 *
 * Complexity:
 *  insert - O(log n), worst case O(n)
 *  delete - O(log n), worst case O(n)
 *  search - O(log n), worst case O(n)
 */
public class BinarySearchTree<T extends Comparable> {
    private class DefaultComparator implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
    private TreeNode<T> rootNode;
    private Comparator<T> comparator = new DefaultComparator();

    /*****************************************************
     *
     *            Constructor
     *
     ******************************************************/
    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    /*****************************************************
    *
    *            INSERT
    *
    ******************************************************/
    public void add(List<T> values) {
        for(T value : values)
            add(value);
    }

    public void add(T toInsert) {
        rootNode = insert(rootNode, toInsert);
    }

    private TreeNode<T> insert(TreeNode<T> root, T toInsert) {
        //if leaf node, create a new node
        if (null == root) {
            return new TreeNode<>(toInsert);
        }

        if(comparator.compare(root.data,toInsert) > 0) {
            //if(null == rootNode.left) rootNode.left = new TreeNode<>(value);
            root.left = insert(root.left, toInsert);
        } else if (comparator.compare(root.data,toInsert) < 0) {
            //if (null == rootNode.right) rootNode.right = new TreeNode<>(value);
            root.right = insert(root.right, toInsert);
        }

        //Return the node if value==rootNode.data
        return root;
    }

    /*****************************************************
     *
     *            SEARCH
     *
     ******************************************************/
    public boolean search(T toSearch) {
        return search(rootNode,toSearch);
    }

    private boolean search(TreeNode<T> root, T toSearch) {
        if(root == null)
            return false;

        if (comparator.compare(root.data, toSearch) > 0)
            return search(root.left, toSearch);
        else if (comparator.compare(root.data, toSearch) < 0)
            return search(root.right, toSearch);

        //if comparator equal to 0, value matches root.data
        return true;
    }

    /*****************************************************
     *
     *            DELETE
     *
     ******************************************************/
    public void delete(T toDelete) {
        delete(rootNode, toDelete);
    }

    private TreeNode<T> delete(TreeNode<T> root, T data) {
        if(root == null) return null;

        int compareValue = comparator.compare(root.data,data);
        if(compareValue > 0)
            root.left = delete(root.left, data);
        else if(compareValue < 0)
            root.right = delete(root.right, data);
        else {
            //Delete value has matched
            // Case 1:  No child
            if(root.right == null && root.left == null)
                root = null;
            //Case 2: One child - return the child node
            else if(root.right == null)
                root = root.left;
            else if(root.left == null)
                root = root.right;
            //case 3: 2 children
            else {
                //1. find the min value from the right subtree (inorder successor of deleted node)
                TreeNode<T> minValueNode = root.right;
                while (minValueNode.left != null) minValueNode = minValueNode.left;
                //2. swap its value with the node to be deleted
                root.data = minValueNode.data;
                //3. delete the min value node
                root.right = delete(root.right, minValueNode.data);
            }
        }
        return root;
    }

    /*************************************************
     *
     *            TRAVERSAL
     *
     **************************************************/
    
    public List<T> inOrderTraversal() {
        return inOrderHelper(rootNode, new ArrayList<>());
    }

    private List<T> inOrderHelper(TreeNode<T> root, List<T> results) {
        if(root == null) return results;

        inOrderHelper(root.left, results);
        results.add(root.data);
        inOrderHelper(root.right, results);
        return results;
    }

    public List<T> preOrderTraversal() {
        return preOrderHelper(rootNode, new ArrayList<>());
    }

    private List<T> preOrderHelper(TreeNode<T> root, List<T> results) {
        if(root == null) return results;
        results.add(root.data);
        preOrderHelper(root.left, results);
        preOrderHelper(root.right, results);
        return results;
    }

    public List<T> bredthFirstTraversal() {
        return bfsHelper(rootNode, new ArrayList<>());
    }

    private List<T> bfsHelper(TreeNode<T> startNode, List<T> results) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(startNode);

        while(!queue.isEmpty()) {
          TreeNode<T> node = queue.poll();
          results.add(node.data);
          if(null != node.left) queue.offer(node.left);
          if(null != node.right) queue.offer(node.right);
        }
        return results;
    }

    /*************************************************
     *
     *            CLONE
     *
     **************************************************/
    public BinarySearchTree<T> clone() {
        BinarySearchTree<T> clonedBST = new BinarySearchTree<>(comparator);
        clonedBST.rootNode = cloneHelper(rootNode);
        return clonedBST;
    }

    private TreeNode<T> cloneHelper(TreeNode<T> node) {
        if(node == null) return null;

        TreeNode<T> newNode = new TreeNode<>(node.data);
        newNode.left = cloneHelper(node.left);
        node.right = cloneHelper(node.right);
        return newNode;
    }

    /*************************************************
     *
     *            Tree Properties
     *
     **************************************************/
    public int height() {
        return height(rootNode);
    }
    private int height(TreeNode<T> root) {
        //Height of leaf node is 0 (1 + Max(-1,-1) )
        if(root == null) return -1;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int countLeaves() {
        return countLeaves(rootNode);
    }
    private int countLeaves(TreeNode<T> p) {
        if(p == null) return 0;
        if(p.left == null && p.right == null) return 1;

        return countLeaves(p.left) + countLeaves(p.right);
    }

    //The width of a binary tree is the maximum number of elements on one level of the tree.
    public int width() {
        int max=0;
        for(int i=0; i<=height(); i++) {
            int width = width(rootNode,i);
            if(width > max) max=width;
        }
        return max;
    }
    private int width(TreeNode<T> node, int depth) {
        if(node == null) return 0;
        if(depth == 0) return 1;
        return width(node.left, depth-1) + width(node.right, depth-1);
    }

    //The getDiameter of a tree is the number of nodes on the longest path between two leaves in the tree
    //Moved to problems since it had two solutions


    /*************************************************
     *
     *            Print Tree
     *
     **************************************************/

    public void printTree() {
        if(null == rootNode) {
            System.out.println("No tree exists to print");
            return;
        }

        printTree(rootNode.right, true, "");
        System.out.println(rootNode.data);
        printTree(rootNode.left,false, "");
    }

    private void printTree(TreeNode<T> startNode, boolean isRight, String indent) {
        if(startNode == null) return;
        printTree(startNode.right, true, indent + (isRight ? "        " : " |      ") );

        System.out.print(indent);
        if(isRight) System.out.print(" /-----  ");
        else System.out.print(" \\-----  ");
        System.out.println(startNode.data);

        printTree(startNode.left, false, indent + (isRight ? " |      " : "        "));
    }

    /*************************************************
     *
     *            Main
     *
     **************************************************/
    public static void main(String[] args) {
        BinarySearchTree<Integer> intTree = new BinarySearchTree<>();
        int[] input = {5,18, 9, 6, 12, 1, 3, 2};
        List<Integer> listOfNumbers = Arrays.stream(input).boxed().collect(Collectors.toList());
        intTree.add(listOfNumbers);

        System.out.println("Input is " + Arrays.toString(input));
        intTree.printTree();
        System.out.println();


        System.out.println("Inserting 24...");
        intTree.add(Integer.valueOf(24));
        intTree.printTree();
        System.out.println();

        System.out.println("Deleting 9...");
        intTree.delete(9);
        intTree.printTree();
        System.out.println();

        System.out.println("Inorder Traversal:" + intTree.inOrderTraversal());
        System.out.println("Preorder Traversal: " + intTree.preOrderTraversal());
        System.out.println("Bredth Order Traversal: " + intTree.bredthFirstTraversal());
        System.out.println();

        System.out.println("Height of the tree is " + intTree.height());
        System.out.println("Total leaves in tree is " + intTree.countLeaves());
        System.out.println("Width of the tree is " + intTree.width());
    }
}
