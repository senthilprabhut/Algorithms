package string;

/**
 * Difficulty Level: MEDIUM (LOCKED)
 * <p>
 * Problem:
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
 * + and -, you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to determine if the starting player can guarantee a win.
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up: Derive your algorithm’s runtime complexity.
 * <p>
 * Links:
 * https://leetcode.com/problems/flip-game-ii
 * https://www.programcreek.com/2014/05/leetcode-flip-game-ii-java/
 */
public class q56_FlipGame2 {
    public static void main(String[] args) {
        q56_FlipGame2 fg1 = new q56_FlipGame2();
        System.out.println(fg1.canWin("++++"));
        System.out.println(fg1.canWin("++-++"));
    }

    /**
     * Approach:
     *  Just check whether two adjacent characters are the same and they are equal to ‘+’.
     *
     * Links:
     *  https://www.programcreek.com/2014/05/leetcode-flip-game-ii-java/
     *
     * Explanation:
     *  http://www.bo-song.com/leetcode-flip-game/
     *  http://www.cnblogs.com/jcliBlogger/p/4886741.html
     *
     * Complexity:
     *  Time is O(nⁿ). Roughly, the time is n*n*...n, which is O(n^n). The reason is each recursion takes O(n) and there are totally n recursions.
     */
    public boolean canWin(String s) {

        if (s == null || s.length() == 0) {
            return false;
        }
        return canWinHelper(s.toCharArray());
    }

    public boolean canWinHelper(char[] chars) {
        for(int i=1; i<chars.length; i++) {
            if (chars[i] == chars[i-1] && chars[i] == '+') {
                chars[i] = chars[i-1] = '-';
                boolean win = canWinHelper(chars);
                chars[i] = chars[i-1] = '+'; //Reset the modified values
                if (! win) { //if the other player cannot win
                    return true; //can win
                }
            }
        }
        return false; //cannot win
    }

    /**
     * Approach2: Dynamic programming and Game Theory
     *  A more sophisticated solution using game theory (it reduces the running time to 0 seconds!)
     *
     * Links:
     *  https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms
     *
     * Complexity:
     *
     */

}
