package t00_binarytree.pcreekproblems;

import java.util.LinkedList;
import java.util.List;

/**
 * Problem:
 *  Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
 *  Find an algorithm without reconstructing the tree.
 *
 * Links:
 *  https://www.programcreek.com/2015/01/leetcode-verify-preorder-serialization-of-a-binary-tree-java/
 */
public class q19_VerifyPreorderSerialization {
    public static void main(String[] args) {
        q19_VerifyPreorderSerialization vpos = new q19_VerifyPreorderSerialization();
        System.out.println(vpos.isValid("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    /**
     * Approach:
     *  In a pre-order traversal, the right-most 3 values will have the root and its two leaves
     *  If we work backwards, you should always have the root and its two leaves together
     *
     * Complexity:
     *  Time Complexity is O(n)
     *  Space Complexity is O(log n) //Stack holds max of height of tree values
     */
    public boolean isValid(String preorder) {
        List<String> list = new LinkedList<>();
        String[] chars = preorder.split(",");

        //Needs min of 3 chars to work
        for(int i=chars.length-1; i>=0; i--) {
            list.add(chars[i]);
            while(list.size() >= 3 &&
                    list.get(list.size()-3).equals("#") &&
                    list.get(list.size()-2).equals("#") &&
                    ! list.get(list.size() -1).equals("#")) {
                    list.remove(list.size()-1);
                    list.remove(list.size()-1);
                    list.remove(list.size()-1);

                    list.add("#");
            }
        }

        if(list.size()==1 && list.get(0).equals("#")) return true;

        return false;
    }
}
