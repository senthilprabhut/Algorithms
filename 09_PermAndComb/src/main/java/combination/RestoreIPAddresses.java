package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 *  Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *  For example:
 *  Given "25525511135",
 *  return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 *
 * Links:
 *  https://leetcode.com/problems/restore-ip-addresses
 *  http://www.programcreek.com/2014/06/leetcode-restore-ip-addresses-java/
 *  Solution 1 - https://discuss.leetcode.com/topic/4742/very-simple-dfs-solution
 * Complexity:
 *
 *
 */
public class RestoreIPAddresses {
    public static void main(String[] args) {
        RestoreIPAddresses rip = new RestoreIPAddresses();
        rip.restoreIpAddresses("25525511135");
    }
    public List<String> restoreIpAddresses(String badString) {
        List<String> solutions = new ArrayList<String>();
        restoreIp(badString, solutions, 0, "", 0);
        return solutions;
    }

    private void restoreIp(String badIPString, List<String> solutions, int currentIndex, String restored, int count) {
        if (count > 4) return; //IP cannot have more than 4 3 digit numbers
        if (count == 4 && currentIndex == badIPString.length()) solutions.add(restored);

        for (int i=1; i<4; i++) {
            if (currentIndex+i > badIPString.length()) break;
            String s = badIPString.substring(currentIndex,currentIndex+i);
            if ((s.startsWith("0") && s.length()>1) || (i==3 && Integer.parseInt(s) >= 256)) continue;
            restoreIp(badIPString, solutions, currentIndex+i, restored+s+(count==3?"" : "."), count+1);
        }
    }
}
