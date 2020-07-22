package t01_bst;

import utils.TreeUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Links:
 *  Tree Traversals - https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java
 *  http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 */
public class BSTSimple<T> {
    private Comparator<T> comparator;

    /*****************************************************
     *            Constructor
     ******************************************************/
    public BSTSimple(Comparator<T> comparator) {
        this.comparator = comparator;
    }


    /*****************************************************
     *            INSERT
     ******************************************************/
    public TreeNode<T> add(TreeNode<T> rootNode, List<T> dataList) {
        for(T data : dataList)
            rootNode = insert(rootNode, data);
        return rootNode;
    }

    public TreeNode<T> insert(TreeNode<T> root, T data) {
        //New nodes are inserted at the leaf
        if(root == null) {
            return new TreeNode<>(data);
        }
        TreeNode<T> parent = null, current = root;
        while (current != null) {
            parent = current;
            if (comparator.compare(data, current.data) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if (comparator.compare(data, parent.data) < 0)
            parent.left = new TreeNode<>(data);
        else
            parent.right = new TreeNode<>(data);
        return root;
    }

    /*****************************************************
     *
     *            SEARCH
     *
     ******************************************************/
    public TreeNode<T> search(TreeNode<T> root, T data) {
        TreeNode<T> current = root;
        while (current != null) {
            if(comparator.compare(data, current.data) == 0)
                break;
            if (comparator.compare(data, current.data) < 0)
                current = current.left;
            else
                current = current.right;
        }
        return current;
    }

    /*************************************************
     *
     *            TRAVERSAL
     *
     **************************************************/
    public List<T> inorderTraversal(TreeNode<T> root, List<T> result) {
        if (root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> node = root;
        while (true) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if(stack.isEmpty()) break;
                node = stack.pop();
                result.add(node.data);
                node = node.right;
            }
        }
        return result;
    }

    public List<T> preorderTraversal(TreeNode<T> root, List<T> result) {
        if (root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> node = root;
        while (true) {
            if(node != null) {
                result.add(node.data);
                stack.push(node);
                node = node.left;
            } else {
                if(stack.isEmpty()) break;
                node = stack.pop();
                node = node.right;
            }
        }
        return result;
    }

    /*public List<T> preorderTraversal(TreeNode<T> root, List<T> result) {
        if(root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(root);

        while(! stack.isEmpty()) {
            root = stack.pop();
            result.add(root.data); //Process root first
            //Push right one first so that it'll be popped 2nd
            if(root.right != null) {
                stack.push(root.right);
            }
            //Push left one 2nd so that it'll be popped first
            if(root.left != null) {
                stack.push(root.left);
            }
        }
        return result;
    }*/

    public List<T> postorderTraversal(TreeNode<T> root, List<T> result) {
        if(root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> node = root;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode<T> temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.pop();
                    result.add(temp.data);
                    //IF the right node is processed, process the root node also
                    while(! stack.isEmpty() && stack.peek().right == temp) {
                        temp = stack.pop();
                        result.add(temp.data);
                    }
                }
                else {
                    node = temp;
                }
            }
        }
        return result;
    }

    /*************************************************
     *
     *            Main
     *
     **************************************************/
    public static void main(String[] args) {
        BSTSimple<Integer> intTree = new BSTSimple<>(Comparator.comparingInt(a -> a));
        int[] input = {5,18, 9, 6, 12, 1, 3, 2};
        List<Integer> listOfNumbers = Arrays.stream(input).boxed().collect(Collectors.toList());
        TreeNode<Integer> root = intTree.add(null, listOfNumbers);
        TreeUtils.printTree(root);

        TreeNode<Integer> search = intTree.search(root, 3);
        System.out.printf("Is Node 3 present in BST: %b\n", (search != null));

        System.out.println("Inorder traversal is " + intTree.inorderTraversal(root, new ArrayList<>()));
        System.out.println("Preorder traversal is " + intTree.preorderTraversal(root, new ArrayList<>()));
        System.out.println("Postorder traversal is " + intTree.postorderTraversal(root, new ArrayList<>()));
    }
}



