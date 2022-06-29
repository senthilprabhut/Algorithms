package t00_binarytree.pcreekproblems;

import datastructure.BSTSimple;
import datastructure.TreeNode;
import utils.TreeUtils;

import java.util.Comparator;

/**
 * Problem:
 *  Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Links:
 *  Solution - https://www.programcreek.com/2014/06/leetcode-construct-binary-tree-from-preorder-and-inorder-traversal-java/
 *  http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 */
public class q08_ConstructTreeInorderPreorder<T> {
    private Comparator<T>  comparator;

    public static void main(String[] args) {
        q08_ConstructTreeInorderPreorder<Integer> ctip = new q08_ConstructTreeInorderPreorder<>(Comparator.comparingInt(a -> a));
        //testing restoring a tree from two given traversals
        TreeNode<Integer> root = ctip.restore(new Integer[] {5, 1, 3, 2, 18, 9, 6, 12},
                new Integer[] {1, 2, 3, 5, 6, 9, 12, 18});

        BSTSimple<Integer> bstSimple = new BSTSimple<>(Comparator.comparingInt(a -> a));
        TreeUtils.printTree(root);
    }

    public q08_ConstructTreeInorderPreorder(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public TreeNode<T> restore(T[] preorder, T[] inorder) {
        return buildTree(preorder, 0, preorder.length-1,
                inorder, 0, inorder.length-1);
    }

    /**
     * Approach:
     *  Pick the root node from pre-order array and set its left and right values from the
     *  inorder array. First get the index of the root in inorder array and all elements left of
     *  it are the left subtree and all elements right of it is the right subtree
     *
     * Complexity:
     *  Time Complexity - worst case occurs when the tree is skewed O(n²). Avg case is O(n log n)
     *  Space Complexity - O(n) for the no of recursive calls made
     */
    private TreeNode<T> buildTree(T[] preorder, int pStart, int pEnd, T[] inorder, int iStart, int iEnd) {
        if(iStart > iEnd) return null;

        // Pick current node from Preorder traversal using preIndex and increment preIndex
        T val = preorder[pStart];
        TreeNode<T> node = new TreeNode<>(val);

        // If this node has no children then return
        if(iStart == iEnd) return node;

        // Else find the index of this node in Inorder traversal  - Binary search in O(log n)
        int mid = (iStart + iEnd) / 2, tempStart = iStart, tempEnd = iEnd;
        while ( comparator.compare(val, inorder[mid]) != 0) {
            if (comparator.compare(val, inorder[mid]) < 0)
                tempEnd = mid;
            else
                tempStart = mid + 1;
            mid = (tempStart + tempEnd) / 2;
        }

        // Using index in Inorder traversal, construct left and right subtrees
        //mid - inStart gives the no of elements in the left tree
        node.left = buildTree(preorder, pStart+1, pStart+(mid-iStart), inorder, iStart, mid-1);
        node.right = buildTree(preorder, pStart+(mid-iStart)+1, pEnd, inorder, mid+1, iEnd);

        return node;
    }
}
