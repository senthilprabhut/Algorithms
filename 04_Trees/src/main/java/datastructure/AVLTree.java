package datastructure;

/**
 * AVL TREE
 *  AVL tree is self balancing binary tree
 *  Difference of heightWithDiameter of left or right subtree cannot be greater than one
 *  Height of an AVL tree is always O(log n) where n is the no of nodes in the tree
 *
 *  Follow rotation rules to keep tree balanced
 *  At every node we will also keep heightWithDiameter of the tree so that we don't have to recalculate values again
 *
 * Link:
 *  Tushar Roy - https://youtu.be/rbg7Qf8GkQ4
 *  Solution Insert - http://www.geeksforgeeks.org/?p=17679
 *  Solution Delete - http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 *
 * Complexity:
 *  insert - O(log n), (in worst case O(2*log n) ~= O(log n) )
 *  delete - O(log n)
 *  search - O(log n)
 */
public class AVLTree<T extends Comparable> {
    private TreeNode<T> rootNode;

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(5);
        avlTree.printTree();
        avlTree.insert(2);
        avlTree.printTree();
        avlTree.insert(7);
        avlTree.printTree();
        avlTree.insert(1);
        avlTree.printTree();
        avlTree.insert(4);
        avlTree.printTree();
        avlTree.insert(6);
        avlTree.printTree();
        avlTree.insert(9);
        avlTree.printTree();
        avlTree.insert(3);
        avlTree.printTree();
        avlTree.insert(16);
        avlTree.printTree();
        avlTree.insert(15);
        avlTree.printTree();
        avlTree.insert(17);
        avlTree.printTree();
        avlTree.insert(8);
        avlTree.printTree();

        System.out.println("DELETING KEYS");
        avlTree.delete(9);
        avlTree.printTree();
    }

    public void printTree() {
        if(null == rootNode) System.out.println("No tree exists to print");

        printTree(rootNode.right, true, "");
        System.out.println(rootNode.data + " (" + rootNode.height + "," + (height(rootNode.left) - height(rootNode.right)) + ")");
        printTree(rootNode.left, false, "");
        System.out.println("---");
    }

    private void printTree(TreeNode<T> root, boolean isRight, String indent) {
        if(null == root) return;
        printTree(root.right, true, indent + (isRight ? "        " : " |      "));
        System.out.print(indent);
        if(isRight)
            System.out.print(" /");
        else
            System.out.print(" \\");
        System.out.print("----- ");
        System.out.println(root.data + " (" + root.height + "," + (height(root.left) - height(root.right)) + ")");
        printTree(root.left, false, indent + (isRight ? " |      " : "        ") );
    }

    public void insert(T key) {
        rootNode = insert(rootNode, key);
    }

    /**
     * There are four different use cases to insert into AVL tree
     *  left left - needs ones right rotation
     *  left right - needs one left and one right rotation
     *  right left - needs one right and one left rotation
     *  right right - needs one left rotation
     */
    private TreeNode<T> insert(TreeNode<T> root, T key) {
        /* 1.  Perform the normal BST insertion */
        if(root == null)
            return new TreeNode<>(key);

        int value = key.compareTo(root.data);
        if (value == -1) //data is smaller than root
            root.left = insert(root.left, key);
        else if(value == 1) //data is bigger than root
            root.right = insert(root.right, key);
        else
            return root; // Duplicate keys not allowed

        /* 2. Get the balance factor of this node to check whether this node became unbalanced
              AVL Tree allows balancing factor of -1, 0, 1 */
        int balancingFactor = height(root.left) - height(root.right);
        //LEFT-LEFT situation - rotate right at root
        if(balancingFactor > 1 && key.compareTo(root.left.data) == -1 ) {
            root = rotateRight(root);
        }
        //LEFT-RIGHT situation - rotate left the child and rotate right the root
        else if (balancingFactor > 1 && key.compareTo(root.left.data) == 1 ) {
            root.left = rotateLeft(root.left);
            root = rotateRight(root);
        }
        //RIGHT-RIGHT situation - rotate left at root
        else if (balancingFactor < -1 && key.compareTo(root.right.data) == 1 ) {
            root = rotateLeft(root);
        }
        //RIGHT-LEFT situation - rotate right the child and rotate left the root
        else if (balancingFactor < -1 && key.compareTo(root.right.data) == -1 ) {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        } else {
            /* 3. Update heightWithDiameter of this node */
            root.height = calculateHeight(root);
        }
        return root;
    }

    private void delete(T key) {
        delete(rootNode, key);
    }

    private TreeNode<T> delete(TreeNode<T> root, T key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if(root == null) return null;
        int compareValue = key.compareTo(root.data);
        if(compareValue == -1) root.left = delete(root.left, key);
        else if(compareValue == 1) root.right = delete(root.right, key);
        else {
            //Node is found
            if (root.left == null || root.right == null) {
                //Case1: Leaf node, just return null
                if (root.left == null && root.right == null) {
                    root = null;
                }
                //Case 2: Node has either 1 left or 1 right child - return the child node
                else if (root.left != null) {
                    TreeNode<T> child = root.left;
                    root.left = null; //disconnect node and its child
                    root = child;
                } else if (root.right != null) {
                    TreeNode<T> child = root.right;
                    root.right = null; //disconnect node and its child
                    root = child;
                }
            }
            //Case 3: Node has both right and left children
            else {
                //1. Find the inorder successor of this node - min value in the right tree
                TreeNode<T> successor = root.right;
                while (successor.left!=null) successor=successor.left;
                //2. Copy the inorder successor's data to this node
                root.data = successor.data;
                //3. Delete the inorder successor - this is a leaf node
                root.right =  delete(root.right, successor.data);
            }
        }

        //If root has become null after deletion
        if(root == null) return root;

        // STEP 2: GET THE BALANCE FACTOR OF THIS NODE (to check whether this node became unbalanced)
        int balancingFactor = height(root.left) - height(root.right);
        if(balancingFactor > 1) {
            //In left subtree
            if (height(root.left.left) >= height(root.left.right))
                //LEFT-LEFT situation - rotate right at root
                root = rotateRight(root);
            else {
                //LEFT-RIGHT situation - rotate left the child and rotate right the root
                root.left = rotateLeft(root.left);
                root = rotateRight(root);
            }
        } else if (balancingFactor < -1) {
            //In right subtree
            if(height(root.right.right) >= height(root.right.left)) {
                //RIGHT-RIGHT situation - rotate left at root
                root = rotateLeft(root);
            } else {
                //RIGHT-LEFT situation - rotate right the child and rotate left the root
                root.right=rotateRight(root.right);
                root = rotateLeft(root);
            }
        } else {
            // STEP 3: UPDATE HEIGHT OF THE CURRENT NODE
            root.height = 1 + Math.max(height(root.left), height(root.right));
        }

        return root;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> root) {
        System.out.println("Rotating left at " + root.data);
        TreeNode<T> newRoot = root.right;
        //Perform rotation
        root.right = newRoot.left;
        newRoot.left = root;
        //Update heights
        root.height = calculateHeight(root);
        newRoot.height = calculateHeight(newRoot);
        return newRoot;
    }

    private TreeNode<T> rotateRight(TreeNode<T> root) {
        System.out.println("Rotating right at " + root.data);
        TreeNode<T> newRoot = root.left;
        //Perform rotation
        root.left = newRoot.right;
        newRoot.right = root;
        //Update heights
        root.height = calculateHeight(root);
        newRoot.height = calculateHeight(newRoot);
        return newRoot;
    }

    private int calculateHeight(TreeNode<T> root) {
        //if the root is empty or leaf node, its heightWithDiameter is 0
        if(root == null || isLeaf(root)) return 0;
        return 1 + Math.max(height(root.left),height(root.right));
    }

    private int height(TreeNode<T> root) {
        if(root == null)
            return 0;
        return root.height;
    }

    private boolean isLeaf(TreeNode<T> root) {
        return (root.left == null && root.right == null);
    }
}
