package t00_binarytree.pcreekproblems;

import utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

import static t00_binarytree.pcreekproblems.q20_PopulateNextRtPointer1.TreeLinkNode;

/**
 * Problem:
 *  Follow up for problem "Populating Next Right Pointers in Each Node".
 *  What if the given tree could be any binary tree?
 *
 * Links:
 *  https://www.programcreek.com/2014/06/leetcode-populating-next-right-pointers-in-each-node-ii-java/
 *
 */
public class q20_PopulateNextRtPointer2 {
    public static void main(String[] args) {
        /* Constructed binary tree is
                 1
               /  \
              2    3
             / \    \
            4  5     7
           /
          8
        */
        q20_PopulateNextRtPointer2 pnp = new q20_PopulateNextRtPointer2();

        Integer[] data = new Integer[]{1, 2, 3, 4, 5, null, 7, 8};
        TreeLinkNode<Integer> root = TreeLinkNode.buildTree(data);
        TreeUtils.printTree(root);
        pnp.connect(root);
        q20_PopulateNextRtPointer1.printNextLinks(root);

        System.out.println();
        TreeLinkNode<Integer> root2 = TreeLinkNode.buildTree(data);
        pnp.connect(root2);
        q20_PopulateNextRtPointer1.printNextLinks(root2);
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
        if(root == null) return;

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
                queue2.offer(current.left);

            if(current.right != null)
                queue2.offer(current.right);

            if(queue1.isEmpty()) {
                Queue<TreeLinkNode<Integer>> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }
        }
    }

    /**
     * Approach:
     *  The basic idea is have 4 pointers to move towards right on two levels
     *                                1
     *                               /  \
     *       prevHead, prevPtr ->   2    3
     *                             / \    \
     * currentHead, currentPtr -> 4  5     7
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(n)
     */
    public void connect2(TreeLinkNode<Integer> root) {
        if(root == null) return;

        TreeLinkNode<Integer> prevLvlHead = root, prevLvlPtr = null;
        TreeLinkNode<Integer> currLvlHead = null, currLvlPtr = null;

        while (prevLvlHead != null) {
            prevLvlPtr = prevLvlHead;
            while (prevLvlPtr != null) {
                //left child is not null
                if(prevLvlPtr.left != null) {
                    if (currLvlHead == null) {
                        currLvlHead = prevLvlPtr.left;
                        currLvlPtr = prevLvlPtr.left;
                    } else {
                        currLvlPtr.next = prevLvlPtr.left;
                        currLvlPtr = currLvlPtr.next;
                    }
                }

                //right child is not null
                if(prevLvlPtr.right!=null) {
                    if (currLvlHead == null) {
                        currLvlHead = prevLvlPtr.right;
                        currLvlPtr = prevLvlPtr.right;
                    } else {
                        currLvlPtr.next = prevLvlPtr.right;
                        currLvlPtr = currLvlPtr.next;
                    }
                }
            }
            //End of level
            prevLvlHead = currLvlHead;
            currLvlHead = null;
        }
    }
}
