package t00_binarytree.pcreekproblems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import t00_binarytree.BinaryTree;
import t01_bst.TreeNode;
import utils.TreeUtils;

/**
 * Problem:
 *  Design an algorithm to serialize and deserialize a binary tree.
 *  There is no restriction on how your serialization/deserialization algorithm should work.
 *  You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to
 *  the original tree structure.
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-serialize-and-deserialize-binary-tree-java/
 */
public class q29_SerializeDeserializeBinTree {
    public static void main(String[] args) {
        /* Constructed binary tree is
                  1
                /   \
               2     3
             /  \
            4    5
           /
          6
        */
        q29_SerializeDeserializeBinTree sdbt = new q29_SerializeDeserializeBinTree();

        BinaryTree tree = new BinaryTree();
        Integer[] data = new Integer[] {1, 2, 3, 4, 5,null, null, 6};
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        String encoding = sdbt.serialize(root);
        System.out.println(encoding);

        root = sdbt.deserialize(encoding);
        TreeUtils.printTree(root);

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode<Integer> root) {
        if(root == null) return "";

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.poll();
            if(current == null)
                sb.append("#,");
            else {
                sb.append(current.data).append(",");
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        sb.deleteCharAt(sb.length()-1); // Delete the last ,
        return sb.toString();
    }

    //Decodes a tree
    public TreeNode<Integer> deserialize(String data) {
        if(data==null || data.length()==0)
            return null;

        String[] arr = data.split(",");
        TreeNode<Integer> root = new TreeNode<>(Integer.parseInt(arr[0]));
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        for(int i=1; i<arr.length; i++) {
            TreeNode<Integer> current = queue.poll();
            if(arr[i].equals("#")) {
                current.left = null;
            } else {
                current.left = new TreeNode<>(Integer.parseInt(arr[i]));
                queue.offer(current.left);
            }

            i++;
            if(arr[i].equals("#")) {
                current.right = null;
            } else {
                current.right = new TreeNode<>(Integer.parseInt(arr[i]));
                queue.offer(current.right);
            }
        }
        return root;
    }

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        if(root==null)
            return null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            TreeNode h = stack.pop();
            if(h!=null){
                sb.append(h.data+",");
                stack.push(h.right);
                stack.push(h.left);
            }else{
                sb.append("#,");
            }
        }

        return sb.toString().substring(0, sb.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if(data == null)
            return null;

        int[] t = {0};
        String[] arr = data.split(",");

        return helper(arr, t);
    }

    public TreeNode helper(String[] arr, int[] t){
        if(arr[t[0]].equals("#")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[t[0]]));

        t[0]=t[0]+1;
        root.left = helper(arr, t);
        t[0]=t[0]+1;
        root.right = helper(arr, t);

        return root;
    }
}
