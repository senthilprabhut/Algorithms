package utils;

import datastructure.TreeNodeInterface;

public class TreeUtils {
    public static void printTree(TreeNodeInterface root) {
        if (root == null) {
            System.out.println("No tree exists to print");
            return;
        }
        printTree(root.right(), true, "");
        System.out.println(root.data());
        printTree(root.left(), false, "");
    }

    private static void printTree(TreeNodeInterface root, boolean isRight, String indent) {
        if(root == null) return;

        printTree(root.right(), true, indent + (isRight ? "        " : " |      "));
        System.out.print(indent);
        if(isRight)
            System.out.print(" /----- ");
        else
            System.out.print(" \\----- ");
        System.out.println(root.data());
        printTree(root.left(), false, indent + (isRight ? " |      " : "        "));
    }
}
