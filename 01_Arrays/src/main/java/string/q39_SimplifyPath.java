package string;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an absolute path for a file (Unix-style), simplify it.
 *  For example,
 *      path = "/home/", => "/home"
 *      path = "/a/./b/../../c/", => "/c"
 *  Corner Cases:
 *      Did you consider the case where path = "/../"?
 *      In this case, you should return "/".
 *      Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 *      In this case, you should ignore redundant slashes and return "/home/foo".
 *
 * Links:
 *  https://leetcode.com/problems/simplify-path/description/
 *  https://www.programcreek.com/2014/04/leetcode-simplify-path-java/
 */
public class q39_SimplifyPath {
    public static void main(String[] args) {
        q39_SimplifyPath sp = new q39_SimplifyPath();
        System.out.println(sp.simplifyPath("/home/")); // /home
        System.out.println(sp.simplifyPath("/a/./b/../../c/")); // /c
        System.out.println(sp.simplifyPath("/../")); //  /
        System.out.println(sp.simplifyPath("/home//foo/"));  // /home/foo
    }

    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));

        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (! skip.contains(dir)) stack.push(dir);
        }

        StringBuilder sb = new StringBuilder();
        while (stack.peekLast() != null) {
            sb.append("/").append(stack.pollLast());
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}
