package t01_bst;

import utils.TreeNodeInterface;

public class TreeNode<T> implements TreeNodeInterface<T>{
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;
    public int height;
    public Color color;

    public static enum Color {RED, BLACK}

    public TreeNode(T data) {
        this.data = data;
        this.height = 0; //ht of leaf is 0
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

    public String toString() {
        return data.toString();
    }
}
