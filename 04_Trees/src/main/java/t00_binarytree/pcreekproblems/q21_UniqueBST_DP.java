package t00_binarytree.pcreekproblems;

/**
 * Problem:
 *  Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *  For example, Given n = 3, there are a total of 5 unique BST's.
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *   3      2     1      1   3      2
 *  /      /       \                 \
 *  2     1         2                 3
 *
 * Links:
 *  https://www.programcreek.com/2014/05/leetcode-unique-binary-search-trees-java/
 */
public class q21_UniqueBST_DP {
    public static void main(String[] args) {
        q21_UniqueBST_DP ub = new q21_UniqueBST_DP();
        System.out.printf("No of unique bst with %d values is %d%n", 2, ub.numBSTrees(2));
        System.out.printf("No of unique bst with %d values is %d%n", 3, ub.numBSTrees(3));
        System.out.printf("No of unique bst with %d values is %d%n", 4, ub.numBSTrees(4));
    }

    /**
     * Approach:
     *  Let count[i] be the number of unique binary search trees for i.
     *  The number of trees are determined by the number of subtrees which have different root node.
     *  Use dynamic programming to solve the problem.
     *      i=0, count[0]=1 //empty tree
     *      i=1, count[1]=1 //one root node
     *      i=2, count[2]=count[0]*count[1] // 0 LeftNode * 1 RightNode, 1 Root Node
     *                  + count[1]*count[0] // 1 LeftNode * 0 RightNode, 1 Root Node
     *      i=3, count[3]=count[0]*count[2] // 0 LeftNode * 2 RightNode, 1 Root Node
     *                  + count[1]*count[1] // 1 LeftNode * 1 RightNode, 1 Root Node
     *                  + count[2]*count[0] // 0 LeftNode * 2 RightNode, 1 Root Node
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public int numBSTrees(int total) {
        int[] countDP = new int[total + 1];

        //Base Case
        countDP[0] = 1;
        countDP[1] = 1;

        //Start calculating the product till "total"
        for(int currTotal=2; currTotal<=total; currTotal++) {
            int leftNodes=0, rightNodes = currTotal-1-leftNodes;
            while(leftNodes<=currTotal-1) {
                countDP[currTotal] = countDP[currTotal] + countDP[leftNodes] * countDP[rightNodes];
                leftNodes++;
                rightNodes--;
            }
        }
        return countDP[total];
    }
}
