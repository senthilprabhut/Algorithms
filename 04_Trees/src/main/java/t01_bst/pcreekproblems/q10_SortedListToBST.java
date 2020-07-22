package t01_bst.pcreekproblems;

import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * Problem:
 *  Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * Links:
 *  https://www.programcreek.com/2013/01/leetcode-convert-sorted-list-to-binary-search-tree-java/
 */
public class q10_SortedListToBST {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 5, 6, 9 ,12, 18};

        q10_SortedListToBST atb = new q10_SortedListToBST();
        List<Integer> inputList = Arrays.stream(input).boxed().collect(Collectors.toList());
        TreeUtils.printTree(atb.sortedListToBST(inputList));
    }

    /**
     * Approach:
     *  In-order like approach - build left, root and then right tree
     *  If you are given an array, the problem is quite straightforward. But things get a little more complicated
     *  when you have a singly linked list instead of an array. Now you no longer have random access to an element in O(1) time.
     *  Therefore, you need to create nodes bottom-up, and assign them to its parents.
     *  The bottom-up approach enables us to access the list in its order at the same time as creating nodes.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is  O(h) = O(log n)
     */
    public TreeNode<Integer> sortedListToBST(List<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator();
        return sortedListToBST(list, iterator, 0, list.size()-1);
    }

    private TreeNode<Integer> sortedListToBST(List<Integer> list, ListIterator<Integer> iterator, int start, int end) {
        if(start > end) return null;

        int mid = (start + end)/2;
        TreeNode<Integer> left = sortedListToBST(list,iterator,start, mid-1);
        TreeNode<Integer> root = new TreeNode<>(iterator.next());
        TreeNode<Integer> right = sortedListToBST(list,iterator,mid+1, end);

        root.left = left;
        root.right = right;
        return root;
    }
}
