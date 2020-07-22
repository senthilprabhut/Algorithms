package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Difficulty Level: EASY (Premium)
 *
 * Problem:
 *  You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
 *  + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
 *  longer make a move and therefore the other person will be the winner.
 *  Write a function to compute all possible states of the string after one valid move.
 *  For example, given s = "++++", after one move, it may become one of the following states:
 *  ["--++","+--+", "++--"]
 *  If there is no valid move, return an empty list [].
 *
 * Links:
 *  https://leetcode.com/problems/flip-game
 *  https://www.programcreek.com/2014/04/leetcode-flip-game-java/
 */
public class q56_FlipGame1 {
    public static void main(String[] args) {
        q56_FlipGame1 fg1 = new q56_FlipGame1();
        System.out.println(fg1.generatePossibleNextMoves("++++"));
    }

    /**
     * Approach:
     *  Just check whether two adjacent characters are the same and they are equal to ‘+’.
     *
     * Links:
     *  https://www.programcreek.com/2014/04/leetcode-flip-game-java/
     *
     * Explanation:
     *  http://www.bo-song.com/leetcode-flip-game/
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();

        if(s==null || s.length() == 0) {
            return result;
        }

        char[] chars = s.toCharArray();
        for(int i=1; i<chars.length; i++) {
            if (chars[i] == chars[i-1] && chars[i] == '+') {
                chars[i] = chars[i-1] = '-';
                result.add(new String(chars));
                chars[i] = chars[i-1] = '+'; //Reset the modified values
            }
        }
        return result;
    }


}
