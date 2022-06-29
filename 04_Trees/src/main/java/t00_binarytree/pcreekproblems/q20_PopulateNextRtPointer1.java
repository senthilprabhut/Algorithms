package t00_binarytree.pcreekproblems;

import datastructure.TreeNodeInterface;
import utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Given a perfect binary tree, populate next right pointers in each node
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-populating-next-right-pointers-in-each-node-java/
 *
 */
public class q20_PopulateNextRtPointer1 {
    public static void main(String[] args) {
        /* Constructed binary tree is
                 1
               /  \
              2    3
             / \  / \
            4  5  6  7
         */
        q20_PopulateNextRtPointer1 pnp = new q20_PopulateNextRtPointer1();

        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        TreeLinkNode<Integer> root = TreeLinkNode.buildTree(data);
        TreeUtils.printTree(root);
        pnp.connect(root);
        printNextLinks(root);

        System.out.println();
        TreeLinkNode<Integer> root2 = TreeLinkNode.buildTree(data);
        pnp.connect2(root2);
        printNextLinks(root2);
    }

    public static void printNextLinks(TreeLinkNode<Integer> root) {
        //Traverse the tree and print the links
        Queue<TreeLinkNode<Integer>> queue1 = new LinkedList<>();
        Queue<TreeLinkNode<Integer>> queue2 = new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty()) {
            TreeLinkNode<Integer> current = queue1.poll();
            System.out.printf("%d->", current.data); //current.next==null?null:current.next.data
            if(current.left != null) queue2.add(current.left);
            if(current.right != null) queue2.add(current.right);

            if(queue1.isEmpty()) {
                Queue<TreeLinkNode<Integer>> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
                System.out.println(current.next); //print the null value
            }
        }
    }

    /**
     * Approach:
     *  Do a BFS and add from right to left - so that the right node can
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n), n/2 leaves will be added to queue in the leaf level
     */
    public void connect(TreeLinkNode<Integer> root) {
        if(root == null)
            return;

        Queue<TreeLinkNode<Integer>> queue1 = new LinkedList<>();
        Queue<TreeLinkNode<Integer>> queue2 = new LinkedList<>();
        queue1.add(root);

        TreeLinkNode<Integer> current=null, prev=null;
        while (!queue1.isEmpty()) {
            current = queue1.poll();

            if(prev != null) {
                prev.next = current;
                prev = prev.next;
            }
            if(current.left != null)
                queue2.add(current.left);

            if(current.right != null)
                queue2.add(current.right);


            //Swap the queues if one level is completed
            if(queue1.isEmpty()) {
                Queue<TreeLinkNode<Integer>> temp = queue1;
                queue1 = queue2;
                queue2 = temp;

                //Also set prev to null
                prev = null;
            }
        }
    }

    /**
     * Approach:
     *  The basic idea is have 4 pointers to move towards right on two levels
     *                             1
     *                           /  \
     * lastHead, lastCurrent->  2    3
     *                         / \  / \
     * currentHead, current-> 4  5  6  7
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public void connect2(TreeLinkNode<Integer> root) {
        if(root == null)
            return;

        TreeLinkNode<Integer> prevLvlHead = root, prevLvlPtr = null; //previous level's head and pointer
        TreeLinkNode<Integer> currLvlHead = null, currLvlPtr = null; //current level's head and pointer

        while (prevLvlHead != null) {
            prevLvlPtr = prevLvlHead;
            while (prevLvlPtr != null) { //Loop through the first level, set links in the 2nd level
                if(currLvlHead == null) { //processing first node in the 2nd level
                    currLvlHead = prevLvlPtr.left;
                    currLvlPtr = prevLvlPtr.left;
                } else {
                    currLvlPtr.next = prevLvlPtr.left;
                    currLvlPtr = currLvlPtr.next; //move the pointer forward
                }

                if(currLvlHead != null) { //since this is a perfect bin tree, a left node will always have a right node
                    currLvlPtr.next = prevLvlPtr.right;
                    currLvlPtr = currLvlPtr.next;
                }
                prevLvlPtr = prevLvlPtr.next;
            }
            //If one level is traversed, move one level down and do the same 2 pointer traversal
            prevLvlHead = currLvlHead;
            currLvlHead = null;
        }
    }

    public static class TreeLinkNode<T> implements TreeNodeInterface<T> {
        public T data;
        public TreeLinkNode<T> left;
        public TreeLinkNode<T> right;
        public TreeLinkNode<T> next;

        public TreeLinkNode(T data) {
            this.data = data;
        }

        public String toString() {
            return data.toString();
        }

        public static TreeLinkNode<Integer> buildTree(Integer[] data) {
            return insert(data, 0);
        }

        private static TreeLinkNode<Integer> insert(Integer[] data, int i) {
            if (i < data.length && data[i] != null) {
                TreeLinkNode<Integer> root = new TreeLinkNode<>(data[i]);
                root.left = insert(data, 2*i + 1);
                root.right = insert(data, 2*i + 2);
                return root;
            }
            return null;
        }

        @Override
        public T data() {
            return data;
        }

        @Override
        public TreeNodeInterface<T> left() {
            return left;
        }

        @Override
        public TreeNodeInterface<T> right() {
            return right;
        }
    }
}

