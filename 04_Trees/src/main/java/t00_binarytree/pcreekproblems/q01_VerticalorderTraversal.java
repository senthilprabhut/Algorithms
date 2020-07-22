package t00_binarytree.pcreekproblems;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

import java.util.*;

/**
 * Problem:
 *  Given a binary tree, return the vertical order traversal of its nodes' values.
 *  (ie, from top to bottom, column by column).
 *
 * Links:
 *  Both from https://www.programcreek.com/2014/04/leetcode-binary-tree-vertical-order-traversal-java/
 */
public class q01_VerticalorderTraversal<T> {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \   /  \
            4    5 6    7
        */
        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6, 7};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q01_VerticalorderTraversal<Integer> vot = new q01_VerticalorderTraversal<>();
        System.out.println(vot.verticalOrderTraversal1(root));
        System.out.println(vot.verticalOrderTraversal2(root));
    }


    /**
     * Approach:
     *  BFS based approach
     *  For each node, its left child's degree is -1 and is right child's degree is +1.
     *  We can do a level order traversal and save the degree information.
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>> verticalOrderTraversal1(TreeNode<T> rootNode) {
        List<List<T>> result = new ArrayList<>();
        if(rootNode == null)
            return result;


        Map<Integer, List<T>> map = new HashMap<>();

        Queue<TreeNode<T>> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        queue1.offer(rootNode);
        queue2.offer(0);

        int maxColumn = 0, minColumn = 0;
        while (! queue1.isEmpty()) {
            rootNode = queue1.poll();
            int col = queue2.poll();

            //track min and max levels
            maxColumn = Math.max(col,maxColumn);
            minColumn = Math.min(col, minColumn);

            if(! map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(rootNode.data);

            if(rootNode.left != null) {
                queue1.offer(rootNode.left);
                queue2.offer(col-1);
            }

            if(rootNode.right != null) {
                queue1.offer(rootNode.right);
                queue2.offer(col+1);
            }
        }

        for(int i=minColumn; i<=maxColumn; i++){
            if(map.containsKey(i)){
                result.add(map.get(i));
            }
        }
        return result;
    }

    /**
     * Approach:
     *  DFS based Recursive approach
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public List<List<T>> verticalOrderTraversal2(TreeNode<T> rootNode) {
        Map<Integer,List<T>> columnMap = new TreeMap<>();
        List<List<T>> result = new LinkedList<>();

        columnOrder(rootNode, 0, columnMap);
        for (List<T> value : columnMap.values()) {
            result.add(value);
        }
        return result;
    }

    private void columnOrder(TreeNode<T> rootNode, Integer col, Map<Integer,List<T>>columnMap) {
        if(rootNode == null) return;

        List<T> column = columnMap.computeIfAbsent(col, i -> new LinkedList<T>());
        column.add(rootNode.data);
        columnOrder(rootNode.left, col-1, columnMap);
        columnOrder(rootNode.right, col+1, columnMap);
    }
}
