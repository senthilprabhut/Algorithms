package t00_binarytree.pcreekproblems;

import datastructure.BSTSimple;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.Comparator;

/**
 * Problem:
 *  Given postorder and inorder traversal of a tree, construct the binary tree.
 *
 * Links:
 *  https://www.programcreek.com/2013/01/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class q08_ConstructTreeInorderPostorder<T> {
    private Comparator<T> comparator;

    public static void main(String[] args) {
        q08_ConstructTreeInorderPostorder<Integer> ctip = new q08_ConstructTreeInorderPostorder<>(Comparator.comparingInt(a -> a));
        TreeNode<Integer> root = ctip.restore(new Integer[] {2, 3, 1, 6, 12, 9, 18, 5},
                new Integer[]{1, 2, 3, 5, 6, 9, 12, 18});

        BSTSimple<Integer> bst = new BSTSimple<>(Comparator.comparingInt(a -> a));
        TreeUtils.printTree(root);
    }

    public q08_ConstructTreeInorderPostorder(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public TreeNode<T> restore(T[] postOrder, T[] inOrder) {
        return buildTree(postOrder, 0, postOrder.length-1,
                inOrder, 0, inOrder.length-1);
    }

    /**
     * Approach:
     *  Pick the root node from post-order array and set its left and right values from the
     *  inorder array. First get the index of the root in inorder array and all elements left of
     *  it are the left subtree and all elements right of it is the right subtree
     *
     * Complexity:
     *  Time Complexity - worst case occurs when the tree is skewed O(n²). Avg case is O(n log n)
     *  Space Complexity - O(n) for the no of recursive calls made
     */
    private TreeNode<T> buildTree(T[] postOrder, int pStart, int pEnd, T[] inOrder, int iStart, int iEnd) {
        if(iStart > iEnd) return null;

        //Get the root node from postOrder array - it is the last element
        T val = postOrder[pEnd];
        TreeNode<T> node = new TreeNode<>(val);

        // If this node has no children then return
        if(iStart == iEnd) return node;

        //Find the index of the item in the inOrder array
        int mid=(iStart+iEnd)/2, tempStart=iStart, tempEnd=iEnd;
        while(comparator.compare(val,inOrder[mid]) != 0) {
            if(comparator.compare(val,inOrder[mid]) < 0)
                tempEnd=mid;
            else
                tempStart=mid+1;
            mid = (tempStart+tempEnd)/2;
        }

        //Create the left and right subtrees
        node.left = buildTree(postOrder, pStart , pStart+(mid-iStart)-1, inOrder, iStart, mid-1);
        node.right = buildTree(postOrder, pStart+(mid-iStart), pEnd-1, inOrder, mid+1, iEnd);
        return node;
    }
}
