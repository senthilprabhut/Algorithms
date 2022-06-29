package datastructure;

import utils.TreeUtils;

public class BinaryTree<T> {
    public TreeNode<T> buildTree(T[] data) {
        return insert(data, 0);
    }

    private TreeNode<T> insert(T[] data, int i) {
        if (i < data.length && data[i] != null) {
            TreeNode<T> root = new TreeNode<>(data[i]);
            root.left = insert(data, 2*i + 1);
            root.right = insert(data, 2*i + 2);
            return root;
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        BinaryTree<Integer> tree = new BinaryTree<>();
        TreeNode<Integer> root = tree.buildTree(array);

        TreeUtils.printTree(root);
    }
}
