package t00_binarytree.pcreekproblems;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import utils.TreeUtils;

/**
 * Problem:
 *  Given a complete binary tree, count the number of nodes.
 *
 * Links:
 *  https://leetcode.com/problems/count-complete-tree-nodes/description/
 *  https://www.programcreek.com/2014/06/leetcode-count-complete-tree-nodes-java/
 *  Check discussion abt master theorem - https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2/5
 */
public class q23_CountCompleteTreeNodes {

    public static void main(String[] args) {
        /* Constructed binary tree is
                 1
               /  \
              2    3
             / \
            4  5
         */
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6};
        BinaryTree<Integer> tree = new BinaryTree<>();
        TreeNode<Integer> root = tree.buildTree(data);
        TreeUtils.printTree(root);

        q23_CountCompleteTreeNodes cctn = new q23_CountCompleteTreeNodes();
        System.out.println("Total nodes is " + cctn.countNodes(root, cctn.height(root)));
        System.out.println("Total nodes is " + cctn.countNodes2(root));
    }

    /**
     * Approach:
     *  The height of a tree can be found by just going left.
     *  Check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.
     *      If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1.
     *      So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
     *      Put this in formula for no of nodes = 2^h+1 - 1 = 2^(h-1+1) - 1 + 1(root node) = 2^h + CountNodes(rt subtree)
     *
     *      If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2.
     *      So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
     *      Put this in formula for no of nodes = 2^h+1 - 1 = 2^(h-2+1) -1 + 1(root node) = 2^h-1 + CountNodes(left subtree)
     *
     * Links:
     *  https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2
     *
     * Complexity:
     *  Time Complexity - Since I halve the tree in every recursive step, I have O(log(n)) steps.
     *      Finding a height at each step costs O(log(n)). So overall O( (log n)^2).
     *  Space Complexity is O(log n) for the recursive stack
     */
    public int countNodes(TreeNode<Integer> rootNode, int height) {
        return (height < 0) ? 0 :
                height(rootNode.right) == height-1   //left & rt subtree have same ht
                        ? (1<<height) + countNodes(rootNode.right, height-1)
                        : (1<< height-1) + countNodes(rootNode.left, height-1);
    }

    //In Complete Binary Tree, the leftmost node is the indicator of start of a level
    public int height(TreeNode<Integer> node) {
        return (node == null) ? -1 : 1 + height(node.left);
    }

    /**
     * Approach:
     *  Iterative version
     *
     * Links:
     *  https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2
     *
     * Complexity:
     *  Time Complexity is O(h²) = O(log n)² - this has log n steps and height is calcualted each time which is log n
     *  Space Complexity is O(1)
     */
    public int countNodes2(TreeNode<Integer> rootNode) {
        int height = height(rootNode);

        int count = 0;
        while (rootNode != null) {
            if(height(rootNode.right) == height - 1) { //Lt subtree & rt subtreee are of same ht
                count += (1 << height);
                rootNode = rootNode.right; //count nodes in right subtree
            }
            else {
                //Lt subtree has more nodes. so calculate the rt subtree nodes and count nodes in left subtree
                count += (1 << height-1);
                rootNode = rootNode.left;
            }
            height--;
        }
        return count;
    }

    /**
     * Approach:
     *  Another Iterative version - The logic is basically this  1 + countNodes(root.left) + countNodes(root.right)
     *  That would be O(n). But... the actual solution has a gigantic optimization. It first walks all the way left and
     *  right to determine the height and whether it's a full tree, meaning the last row is full.
     *  If so, then the answer is just 2^height-1. And since always at least one of the two recursive calls is such a full tree,
     *  at least one of the two calls immediately stops.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2
     *
     * Complexity:
     *  Time Complexity is O(h²) = O(log n)² - this has log n steps and height is calcualted each time which is log n
     *  Space Complexity is O(1)
     */
    public int countNodes3(TreeNode<Integer> rootNode) {
        if(rootNode == null) return 0;

        TreeNode<Integer> left = rootNode, right = rootNode;
        int height = -1;

        //Check if left and right subtrees are of same height - O(log n)
        //If left subtree is bigger, right will be null but left will be NOT null
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }

        if(left == null) //both subtrees are of same height
            return (1<< height+1) - 1; //formula 2^h+1 - 1

        return 1 + countNodes3(rootNode.left) + countNodes3(rootNode.right);
    }

    /**
     * Approach:
     *  Firstly, we need to find the height of the binary tree and count the nodes above the last level.
     *  Then we should find a way to count the nodes on the last level.
     *  Here I used a kind of binary search. We define the "midNode" of the last level as
     *  a node following the path "root->left->right->right->...->last level".
     *      If midNode is null, then it means we should count the nodes on the last level in the left subtree.
     *      If midNode is not null, then we add half of the last level nodes to our result and then count the nodes on the last level in the right subtree.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/31392/my-java-solution-with-explanation-which-beats-99
     *
     * Complexity:
     *  Time Complexity is
     *  Space Complexity is O(1)
     */
    public int countNodes4(TreeNode<Integer> rootNode) {
        if(rootNode == null) return 0;
        if(rootNode.left == null) return 1; //only root node is present

        //Calculate height and count nodes for till height h-1
        TreeNode<Integer> current = rootNode;
        int height=0, nodeCount=0; //root has height 0
        while(current.left != null) { //not leaf node
            nodeCount += (1<<height); //node count at this height
            current = current.left;
            height++;
        }

        //Once we reach leaf node, we have node count till the prev level
        return nodeCount + countLastLevel(rootNode, height);
    }

    private int countLastLevel(TreeNode<Integer> rootNode, int height) {
        if(height==1)
            if (rootNode.right!=null) return 2;
            else if (rootNode.left!=null) return 1;
            else return 0;

        //Go to the middle node
        TreeNode<Integer> midNode = rootNode.left;
        int currentHt = 1;
        while (currentHt < height) {
            midNode = midNode.right;
            currentHt++;
        }
        //If left subtree is in-complete
        if(midNode == null)
            return countLastLevel(rootNode.left, height-1);

        //If left subtree is complete, no of leaf nodes is same as the no of nodes in h-1 level
        //Add the leaf node count to the right subtree last level
        return (1<<height-1) + countLastLevel(rootNode.right, height-1);
    }
}
